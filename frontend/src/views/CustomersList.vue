<template>
  <v-container>
    <v-row>
      <v-col>
        <h4>Customers List</h4>
      </v-col>
    </v-row>
    <v-row>
      <v-col>
        <v-simple-table>
          <thead>
            <tr>
              <th v-for="header in headers" :key="header">{{ header }}</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="customer in customers" :key="customer.id">
              <td>{{ customer.id }}</td>
              <td>{{ customer.name }}</td>
              <td>{{ customer.age }}</td>
              <td class="text-center">
                <router-link
                  :to="{
                  name:'customer-details',
                  params: { id: customer.id, customer: customer}
                  }"
                >detail</router-link>
              </td>
            </tr>
          </tbody>
        </v-simple-table>
      </v-col>
    </v-row>
    <router-view></router-view>
  </v-container>
</template>

<script>
import http from "../http-common";

export default {
  name: "customers-list",
  data() {
    return {
      customers: [],
      headers: []
    };
  },
  methods: {
    async retrieveCustomers() {
      try {
        const res = await http.get("/customers");
        this.customers = res.data;
        this.getHeadersList();
      } catch (error) {
        console.log(error);
      }
    },
    // active以外のcustomersのkeyを取得し、headersに格納する
    getHeadersList() {
      const arr = Object.keys(this.customers[0]).filter(
        val => val !== "active"
      );
      this.headers = arr.concat("");
    }
  },
  mounted() {
    this.retrieveCustomers();
  }
};
</script>