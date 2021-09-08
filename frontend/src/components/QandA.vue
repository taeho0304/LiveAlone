<template>
  <v-stepper v-model="e1" class="rounded-xl">
    <v-stepper-header>
      <template v-for="n in QnA">
        <v-stepper-step
          class="mx-0 rounded-xl"
          :key="`${n.index}-step`"
          :complete="e1 > n.index"
          :step="n.index"
          editable
        >
          Question {{ n.index }}
        </v-stepper-step>
        <v-divider v-if="n.index !== QnA" :key="n.index"></v-divider>
      </template>
    </v-stepper-header>

    <v-stepper-items class="QnA">
      <v-stepper-content
        v-for="n in QnA"
        :key="`${n.index}-content`"
        :step="n.index"
      >
        <div class="col-12 click ml-auto my-10">
          <p class="text-h5">
            <strong> {{ n.Q }} </strong>
          </p>
        </div>

        <div class="col-12 click ml-auto pa-1">
          <v-btn
            rounded
            class="col-6 click ml-auto mx-1 my-5 black--text"
            x-large
            color="secondary"
            v-for="a in n.ans"
            :key="`${a.index}`"
            @click="nextStep(n.index)"
          >
            <span class="text-h6"
              ><strong>{{ a.A }}</strong></span
            >
          </v-btn>
        </div>
      </v-stepper-content>
    </v-stepper-items>
  </v-stepper>
</template>

<style >
.QnA {
  background-color: #9baec8;
}
</style>

<script>
export default {
  data() {
    return {
      questionCount: 5,
      QnA: [
        {
          Q: "요즘 어떤일을 하시고 있나요?",
          index: 1,

          ans: [
            { index: 1, A: "회사원" },
            { index: 2, A: "학생" },
          ],
        },
        {
          Q: "오랜만에 만나는 친구,만나고 싶은 약속 장소는?",
          index: 2,
          ans: [
            { index: 1, A: "조용하고 한적한 카페" },
            { index: 2, A: "놀거리가 많은 번화가" },
          ],
        },
        {
          Q: "어느 지역에 살고 싶나요?",
          index: 3,
          ans: [
            { index: 1, A: "강서구" },
            { index: 2, A: "강남구" },
          ],
        },
        {
          Q: "가장 중요하게 생각하는 시설은?",
          index: 4,
          ans: [
            { index: 1, A: "편의시설" },
            { index: 2, A: "문화시설" },
            { index: 3, A: "교통시설" },
          ],
        },
        {
          Q: "대중 교통을 자주 이용하시나요?",
          index: 5,
          ans: [
            { index: 1, A: "일주일 1~2회" },
            { index: 2, A: "일주일 3~4회" },
            { index: 3, A: "일주일 5회" },
          ],
        },
      ],

      e1: 1,
    };
  },
  watch: {
    steps(val) {
      if (this.e1 > val) {
        this.e1 = val;
      }
    },
  },

  methods: {
    nextStep(n) {
      if (n === this.steps) {
        this.e1 = 1;
      } else {
        this.e1 = n + 1;
      }

      if (n == 5) {
        console.log(":done");
      }
    },
  },
  props: {},
  components: {},
};
</script>