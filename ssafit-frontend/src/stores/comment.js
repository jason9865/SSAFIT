import {ref, computed} from 'vue'
import {defineStore} from 'pinia'
import axios from 'axios'
import { useRouter } from 'vue-router'

const REST_API = 'http://localhost:8080/comment'

export const useCommentStore = defineStore('comment', () => {
    const router = useRouter()
    
    // 댓글 리스트 조회
    const commentList = ref([])

    const getCommentList = function(articleId) {
        axios({
            url : `${REST_API}/${articleId}`,
            method : "GET"
        })
        .then((res) =>{
            commentList.value = res.data
        }
        )
        .catch((err) =>{
            console.log(err)
            alert("서버 에러!")
        }
        )
    }

    // 개별 댓글 조회
    const comment = ref({});

    const getComment = function(commentId) {
        axios({
            url : `${REST_API}/detail/${commentId}`,
            method : "GET"
        })
        .then((res) => {
            comment.value = res.data
        })
        .catch((err) => {
            console.log(err);
            alert("서버 에러!")
        })
    }

    // 댓글 작성
    const createComment = function(comment) {
        axios({
            url : `${REST_API}/${comment.articleId}`,
            method : "POST",
            headers : {
                'Content-Type' : 'application/json',
                'userSeq' : sessionStorage.getItem("userSeq")
            },
            data : comment
        })
        .then((res) => {
           alert("댓글 등록 완료") 
           getCommentList(comment.articleId)
        })
        .catch((err) => {
            console.log(err);
            alert("댓글 등록에 실패하였습니다.")
        })
    }

    // 댓글 수정
    const updateComment = function(comment,articleId) {
        axios({
            url : `${REST_API}/${comment.commentId}`,
            method : 'PUT',
            headers : {
                'Content-Type' : `application/json`,
                'userSeq' : sessionStorage.getItem("userSeq")
            },
            data : comment
        })
        .then((res) => {
            // alert("댓글 수정 완료") 
            getCommentList(articleId)
        })
        .catch((err) => {
            console.log(err);
            alert("댓글 수정에 실패하였습니다.")
        })
    }

    // 댓글 삭제
    const deleteComment = function(commentId,articleId) {
        axios({
            url : `${REST_API}/${commentId}`,
            method : `DELETE`
        })
        .then((res)=>{
            // alert("댓글 삭제 완료")
            getCommentList(articleId)
        })
        .catch((err) => {
            console.log(err);
            alert("댓글 삭제에 실패하였습니다.")
        })
    }



    return {
        commentList, getCommentList,
        comment, getComment,
        createComment, updateComment, deleteComment
    }
    
    
})