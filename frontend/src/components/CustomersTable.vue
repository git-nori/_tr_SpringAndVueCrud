<template>
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
          <customer-dialog :customer="customer" />
        </td>
      </tr>
    </tbody>
  </v-simple-table>
</template>

<script>
import CustomerDialog from "../components/CustomerDialog";

export default {
  name: "customers-table",
  props: ["customers"],
  components: { CustomerDialog },
  data() {
    return {
      headers: []
    };
  },
  methods: {
    // active以外のcustomersのkeyを取得し、headersに格納する
    getHeadersList(customers) {
      const arr = Object.keys(customers[0]).filter(val => val !== "active");
      this.headers = arr.concat("");
    }
  }
};
</script>