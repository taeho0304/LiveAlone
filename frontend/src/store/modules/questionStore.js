
import http from '@/util/http-common';
import router from "@/router/router";


export default {
    namespaced: true,
    state: {

        questionResult: [],

    },
    mutations: {

        SETQNA(state, payload) {
            console.log(payload);
            console.log(payload.qna)
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
                    // console.log(data)
                }).catch((err) => {

                    console.log(err);
                });
        },
        setQnARes({ commit }, data) {
            // console.log("done")
            commit("SETRES", data);

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
