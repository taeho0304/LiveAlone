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
        dongComiercial: [],
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
      
        },
        DONGCOUNT(state, payload) {
            state.dongcount = payload.counts;
         
        },
        DONGCOMMERCIAL(state, payload) {
            state.dongComiercial = payload.commercialCountModelList;
        }
    },
    actions: {
        requestRoomType({ commit }) {
            http
                .get(`/api/v1/search/roomtypes`)
                .then(({ data }) => {
                    commit("ROOMTYPE", data);
                   
                })
                .catch((err) => {
                    
                })
        },
        requestBargainType({ commit }) {
            http
                .get(`/api/v1/search/bargaintypes`)
                .then(({ data }) => {
                    commit("BARGAINTYPE", data);
                 
                }).catch((err) => {
                  
                })
        },
        requestDongCommercial({ commit }, data) {
         
            http
                .get(`/api/v1/residences/commercialcount/`, { params: { dongName: data } })
                .then(({ data }) => {
                   
                    commit("DONGCOMMERCIAL", data)
                })
                .catch((err) => {
                  
                })
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
        },
        getDongCommercial(state) {
            return state.dongComiercial;
        }
    }
}
