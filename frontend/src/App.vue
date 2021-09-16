<template>
  <div id="app">
   /* eslint-disable no-console */ 
    <router-view name="header" />
    <div class="wrapper">
      <router-view />
    </div>
    <router-view name="footer" />
  </div>
</template>
<script>
export default {};
</script>
