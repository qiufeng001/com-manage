import {Notification} from "element-ui";
import { getToken } from '@/utils/auth';

let count = 0;// 记录计数
let lockReconnect = false;//避免ws重复连接
const text1 = "您有新的订单，请及时处理！";
let ws = null;// 判断当前浏览器是否支持WebSocket
const server = `ws://localhost:81/ws/server`;// WebSocket服务地址
// 监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
window.onbeforeunload = function () {
  ws.close();
};
// 重连
let reconnect = (server) => {
  if (count >= 5) return console.log("超出重连次数！");
  if (lockReconnect) return false;
  lockReconnect = true;
  setTimeout(function () {     //没连接上会一直重连，设置延迟避免请求过多
    createWebSocket(server);
    lockReconnect = false;
    count++;
  }, 5000);
};
// 创建实例websocket
let createWebSocket = (server) => {
  var headers = {};
  headers['Authorization'] = 'Bearer ' + getToken();
  try {
    if('WebSocket' in window){
      ws = new WebSocket(server);
    }else if('MozWebSocket' in window){
      ws = new MozWebSocket(server);
    } else  {
      Notification({message:"当前浏览器不支持websocket协议,建议使用现代浏览器",type:"success"})
    }
    // 连接建立时触发
    initEventHandle();
  } catch (e) {
    console.log("ERR-----------捕获异常", e)
  }
};
// 初始化事件函数
let initEventHandle = () => {
  // 连接报错
  ws.onerror = function (evt, e) {

    console.log("链接失败。。。，" + e);
    reconnect(server)
  };
  // 连接关闭
  ws.onclose = function (evt) {
    console.log(evt);
    console.log("连接关闭---" + new Date().toLocaleTimeString());
    reconnect(server)
  };
  // 链接成功
  ws.onopen = function (evt) {
    console.log("链接成功。。。")
    heartCheck.reset().start();// 心跳检测重置
    count = 0;// 重置重连次数
  };
  // 接受数据
  ws.onmessage = function (evt) {// 如果获取到消息，心跳检测重置
    heartCheck.reset().start();// 拿到任何消息都说明当前连接是正常的
    let eventData  = undefined;
    try {
      eventData = JSON.parse(evt.data);
      handMsg(eventData)
    }catch (e) {
      console.log("捕获异常: 当前返回的数据不能解析;");
      console.log("内容：" + evt.data)
    }
  };
};
// 心跳检测
const heartCheck = {
  timeout: 25000,        // 设置心跳时间
  timeoutObj: null,
  serverTimeoutObj: null,
  reset: function () {
    clearTimeout(this.timeoutObj);
    clearTimeout(this.serverTimeoutObj);
    return this;
  },
  start: function () {
    const self = this;
    this.timeoutObj = setTimeout(function () {
      // 这里发送一个心跳，后端收到后，返回一个心跳消息，onmessage拿到返回的心跳就说明连接正常
      ws.send("ping--------------Ping");
      self.serverTimeoutObj = setTimeout(function () {
        // 如果超过一定时间还没重置，说明后端主动断开了
        // 如果onclose会执行reconnect，我们执行ws.close()就行了.如果直接执行reconnect 会触发onclose导致重连两次
        ws.close();
      },self.timeout)
    },this.timeout)
  }
};
// 处理消息
let handMsg = (eventData) => {
  if (ws.readyState === WebSocket.OPEN) {
    if (eventData.code === "2000" && eventData.type === "order_notice") {
      Notification({message: text1, type:"warning"})
    }
  }
};
let startWebSocket = () => {
  createWebSocket(server)
};
export default { startWebSocket }
