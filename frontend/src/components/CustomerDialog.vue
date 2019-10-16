<template>
  <v-row justify="center">
    <v-dialog v-model="dialog" max-width="600px">
      <template v-slot:activator="{ on }">
        <v-btn color="primary" text v-on="on">DETAIL</v-btn>
      </template>
      <v-card>
        <v-card-title>
          <span class="headline">Customer Profile</span>
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-row>
              <v-col cols="12">
                <v-text-field label="Id" v-model="data.id" readonly></v-text-field>
              </v-col>
              <v-col cols="12">
                <v-text-field label="Name" v-model="data.name" required></v-text-field>
              </v-col>
              <v-col cols="12">
                <v-text-field label="Age" v-model="data.age" required></v-text-field>
              </v-col>
            </v-row>
          </v-container>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" text @click="updateActive">UDPATE</v-btn>
          <v-btn color="blue darken-1" text @click="deleteCustomer">DELETE</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
import http from "../http-common";

export default {
  name: "customer",
  props: ["customer"],
  data() {
    return {
      data: {
        id: this.customer.id,
        name: this.customer.name,
        age: this.customer.age,
        active: this.active
      },
      dialog: false,
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
    // customerの更新メソッド
    async updateActive() {
      if (this.validate) {
        try {
          const res = await http.put("/customer/" + this.data.id, this.data);
          this.$router.push("/");
        } catch (error) {
          console.log(error);
        }
      }
    },
    // customerの削除メソッド
    async deleteCustomer() {
      try {
        const res = await http.delete("/customer/" + this.customer.id);
        console.log(res.data);
        this.$router.push("/");
      } catch (error) {
        console.log(error);
      }
    }
  }
};
</script>