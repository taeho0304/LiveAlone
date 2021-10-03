<template>
  <div>
    <div v-show="isResiShow" class="Resi col-md-3" style="max-width: 430px">
      <ResidenceList v-bind:resiList="resiList" />
    </div>
    <div v-show="isQnAshow" class="Resi col-md-3" style="max-width: 430px">
      <QnAResList />
    </div>

    <div id="map" style="width: 100%; height: 100%"></div>
    <ul id="category" v-show="isShow">
      <li id="BK9" data-order="0">
        <span class="category_bg bank"></span>
        은행
      </li>
      <li id="MT1" data-order="1">
        <span class="category_bg mart"></span>
        마트
      </li>
      <li id="PM9" data-order="2">
        <span class="category_bg pharmacy"></span>
        약국
      </li>
      <li id="OL7" data-order="3">
        <span class="category_bg oil"></span>
        주유소
      </li>
      <li id="CE7" data-order="4">
        <span class="category_bg cafe"></span>
        카페
      </li>
      <li id="CS2" data-order="5">
        <span class="category_bg store"></span>
        편의점
      </li>
    </ul>

    <n-button class="temp" type="neutral" round size="" @click="clickShow()">
      <i class="now-ui-icons ui-1_zoom-bold"></i
    ></n-button>
  </div>
</template>

<script>
import { Button } from "@/components";
import ResidenceList from "./ResidenceList.vue";
import http from "@/util/http-common";
import QnAResList from "@/pages/qna/QnAResult.vue";
export default {
  computed: {},
  components: {
    [Button.name]: Button,
    ResidenceList,
    QnAResList,
  },
  name: "index",
  bodyClass: "index-page",
  computed: {},
  mounted() {
    window.kakao && window.kakao.maps
      ? this.initMap()
      : this.addKakaoMapScript();
  },
  data() {
    return {
      isShow: false,
      isResiShow: false,
      isQnAshow: true,
      qnaResiList: [],
      markerList: [],
      resiList: [],
      map: null,
      gugunCount: this.$store.getters[`search/getgugunCount`],
      dongCount: this.$store.getters[`search/getdongCount`],
      marking: null,
      cluster: null,
      moveDong: null,
    };
  },
  props: {
    marker: Object,
    detailList: Array,
  },
  watch: {
    qnaResiList: function (newVal) {
      if (this.isResiShow) {
        this.isResiShow = false;
      }
      this.isQnAshow = true;
    },
    detailList: function (newVal) {
      console.log(newVal);
      this.resiList = newVal;
      this.isResiShow = true;
    },
    resiList: function (newVal) {
      console.log("new", newVal);
      if (this.isQnAshow) {
        this.isQnAshow = false;
      }
      this.isResiShow = true;
    },
    markerList: function (newVal) {
      console.log(newVal);
      var markers = newVal.positionModelList.map(function (position) {
        return new kakao.maps.Marker({
          position: new kakao.maps.LatLng(position.lat, position.lon),
          title: position.id,
        });
      });
      this.cluster.addMarkers(markers);
    },
    marker: function (newVal) {
      var moveLatLon = new kakao.maps.LatLng(newVal.lat, newVal.long);
      this.cluster.clear();
      console.log("moveTo", moveLatLon);
      this.map.setLevel(6);
      this.map.panTo(moveLatLon);
      this.moveDong = newVal.dong;
    },
    moveDong: function (newVal) {
      this.cluster.clear();
      console.log(newVal);

      http
        .get(
          "/api/v1/residences/positions?%EB%8F%99%EC%9D%B4%EB%A6%84=" + newVal
        )
        .then((res) => {
          console.log(res.data);
          this.markerList = res.data;
        });
    },
  },
  created() {},
  methods: {
    clickShow() {
      this.isShow = !this.isShow;
    },
    addKakaoMapScript() {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=f52d6b75a8a65ca935ff31e1ba7eace5&libraries=services,clusterer,drawing";
      document.head.appendChild(script);
    },
    initMap() {
      var container = document.getElementById("map"); //지도를 담을 영역의 DOM 레퍼런스
      var options = {
        //지도를 생성할 때 필요한 기본 옵션
        center: new kakao.maps.LatLng(37.564214, 127.001699),
        level: 8, //지도의 레벨(확대, 축소 정도)
      };

      var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
      this.map = map;
      kakao.maps.event.addListener(map, "dragend", this.mapdrag);

      var clusterer = new kakao.maps.MarkerClusterer({
        map: this.map,
        // 마커들을 클러스터로 관리하고 표시할 지도 객체
        averageCenter: true,
        // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정
        minLevel: 2,
        disableClickZoom: true,
        // 클러스터 할 최소 지도 레벨
      });
      this.cluster = clusterer;

      var zoomControl = new kakao.maps.ZoomControl();
      map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);

      // 지도가 확대 또는 축소되면 마지막 파라미터로 넘어온 함수를 호출하도록 이벤트를 등록합니다
      kakao.maps.event.addListener(map, "zoom_changed", function () {
        // 지도의 현재 레벨을 얻어옵니다
        var level = map.getLevel();

        var message = "현재 지도 레벨은 " + level + " 입니다";
        console.log(message);
      });

      kakao.maps.event.addListener(clusterer, "clusterclick", this.temp);
    },
    mapdrag() {
      // 지도 중심좌표를 얻어옵니다
      var latlng = this.map.getCenter();

      var message = "변경된 지도 중심좌표는 " + latlng.getLat() + " 이고, ";
      message += "경도는 " + latlng.getLng() + " 입니다";

      console.log(message);
      var geocoder = new kakao.maps.services.Geocoder();
      // 좌표로 행정동 주소 정보를 요청합니다

      var ddd = geocoder.coord2RegionCode(
        latlng.getLng(),
        latlng.getLat(),
        this.getnewdong
      );
    },
    getnewdong(result, status) {
      console.log(status);
      if (status === kakao.maps.services.Status.OK) {
        const move = {
          dong: result[0].region_3depth_name,
        };
        this.moveDong = move.dong;
        console.log(move.dong);
      }
    },
    temp(cluster) {
      var clickcluster = cluster.getMarkers().length;
      var Item = [];
      const CSRF_TOKEN = localStorage.getItem("accessToken");
      for (var i = 0; i < clickcluster; i++) {
        Item.push(cluster.getMarkers()[i].Fb);
      }

      if (CSRF_TOKEN != null) {
        http
          .post("/api/v1/residences/ids", Item, {
            headers: { Authorization: "Bearer " + CSRF_TOKEN },
          })
          .then((res) => {
            this.resiList = res.data.residenceInfo;
            console.log("받아온데이터", this.resiList);
          });
      } else {
        http.post("/api/v1/residences/ids", Item).then((res) => {
          this.resiList = res.data.residenceInfo;
          console.log("받아온데이터", this.resiList);
        });
      }
    },
  },
};
</script>
<style>
.Resi {
  float: left;
  margin-left: 7px;
  margin-top: 60px;
  z-index: 6;
  border-radius: 10px;

  background-color: #fafafa !important;
  padding-top: 0;
}
.temp {
  position: absolute;
  left: 50%;

  transform: translate(-50%, -50%);

  bottom: 0;
  border-radius: 5px;
  border: 1px solid #909090;
  box-shadow: 0 1px 1px rgba(0, 0, 0, 0.4);
  background: #fff;
  overflow: hidden;
  z-index: 2;
}
#map {
  position: absolute;
  left: 0;
  top: 0;
  margin: 0;
  padding: 0;
}

#category {
  position: absolute;
  left: 50%;

  transform: translate(-50%, -50%);

  bottom: 3%;
  border-radius: 5px;
  border: 1px solid #909090;
  box-shadow: 0 1px 1px rgba(0, 0, 0, 0.4);
  background: #fff;
  overflow: hidden;
  z-index: 2;
}
#category n-button {
  left: 50;
}
#category li {
  float: left;
  list-style: none;
  width: 50px;
  border-right: 1px solid #acacac;
  padding: 6px 0;
  text-align: center;
  cursor: pointer;
}
#category li.on {
  background: #eee;
}
#category li:hover {
  background: #ffe6e6;
  border-left: 1px solid #acacac;
  margin-left: -1px;
}
#category li:last-child {
  margin-right: 0;
  border-right: 0;
}
#category li span {
  display: block;
  margin: 0 auto 3px;
  width: 27px;
  height: 28px;
}
#category li .category_bg {
  background: url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_category.png)
    no-repeat;
}
#category li .bank {
  background-position: -10px 0;
}
#category li .mart {
  background-position: -10px -36px;
}
#category li .pharmacy {
  background-position: -10px -72px;
}
#category li .oil {
  background-position: -10px -108px;
}
#category li .cafe {
  background-position: -10px -144px;
}
#category li .store {
  background-position: -10px -180px;
}
#category li.on .category_bg {
  background-position-x: -46px;
}
.placeinfo_wrap {
  position: absolute;
  bottom: 28px;
  left: -150px;
  width: 300px;
}
.placeinfo {
  position: relative;
  width: 100%;
  border-radius: 6px;
  border: 1px solid #ccc;
  border-bottom: 2px solid #ddd;
  padding-bottom: 10px;
  background: #fff;
}
.placeinfo:nth-of-type(n) {
  border: 0;
  box-shadow: 0px 1px 2px #888;
}
.placeinfo_wrap .after {
  content: "";
  position: relative;
  margin-left: -12px;
  left: 50%;
  width: 22px;
  height: 12px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png");
}
.placeinfo a,
.placeinfo a:hover,
.placeinfo a:active {
  color: #fff;
  text-decoration: none;
}
.placeinfo a,
.placeinfo span {
  display: block;
  text-overflow: ellipsis;
  overflow: hidden;
  white-space: nowrap;
}
.placeinfo span {
  margin: 5px 5px 0 5px;
  cursor: default;
  font-size: 13px;
}
.placeinfo .title {
  font-weight: bold;
  font-size: 14px;
  border-radius: 6px 6px 0 0;
  margin: -1px -1px 0 -1px;
  padding: 10px;
  color: #fff;
  background: #d95050;
  background: #d95050
    url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/arrow_white.png)
    no-repeat right 14px center;
}
.placeinfo .tel {
  color: #0f7833;
}
.placeinfo .jibun {
  color: #999;
  font-size: 11px;
  margin-top: 0;
}
</style>
