<template >
    <div>
        <MainNav/>

        <Map />
    </div>
</template>

<script>
import MainNav from '../layout/MainNavbar.vue'
// import SearchBar from '../pages/SearchBar.vue'
import Map from '../pages/Map.vue'

export default {
    components:{
        MainNav,
        
        Map
    }
}
</script>
