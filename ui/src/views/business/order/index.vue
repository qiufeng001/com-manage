<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="订单编号" prop="orderNo">
        <el-input
          v-model="queryParams.orderNo"
          placeholder="请输入订单编号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="门店" prop="supplierId">
        <el-select v-model="queryParams.shopId">
           <el-option
             v-for="item in shops"
             :key="item.id"
             :label="item.name"
             :value="item.id"
           ></el-option>
         </el-select>
        </el-select>
      </el-form-item>
      <el-form-item label="折扣方案" prop="discountId">
        <el-select v-model="queryParams.discountId">
           <el-option
             v-for="item in discounts"
             :key="item.id"
             :label="item.name"
             :value="item.id"
           ></el-option>
         </el-select>
        </el-select>
      </el-form-item>
      <el-form-item label="收款人" prop="payee">
        <el-input
          v-model="queryParams.payee"
          placeholder="请输入收款人"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="cyan" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['business:order:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['business:order:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['business:order:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['business:order:export']"
        >导出</el-button>
      </el-col>
	  <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="orderList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="订单id" align="center" prop="id" />
      <el-table-column label="订单编号" align="center" prop="orderNo" />
      <el-table-column label="门店id" align="center" prop="shopId" />
      <el-table-column label="订单商品总数" align="center" prop="total" />
      <el-table-column label="订单实际收额" align="center" prop="paidInAmount" />
      <el-table-column label="订单总额" align="center" prop="totalAmount" />
      <el-table-column label="折扣方案" align="center" prop="discountId" />
      <el-table-column label="收款人" align="center" prop="payee" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['business:order:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['business:order:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改订单对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
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
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listOrder, getOrder, delOrder, addOrder, updateOrder, exportOrder } from "@/api/business/order";

export default {
  name: "Order",
  components: {
  },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 订单表格数据
      orderList: [],
      // 门店
      shops: [],
      discounts: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        orderNo: null,
        shopId: null,
        total: null,
        paidInAmount: null,
        totalAmount: null,
        discountId: null,
        payee: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        orderNo: [
          { required: true, message: "订单编号不能为空", trigger: "blur" }
        ],
        shopId: [
          { required: true, message: "门店id不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询订单列表 */
    getList() {
      this.loading = true;
      listOrder(this.queryParams).then(response => {
        this.orderList = response.rows;
        this.total = response.total;
        this.shops = response.fillData.shops;
        this.discounts = response.fillData.discounts;
        this.loading = false;
      });
    },
    /* getShops() {
      listShops().then(response => {
         if(response.code === 200) {
            this.shops = response.data;
         }else{
            this.$alert(response.msg, "查询结果");
         }
      });
    },
    getDiscounts() {
      listDiscounts().then(response => {
         if(response.code === 200) {
            this.discounts = response.data;
         }else{
            this.$alert(response.msg, "查询结果");
         }
      });
    }, */
    // 取消按钮
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
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      /* this.reset();
      this.open = true;
      this.title = "添加订单"; */
      this.$router.push({path: '/business/orderDetail'})
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getOrder(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改订单";
      });
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
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$confirm('是否确认删除订单编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delOrder(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有订单数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportOrder(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    }
  }
};
</script>
