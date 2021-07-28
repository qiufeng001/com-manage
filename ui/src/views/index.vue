<template>
  <div class="app-container home">
    <el-row :gutter="20">
      <el-col :sm="24" :lg="8">
        <div id="netProfitChart" :style="{width: '100%', height: '300px'}"></div>
      </el-col>
      <el-col :sm="24" :lg="16">
        <div id="salesChart" :style="{width: '100%', height: '300px'}"></div>
      </el-col>
    </el-row>
    <el-divider />
    <el-row :gutter="20">
      <el-col :xs="24" :sm="24" :md="12" :lg="14">
        <el-card class="update-log">
          <div slot="header" class="clearfix">
            <span>时段销售情况</span>
          </div>
          <div class="body">
            <el-form :inline="true" label-width="68px" ref="timeReportQueryForm" :model="timeReportParams">
              <el-form-item label="门店">
                <el-select v-model="timeReportParams.shopId" style="width: 170px;" @change="handleShopChange">
                  <el-option v-for="item in shops" :key="item.id" :label="item.shopName" :value="item.id"></el-option>
                </el-select>
              </el-form-item>
              <el-form-item>
                <el-button icon="el-icon-refresh" size="mini" @click="resetShopReportQuery">重置</el-button>
              </el-form-item>
            </el-form>
            <div id="timeChart" :style="{width: '100%', height: '400px'}"></div>
          </div>
        </el-card>
      </el-col>
      <el-col :xs="24" :sm="24" :md="12" :lg="10">
        <el-card class="update-log">
          <div slot="header" class="clearfix">
            <span>待处理订单</span>
          </div>
          <div class="body">

          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>
<script>
  import {
    netProfitReport,
    salesReport,
    timeReportQuery,
    searchData
  } from "@/api/report";


  export default {
    name: "index",
    data() {
      return {
        timeReportParams: {
          shopId: null
        },
        shops: [],
        timeReportDtos: []
      };
    },
    mounted() {
      this.searchData();
      this.initNetProfitChart();
      this.initSalesChart();
      this.initTimeChart();
      this.startWebSocket();
    },
    methods: {
      goTarget(href) {
        window.open(href, "_blank");
      },
      searchData() {
        var params = {};
        searchData(params).then(res => {
          this.shops = res.shops;
        });
      },
      resetShopReportQuery() {
        this.timeReportParams.shopId = null;
        this.handleShopChange();
      },
      // 获取订单报表
      initNetProfitChart() {
        const params = {
          "reportType": "total",
          "timeType": "all"
        }
        netProfitReport(params).then(res => {
          var orderReports = res.orderReports;
          if (orderReports == null || orderReports == undefined) {
            return;
          }
          this.netProfitReportChart(orderReports);
        });
      },
      // 利润报表
      initSalesChart() {
        const params = {
          "reportType": "total",
          "timeType": "all"
        }
        salesReport(params).then(res => {
          this.salesReportChart(res.names, res.saleReports, res.profitReports);
        });

      },
      // 时间报表
      initTimeChart() {
        timeReportQuery(this.timeReportParams).then(res => {
          this.timeReportDtos = res.timeReportDtos;
          this.timeReportChart(res.names, res.trueAmountReports);
        });
      },
      handleShopChange() {
        this.initTimeChart();
      },
      // 饼图
      netProfitReportChart(result) {
        // 基于准备好的dom，初始化echarts实例
        let netProfitChart = this.$echarts.init(document.getElementById('netProfitChart'));
        // 绘制图表
        var option = {
          title: {
            text: '净利润（门店）',
            subtext: '净利润（元）',
            left: 'center'
          },
          tooltip: {
            trigger: 'item'
          },
          legend: {
            orient: 'vertical',
            left: 'left',
          },
          series: [{
            name: '净利',
            type: 'pie',
            radius: '50%',
            data: result,
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
            }
          }]
        };
        netProfitChart.setOption(option, true);
      },
      salesReportChart(names, sales, profit) {
        var salesChart = this.$echarts.init(document.getElementById('salesChart'));
        var option = {
          grid: {
            height: 190
          },
          tooltip: {
            trigger: 'axis',
            axisPointer: { // 坐标轴指示器，坐标轴触发有效
              type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
            }
          },
          yAxis: {
            type: 'value'
          },
          xAxis: {
            type: 'category',
            data: names,
            inverse: true,
            animationDuration: 300,
            animationDurationUpdate: 300,
            alignWithLabel: true,
            width: '20%',
            max: (names.length - 1),
            splitLine: {
              lineStyle: {
                width: '30%',
                type: 'solid'
              },
              show: false
            }
          },
          series: [{
            realtimeSort: true,
            name: '销量',
            type: 'bar',
            data: sales,
            barWidth: '10%',
            label: {
              show: true,
              position: 'top',
              valueAnimation: true
            }
          }, {
            realtimeSort: true,
            name: '净利润(元)',
            type: 'bar',
            data: profit,
            barWidth: '10%',
            label: {
              show: true,
              position: 'right',
              valueAnimation: true
            }
          }],
          legend: {
            show: true
          },
          animationDuration: 0
        };
        salesChart.setOption(option);
      },
      timeReportChart(names, profit) {
        var timeChart = this.$echarts.init(document.getElementById('timeChart'));
        var option = {
          grid: {
            height: 300
          },
          tooltip: {
            trigger: 'axis',
            axisPointer: { // 坐标轴指示器，坐标轴触发有效
              type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
            }
          },
          yAxis: {
            type: 'value'
          },
          xAxis: {
            type: 'category',
            data: ["0-2", "2-4", "4-6", "6-8", "8-10", "10-12", "12-14", "14-16", "16-18", "18-20", "20-22", "22-00"],
            inverse: true,
            animationDuration: 300,
            animationDurationUpdate: 300,
            alignWithLabel: true,
            width: '20%',
            max: 11,
            axisLine: {
              lineStyle: {
                width: '30%',
                type: 'solid',
                color: "green"
              },
              show: true
            }
          },
          series: [{
            realtimeSort: true,
            name: '销售额(元)',
            type: 'bar',
            data: profit,
            barWidth: '10%',
            label: {
              show: true,
              position: 'top',
              valueAnimation: true
            }
          }],
          legend: {
            show: true
          },
          animationDuration: 0
        };
        timeChart.setOption(option);
      }
    }
  };
</script>

<style scoped lang="scss">
  .home {
    height: 40%;

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

    font-family: "open sans",
    "Helvetica Neue",
    Helvetica,
    Arial,
    sans-serif;
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
