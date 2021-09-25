<template >
    <div class="app" v-bind:style="{ background: color }">
        <Login/>
    </div>
</template>

<script>
import Login from '../pages/user/Login.vue'
export default {
    data(){
        return {
      color: "linear-gradient(0deg,#e6e9f0,#eef1f5)",
    };
    },
    components:{

        Login,
        
    }
}
</script>
<style scoped>
.app {
  display: flex;
  min-width: 100vw;
  min-height: 100vh;
  justify-content: center;
  align-items: center;
  left: 50%;
  top: 50%;
  text-align: center;
}
</style>
