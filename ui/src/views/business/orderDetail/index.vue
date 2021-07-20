<template>
  <div class="app-container">
    <!-- 添加或修改订单对话框 -->
    <div slot="footer" class="form-header" style="text-align: left;">
      <el-button type="primary" @click="submitForm" style="margin-left: 300px;">确 定</el-button>
      <el-button @click="reset()" type="danger">清空</el-button>
    </div>

    <el-row v-for="(item, index) in order.details">
        <el-form
            :inline="true"
            :model="order.details[index]"
            :rules="order"
            ref="details"
            label-width="100px"
            class="demo-form-inline"
            size="small"
        >
            <el-form-item label="商品" prop="goodsId">
                <el-select
                    v-model="order.details[index].goodsId"
                    placeholder="请选择商品"
                >
                    <el-option
                        v-for="item in goods"
                        :key="item.id"
                        :label="item.name"
                        :value="item.id"
                    ></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="数量(份数)" prop="goods">
                <el-input v-model="order.details[index].goodsNum"></el-input>
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
        </el-form>
    </el-row>

  </div>
</template>
<script>
import { getInfo } from "@/api/business/orderDetail";


export default {
  name: "OrderDetail",
  data() {
      return {
          goods: [],
          discounts: [],
          order: { details: [{}] },
      };
  },
  mounted() {
  　　this.getInfo();
  },
  methods: {
    getInfo() {
      getInfo().then(response => {
        this.discounts = response.data.discounts;
        this.goods = response.data.goods;

      });
    },
    // 表单重置
    reset() {
     this.order = { details: [{}] };
    },

    addItem() {
        this.order.details.push({
            goods: this.goods
        });
    },
    // 删除播放时间
    del(index) {
        this.order.details.splice(index, 1);
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateOrder(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addOrder(this.form).then(response => {
              this.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    }
  }
}
</script>
