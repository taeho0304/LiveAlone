import Vue from "vue";
import VueRouter from "vue-router";

import Home from "../views/startPage.vue";
import Search from "../views/mainPage.vue";

Vue.use(VueRouter);


const routes = [
  {
    path: "/",
    name: "Home",
    component: Home,
  },
  {
    path: "/search",
    name: "Search",
    component: Search,
  },
];

const router = new VueRouter({
  mode: "history",
  base: "",
  routes,
});


export default router;
