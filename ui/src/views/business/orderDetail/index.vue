<template>
  <div class="app-container">
    <!-- 添加或修改订单对话框 -->
    <!-- <el-form ref="form" :model="form" :rules="rules" label-width="80px">
      <el-form-item label="订单编号" prop="orderNo">
        <el-input v-model="form.orderNo" placeholder="请输入订单编号" />
      </el-form-item>
      <el-form-item label="门店id" prop="shopId">
        <el-input v-model="form.shopId" placeholder="请输入门店id" />
      </el-form-item>
      <el-form-item label="订单商品总数" prop="total">
        <el-input v-model="form.total" placeholder="请输入订单商品总数" />
      </el-form-item>
      <el-form-item label="订单实际收额" prop="paidInAmount">
        <el-input v-model="form.paidInAmount" placeholder="请输入订单实际收额" />
      </el-form-item>
      <el-form-item label="订单总额" prop="totalAmount">
        <el-input v-model="form.totalAmount" placeholder="请输入订单总额" />
      </el-form-item>
      <el-form-item label="折扣方案" prop="discountId">
        <el-input v-model="form.discountId" placeholder="请输入折扣方案" />
      </el-form-item>
      <el-form-item label="收款人" prop="payee">
        <el-input v-model="form.payee" placeholder="请输入收款人" />
      </el-form-item>
    </el-form> -->
    <div slot="footer" class="dialog-footer">
      <el-button type="primary" @click="submitForm">确 定</el-button>
      <el-button @click="cancel">取 消</el-button>
    </div>
  </div>
</template>
<script type="text/javascript">
export default {
  name: "OrderDetail",
  components: {
  },
methods: {
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        orderNo: null,
        shopId: null,
        total: null,
        paidInAmount: null,
        totalAmount: null,
        discountId: null,
        payee: null,
        createBy: null,
        updateBy: null,
        createTime: null,
        updateTime: null
      };
      this.resetForm("form");
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
