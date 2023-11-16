import {ref, computed} from 'vue'
import {defineStore} from 'pinia'
import axios from 'axios'
import { useRouter } from 'vue-router'
import { useBoardStore } from './board'

const REST_API = 'http://localhost:8080/article'

export const useArticleStore = defineStore('article', () => {
    const router = useRouter()
    const boardStore = useBoardStore()

    const article = ref({})
    const getArticle = function(articleId) {
        axios({
            url : `${REST_API}/${articleId}`,
            method : "GET"
        })
        .then((res) => {
            article.value = res.data
        })
        .catch((err) => {
            console.log(err)
            alert("서버 에러")
          })
    }

    const writeArticle = function(article) {
        console.log(article.boardId)
        axios({
            url : `${REST_API}/write`,
            method : "POST",
            headers : {
                'Content-Type' : `application/json`,
                'loginUser' : JSON.parse(sessionStorage.getItem('loginUser')),
                'userSeq' : sessionStorage.getItem("userSeq"),  
            },
            data : article
        })
        .then((res) =>{
            alert("게시글 등록 완료")
            boardStore.getArticleList(article.boardId)
            router.go(-1)
        })
        .catch((err) => {
            console.log(err)
            alert("서버 에러")
        })
    }

    const updateArticle = function(article) {
        axios({
            url : `${REST_API}/${article.articleId}/update`,
            method : "PUT",
            headers : {
                'Content-Type' : `application/json`,
                'userSeq' : sessionStorage.getItem("userSeq")
            },
            data : article
        })
        .then((res) =>{
            alert("게시글 수정 완료")
            boardStore.getArticleList(article.boardId)
            router.go(-1)
        })
        .catch((err) => {
            console.log(err)
            alert("서버 에러")
        })
    }

    const deleteArticle = function(articleId) {
        axios({
            url : `${REST_API}/${articleId}`,
            method : "DELETE"
        })
        .then((res) =>{
            alert("게시글 삭제 완료") 
        })
        .catch((err) => {
            console.log(err)
            alert("서버 에러")
        })
    }

    const likeCount = ref('')
    const getLikeCount = function(articleId){
        axios({
            url : `${REST_API}/${articleId}/like`,
            method : "GET"
        })
        .then((res) => {
            likeCount.value = res.data
        })
        .catch((err) => {
            console.log(err);
            alert("서버 에러")
        })
    }

    const doLikeArticle = function(articleLike) {
        axios({
            url : `${REST_API}/${articleLike.articleId}/like`,
            method : "POST",
            headers : {
                "Content-Type" : "application/json"
            },
            data : articleLike
        })
        .then((res) => {
            res == true?
            alert("좋아요 완료"):
            alert("좋아요 실패");
        })
        .catch((err)=> {
            console.log(err);
            alert("서버 에러")
        })
    }

    // const  undoLikeArticle = function(articleLikeId) {
    //     axios({
    //         url : 
    //     })
    // }

    return {
        article,
        getArticle,
        writeArticle,
        deleteArticle,
        doLikeArticle,
        getLikeCount,
        updateArticle,
    }
})