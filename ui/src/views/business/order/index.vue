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

    <el-table v-loading="loading" :data="orderList"
          @selection-change="handleSelectionChange"
          @row-dblclick="dbSelected">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="订单编号" align="center" prop="orderNo" />
      <el-table-column label="门店" align="center" prop="shopName" />
      <el-table-column label="订单商品总数" align="center" prop="total" />
      <el-table-column label="实际收额(元)" align="center" prop="paidInAmount" />
      <el-table-column label="订单总额(元)" align="center" prop="totalAmount" />
      <el-table-column label="折扣方案" align="center" prop="discountName" />
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
    <el-dialog :title="title" :visible.sync="open" width="70%" append-to-body>
        <table class="orderClass">
           <tr>
             <td width="10%">门店:</td>
             <td width="10%">{{this.orderVo.shopName}}</td>
             <td width="10%">订单总数:</td>
             <td width="10%">{{this.orderVo.total}}</td>
             <td width="10%">实收：</td>
             <td width="15%">{{this.orderVo.paidInAmount}}元</td>
             <td width="10%">实际金额:</td>
             <td width="15%">{{this.orderVo.totalAmount}}元</td>
           </tr>
           <tr>
             <td>折扣方案</td>
             <td>{{this.orderVo.discountName}}</td>
             <td>收款人</td>
             <td>{{this.orderVo.payee}}</td>
             <td>订单号</td>
             <td colspan="3">{{this.orderVo.orderNo}}</td>
           </tr>
        </table>
        <br />
        <el-table v-loading="loading" :data="orderVo.details" class="details-class">
          <el-table-column label="商品" align="center" prop="goodsName" />
          <el-table-column label="单价" align="center" prop="unitPrice" />
          <el-table-column label="数量(份数)" align="center" prop="goodsNum" />
          <el-table-column label="金额(元)" align="center" prop="amount" />
        </el-table>
        <table class="orderClass">
           <tr>
             <td>
               <span style="width:30px;margin-left: 55px;">商品数量：{{this.orderVo.goodsNum}}</span>
               <span style="width:30px;margin-left: 100px;">实际金额：{{this.orderVo.totalAmount}}元</span>
               <span style="width:30px;margin-left: 100px;color: red;">实收金额：{{this.orderVo.paidInAmount}}元</span>
                <span style="width:30px;margin-left: 100px;">以优惠：{{this.orderVo.preferentialAmouont}}元</span>
             </td>
           </tr>
        </table>
    </el-dialog>
  </div>
</template>

<script>
import { listOrder, getOrder, delOrder, addOrder, updateOrder, exportOrder,getDetails } from "@/api/business/order";

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
      orderVo: {},
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
    dbSelected(row) {
      getDetails(row).then(res => {
          this.open = true;
          this.title = "订单明细查看";
          this.orderVo = res.data;
      });
    },

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
<style>
  .orderClass {
    width: 90%;
  }
  .el-dialog__header {
    text-align: center;
  }
  .details-class {
    max-height: 310px;
    overflow: auto;
  }
</style>
