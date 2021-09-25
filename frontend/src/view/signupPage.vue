<template >
    <div class="app" v-bind:style="{ background: color }">   

<Signup/>

 
           
        
  
    </div>
</template>

<script>


import Signup from '../pages/user/Signup.vue'

export default {
    data(){
        return {
      color: "linear-gradient(0deg,#fdfbfb,#ebedee)",
    };
    },
    components:{

        Signup,
        
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
