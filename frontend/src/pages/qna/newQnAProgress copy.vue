<template>
  <flow-form v-bind:questions="questions" />
</template>

<script src="https://unpkg.com/@ditdot-dev/vue-flow-form@1.1.7"></script>
<script>
  // Import necessary components and classes
  import FlowForm, { QuestionModel, QuestionType, ChoiceOption } from '@ditdot-dev/vue-flow-form'

  export default {
    name: 'example',
    components: {
      FlowForm
    },
    data() {
      return {
        questions: [
          // QuestioModel array
          new QuestionModel({
            question: '...',
            type: QuestionType.MultipleChoice,
            options: [
              new ChoiceOption({
                label: '...'
              })
            ]
          })
        ]
      }
    }
  }
</script>

<style>
  /* Import Vue Flow Form base CSS */
  @import '@ditdot-dev/vue-flow-form/dist/vue-flow-form.css';
  /* Import Vue Flow Form theme CSS (optional) */
  @import '@ditdot-dev/vue-flow-form/dist/vue-flow-form.theme-minimal.css';
</style>