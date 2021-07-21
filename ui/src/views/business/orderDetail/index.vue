<template>
  <div class="app-container">
    <!-- 添加或修改订单对话框 -->
    <div slot="footer" class="form-header" style="text-align: left;">
      <el-button type="primary" @click="submitForm" style="margin-left: 300px;">确 定</el-button>
      <el-button @click="reset()" type="danger">清空</el-button>
    </div>
        <el-form
            :inline="true"
            :model="order"
            :rules="rules"
            ref="orderForm"
            label-width="100px"
            class="demo-form-inline"
            size="small"
        >
          <el-row v-for="(item, index) in order.details">
            <el-form-item label="商品" prop="item">
                <el-select
                    v-model="order.details[index].goodsId"
                    placeholder="请选择商品" style="width: 170px"
                >
                    <el-option
                        v-for="item in goods"
                        :key="item.id"
                        :label="item.name"
                        :value="item.id"
                    ></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="数量(份数)" prop="item">
                <el-input v-model="order.details[index].goodsNum" style="width: 60px"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button
                    v-if="index != 0"
                    type="success"
                    @click="addItem"
                    size="small"
                >添加</el-button>
                <el-button
                    v-if="index!=0"
                    type="danger"
                    @click="del(index)"
                    size="small"
                >删除</el-button>
                <el-button
                    v-if="index == 0"
                    type="success"
                    @click="addItem"
                    size="small"
                >添加</el-button>
            </el-form-item>
          </el-row>
        </el-form>
  </div>
</template>
<script>
import { getInfo, addOrder } from "@/api/business/orderDetail";


export default {
  name: "OrderDetail",
  data() {
      return {
          goods: [],
          discounts: [],
          order: { discountId: "", details: [{}] },
          rules: {
            goodsNum: [
              { required: true, message: "数量必须是数字", trigger: "blur" }
            ]
          }
      };
  },
  created(){
      this.getInfo();
  },
  methods: {
    getInfo() {
      getInfo().then(response => {
        this.discounts = response.data.discounts;
        this.goods = response.data.goods;
        this.order.discountId = this.discounts[0].id;
      });
    },
    // 表单重置
    reset() {
     this.order = { details: [{}] };
    },

    addItem() {
        this.order.details.push({});
    },
    // 删除播放时间
    del(index) {
        this.order.details.splice(index, 1);
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["orderForm"].validate(valid => {
        if (valid) {
          if (this.order.id != null) {
            updateOrder(this.order).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addOrder(this.order).then(response => {
              debugger
              if(response.code == 200) {
                  this.msgSuccess("新增成功");
                  this.reset();
              }else{
                  this.msgError("新增失败，" + response.msg);
              }
            });
          }
        }
      });
    }
  }
}
</script>
