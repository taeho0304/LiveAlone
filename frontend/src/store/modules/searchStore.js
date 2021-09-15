import jwt_decode from 'jwt-decode';
import http from '@/util/http-common';
import router from "@/router/router";


export default {
    namespaced: true,
    state: {
        roomType:[],
        bargainType:[],
    },
    mutations: {
        ROOMTYPE(state, payload){
            state.roomType=payload;
            localStorage.setItem("roomType", JSON.stringify(payload));
        },
        BARGAINTYPE(state, payload){
            state.bargainType=payload;
            localStorage.setItem("bargainType", JSON.stringify(payload));
        }
    },
    actions: {
        requestRoomType({commit}){
            http
            .get(`/api/v1/search/roomtypes`)
            .then(({data})=> {
                commit("ROOMTYPE", data);
                console.log(data);
            })
            .catch((err)=>{
                console.log(err);
            })
        },
        requestBargainType({commit}){
            http
            .get(`/api/v1/search/bargaintypes`)
            .then(({data})=>{
                commit("BARGAINTYPE", data);
                console.log(data);
                
            }).catch((err)=>{
                console.log(err);
            })
        }

    },
    getters: {
        getRoomType(state){
            return state.roomType;
        },
        getBargainType(state){
            return state.bargainType;
        }
    }
}
