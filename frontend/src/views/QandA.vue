<template>
  <v-stepper v-model="e1">
    <v-stepper-header>
      <template v-for="n in QnA">
        <v-stepper-step
          :key="`${n.index}-step`"
          :complete="e1 > n.index"
          :step="n.index"
          editable
        >
          Step {{ n.index }}
        </v-stepper-step>

        <v-divider v-if="n.index !== QnA" :key="n.index"></v-divider>
      </template>
    </v-stepper-header>

    <v-stepper-items>
      <v-stepper-content
        v-for="n in QnA"
        :key="`${n.index}-content`"
        :step="n.index"
      >
        {{ n.Q }}

        <v-btn
          v-for="a in n.ans"
          :key="`${a.index}`"
          color="primary"
          @click="nextStep(n.index)"
        >
          {{ a.A }}
        </v-btn>
      </v-stepper-content>
    </v-stepper-items>
  </v-stepper>
</template>


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
    },
  },
  props: {},
  components: {},
};
</script>