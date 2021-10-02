import Vue from 'vue';
import Router from 'vue-router';
import startPage from '../view/startPage.vue';
import searchPage from '../view/searchPage.vue';
import Login from '../view/loginPage.vue';
import Profile from '../view/profilePage.vue';
import Manage from '../view/managePage.vue';
import MainFooter from '../layout/MainFooter.vue';
import signupPage from '../view/signupPage.vue';
import QnAPage from '../view/QnAPage.vue';
import Service from '../view/servicePage.vue';
import ManageRegist from '../view/residenceRegistPage.vue';
import VueSimpleAlert from "vue-simple-alert";
Vue.use(Router);
const requireAuth = () => (to, from, next) => {
  if (localStorage.getItem('accessToken')) {
    return next();
  }
  VueSimpleAlert.fire({
    title:"서비스 권한 없음",
    text:"추천받기 서비스는 회원 전용 서비스 입니다!",
    type:"error",
}).then(() => {
    next('/login');
  }); 
};
export default new Router({
  mode: 'history',
  linkExactActiveClass: 'active',
  routes: [
    {
      path: '/',
      name: 'start',
      component: startPage,
    },
    {
      path: '/search',
      name: 'search',
      component: searchPage,
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: 'black' }
      }
    },
    {
      path: '/login',
      name: 'login',
      component : Login,
    },
    {
      path: '/profile',
      name: 'profile',
      component: Profile,
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: 'black' }
      }
    },
    {
      path: '/manage',
      name: 'manage',
      component: Manage,
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: 'black' }
      },
      children: [
        {
          path: "/manage/regist",
          component: ManageRegist,
        }
      ],
    },
    {
      path: '/signup',
      name: 'signup',
      component : signupPage,
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: 'black' }
      }
    },

    {
      path: '/qna',
      name: 'qna',
      components: { default: QnAPage, footer: MainFooter },
      beforeEnter: requireAuth(),
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: 'black' }
      }
    }

  ],
  scrollBehavior: to => {
    if (to.hash) {
      return { selector: to.hash };
    } else {
      return { x: 0, y: 0 };
    }
  }
});
