import {ref} from 'vue'
import axios from 'axios'
import {defineStore} from 'pinia'


const REST_API = 'http://localhost:8080/videoComment'

export const useVideoCommentStore = defineStore("videoComment", () => {

    const videoComment = ref({})
    const videoCommentList = ref([])

    const getVideoComment = function(videoCommentId) {
        axios({
            url : `${REST_API}/${videoCommentId}/detail`,
            method : "GET"
        })
        .then((res) => {
            console.log("getVideoComment",res.data)
            videoComment.value = res.data
        })
        .catch((err) => {
            console.log(err)
            alert("비디오 댓글 불러오기 실패")
        })
    }

    const getVideoCommentList = function(videoId) {
        axios({
            url : `${REST_API}/${videoId}`,
            method : "GET"
        })
        .then((res) => {
            videoCommentList.value = res.data
        })
        .catch((err) => {
            console.log(err)
            alert("비디오 댓글 리스트 불러오기 실패")
        })
    }

    const writeVideoComment = function(videoComment) {
        axios({
            url : `${REST_API}/${videoComment.videoId}/write`,
            method : "POST",
            headers : {
                "Content-Type" : "application/json",
                "userSeq" : sessionStorage.getItem("userSeq"),
            },
            data : videoComment,
        })
        .then((res) => {
            alert("비디오 댓글 등록 완료")
            getVideoCommentList(videoComment.videoId)
        })
        .catch((err) => {
            console.log(err)
            alert("비디오 댓글 등록 실패")
        })
    }

    const updateVideoComment = function(videoComment){
        axios({
            url : `${REST_API}/${videoComment.videoCommentId}/update`,
            method : "PUT",
            headers : {
                "Content-Type" : "application/json",
                "userSeq" : sessionStorage.getItem("userSeq"),
            },
            data : videoComment
        })
        .then((res) => {
            alert("비디오 댓글 수정 완료")
            getVideoCommentList(videoComment.videoId)
        })
        .catch((err) => {
            console.log(err)
            alert("비디오 댓글 수정 실패")
        })
    }

    const deleteVideoComment = function(videoCommentId, videoId) {
        axios({
            url : `${REST_API}/${videoCommentId}/delete`,
            method : "DELETE"
        })
        .then((res) => {
            alert("비디오 댓글 삭제 완료")
            getVideoCommentList(videoId)
        })
        .catch((err) => {
            console.log(err)
            alert("비디오 댓글 삭제 실패")
        })
    }
    return{
        videoComment, videoCommentList,
        getVideoComment, getVideoCommentList, writeVideoComment, updateVideoComment, deleteVideoComment,
    }

})
