import jwt_decode from 'jwt-decode';
import http from '@/util/http-common';
import router from "@/router/router";
import VueSimpleAlert from "vue-simple-alert";

export default {
    namespaced: true,
    state: {
        userInfo: null,
        accessToken: "",
        accessEstate:null,
        estateInfo: null,
    },
    getters: {
        getAccessToken(state) {
          return state.accessToken;
        },
        getAccessInfo(state){
          return state.accessEstate;
        },
        getLoginStatus(state) {
            return state.loginStatus;
        },
        getUserInfo(state) {
            return state.userInfo;
          },
        getEstateInfo(state){
            return state.estateInfo;
        },
    },
    mutations: {
        LOGIN(state, payload) {
            const decode = jwt_decode(payload.accessToken);
            const info = decode.userInfo;
            state.accessEstate = info.isEstate;
            state.accessToken = payload.accessToken;
            localStorage.setItem("accessToken", state.accessToken);
            localStorage.setItem("accessEstate", state.accessEstate);
        },
        USERINFO(state, payload) {
            state.userInfo = payload;
        },
        ESTATEINFO(state, payload){
            state.estateInfo = payload;
        },
    },
    actions: {
        requestRegister(context, payload) {
            let body = payload

            http.post('/api/v1/users', body).then(() => {
              VueSimpleAlert.fire({
                title: "SUCCESS",
                text: "회원가입이 완료 되었습니다.👋",
                type: "success",
              })
                router.push('/login');
            }).catch((err) => {
              if(err.response.status==409){
                VueSimpleAlert.alert("중복된 아이디 입니다.😭");
              }
            });
        },
        requestLogin({ commit }, user) {
            http
                .post(`/api/v1/auth/login`, user)
                .then(({ data }) => {
                    commit("LOGIN", data);
                    VueSimpleAlert.fire({
                      title: "로그인 성공",
                      text: "로그인이 완료 되었습니다.🙌",
                      type: "success",
                    })
                    router.push('/search');
                })
                .catch((err) => {
                  
                  if(err.response.status==401){
                    VueSimpleAlert.fire({
                      title: "로그인 실패",
                      text: "아이디와 패드워드를 다시 확인해주세요.😭",
                      type: "error",
                    })
                  }else if(err.response.status==404){
                    VueSimpleAlert.fire({
                      title: "로그인 실패",
                      text: "회원정보가 없습니다.😭",
                      type: "error",
                    })
                  }else{
                    console.log(err.response)
                  }
                });
        },
        requestUserInfo({commit}){
            const CSRF_TOKEN=localStorage.getItem("accessToken");
            http
              .get(`/api/v1/users/me`,{headers: {"Authorization": 'Bearer '+ CSRF_TOKEN }
            })
            .then(({ data })=>{
                commit("USERINFO", data);
                console.log(data);
            })
            .catch((err) => {
              console.log(err.response);
            });
        },
        requestModify({commit}, user){
            console.log(user);
            http
              .patch(`/api/v1/users/`+user.userId,user)
              .then(({ data })=>{
                commit("USERINFO", data);
                VueSimpleAlert.fire({
                  title: "수정 성공",
                  text: "내용이 성공적으로 반영되었습니다.✍️",
                  type: "success",
                })
                window.location.reload();
              })
              .catch(() => {

              });
        },
        requestDuplicate({commit}, userId){
            http
              .get(`/api/v1/users/`+userId)
              .then((res) => {
                  VueSimpleAlert.fire({
                    title: "SUCCESS",
                    text: "사용가능한 아이디입니다.",
                    type: "success",
                  })
                  commit('user/USERID', true, {root: true});
                })
              .catch((error) => {
                if(error.response.data.statusCode==409){
                  commit('user/USERID', false, {root: true});
                  VueSimpleAlert.fire({
                    title: "FAIL",
                    text: "이미 존재하는 아이디입니다.",
                    type: "error",
                  })
                }
            });
        },
        requestEstate({commit}, estateNum){
            console.log(estateNum);
            http
            .get(`/api/v1/users/estate`,{ params: { registrationNumber: estateNum }})
              .then((res) => {
                commit("user/ESTATEINFO", res.data.estateInfo, {root: true});
                  VueSimpleAlert.fire({
                    title: "SUCCESS",
                    text: "사업자 번호가 확인되었습니다.",
                    type: "success",
                  })
                
                })
              .catch((error) => {
                if(error.response.data.statusCode==500){
                  commit("ESTATENUMBER", false);
                  VueSimpleAlert.fire({
                    title: "FAIL",
                    text: "사업자 번호가 존재하지 않습니다.",
                    type: "error",
                  })
                }
            });
        },
    },

}
