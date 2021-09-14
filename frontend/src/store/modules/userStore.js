import jwt_decode from 'jwt-decode';
import http from '@/util/http-common';
import router from "@/router/router";


export default {
    namespaced: true,
    state: {
        userInfo: null,
        accessToken: "",

    },
    mutations: {
        LOGIN(state, payload) {
            state.accessToken = payload.accessToken;
            localStorage.setItem("accessToken", state.accessToken);
            console.log(payload.accessToken);
        },
        USERINFO(state, payload) {

        }
    },
    actions: {
        requestRegister(context, payload) {
            let body = payload

            http.post('/api/v1/users', body).then(() => {
                router.push('/login');
            }).catch((err) => {
                //alert(err.response.data.message);
                console.log(err);
            });
        },
        requestLogin({ commit }, user) {
            http
                .post(`/api/v1/auth/login`, user)
                .then(({ data }) => {
                    commit("LOGIN", data);
                    router.push('/search');
                    console.log(localStorage.getItem("accessToken"));
                })
                .catch((err) => {
                    console.log(err);
                });
        },
        requestUserInfo({commit}){
            const CSRF_TOKEN=localStorage.getItem("accessToken");
            if (!CSRF_TOKEN) return;
            http
              .get(`/api/v1/users/me`,{headers: {"Authorization": 'Bearer '+ CSRF_TOKEN }
            })
            .then(({ data })=>{
                commit("USERINFO", data);
                console.log(data);
            })
            .catch(() => {
                console.error();
            });
        },


    },
    getters: {
        getAccessToken(state) {
            return state.accessToken;
        },
        getLoginStatus(state) {
            return state.loginStatus;
        },
        getUserInfo(state) {
            return state.userInfo;
          },
    }
}
