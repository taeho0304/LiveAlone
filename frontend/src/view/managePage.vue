<template >
    <div class="Site">
        <main-navbar/>
        <Manage class="Site-content"/>
        <main-footer/>
    </div>
</template>

<script>
import MainFooter from '../layout/Footer.vue'
import MainNavbar from '../layout/MainNavbar.vue'
import Manage from '../pages/user/Manage.vue'

export default {
    components:{
        Manage,
        MainNavbar,
        MainFooter,  
    }
}
</script>
<style lang="scss" scoped>
.Site {
  display: flex;
  min-height: 100vh;
  flex-direction: column;
}

.Site-content {
  flex: 1;
}

::-webkit-scrollbar {
  width: 10px;
}
 
::-webkit-scrollbar-track {
  background: #eeeeee;
  border-radius: 5px;
}
 
::-webkit-scrollbar-thumb {
  background: linear-gradient(#c2e59c, #64b3f4);
}
</style>
