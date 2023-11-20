import {ref, computed} from 'vue'
import {defineStore} from 'pinia'
import axios from 'axios'

const REST_API = 'http://localhost:8080/video'

export const useVideoStore = defineStore('video', () => {

    const video = ref({})
    const videoLikeList = ref([])
    const isVideoLike = ref('')

    const getVideo = function(videoId) {
        axios({
            url : `${REST_API}/${videoId}/detail`,
            method : "GET"
        })
        .then((res) => {
            video.value = res.data
        })
        .catch((err) => {
            console.log(err)
            alert("서버 에러")
          })
    }

    const getVideoLikeList = (videoId) => {
        axios({
            url : `${REST_API}/videoLikeList`,
            method : "GET",
            headers : {
                "userSeq":sessionStorage.getItem("userSeq")
            }
        })
        .then((res) => {
            console.log("getVideoLikeList",res.data)
            videoLikeList.value = res.data
        })
        .catch((err) => {
            console.log(err)
            alert("비디오 좋아요 리스트를 불러오는데 실패했습니다.")
        })
    }

    const checkVideoLike = (videoId) => {
        axios({
            url : `${REST_API}/${videoId}/isLikeVideo`,
            method : "GET",
            headers : {
                "userSeq":sessionStorage.getItem("userSeq")
            }
        })
        .then((res) => {
            console.log("checkVideoLike",res.data)
            isVideoLike.value = res.data
        })
        .catch((err) => {
            console.log(err)
            alert("서버 오류 발생")
        })
    }

    const doVideoLike = (videoId) => {
        axios({
            url : `${REST_API}/${videoId}/doVideoLike`,
            method : "GET",
            headers : {
                "userSeq":sessionStorage.getItem("userSeq")
            }
        })
        .then((res) => {
            console.log("doVideoLike",res.data)
            alert("찜하기 완료")
            checkVideoLike(videoId)
        })
    }

    const undoVideoLike = (videoId) => {
        axios({
            url : `${REST_API}/${videoId}/undoVideoLike`,
            method : "Delete",
            headers : {
                "userSeq":sessionStorage.getItem("userSeq")
            }
        })
        .then((res) => {
            console.log("undoVideoLike",res.data)
            alert("찜하기 취소 완료")
            checkVideoLike(videoId)
        })
    }

    return {
        video, videoLikeList, isVideoLike,
        getVideo, getVideoLikeList, checkVideoLike, doVideoLike, undoVideoLike
    }
})