<template >
  <div id="background">
    <StartPage />
  </div>
</template>

<style>
html {
  background-color: #d9e1e8;
}

.v-application--wrap {
  background-color: #d9e1e8;
}
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
</style>


<script>
import StartPage from "../components/StartPage.vue";

export default {
  components: {
    StartPage,
  },
};
</script>
