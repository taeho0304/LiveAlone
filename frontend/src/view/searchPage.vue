<template >
    <div>
        <MainNav/>
        <Search />
    </div>
</template>

<script>
import MainNav from '../layout/MainNavbar.vue'
import Search from '../pages/Search.vue'

export default {
    components:{
        MainNav,
        Search
    }
}
</script>
