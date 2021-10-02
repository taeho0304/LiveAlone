<template >
  <div>
    <MainNav
      v-bind:isAvailable="false"
      @maker="getMarkerData"
      @detailS="getDetailSearch"
    />

    <Map v-bind:marker="marker" v-bind:detailList="detailList" />
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
      detailList: [],
      isAvailable: false,
    };
  },
  methods: {
    getMarkerData(data) {
      this.marker = data;
      console.log(this.marker);
    },
    getDetailSearch(data) {
      this.detailList = data;
      console.log("디테일검색 결과", data);
    },
  },
};
</script>
<style scoped>
</style>