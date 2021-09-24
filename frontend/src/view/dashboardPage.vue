<template>
  <div class="flex min-h-screen">
    <the-sidebar />



      <div class="mb-auto">
        <router-view />
      </div>

    </div>

</template>

<script>
import TheSidebar from '../layout/TheSidebar.vue'


export default {
  components: {
    TheSidebar,

  },
}
</script>
