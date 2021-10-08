<template >
  <div>
    <MainNav
      v-bind:isAvailable="false"
      @maker="getMarkerData"
      @detailS="getDetailSearch"
      @move="getMoveData"
      v-bind:moveMapDong="moveMapDong"
    />

    <Map
      v-bind:marker="marker"
      v-bind:detailFilter="detailFilter"
      @moveJuso="moveJuso"
    />
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
      moveMapDong: null,
    };
  },
  methods: {
    moveJuso(data) {
      this.moveMapDong = data;
    },
    getMarkerData(data) {
      this.marker = data;
    },
    getMoveData(data) {
      this.move = data;
    },
    getDetailSearch(data) {
      this.detailFilter = data;
    },
  },
};
</script>
<style scoped>
</style>