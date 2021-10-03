<template >
  <div>
    <MainNav v-bind:isAvailable="true" />
    <StartPage />
  </div>
</template>

<script>
import StartPage from "../pages/first/Start.vue";
import MainNav from "../layout/MainNavbar.vue";
export default {
  components: {
    StartPage,
    MainNav,
  },
  data() {
    return {
      isAvailable: true,
    };
  },
};
</script>
