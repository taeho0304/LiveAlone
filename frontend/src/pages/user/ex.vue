<template>
  <div class="mb-16">
    <!-- Setup basic form -->
    <fvl-form :data="form" class="relative" url="/basic">
      <!-- Add source code toggle button (only for example) -->

      <!-- Text input component -->
      <fvl-input
      id="b1"
        :value.sync="form.name"
        autocomplete="name"
        label="Name"
        name="name"
        placeholder="Type your name"
        type="text"
      />
      <div v-if="isCheck" class="fvl-checkbox-error-message">Please enter your Name!</div>
      <!-- Source code area (only for example) -->

      <fvl-textarea
        :value.sync="form.text"
        label="Text"
        name="text"
        placeholder="Type your story..."
      />

      <fvl-input
        :value.sync="form.password"
        autocomplete="new-password"
        class="w-full lg:w-1/2 relative"
        field-class="pr-8"
        label="Password"
        name="password"
        placeholder="Type password"
        type="password"
      >
        <!-- Optional password strength meter using the hint slot -->
        <template slot="hint">
          <div class="absolute right-0 top-0 mt-11 mr-4">
            <transition name="slide-down">
              <span v-if="form.password.length > 0 && form.password.length < 6">😔</span>
              <span v-if="form.password.length >= 6 && form.password.length < 10">😌</span>
              <span v-if="form.password.length >= 10 && form.password.length < 15">😃</span>
              <span v-if="form.password.length >= 15">😍</span>
            </transition>
          </div>
        </template>
      </fvl-input>
      <!-- Add source code toggle button (only for example) -->

      <fvl-slider
        :value.sync="form.slider"
        value-position="left"
        class="w-full lg:w-1/2"
        label="Slider"
        name="slider"
      ></fvl-slider>
      <!-- Source code area (only for example) -->


      <!-- Add source code toggle button (only for example) -->

      <fvl-color-picker
        :value.sync="form.colorPicker"
        class="w-full lg:w-1/2"
        field-class="w-40"
        format="hex"
        label="Color Picker"
        name="colorpicker"
      ></fvl-color-picker>


      <!-- Source code area (only for example) -->
      <!-- Submit button component -->
      <button  @click="click(form)">Validate</button>
    </fvl-form>
  </div>
</template>

<script>
import 'formvuelar/dist/formvuelar.css';
  import {
    FvlForm,
    FvlInput,
    FvlTextarea,
    FvlSlider,
    FvlColorPicker,
    FvlSubmit
  } from 'formvuelar'
  export default {
    components: {
      FvlForm,
      FvlInput,
      FvlTextarea,
      FvlSlider,
      FvlColorPicker,
      
    },
    data() {
      return {
          isCheck:false,
        form: {
          name: '',
          text: '',
          password: '',
          color: '',
          option: '',
          agree: false,
          switch: false,
          slider: '0',
          colorPicker: '#C75454'
        },
        showSource: '',
        source: {
          name:
            `<fvl-input \n` +
            `     label="Name" \n` +
            `     name="name" \n` +
            `     type="text"  \n` +
            `     autocomplete="name"  \n` +
            `     :value.sync="form.name"  \n` +
            `     placeholder="Type your name"  \n` +
            `/>`,
          text:
            `<fvl-textarea \n` +
            `     label="Text" \n` +
            `     name="text" \n` +
            `     :value.sync="form.text" \n` +
            `     placeholder="Type your story..." \n` +
            `/>`,
          password:
            `<fvl-input \n` +
            `    label="Password" \n` +
            `    name="password" \n` +
            `    type="password" \n` +
            `    autocomplete="new-password" \n` +
            `    :value.sync="form.password" \n` +
            `    placeholder="Type password" \n` +
            `    class="w-1/2 relative" \n` +
            `    fieldClass="pr-8" \n` +
            `> \n` +
            `    <!-- Optional Password Meter --> \n` +
            `    <template slot="hint"> \n` +
            `    <div class="absolute right-0 top-0 mt-12 mr-4"> \n` +
            `        <span v-if="form.password.length > 0 &&  \n` +
            `                    form.password.length < 6">😔</span> \n` +
            `        <span v-if="form.password.length >= 6 &&  \n` +
            `                    form.password.length < 10">😌</span> \n` +
            `        <span v-if="form.password.length >= 10 &&  \n` +
            `                    form.password.length < 15">😃</span> \n` +
            `        <span v-if="form.password.length >= 15">😍</span> \n` +
            `    </div> \n` +
            `    </template> \n` +
            `</fvl-input> \n`,
          slider:
            `<fvl-slider \n` +
            `    label="Slider" \n` +
            `    name="slider" \n` +
            `    value-position="left" \n` +
            `    :value.sync="form.slider" \n` +
            `    class="w-full lg:w-1/2 relative" \n` +
            `/> \n`,
          
        }
      }
    },
    methods: {
        click(config){
            console.log("되냐")
            
 console.log(config)
                var errors = {}
                if ( config.name === '' ) {
                   document.getElementById("b1").style.borderColor = "red";
                    this.isCheck=true;
                }
               
        return [ 422, {
        "message": "The given data was invalid.",
        "errors": errors
    } ]

            
        },
      toggleSource(field) {
        this.showSource = this.showSource == field ? '' : field
      }
    }
  }
</script>
<style scoped>
.error{
    --tw-border-opacity: 1;
    border-color: rgba(252,129,129,1);
}
</style>