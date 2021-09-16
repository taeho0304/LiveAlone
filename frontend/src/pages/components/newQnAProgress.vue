<template>
  <div id="app">
    <card
      class="card-nav-tabs text-center"
      header-classes="card-header-warning"
    >
      <div slot="header" class="col-md-12 my-3">
        <h4>
          <n-progress class="my-4" type="primary" :value="pr" show-value>
          </n-progress>
          <strong> {{ QnA[index].question }}</strong>
        </h4>
      </div>

      <div class="container" v-if="index == 2">
        <drop-down
          class="
            select
            col-md-8
            ml-auto
            mr-auto
            my-4
            btn btn-primary btn-round btn-lg btn-block
          "
          :title="areatitle"
        >
          <p
            class="dropdown-item"
            v-for="(a, idx) in QnA[index].answer"
            :key="idx"
            @click="changeArea(a, idx)"
          >
            {{ a }}
          </p>
        </drop-down>

        <a
          v-if="areatitle != `지역선택`"
          class="
            col-md-8
            ml-auto
            mr-auto
            my-4
            select
            btn btn-primary btn-round btn-lg btn-block
            my-4
          "
          @click="next(areatitle, areaindex)"
          >다음
        </a>
      </div>

      <div
        class="container"
        v-else
        v-for="(a, idx) in QnA[index].answer"
        :key="idx"
        @click="next(a, idx)"
      >
        <a
          class="
            col-md-8
            ml-auto
            my-4
            mr-auto
            btn btn-primary btn-round btn-lg btn-block
          "
          >{{ a }}
        </a>
      </div>

      <div slot="footer" class="card-footer text-muted mb-2">live-alone</div>
    </card>
  </div>
</template>

<script>
import { Progress } from "@/components";
import { mapActions, mapGetters } from "vuex";
import { DropDown } from "@/components";
import { Card } from "@/components";

export default {
  components: {
    [Progress.name]: Progress,
    DropDown,
    Card,
  },
  data() {
    return {
      areatitle: "지역선택",
      areaindex: 0,
      QnARes: [],
      QnA: JSON.parse(localStorage.getItem("getQnAOpt")),
      index: 0,
      pr: 0,
    };
  },
  methods: {
    changeArea(a, ans) {
      this.areatitle = a;
      this.areaindex = ans;
      return;
    },
    next(a, ans) {
      this.QnARes.push(ans);
      //console.log(a);
      //console.log(ans);

      if (this.index == 4) {
       // console.log(this.QnARes);
        this.setRes(this.QnARes);
        return;
      }
      this.index += 1;
      this.pr += 25;
    },
    ...mapActions("question", ["setQnARes"]),
    setRes(data) {
      this.setQnARes(data);
    },
  },
};
</script>
<style>
.container {
  text-align: center;
}
</style>