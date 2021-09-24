<template>
  <div class="container" style="padding-right: 0">
    <div
      class="row col-md-12 resiwrap"
      style="padding-bottom: 0; padding-top: 0"
    >
      <div class="col-md-4 ml-auto mr-auto">
        <a class="btn btn-warning btn-round btn-block" style="color: #5e2c04"
          >가격</a
        >
      </div>
      <div class="col-md-4 ml-auto mr-auto">
        <a class="btn btn-warning btn-round btn-block" style="color: #5e2c04"
          >면적</a
        >
      </div>
      <div class="col-md-4 ml-auto mr-auto">
        <a class="btn btn-warning btn-round btn-block" style="color: #5e2c04"
          >선호</a
        >
      </div>
    </div>
    <template>
      <div class="scroll row col-md-12 mr-0 mt-0">
        <card
          class="card-nav-tabs text-center"
          header-classes="card-header-warning"
          style="min-width: 300px; max-height: 280px"
        >
          <div class="row" style="margin-rigth: 0">
            <div class="col-md-8">
              <img
                style="width: 100%; height: 95%"
                src="https://d1774jszgerdmk.cloudfront.net/512/6a0ecfd6-d540-4349-bd11-ca9b66f8a5a0"
              />
            </div>
            <div class="col-md-4 pr-0 pl-0">
              <div class="col-md-12 pl-0 pb-0 title">
                <strong><h5 class="mb-1">전세</h5></strong>
              </div>
              <div class="col-md-12 pt-1 pl-0 pr-0 title">
                <strong><h5 class="mb-1">1억 6500</h5></strong>
              </div>
              <div class="col-md-12 pt-1 pl-0 pr-0 title">
                <h6 class="mb-1">투룸</h6>
              </div>
              <div
                class="row col-md-12 ml-auto mr-auto mt-1 pt-1 pl-0 pr-0 title"
              >
                <div class="col-md-6 pr-0">
                  <i class="now-ui-icons ui-2_favourite-28"></i>
                </div>
                <div class="col-md-6 pr-0">
                  <i class="now-ui-icons ui-1_zoom-bold" @click="showModal"></i>
                </div>
              </div>
            </div>
            <div slot="footer" class="col-md-12 mt-0 text-muted mb-2">
              <p>1층,9평,관리비5만, *낙성대 초 역세권* 넓고...</p>
            </div>
          </div>
        </card>
      </div>
    </template>
    <template>
      <modal
        class="resiDetailModal"
        :show.sync="showResiDetail"
        modal-classes="modal-lg"
        header-classes="justify-content-center"
      >
        <ResiDetail />
      </modal>
    </template>
  </div>
</template>
<script>
import { Card } from "@/components";
import Modal from "@/components/Modal.vue";
import ResiDetail from "@/pages/components/ResiDetail.vue";
export default {
  components: {
    Card,
    Modal,
    ResiDetail,
  },
  setup() {},
  data() {
    return {
      showResiDetail: false,
      residence: [
        {
          area: 0,
          content: "string",
          cost: 0,
          dong: "string",
          gu: "string",
          id: 0,
          imgurl: "residenceInfo",
          jeonseCost: 0,
          lat: "string",
          lon: "string",
          manageCost: 0,
          name: "string",
          residenceCategory: {
            categoryName: "string",
            id: 0,
          },
          residenceDetail: {
            building: "string",
            id: 0,
            unit: "string",
          },
          residenceEstate: {
            address: "string",
            id: 0,
            name: "string",
            phone: "string",
            tel: "string",
          },
          residenceType: {
            id: 0,
            type: "string",
          },
          residenceWeight: {
            commercialCategory: {
              categoryName: "string",
              id: 0,
            },
            id: 0,
            weight: "string",
          },
          wolseCost: 0,
        },
      ],
    };
  },
  methods: {
    showModal() {
      this.showResiDetail = !this.showResiDetail;
    },
  },
};
</script>
<style scoped>
.title {
  text-align: left;
}
.resiwrap {
  display: flex;
}
.scroll {
  overflow: auto;
  height: calc(100vh - 170px);
}
.scroll::-webkit-scrollbar {
  width: 5px; /*스크롤바의 너비*/
}
.scroll::-webkit-scrollbar-thumb {
  background-color: #2f3542;
  border-radius: 10px;
}
.scroll::-webkit-scrollbar-track {
  background-color: grey;
  border-radius: 10px;
  box-shadow: inset 0px 0px 5px white;
}
.resiDetailModal {
  display: flex;
}
</style>
