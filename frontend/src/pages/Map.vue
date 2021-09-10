<template>
  <div>
    <div id="map" style="width:100%;height:100%;"></div>
  </div>
</template>

<script>
export default {
  name: 'index',
  bodyClass: 'index-page',
  components: {
    
  },
  methods:{
      initMap() {
        var mapContainer = document.getElementById('map'), // 지도를 표시할 div
        mapOption = {
          center: new kakao.maps.LatLng(37.566826, 126.9786567), // 지도의 중심좌표
          level: 3, // 지도의 확대 레벨
        };
  
        var map = new kakao.maps.Map(mapContainer, mapOption);
      }
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
        this.initMap();
    } else {
        const script = document.createElement('script');
    /* global kakao */
        script.onload = () => kakao.maps.load(this.initMap);
        script.src =
        'http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=f52d6b75a8a65ca935ff31e1ba7eace5';
        document.head.appendChild(script);
      }
    },
};
</script>
<style>
#map {
  position:absolute;
  left:0;
  top:0;
  margin:0;
  padding:0;
}
</style>
