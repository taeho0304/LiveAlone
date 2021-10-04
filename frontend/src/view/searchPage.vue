<template >
  <div>
    <MainNav
      v-bind:isAvailable="false"
      @maker="getMarkerData"
      @detailS="getDetailSearch"
      @move="getMoveData"
    />

    <Map v-bind:marker="marker" v-bind:detailFilter="detailFilter" />
  </div>
</template>

<script>
import MainNav from "../layout/MainNavbar.vue";
import Map from "../pages/map/Map.vue";

export default {
  components: {
    MainNav,
    Map,
  },
  data() {
    return {
      marker: null,
      move: null,
      detailFilter: null,
      isAvailable: false,
    };
  },
  methods: {
    getMarkerData(data) {
      this.marker = data;
      console.log(this.marker);
    },
    getMoveData(data) {
      this.move = data;
      console.log(this.move);
    },
    getDetailSearch(data) {
      this.detailFilter = data;
      console.log("디테일검색 필터", data);
    },
  },
};
</script>
<style scoped>
</style>