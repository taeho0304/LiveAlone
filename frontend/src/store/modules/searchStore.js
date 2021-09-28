import jwt_decode from 'jwt-decode';
import http from '@/util/http-common';
import router from "@/router/router";


export default {
    namespaced: true,
    state: {
        roomType: [],
        bargainType: [],
        gugunCount: [],
        dongcount: [],
    },
    mutations: {
        ROOMTYPE(state, payload) {
            state.roomType = payload;
            localStorage.setItem("roomType", JSON.stringify(payload));
        },
        BARGAINTYPE(state, payload) {
            state.bargainType = payload;
            localStorage.setItem("bargainType", JSON.stringify(payload));
        },
        GUGUNCOUNT(state, payload) {
            state.gugunCount = payload.counts;
            console.log(state.gugunCount)
        },
        DONGCOUNT(state, payload) {
            state.dongcount = payload.counts;
            console.log(state.dongcount)
        }
    },
    actions: {
        requestRoomType({ commit }) {
            http
                .get(`/api/v1/search/roomtypes`)
                .then(({ data }) => {
                    commit("ROOMTYPE", data);
                    // console.log(data);
                })
                .catch((err) => {
                    // console.log(err);
                })
        },
        requestBargainType({ commit }) {
            http
                .get(`/api/v1/search/bargaintypes`)
                .then(({ data }) => {
                    commit("BARGAINTYPE", data);
                    // console.log(data);
                }).catch((err) => {
                    // console.log(err);
                })
        }
        , requestGugunCount({ commit }) {
            http.get("/api/v1/residences/gucount").then((res) => {
                commit("GUGUNCOUNT", res.data);
            });
        },
        requestDongCount({ commit }) {
            http.get("/api/v1/residences/dongcount").then((res) => {
                commit("DONGCOUNT", res.data);
            });
        },
    },
    getters: {
        getdongCount(state) {
            return state.dongcount;
        },
        getgugunCount(state) {
            return state.gugunCount;
        },
        getRoomType(state) {
            return state.roomType;
        },
        getBargainType(state) {
            return state.bargainType;
        }
    }
}
