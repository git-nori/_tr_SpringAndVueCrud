<template>
  <v-container>
    <v-row>
      <v-col>
        <h4>Customer Add</h4>
      </v-col>
    </v-row>
    <v-row>
      <v-col v-if="!submitted">
        <v-form ref="form" v-model="valid" lazy-validation>
          <v-text-field label="name" required :rules="rules" v-model="customer.name"></v-text-field>
          <v-text-field label="age" required :rules="rules" v-model="customer.age"></v-text-field>
          <v-btn :disabled="!valid" text color="success" @click="saveCustomer">submit</v-btn>
        </v-form>
      </v-col>
      <v-col v-else align="center">
        <h4>You submitted successful!!!</h4>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import http from "../http-common";

export default {
  name: "add-customer",
  data() {
    return {
      customer: {
        id: "",
        name: "",
        age: "",
        active: false
      },
      rules: [v => !!v || "required"],
      valid: false,
      submitted: false
    };
  },
  methods: {
    validate() {
      if (this.$refs.form.validate()) {
        return true;
      }
      return false;
    },
    // Customerの新規追加メソッド
    async saveCustomer() {
      if (this.validate()) {
        var data = {
          name: this.customer.name,
          age: this.customer.age
        };

        // [feature]ageに数値が入るようバリデーションをかける機能の追加
        try {
          const res = await http.post("/customer", data);
          this.customer.id = res.data.id;
          this.customer = {};
          this.submitted = true;
        } catch (error) {
          console.log(error);
        }
      }
    }
  }
};
</script>