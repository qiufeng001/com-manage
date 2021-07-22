<template>
  <div class="app-container home">
    <el-row :gutter="20">
      <el-col :sm="24" :lg="12">
        <div id="pieChart" :style="{width: '100%', height: '300px'}"></div>
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
              <i class="el-icon-user-solid"></i>
            </p>
            <p>
              <i class="el-icon-chat-dot-round"></i> 微信：
              <a href="javascript:;"></a>
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
import * as $echarts from 'echarts';
import { reportData } from "@/api/home";


export default {
  name: "index",
  data() {
    return {
      orderReport: [],
      pieData: []
    };
  },
  mounted(){
    this.pieReportData();
  },
  methods: {
    goTarget(href) {
      window.open(href, "_blank");
    },
    // 获取订单报表
    pieReportData() {
      const params = {"reportType": "total", "timeType": "all"}
      reportData(params).then(res => {
        var orderReport = res.orderReport;
        if(orderReport == null || orderReport == undefined) {
          return;
        }
        var data = new Array();
        for(var i = 0;i < orderReport.length;i++) {
            var dataItem = {};
            dataItem.value = "实收" + orderReport[i].paidInAmount + "元, 实际总额" + orderReport[i].totalAmount + "元, 优惠" + orderReport[i].discountAmount + "元";
            dataItem.name = orderReport[i].shopName;
            data.push(dataItem);
        }
        this.pieReport(data);
      });
    },
    // 饼图
    pieReport(data){
      console.log(data)
      // 基于准备好的dom，初始化echarts实例
      let myChart = this.$echarts.init(document.getElementById('pieChart'))
      // 绘制图表
      var option = {
        title: {
            text: '店铺营收情况',
            subtext: '店铺数据',
            left: 'center'
        },
        tooltip: {
            trigger: 'item'
        },
        legend: {
            orient: 'vertical',
            left: 'left',
        },
        series: [
            {
                name: '店铺数据',
                type: 'pie',
                radius: '50%',
                data: data,
                emphasis: {
                    itemStyle: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
      };
      myChart.setOption(option);
    }
  }
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
