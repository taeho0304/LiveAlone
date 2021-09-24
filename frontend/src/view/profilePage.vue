<template >
    <div>
        <main-navbar/>
        <Profile/>
        <main-footer/>
    </div>
</template>

<script>
import MainFooter from '../layout/MainFooter.vue'
import MainNavbar from '../layout/MainNavbar.vue'
import Profile from '../pages/Signup copy.vue'

export default {
    components:{
        Profile,
        MainNavbar,
        MainFooter,  
    }
}
</script>
