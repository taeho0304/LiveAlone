<template >
  <div>
    <MainNav @maker="getMarkerData" />

    <Map v-bind:marker="marker" />
  </div>
</template>

<script>
import MainNav from "../layout/MainNavbar.vue";
// import SearchBar from '../pages/SearchBar.vue'
import Map from "../pages/Map.vue";

export default {
  components: {
    MainNav,

    Map,
  },
  data() {
    return {
      marker: null,
    };
  },
  methods: {
    getMarkerData(data) {
      this.marker = data;
      console.log(this.marker);
    },
  },
};
</script>
