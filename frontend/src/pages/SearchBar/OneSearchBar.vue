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
          <div v-if="item.index == 0">
            <div>
              <n-checkbox
                v-for="(items, index) in getBargainType.residenceTypeList"
                :key="index"
                class="dropdown-item"
                >{{ items.type }}</n-checkbox
              >
            </div>

            <div>
              <div style="padding-top: 30px">
                <v-card flat color="transparent">
                  <v-subheader>전세가/보증금</v-subheader>

                  <v-card-text>
                    <v-row>
                      <v-col class="px-4">
                        <v-range-slider
                          v-model="range"
                          :max="max"
                          :min="min"
                          hide-details
                          class="align-center"
                        >
                          <template v-slot:prepend>
                            <v-text-field
                              :value="range[0]"
                              class="mt-0 pt-0"
                              hide-details
                              single-line
                              type="number"
                              style="width: 60px"
                              @change="$set(range, 0, $event)"
                            ></v-text-field>
                          </template>
                          <template v-slot:append>
                            <v-text-field
                              :value="range[1]"
                              class="mt-0 pt-0"
                              hide-details
                              single-line
                              type="number"
                              style="width: 60px"
                              @change="$set(range, 1, $event)"
                            ></v-text-field>
                          </template>
                        </v-range-slider>
                      </v-col>
                    </v-row>
                  </v-card-text>
                </v-card>
              </div>
            </div>
          </div>
        </drop-down>
      </template>
    </navbar>
  </div>
</template>

<script type="text/javascript">
import {
  Navbar,
  DropDown,
  Checkbox,
  Button,
  FormGroupInput,
  Slider,
} from "@/components";
import { mapGetters } from "vuex";
import { Popover } from "element-ui";

export default {
  name: "main-navbar",
  props: {
    visible: {
      type: Boolean,
      require: true,
      default: false,
      //getBargainType : localStorage.getItem("bargainType")
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
      formatter: ``,
      depositvalue: [0, 100], //전세

      min: 0,
      max: 1000,
      range: [0, 100],

      monthvalue: [0, 100], //월세
      pricevalue: [0, 100], //매매
      managementvalue: [0, 100], //관리비
      sizevalue: [0, 100], //방크기
      isSearch: false,
      searchItems: [
        {
          title: "방 종류",
          index: 0,
        },
        {
          title: "거래방식",
          index: 1,
        },

        {
          title: "관리비",
          index: 2,
        },
        {
          title: "방 크기",
          index: 3,
        },
      ],
    };
  },

  computed: {
    ...mapGetters("search", ["getRoomType", "getBargainType"]),
  },
  created() {},
  methods: {
    check(ans) {
      console.log(ans);
    },
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

.v-slider--horizontal {
  width: 250px;
  margin-left: 8px;
  margin-right: 8px;
}
</style>
