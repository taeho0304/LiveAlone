import Vue from "vue";
import VueRouter from "vue-router";

import Home from "../views/startPage.vue";
import QnA from "../views/QnAPage.vue";

Vue.use(VueRouter);


const routes = [
  {
    path: "/",
    name: "Home",
    component: Home,
  },

  {
    path: "/QnA",
    name: "QnA",
    component: QnA,
  },
];

const router = new VueRouter({
  mode: "history",
  base: "",
  routes,
});


export default router;
