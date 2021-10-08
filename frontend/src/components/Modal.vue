<template>
  <SlideYUpTransition :duration="animationDuration">
    <div
      class="modal fade"
      @click.self="closeModal"
      :class="[
        { 'show d-block': show },
        { 'd-none': !show },
        { 'modal-mini': type === 'mini' },
      ]"
      v-show="show"
      tabindex="-1"
      role="dialog"
      :aria-hidden="!show"
    >
      <div
        class="modal-dialog"
        :class="[{ 'modal-notice': type === 'notice' }, modalClasses]"
        style="
          min-width: 600px;
          max-width: 600px;
          overflow: hidden;
          margin-right: 39.5%;
          border-radius: 10px;
        "
      >
        <div class="modal-content" style="border-radius: 10px">
          <div style="margin-right: 15px; margin-top: 10px">
            <button
              style="justify-content: right"
              type="button"
              v-if="showClose"
              @click="closeModal"
              class="close"
              data-dismiss="modal"
              :aria-hidden="!show"
            >
              <i class="now-ui-icons ui-1_simple-remove"></i>
            </button>
          </div>
          <slot name="base-content">
            <div
              class="modal-body"
              :class="bodyClasses"
              style="padding-bottom: 0; padding-top: 5px; padding-bottom: 5px"
            >
              <slot></slot>
            </div>
          </slot>
        </div>
      </div>
    </div>
  </SlideYUpTransition>
</template>
<script>
import { SlideYUpTransition } from "vue2-transitions";

export default {
  name: "modal",
  components: {
    SlideYUpTransition,
  },
  props: {
    show: Boolean,
    showClose: {
      type: Boolean,
      default: true,
    },
    type: {
      type: String,
      default: "",
      validator(value) {
        let acceptedValues = ["", "notice", "mini"];
        return acceptedValues.indexOf(value) !== -1;
      },
    },
    modalClasses: [Object, String],
    headerClasses: [Object, String],
    bodyClasses: [Object, String],
    footerClasses: [Object, String],
    animationDuration: {
      type: Number,
      default: 500,
    },
  },
  methods: {
    closeModal() {
      this.$emit("update:show", false);
      this.$emit("close");
    },
  },
  watch: {
    show(val) {
      let documentClasses = document.body.classList;
      if (val) {
        documentClasses.add("modal-open");
      } else {
        documentClasses.remove("modal-open");
      }
    },
  },
};
</script>
<style>
</style>
