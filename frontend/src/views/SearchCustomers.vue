<template>
  <v-container>
    <v-row>
      <v-col>
        <h4>Find by Age</h4>
      </v-col>
    </v-row>
    <v-row>
      <v-col v-if="!submitted">
        <search-form @searchCustomer="searchCustomer" />
      </v-col>
      <v-col v-else>
        <div v-if="customers.length" align="center">
          <customers-table ref="customersTable" :customers="customers" />
        </div>
        <div v-else>
          <v-alert type="error">Customers Not Found</v-alert>
          <search-form />
        </div>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import http from "../http-common";
import searchForm from "../components/SearchForm";
import customersTable from "../components/CustomersTable";

export default {
  name: "search-customer",
  components: { searchForm, customersTable },
  data() {
    return {
      customers: [],
      submitted: false
    };
  },
  methods: {
    // 子コンポーネントからのイベント発火で動作 ※args => $emitで渡した子コンポーネントからの引数
    async searchCustomer(args) {
      try {
        const res = await http.get("/customers/age/" + args.age);
        this.customers = res.data;
        this.submitted = true;
        // 子コンポーネントのメソッドを発火できない
        // if (this.customers.length) {
        //   this.$refs.customersTable.getHeadersList(this.customers); // 子コンポーネントに値が渡される前にメソッドが実行されるため引数にcustomersオブジェクトを渡す
        // }
      } catch (error) {
        console.log(error);
      }
    }
  }
};
</script>