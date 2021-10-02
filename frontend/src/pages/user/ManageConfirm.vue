<template>
  <div id="app">
    <link
      href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
      rel="stylesheet"
      integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN"
      crossorigin="anonymous"
    />
    <h1>VUE DATA TABLE</h1>
    <main>
      <h2>TABLE 1</h2>
      <br />
      <data-table v-bind="parametersTable1" @actionTriggered="handleAction" />
      <br />
      <br />
    </main>
  </div>
</template>

<style lang="css">
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
main {
  padding: 32px;
}
</style>

<script>
import users from "./data";
import "bootstrap/dist/css/bootstrap.min.css";
export default {
  name: "App",

  computed: {
    parametersTable1() {
      return {
        data: [
          {
            id: 1,
            name: "Prof. Seth Howell DVM",
            email: "crystal.prosacco@example.org",
            email_verified_at: "2012-02-19 13:28:17",
            created_at: "2020-02-23 21:29:25",
            updated_at: "2020-02-23 21:29:25",
            gender: "Female",
            suffix: "Jr.",
            title: "Dr.",
            username: "turner.brenden",
            userAgent:
              "Mozilla/5.0 (X11; Linux i686) AppleWebKit/5360 (KHTML, like Gecko) Chrome/40.0.868.0 Mobile Safari/5360",
            creditCardType: "Visa",
            creditCardNumber: "2514800271552232",
            photo: "https://lorempixel.com/640/480/?18703",
            info: "Occaecati aut rerum aliquam fugiat officia. Eveniet distinctio ut sapiente minus. Corporis omnis molestiae hic itaque neque voluptas.",
            bio: "Tempora nam ratione inventore aspernatur. Est eius necessitatibus expedita rerum quos quod fuga. Nobis ut saepe itaque ullam. Similique possimus tempora assumenda. Aperiam quasi ratione veritatis placeat enim consequuntur error architecto. Aut unde quasi nostrum blanditiis totam et et. Impedit accusamus enim harum velit quam consequatur.",
            city: "Hamillport",
            state: "South Dakota",
            streetName: "Mohr Mews",
            country: "American Samoa",
            timezone: "Europe/Vilnius",
            job: "Recreational Therapist",
            phone: "+15372887819",
            company: "Doyle, Davis and Prohaska",
          },
          {
            id: 2,
            name: "Corine Kunde",
            email: "kokuneva@example.org",
            email_verified_at: "2003-12-07 12:05:58",
            created_at: "2020-02-23 21:29:25",
            updated_at: "2020-02-23 21:29:25",
            gender: "Male",
            suffix: "PhD",
            title: "Prof.",
            username: "archibald.bashirian",
            userAgent:
              "Opera/8.89 (X11; Linux i686; sl-SI) Presto/2.8.188 Version/10.00",
            creditCardType: "Visa",
            creditCardNumber: "4024007188333",
            photo: "https://lorempixel.com/640/480/?41675",
            info: "Voluptas provident voluptate quibusdam sit rem placeat. Rerum id perspiciatis atque nesciunt in nihil qui.",
            bio: "Possimus enim consequatur esse. Laborum numquam veniam provident impedit. Aut eaque nam explicabo et tempore. Vero ut enim numquam rerum tempora repudiandae architecto. Minima voluptatem doloremque nobis perspiciatis omnis. Consequuntur qui et consequatur voluptatem qui. Officiis et quis est in cum.",
            city: "North Orieburgh",
            state: "New Jersey",
            streetName: "Raynor Course",
            country: "Mali",
            timezone: "Indian/Mauritius",
            job: "Forest and Conservation Technician",
            phone: "1-817-455-8407",
            company: "Hammes-Bergstrom",
          },
        ],
        actionMode: "multiple",
        columnKeys: [
          "name",
          "email",
          "gender",
          "country",
          "job",
          "city",
          "created_at",
          "photo",
        ],
      };
    },
  },

  methods: {
    handleAction(actionName, data) {
      console.log(actionName, data);
      window.alert("check out the console to see the logs");
    },
  },
};
</script>
