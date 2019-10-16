<template>
  <v-form ref="form" v-model="valid" lazy-validation>
    <v-text-field label="age" required :rules="rules" v-model="age"></v-text-field>
    <v-btn :disabled="!valid" text color="success" @click="search">SERACH</v-btn>
  </v-form>
</template>

<script>
export default {
  name: "search-form",
  data() {
    return {
      age: 0,
      rules: [v => !!v || "required"],
      valid: false
    };
  },
  methods: {
    validate() {
      if (this.$refs.form.validate()) {
        return true;
      }
      return false;
    },
    search() {
      if (this.validate()) {
        this.$emit("searchCustomer", { age: this.age });
      }
    }
  }
};
</script>