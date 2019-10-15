<template>
  <v-app>
    <v-content>
      <v-container>
        <v-row>
          <v-col align="center">
            <h1>Vue Springboot example</h1>
          </v-col>
        </v-row>
        <v-row>
          <v-col align="center">
            <v-btn outlined color="info" class="mx-2" to="/">Customers</v-btn>
            <v-btn outlined color="info" class="mx-2" to="/add">Customers</v-btn>
            <v-btn outlined color="info" class="mx-2" to="/search">Customers</v-btn>
          </v-col>
        </v-row>
      </v-container>
      <router-view></router-view>
    </v-content>
  </v-app>
</template>

<script>
export default {
  name: "App",
  data: () => ({
    //
  })
};
</script>
