<template></template>

<script>
import http from "../http-common";

export default {
  name: "customers-list",
  data() {
    return {
      customers: []
    };
  },
  methods: {
    retrieveCustomers() {
      http.get("/customers").then(res => {
        this.customers = res.data;
        console.log(res.data);
      });
    },
    refreshList() {
      this.retrieveCustomers();
    }
  },
  mounted() {
    this.retrieveCustomers();
  }
};
</script>