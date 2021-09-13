<template>
  <div>
    <navbar
      v-show="visible"
      position="fixed"
      style="margin-top: 47px"
      type="warning"
      menu-classes="ml-auto"
    >
      <template slot="navbar-menu">
        <drop-down
          v-for="(item, index) in searchItems"
          :key="index"
          :title="item.title"
          tag="div"
        >
          <div v-if="item.index == 0 || item.index == 1">
            <n-checkbox
              v-for="(items, index) in item.option"
              :key="index"
              class="dropdown-item"
              >{{ items.name }}</n-checkbox
            >
          </div>
          <div v-else>
            <h6 class="dropdown-header">매매/전세가/보증금</h6>
            <div class="dropdown-item">
              <slider
                class="slider-info"
                v-model="rangeSlider"
                :connect="true"
                type="primary"
              ></slider>
            </div>
          </div>
        </drop-down>
      </template>
    </navbar>
  </div>
</template>

<script>
import {
  Navbar,
  DropDown,
  Checkbox,
  Button,
  FormGroupInput,
  Slider,
} from "@/components";

import { Popover } from "element-ui";
export default {
  name: "main-navbar",
  props: {
    visible: {
      type: Boolean,
      require: true,
      default: false,
    },
  },
  components: {
    [Checkbox.name]: Checkbox,
    [FormGroupInput.name]: FormGroupInput,
    [Button.name]: Button,
    Navbar,
    [Slider.name]: Slider,
    DropDown,
    [Popover.name]: Popover,
  },
  data() {
    return {
      isSearch: false,
      rangeSlider: [0, 50],
      searchItems: [
        {
          title: "방 종류",
          index: 0,
          option: [
            { name: "원룸, 투룸" },
            { name: "오피스텔" },
            { name: "아파트" },
            { name: "빌라" },
            { name: "주택" },
          ],
        },
        {
          title: "거래방식",
          index: 1,
          option: [
            { name: "전체" },
            { name: "매매" },
            { name: "전세" },
            { name: "월세" },
          ],
        },
        {
          title: "가격대",
          index: 2,
        },
        { title: "관리비" },
        { title: "방 크기" },
      ],
    };
  },
};
</script>

<style scoped>
.bg-info {
  background-color: #eb8816 !important;
}
.bg-default {
  background-color: #ffffff !important;
}
</style>
