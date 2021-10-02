<template >
  <div class="Site">
    <main-navbar v-bind:isAvailable="isAvailable" />
    <Profile class="Site-content" />
    <main-footer />
  </div>
</template>

<script>
import MainFooter from "../layout/Footer.vue";
import MainNavbar from "../layout/MainNavbar.vue";
import Profile from "../pages/user/Profile.vue";

export default {
  components: {
    Profile,
    MainNavbar,
    MainFooter,
  },
  data() {
    return {
      isAvailable: true,
    };
  },
};
</script>
<style lang="scss" scoped>
.Site {
  display: flex;
  min-height: 100vh;
  flex-direction: column;
}

.Site-content {
  flex: 1;
}
</style>
