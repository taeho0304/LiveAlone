<template>
  <footer
    class="footer"
    :class="{ [`footer-${type}`]: type }"
    :data-background-color="backgroundColor"
    
  >
    <div class="container">
      <nav>
        <ul>
          <li>
            <a href="https://www.creative-tim.com">
              Creative 4남 1녀
            </a>
          </li>
          <li>
            <a href="https:///presentation.creative-tim.com">
              About Us
            </a>
          </li>
          <li>
            <a href="https://humble-polonium-918.notion.site/7ed95eeaebdb49fd935c4a6f547294eb">
              도움말
            </a>
          </li>
        </ul>
      </nav>
      <div class="copyright">
        &copy; {{ year }}, Designed by
        <a href="https://www.instagram.com/ujjgnim/" target="_blank" rel="noopener"
          >MJ Kang</a
        >,
        <a href="https://www.instagram.com/y._.n_reo/" target="_blank" rel="noopener"
          >KH Yoon</a
        >
        and
        <a href="https://www.creative-tim.com" target="_blank" rel="noopener"
          >I will alone</a
        >.
      </div>
    </div>
  </footer>
</template>
<script>
export default {
  props: {
    backgroundColor: String,
    type: String
  },
  data() {
    return {
      year: new Date().getFullYear()
    };
  }
};
</script>
<style scope>
.footer {
    padding: 7px 0;
}

</style>
