import jwt_decode from 'jwt-decode';
import http from '@/util/http-common';
import router from "@/router/router";


export default {
    namespaced: true,
    state: {

        accessToken: "",

    },
    mutations: {
        LOGIN(state, payload) {
            state.accessToken = payload.accessToken;
            localStorage.setItem("accessToken", state.accessToken);
            console.log(payload.accessToken);
        },
        LOGOUT(state) {
            Object.assign(state, getDefaultState());
        },
    },
    actions: {
        requestRegister(context, payload) {
            let body = payload
            return http.post('/users', body)
        },
        requestLogin({ commit }, user) {
            http
                .post(`/api/v1/auth/login`, user)
                .then(({ data }) => {

                    commit("LOGIN",  data );
                    console.log(data)

                    router.push('/');

                    console.log(localStorage.getItem("accessToken"));
                })
                .catch((err) => {
                    //alert(err.response.data.message);
                    console.log(err);
                });
        },
        // NOTE: 로그인 상태 설정

        logout(context) {
            context.commit('LOGOUT')
        },


    },
    getters: {
        getAccessToken(state) {
            return state.accessToken;
        },
        getLoginStatus(state) {
            return state.loginStatus;
        },
    }
}
