<template>
  <div id="hello">
    <v-img id="logo" alt="Vue logo" src="../assets/images/로고.png"></v-img>

    <v-card
        class="mx-auto"
        max-width="504"
        >
        <v-card-text>
        <div>혼족, 일코노미, 나홀로족..</div>
        <p class="text-h4 text--primary">
            이제는 1인 가구 시대!
        </p>
        <div class="text--primary">
            당신만의 매물을 찾고 싶으신가요?<br>
            나 혼자 산다에서 찾아보세요!
        </div>
        </v-card-text>
    </v-card>
    
    <div class="button" align="center">
        <v-btn id="button1" class="button hover1" rounded x-large color="primary">검색하기</v-btn>
        <v-btn id="button2" class="button hover2" rounded x-large color="error">추천받기</v-btn>
    </div>

  </div>
</template>

<script>
export default {
  name: 'StartPage',
  props: {
    msg: String
  }
}
</script>

<style type="text/css">
    @import url("../assets/css/main.css");
</style>
