<template >
  <v-container bg fill-height grid-list-md text-xs-center>
    <v-row align="center" justify="center">
      <v-col align="center">
        <v-sheet
          align="center"
          class="col-6 col-md-6 pa-0 rounded-xl"
          elevation="10"
        >
          <QandA />
        </v-sheet>
      </v-col>
    </v-row>
  </v-container>
</template>



<style>
</style>

<script>
import QandA from "../components/QandA.vue";

export default {
  data() {
    return {};
  },
  components: {
    QandA,
  },
};
</script>
