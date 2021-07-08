<template>
  <div class="app-container home">
    <el-row :gutter="20">
      <el-col :sm="24" :lg="12">
        <blockquote class="text-primary" style="font-size: 14px">

            <div class="verify-line">
              <v-chart :options="lineOption" autoresize />
            </div>

        </blockquote>
      </el-col>
      <el-col :sm="24" :lg="12">
        <h4 class="text-danger" style="font-size: 14px">
          Test Case:<br /><br />
         // TODO 这里做报表
        </h4>
      </el-col>
    </el-row>
    <el-divider />
    <el-row :gutter="20">
      <el-col :xs="24" :sm="24" :md="12" :lg="8">
        <el-card class="update-log">
          <div slot="header" class="clearfix">
            <span>联系信息</span>
          </div>
          <div class="body">
            <p>
              <i class="el-icon-user-solid"></i> QQ群：603909590
            </p>
            <p>
              <i class="el-icon-chat-dot-round"></i> 微信：
              <a href="javascript:;">huangluqian12345</a>
            </p>
            <p>
              <i class="el-icon-user-solid"></i> QQ：<a href="javascript:;">1055202292</a>
            </p>
            <!--<img
              src="~@/assets/image/qqgroup.jpg"
              alt="qqgroup"
              width="100%"/>-->
          </div>
        </el-card>
      </el-col>
      <el-col :xs="24" :sm="24" :md="12" :lg="8">
        <el-card class="update-log">
          <div slot="header" class="clearfix">
            <span>更新日志</span>
          </div>
          <el-collapse accordion>
            <el-collapse-item title="v1.0.0 - 2020-12-16">
              <ol>
                <li>工作流分离版正式发布</li>
              </ol>
            </el-collapse-item>
          </el-collapse>
        </el-card>
      </el-col>
      <el-col :xs="24" :sm="24" :md="12" :lg="8">
        <el-card class="update-log">
          <div slot="header" class="clearfix">
            <span>捐赠支持</span>
          </div>
          <div class="body">
            <img
              src="~@/assets/image/pay.png"
              alt="donate"
              width="100%"
            />
            <span style="display: inline-block; height: 30px; line-height: 30px"
              >你可以请作者喝杯咖啡表示鼓励</span
            >
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>

  import 'echarts/lib/chart/line'
  import 'echarts/lib/component/axis'
  import 'echarts/lib/component/tooltip'
export default {
  name: "index",
  props: {
      info: {//这里是传进来的折线图数据
        type: Array,
        default: () => {
          return []
        }
      }
    },
    data() {
      const splitLineStyle = {
        show: true,
        lineStyle: {
          color: ['rgb(241, 241, 241)'],
          width: 1,
          type: 'solid'
        }
      }

      // 坐标轴线样式
      const axisLine = {
        show: true,
        lineStyle: {
          color: 'rgb(241, 241, 241, .5)'
        }
      }

      // 坐标文字样式
      const axisLabel = {
        interval: 0,
        textStyle: {
          color: '#50576A' // 坐标值得具体的颜色
        }
      }
      return {
        lineOption: {
          // 调整边距(上下左右)
          grid: {
            left: '1%',
            right: '1%',
            bottom: '2%',
            containLabel: true
          },
          xAxis: {
            type: 'category',
            boundaryGap: false, // 坐标轴两边留白
            data: [],
            axisLine: axisLine,
            axisLabel: {
              // 坐标轴刻度标签的相关设置
              interval: 0, // 如果设置为 1，表示『隔一个标签显示一个标签』
              textStyle: {
                color: '#50576A' // 坐标值得具体的颜色
              },
              // 坐标轴文字的显示格式
              formatter: function (params) {
                return params + '月'
              }
            }
          },
          yAxis: {
            type: 'value',
            boundaryGap: [0, '30%'],
            axisLine: axisLine,
            axisLabel: axisLabel,
            splitLine: splitLineStyle,
            // 不显示坐标轴刻度
            axisTick: {
              show: false
            }
          },
          // 高亮提示设置
          tooltip: {
            trigger: 'axis',
            axisPointer: {
              lineStyle: {
                color: {
                  type: 'linear',
                  x: 0,
                  y: 0,
                  x2: 0,
                  y2: 1,
                  colorStops: [
                    {
                      offset: 0,
                      color: '#3AD4A7'
                    },
                    {
                      offset: 1,
                      color: '#3AD4A7'
                    }
                  ],
                  global: false
                }
              }
            }
          },
          series: [
            {
              type: 'line',
              smooth: true,
              showSymbol: true, // 显示转折点变大
              symbol: 'circle', // 设定为实心点
              symbolSize: 1, // 设定实心点的大小
              lineStyle: {
                // 数据线的样式
                color: '#4669F5',
                width: 3
              },
              itemStyle: {
                normal: {
                  // 配置转折点的样式
                  color: '#3AD4A7'
                }
              },
              areaStyle: {
                color: {
                  type: 'linear',
                  x: 0,
                  y: 0,
                  x2: 0,
                  y2: 1,
                  colorStops: [
                    {
                      offset: 0,
                      color: 'rgba(70, 105, 245, .8)' // 0% 处的颜色
                    },
                    {
                      offset: 0.5,
                      color: 'rgba(255, 255, 255, 0.5)' // 100% 处的颜色
                    },
                    {
                      offset: 1,
                      color: 'rgba(255, 255, 255, 0.5)' // 100% 处的颜色
                    }
                  ]
                }
              },
              data: []
            }
          ]
        }
      }
    },
    watch: {//深度监听传过来的折线图数据
      info: {
        immediate: true,
        deep: true,
        handler(val) {
          if (val && val.length) {
            let xAxis = []
            let yAxis = []
            val.forEach((item, index) => {
              xAxis.push(item.month)
              yAxis.push(item.total)
            })
            this.lineOption.series[0].data = yAxis
            this.lineOption.xAxis.data = xAxis
          } else {
            const lineLen = 12
            let xAxis = []
            for (let i = 1; i <= lineLen; i++) {
              xAxis.push(i)
            }
            this.lineOption.series[0].data = []
            this.lineOption.xAxis.data = xAxis
          }
        }
      }
    },

  methods: {
    goTarget(href) {
      window.open(href, "_blank");
    },
  },
};
</script>

<style scoped lang="scss">
.home {
  blockquote {
    padding: 10px 20px;
    margin: 0 0 20px;
    font-size: 17.5px;
    border-left: 5px solid #eee;
  }
  hr {
    margin-top: 20px;
    margin-bottom: 20px;
    border: 0;
    border-top: 1px solid #eee;
  }
  .col-item {
    margin-bottom: 20px;
  }

  ul {
    padding: 0;
    margin: 0;
  }

  font-family: "open sans", "Helvetica Neue", Helvetica, Arial, sans-serif;
  font-size: 13px;
  color: #676a6c;
  overflow-x: hidden;

  ul {
    list-style-type: none;
  }

  h4 {
    margin-top: 0px;
  }

  h2 {
    margin-top: 10px;
    font-size: 26px;
    font-weight: 100;
  }

  p {
    margin-top: 10px;

    b {
      font-weight: 700;
    }
  }

  .update-log {
    ol {
      display: block;
      list-style-type: decimal;
      margin-block-start: 1em;
      margin-block-end: 1em;
      margin-inline-start: 0;
      margin-inline-end: 0;
      padding-inline-start: 40px;
    }
  }
}
</style>
