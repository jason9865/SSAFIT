import { defineStore } from "pinia";

const REST_API = 'http://localhost:8080'

export const useFollowStore = defineStore('follow',()=>{
    
    const followList = ref([]);
    const isFollowed = ref('');
    
    const getFollowList = function(userSeq) {
        axios({
            url : `${REST_API}/getFollowList`,
            method : "GET",
            headers : {
                "userSeq" : userSeq
            }
        })
        .then((res) => {
            console.log("팔로우 중인 사람 목록",res.data)
            followList.value = res.data
        })
        .catch((err) => {
            console.log(err)
        })
    }

    const checkFollow = (toUserSeq) => {
        axios({
            url : `${REST_API}/checkFollowing/${toUserSeq}`,
            method : "GET",
            headers : {
                "userSeq" : sessionStorage.getItem("userSeq")
            }
        })
        .then((res) => {
            console.log("checkFollow",res.data)
            isFollowed.value = res.data
        })
        .catch((err) => {
            console.log(err)
        })
    }

    const doFollow = (toUserSeq) => {
        axios({
            url : `${REST_API}/follow/${toUserSeq}`,
            method : "GET",
            headers : {
                "userSeq" : sessionStorage.getItem("userSeq")
            }
        })
        .then((res) => {
            alert("팔로우 완료")
        })
        .catch((err) => {
            console.log(err)
            alert("팔로우 실패")
        })
    }

    const doUnfollow = (toUserSeq) => {
        axios({
            url : `${REST_API}/unfollow/${toUserSeq}`,
            method : "Delete",
            headers : {
                "userSeq" : sessionStorage.getItem("userSeq")
            }
        })
        .then((res) => {
            alert("언팔로우 완료")
        })
        .catch((err) => {
            console.log(err)
            alert("언팔로우 실패")
        })
    }

    return{
        followList,isFollowed,
        getFollowList,checkFollow,doFollow,doUnfollow
    }
})