import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'customers',
      component: () => import('./views/CustomersList.vue'),
      children: [{
        path: "/customer/:id",
        name: "customer-details",
        component: () => import('./views/Customer.vue'),
        props: true
      }
      ]
    },
    {
      path: '/add',
      name: 'add',
      component: () => import('./views/AddCustomer.vue')
    },
    {
      path: '/search',
      name: 'search',
      component: () => import('./views/SearchCustomers.vue')
    },
  ]
})
