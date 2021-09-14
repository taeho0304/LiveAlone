import Vue from "vue";
import Vuex from "vuex";
import Cookies from "js-cookie";
import user from "./modules/userStore.js";
import search from "./modules/searchStore.js";
import question from "./modules/questionStore.js";
import createPersistedState from "vuex-persistedstate";
Vue.use(Vuex);

export default new Vuex.Store({
    modules: {
        user,
        search,
        question

    },
    plugins: [
        createPersistedState({
            paths: ["user", "search"],
        })
    ],
})