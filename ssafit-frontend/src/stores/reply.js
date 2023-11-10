import {ref, computed} from 'vue'
import {defineStore} from 'pinia'
import axios from 'axios'
import router from '@router'

const REST_API = 'http://localhost:8080/reply'

export const useReplyStore = defineStore('reply', () => {
    // 댓글 별 대댓글 조회
    const replyList = ref([])
    const getReplyList = function(commentId) {
        axios({
            url : `${REST_API}/${commentId}`,
            method : 'GET',
        })
        .then((res)=>
            replyList.value = res.data
        )
        .catch((err) => {
            console.log(err);
            alert("서버 오류")
        })
    }

    // 대댓글 1개 조회
    const reply = ref({})
    const getReply = function(replyId) {
        axios({
            url : `${REST_API}/detail/${replyId}`,
            method : 'GET'
        })
        .then((res) => {
            reply.value = res.data
        })
        .catch((err) => {
            console.log(err);
            alert("서버 오류")
        })
    }

    // 대댓글 등록
    const createReply = function(reply) {
        axios({
            url : `${REST_API}/${reply.commentId}`,
            method : `POST`,
            headers : {
                'Content-Type' : 'application/json'
            },
            data : reply
        })
        .then((res) => {
            res === true ?
            alert("대댓글 등록 완료") :
            alert("대댓글 등록 실패")
        })
        .catch((err) => {
            console.log(err)
            alert("서버 오류")
        })

    }

    // 대댓글 수정
    const updateReply = function(reply) {
        axios({
            url : `${REST_API}/${reply.replyId}`,
            method : "PUT",
            headers : {
                'Content-Type' : 'application/json'
              },
            data : reply
        })
        .then((res) =>{
            res === true ?
            alert("대댓글 수정 완료") :
            alert("대댓글 수정 실패")
        })
        .catch((err) => {
            console.log(err)
            alert("서버 에러")
        })
    }

    const deleteReply = function(replyId) {
        axios({
            url : `${REST_API}/${replyId}`,
            method : "DELETE",
        })
        .then((res)=>{
            res === true ?
            alert("대댓글 삭제 완료") :
            alert("대댓글 삭제 실패")
        })
        .catch((err) => {
            console.log(err);
            alert("서버 에러!")
        })
    }

    return {
        replyList, getReplyList,
        reply, getReply,
        createReply, updateReply,deleteReply
    }
})