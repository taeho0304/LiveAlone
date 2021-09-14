import Vue from 'vue';
import Router from 'vue-router';
import startPage from '../view/startPage.vue';
import searchPage from '../view/searchPage.vue';
import Landing from '../pages/Landing.vue';
import Login from '../view/loginPage.vue';
import Profile from '../pages/Profile.vue';
import MainNavbar from '../layout/MainNavbar.vue';
import MainFooter from '../layout/MainFooter.vue';
import store from '../store/index';
import signupPage from '../view/signupPage.vue';
import QnAPage from '../view/QnAPage.vue';

Vue.use(Router);

export default new Router({
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
      path: '/landing',
      name: 'landing',
      components: { default: Landing, header: MainNavbar, footer: MainFooter },
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
      components: { default: Profile, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: 'black' }
      }
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
