import Vue from 'vue';
import Vuetify from 'vuetify/lib/framework';


Vue.use(Vuetify);

export default new Vuetify({

    theme: {
        themes: {
            light: {
                primary: '#2b90d9',
                secondary: '#d9e1e8',
                accent: '#282c37',
                error: '#b71c1c',
            },
        },
    },

});
