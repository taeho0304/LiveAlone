<template>
  <div>
    <div class="section">
      <div class="container">
        <div style="margin-top: 15px">
          <tabs
            type="primary"
            tabContentClasses="tab-subcategories"
            square
            centered
            class="nav-align-center"
          >
            <tab-pane>
              <span slot="label">
                <i class="now-ui-icons business_badge"></i> Profile
              </span>
              <ManageInfo />
            </tab-pane>

            <tab-pane>
              <!--등록된 매물-->
              <span slot="label">
                <i class="now-ui-icons shopping_shop"></i> Registed
              </span>
              <ManageConfirm />
            </tab-pane>

            <tab-pane>
              <!--검색 필터 저장-->
              <span slot="label">
                <i class="now-ui-icons design_bullet-list-67"></i> Regist
              </span>
              <ManageRegist />
            </tab-pane>
          </tabs>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import { Tabs, TabPane, FormGroupInput, Button } from "@/components";
import ManageInfo from "./ManageInfo.vue";
import ManageRegist from "./ManageRegist.vue";
import ManageConfirm from "./ManageConfirm.vue";
import { mapActions, mapGetters } from "vuex";

export default {
  name: "manage",
  bodyClass: "manage-page",
  components: {
    Tabs,
    TabPane,
    [FormGroupInput.name]: FormGroupInput,
    [Button.name]: Button,
    ManageInfo,
    ManageRegist,
    ManageConfirm,
  },
  data: function () {
    return {
    };
  },
  computed: {
  },
  methods: {
    ...mapActions("user", [
      "requestUserInfo",
      "requestDelete",
      "requestModify",
      "requestRegistResi",
    ]),

    init() {
      console.log("Manage.vue 1");
    },
  },
  created() {
    this.init();
  },
  mounted() {
    this.init();
  },
};
</script>
<style src="@riophae/vue-treeselect/dist/vue-treeselect.min.css"></style>
<style>
.input-file-button {
  padding: 6px 25px;
  background-color: #2ca8ff;
  border-radius: 4px;
  color: white;
  cursor: pointer;
  position: relative;
}
.image-container[data-v-00458278] {
  width: calc(100vw - 440px);
  height: 180px;
  border: 1px dashed #ced4da;
  border-radius: 10px;
  background-color: #fff;
}
.inputLabel {
  padding-left: 5px;
  padding-right: 5px;
  margin-bottom: 3px;
}
.vue-treeselect__control {
  padding-left: 5px;
  padding-right: 5px;
  display: table;
  table-layout: fixed;
  width: 100%;
  height: 47px;
  border: 1px solid #ced4da;
  border-radius: 10px;
  background: #edf2ff;
  -webkit-transition-duration: 0.2s;
  transition-duration: 0.2s;
  -webkit-transition-property: border-color, width, height, background-color,
    opacity, -webkit-box-shadow;
  transition-property: border-color, width, height, background-color, opacity,
    -webkit-box-shadow;
  transition-property: border-color, box-shadow, width, height, background-color,
    opacity;
  transition-property: border-color, box-shadow, width, height, background-color,
    opacity, -webkit-box-shadow;
  -webkit-transition-timing-function: cubic-bezier(0.215, 0.61, 0.355, 1);
  transition-timing-function: cubic-bezier(0.215, 0.61, 0.355, 1);
}
.profile-page .gallery {
  margin-top: 5px;
}

.slick-prev:before,
.slick-next:before {
  font-family: "slick";
  font-size: 20px;
  line-height: 1;
  opacity: 0.75;
  color: coral;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}

.slick-dots {
  position: absolute;
  bottom: 0px;
  display: block;
  width: 100%;
  padding: 0;
  padding-right: 50px;
  margin: 0;
  list-style: none;
  text-align: center;
}
.mobile {
  color: #333;
}

@media screen and (max-width: 800px) {
  .mobile table.v-table tr {
    max-width: 100%;
    position: relative;
    display: block;
  }

  .mobile table.v-table tr:nth-child(odd) {
    border-left: 6px solid deeppink;
  }

  .mobile table.v-table tr:nth-child(even) {
    border-left: 6px solid cyan;
  }

  .mobile table.v-table tr td {
    display: flex;
    width: 100%;
    border-bottom: 1px solid #f5f5f5;
    height: auto;
    padding: 10px;
  }

  .mobile table.v-table tr td ul li:before {
    content: attr(data-label);
    padding-right: 0.5em;
    text-align: left;
    display: block;
    color: #999;
  }
  .v-datatable__actions__select {
    width: 50%;
    margin: 0px;
    justify-content: flex-start;
  }
  .mobile .theme--light.v-table tbody tr:hover:not(.v-datatable__expand-row) {
    background: transparent;
  }
}
.flex-content {
  padding: 0;
  margin: 0;
  list-style: none;
  display: flex;
  flex-wrap: wrap;
  width: 100%;
}

.flex-item {
  padding: 5px;
  width: 50%;
  height: 40px;
  font-weight: bold;
}
</style>