import {ref, computed} from 'vue'
import {defineStore} from 'pinia'
import axios from 'axios'
import router from '@router'

const REST_API = 'http://localhost:8080/comment'

export const useCommentStore = defineStore('comment', () => {
    
    // 댓글 리스트 조회
    const commentList = ref([])

    const getCommentList = function(articleId) {
        axios({
            url : `${REST_API}/${articleId}`,
            method : "GET"
        })
        .then((res) =>
        commentList.value = res.data
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
            header : {
                'Content-Type' : 'application/json'
            },
            data : comment
        })
        .then((res) => {
            router.push({name : ArticleDetail})
        })
        .catch((err) => {
            console.log(err);
            alert("서버 에러!")
        })
    }



    return {
        commentList, getCommentList,
        comment, getComment,
        createComment
    }
    
    }
})