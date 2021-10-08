
import http from '@/util/http-common';
import router from "@/router/router";


export default {
    namespaced: true,
    state: {

        questionResult: [],

    },
    mutations: {

        SETQNA(state, payload) {

            localStorage.setItem("getQnAOpt", JSON.stringify(payload.qna));
        },
        SETRES(state, payload) {
            state.questionResult = payload
        }
    },
    actions: {
        requestQuestion({ commit },) {

            http.get('/api/v1/questions/options')
                .then(({ data }) => {

                    commit("SETQNA", data)

                }).catch((err) => {


                });
        },
        setQnARes({ commit }, data) {

            http.post('/api/v1/residences/recommend', data)
                .then(({ data }) => {

                    commit("SETRES", data.recommendModelList);
                }).catch((err) => {

                });



            router.push('/search');
        }
    },
    getters: {
        getQnAOpt() {
            return localStorage.getItem("getQnAOpt");
        },
        getQuestionResult(state) {
            return state.questionResult;
        },
    }
}
