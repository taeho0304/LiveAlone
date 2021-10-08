import Vue from 'vue';
import App from './App.vue';
// You can change this import to `import router from './starterRouter'` to quickly start development from a blank layout.
import router from './router/router.js';
import NowUiKit from './plugins/now-ui-kit';
import '@progress/kendo-theme-default/dist/all.css';
import store from './store'
import vuetify from './plugins/vuetify'
import VueSimpleAlert from "vue-simple-alert";
import VueLazyload from 'vue-lazyload'
import "@/fontAwesomeIcon.js";
import { Simplert } from 'vue2-simplert-plugin'
import 'vue2-simplert-plugin/dist/vue2-simplert-plugin.min.css'
import DataTable from "@andresouzaabreu/vue-data-table";
import { Pagination } from './components'
import "@andresouzaabreu/vue-data-table/dist/DataTable.css";
import ToggleSwitch from 'vuejs-toggle-switch'
import VueRadioToggleButtons from 'vue-radio-toggle-buttons';
import 'vue-radio-toggle-buttons/dist/vue-radio-toggle-buttons.css';
Vue.use(ToggleSwitch)
Vue.use(VueRadioToggleButtons);

Vue.component("data-table", DataTable);
Vue.component(Pagination.name, Pagination)
Vue.use(Simplert)
Vue.config.productionTip = false;

Vue.use(NowUiKit);
Vue.use(VueLazyload);
Vue.use(VueSimpleAlert, { reverseButtons: true });

new Vue({
    router,
    store,
    vuetify,
    render: h => h(App)
}).$mount('#app');
