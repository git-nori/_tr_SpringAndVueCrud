<template>
  <v-container>
    <v-row>
      <v-col>
        <h4>Customers List</h4>
      </v-col>
    </v-row>
    <v-row>
      <v-col>
        <customers-table ref="customersTable" :customers="customers" />
      </v-col>
    </v-row>
    <router-view></router-view>
  </v-container>
</template>

<script>
import http from "../http-common";
import customersTable from "../components/CustomersTable";

export default {
  name: "customers-list",
  components: { customersTable },
  data() {
    return {
      customers: []
    };
  },
  methods: {
    async retrieveCustomers() {
      try {
        const res = await http.get("/customers");
        this.customers = res.data;
        if (this.customers.length) {
          this.$refs.customersTable.getHeadersList(this.customers); // 子コンポーネントに値が渡される前にメソッドが実行されるため引数にcustomersオブジェクトを渡す
        }
      } catch (error) {
        console.log(error);
      }
    }
  },
  mounted() {
    this.retrieveCustomers();
  }
};
</script>