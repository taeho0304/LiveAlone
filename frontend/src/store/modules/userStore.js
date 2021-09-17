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
            //console.log(payload.accessToken);
        },
        USERINFO(state, payload) {
            state.userInfo = payload;
        }
    },
    actions: {
        requestRegister(context, payload) {
            let body = payload

            http.post('/api/v1/users', body).then(() => {
                router.push('/login');
            }).catch((err) => {
                //alert(err.response.data.message);
                //console.log(err);
            });
        },
        requestLogin({ commit }, user) {
            http
                .post(`/api/v1/auth/login`, user)
                .then(({ data }) => {
                    commit("LOGIN", data);
                    router.push('/search');
                   // console.log(localStorage.getItem("accessToken"));
                })
                .catch((err) => {
                   // console.log(err);
                });
        },
        requestUserInfo({commit}){
            const CSRF_TOKEN=localStorage.getItem("accessToken");
            //if (CSRF_TOKEN==null) return;
            http
              .get(`/api/v1/users/me`,{headers: {"Authorization": 'Bearer '+ CSRF_TOKEN }
            })
            .then(({ data })=>{
                commit("USERINFO", data);
                //console.log(data);
            })
            .catch(() => {
                //console.error();
            });
        },
        requestModify({commit}, user){
            console.log(user);
            http
              .patch(`/api/v1/users/`+user.userId,user)
              .then(({ data })=>{
                commit("USERINFO", data);
                alert('회원정보가 수정 되었습니다.')
                window.location.reload();
                this.requestUserInfo();
              })
              .catch(() => {

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
