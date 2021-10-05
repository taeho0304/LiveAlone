<template>
  <div>
    <div v-show="isResiShow" class="Resi col-md-3" style="max-width: 430px">
      <ResidenceList
        v-if="resiList != null"
        v-bind:resiList="resiList"
        v-bind:pageItem="pageItem"
        v-bind:sortFilter="sortFilter"
        @requestNextItem="requestNextItem"
        @sort="sort"
      />
    </div>
    <div v-show="isQnAshow" class="Resi col-md-3" style="max-width: 430px">
      <QnAResList />
    </div>

    <div id="map" style="width: 100%; height: 100%"></div>

    <div v-if="isCount" class="card temp card-neutral" size="" style="width: 300px;margin-top: 34.5%;margin-right: 7px; margin-bottom:0px">
      <!----><!----><!----><!----><!---->
      <ul class="list-group list-group-flush">
        <li class="list-group-item">우리동네 {{this.moveDong}} 상권🤳</li>
        <li class="list-group-item">편의점🏪 : {{getDongCommercial[1] == null ? 0 : getDongCommercial[1].count}}</li>
        <li class="list-group-item">카페☕️ : {{getDongCommercial[0] == null ? 0 : getDongCommercial[0].count}}</li>
        <li class="list-group-item">헬스장🏋️ : {{getDongCommercial[2] == null ? 0 : getDongCommercial[2].count}}</li>
      </ul>
      
    </div>
  </div>
</template>

<script>
import {  } from "@/components";
import ResidenceList from "./ResidenceList.vue";
import http from "@/util/http-common";
import QnAResList from "@/pages/qna/QnAResult.vue";
import { mapActions, mapGetters } from "vuex";
export default {
  computed: {},
  components: {

    ResidenceList,
    QnAResList,
  },
  name: "index",
  bodyClass: "index-page",
  computed: {
    ...mapGetters("search", ["getDongCommercial"]),
  },
  mounted() {
    window.kakao && window.kakao.maps
      ? this.initMap()
      : this.addKakaoMapScript();
  },
  data() {
    return {
      isCount:false,
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
      mark: null,
      sortFilter: null,
      dongSortType: null,
      dongSortOrder: null,
      pageItem: {
        curpage: null,
        total: null,
        type: null,
      },
      requestForIds: {
        sortOrder: null,
        sortType: null,
        residenceIds: [],
        pageNum: 1,
      },

      dong:{
        convenienceCount:0,
        cafeCount:0,
        healthCount:0,
      }
    };
  },
  props: {
    marker: Object,
    detailFilter: Object,
  },
  watch: {
    qnaResiList: function (newVal) {
      if (this.isResiShow) {
        this.isResiShow = false;
      }
      this.isQnAshow = true;
    },
    detailFilter: function (newVal) {
      console.log("change", newVal);
      this.sortFilter = "none";
      this.pageItem.curpage = 1;
      this.pageItem.type = "details";
      const CSRF_TOKEN = localStorage.getItem("accessToken");
      if (CSRF_TOKEN != null) {
        http
          .post("/api/v1/residences/detail", newVal, {
            headers: { Authorization: "Bearer " + CSRF_TOKEN },
          })
          .then((res) => {
            this.pageItem.total = res.data.pageSize;
            this.resiList = res.data.residenceInfo;
            console.log("deatailRES", this.resiList);
          });
      } else {
        http.post("/api/v1/residences/detail", newVal).then((res) => {
          console.log("deatailRES", res.data.residenceInfo);
          this.pageItem.total = res.data.pageSize;
          this.resiList = res.data.residenceInfo;
        });
      }
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
      var mark = null;
      var markers = newVal.positionModelList.map(this.drawMarker);

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
      this.isCount=true;
      this.cluster.clear();
      console.log(newVal);
      this.pageItem.curpage = 1;
      this.pageItem.type = "dong";
      this.sortFilter = "none";
      this.dongSortType = null;
      this.dongSortOrder = null;
      const CSRF_TOKEN = localStorage.getItem("accessToken");
      if (CSRF_TOKEN != null) {
        http
          .get("/api/v1/residences?dong=" + newVal + "&pageNum=" + "1", {
            headers: { Authorization: "Bearer " + CSRF_TOKEN },
          })
          .then((res) => {
            console.log("movemap", res.data);
            this.pageItem.total = res.data.pageSize;
            this.resiList = res.data.residenceInfo;
          });
      } else {
        http
          .get("/api/v1/residences?dong=" + newVal + "&pageNum=" + "1")
          .then((res) => {
            console.log("movemap", res.data);
            this.pageItem.total = res.data.pageSize;
            this.resiList = res.data.residenceInfo;
          });
      }
      this.requestDongCommercial(newVal);
      http
        .get("/api/v1/residences/positions?dongName=" + newVal)
        .then((res) => {
          console.log(res.data);
          this.markerList = res.data;
        });
    },
  },
  created() {},
  methods: {
    ...mapActions("search", [
      "requestDongCommercial",
    ]),
    sort(res) {
      console.log(res);
      //NOTE: ids , 동 , 상세 중 현재 검색한 타입으로 정렬
      const CSRF_TOKEN = localStorage.getItem("accessToken");
      this.sortFilter = res.sortType;
      if (this.pageItem.type == "ids") {
        console.log("thisType", this.pageItem.type);
        this.pageItem.curpage = 1;
        this.pageItem.type = "ids";
        this.requestForIds.sortOrder = res.sortOrder ? "asc" : "desc";
        this.requestForIds.sortType = res.sortType;
        if (CSRF_TOKEN != null) {
          http
            .post("/api/v1/residences/ids", this.requestForIds, {
              headers: { Authorization: "Bearer " + CSRF_TOKEN },
            })
            .then((res) => {
              this.resiList = res.data.residenceInfo;
              this.pageItem.total = res.data.pageSize;
              console.log("받아온데이터", this.resiList);
            });
        } else {
          http
            .post("/api/v1/residences/ids", this.requestForIds)
            .then((res) => {
              this.resiList = res.data.residenceInfo;
              this.pageItem.total = res.data.pageSize;
              console.log("받아온데이터", this.resiList);
            });
        }
      } else if (this.pageItem.type == "dong") {
        this.dongSortOrder = res.sortOrder ? "asc" : "desc";
        this.dongSortType = res.sortType;
        console.log("thisType", this.pageItem.type);
        if (CSRF_TOKEN != null) {
          http
            .get(
              "/api/v1/residences?dong=" +
                this.moveDong +
                "&pageNum=" +
                "1" +
                "&sortOrder=" +
                this.dongSortOrder +
                "&sortType=" +
                this.dongSortType,
              {
                headers: { Authorization: "Bearer " + CSRF_TOKEN },
              }
            )
            .then((res) => {
              console.log("movemap", res.data);
              this.pageItem.total = res.data.pageSize;
              this.resiList = res.data.residenceInfo;
            });
        } else {
          http
            .get(
              "/api/v1/residences?dong=" +
                this.moveDong +
                "&pageNum=" +
                "1" +
                "&sortOrder=" +
                this.dongSortOrder +
                "&sortType=" +
                this.dongSortType
            )
            .then((res) => {
              console.log("movemap", res.data);
              this.pageItem.total = res.data.pageSize;
              this.resiList = res.data.residenceInfo;
            });
        }
      } else if (this.pageItem.type == "details") {
        console.log("thisType", this.pageItem.type);

        this.pageItem.curpage = 1;
        this.pageItem.type = "details";
        this.detailFilter.sortOrder = res.sortOrder ? "asc" : "desc";
        this.detailFilter.sortType = res.sortType;
        console.log(this.detailFilter);
        if (CSRF_TOKEN != null) {
          http
            .post("/api/v1/residences/detail", this.detailFilter, {
              headers: { Authorization: "Bearer " + CSRF_TOKEN },
            })
            .then((res) => {
              this.pageItem.total = res.data.pageSize;
              this.resiList = res.data.residenceInfo;
              console.log("sortDetails", this.resiList);
            });
        } else {
          http
            .post("/api/v1/residences/detail", this.detailFilter)
            .then((res) => {
              console.log("sortDetails", res.data.residenceInfo);
              this.pageItem.total = res.data.pageSize;
              this.resiList = res.data.residenceInfo;
            });
        }
      }
    },
    requestNextItem(itemnum) {
      console.log(itemnum);
      this.pageItem.curpage = itemnum;
      console.log(this.pageItem.type);
      const CSRF_TOKEN = localStorage.getItem("accessToken");
      if (this.pageItem.type == "dong") {
        if (CSRF_TOKEN != null) {
          http
            .get(
              "/api/v1/residences?dong=" +
                this.moveDong +
                "&pageNum=" +
                itemnum,
              +"&sortOrder=" +
                this.dongSortOrder +
                "&sortType=" +
                this.dongSortType,
              {
                headers: { Authorization: "Bearer " + CSRF_TOKEN },
              }
            )
            .then((res) => {
              console.log("movemap", res.data);
              this.resiList = res.data.residenceInfo;
            });
        } else {
          http
            .get(
              "/api/v1/residences?dong=" +
                this.moveDong +
                "&pageNum=" +
                itemnum +
                "&sortOrder=" +
                this.dongSortOrder +
                "&sortType=" +
                this.dongSortType
            )
            .then((res) => {
              console.log("movemap", res.data);
              this.resiList = res.data.residenceInfo;
            });
        }
      } else if (this.pageItem.type == "ids") {
        this.pageItem.curpage = itemnum;
        this.requestForIds.pageNum = itemnum;
        if (CSRF_TOKEN != null) {
          http
            .post("/api/v1/residences/ids", this.requestForIds, {
              headers: { Authorization: "Bearer " + CSRF_TOKEN },
            })
            .then((res) => {
              this.resiList = res.data.residenceInfo;
              console.log("받아온데이터", this.resiList);
            });
        } else {
          http
            .post("/api/v1/residences/ids", this.requestForIds)
            .then((res) => {
              this.resiList = res.data.residenceInfo;
              console.log("받아온데이터", this.resiList);
            });
        }
      } else if (this.pageItem.type == "details") {
        this.detailFilter.pageNum = itemnum;
        this.pageItem.curpage = itemnum;
        const CSRF_TOKEN = localStorage.getItem("accessToken");
        if (CSRF_TOKEN != null) {
          http
            .post("/api/v1/residences/detail", this.detailFilter, {
              headers: { Authorization: "Bearer " + CSRF_TOKEN },
            })
            .then((res) => {
              this.pageItem.total = res.data.pageSize;
              this.resiList = res.data.residenceInfo;
              console.log("deatailRES", this.resiList);
            });
        } else {
          http
            .post("/api/v1/residences/detail", this.detailFilter)
            .then((res) => {
              console.log("deatailRES", res.data.residenceInfo);
              this.pageItem.total = res.data.pageSize;
              this.resiList = res.data.residenceInfo;
            });
        }
      }
    },
    drawMarker(positions) {
      var mark = new kakao.maps.Marker({
        position: new kakao.maps.LatLng(positions.lat, positions.lon),
        title: positions.id,
      });
      kakao.maps.event.addListener(mark, "click", () => {
        this.makeClickListener(mark);
      });

      return mark;
    },
    makeClickListener(mark) {
      console.log(mark);
      var Item = {
        residenceIds: [],
        pageNum: 1,
      };
      this.pageItem.curpage = 1;
      this.pageItem.total = 1;
      this.pageItem.type = "ids";
      const CSRF_TOKEN = localStorage.getItem("accessToken");
      Item.residenceIds.push(mark.Fb);
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
      this.moveDong = null;
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
          si: result[0].region_1depth_name,
          gu: result[0].region_2depth_name,
          dong: result[0].region_3depth_name,
        };

        this.$emit("moveJuso", move);
        this.moveDong=move.dong;
        
      }
    },
    temp(cluster) {
      var clickcluster = cluster.getMarkers().length;

      const CSRF_TOKEN = localStorage.getItem("accessToken");
      this.pageItem.type = "ids";
      this.requestForIds.residenceIds = [];
      this.pageItem.curpage = 1;
      this.sortFilter = "none";
      for (var i = 0; i < clickcluster; i++) {
        this.requestForIds.residenceIds.push(cluster.getMarkers()[i].Fb);
      }

      if (CSRF_TOKEN != null) {
        http
          .post("/api/v1/residences/ids", this.requestForIds, {
            headers: { Authorization: "Bearer " + CSRF_TOKEN },
          })
          .then((res) => {
            this.resiList = res.data.residenceInfo;
            this.pageItem.total = res.data.pageSize;
            console.log("받아온데이터", this.resiList);
          });
      } else {
        http.post("/api/v1/residences/ids", this.requestForIds).then((res) => {
          this.resiList = res.data.residenceInfo;
          this.pageItem.total = res.data.pageSize;
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
  float: right;
  transform: translate(0%, 0%);
  bottom: 0;
  border-radius: 10px;
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
