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
            url : `${REST_API}/${articleId}/delete`,
            method : "DELETE"
        })
        .then((res) =>{
            alert("게시글 삭제 완료")
            boardStore.getArticleList(article.boardId)
            router.go(-1)
        })
        .catch((err) => {
            console.log(err)
            alert("서버 에러")
        })
    }

    const articleLikeCount = ref('')
    const getArticleLike = function(articleId){
        axios({
            url : `${REST_API}/${articleId}/like`,
            method : "GET"
        })
        .then((res) => {
            articleLikeCount.value = res.data
        })
        .catch((err) => {
            console.log(err);
            alert("서버 에러")
        })
    }

    const isLiked = ref('');
    const checkLiked = function(articleId) {
        axios({
            url : `${REST_API}/${articleId}/isLiked`,
            method : "GET",
            headers : {
                "userSeq" : sessionStorage.getItem("userSeq")
            }
        })
        .then((res) => {
            console.log("isLiked => ", res.data)
            isLiked.value = res.data
        })
        .catch((err) => {
            console.log(err);
        })
    }

    const doArticleLike = function(articleLike) {
        axios({
            url : `${REST_API}/${articleLike.articleId}/like`,
            method : "POST",
            headers : {
                "Content-Type" : "application/json",
                "userSeq" : sessionStorage.getItem("userSeq")
            },
            data : articleLike
        })
        .then((res) => {
            alert("좋아요 완료")
        })
        .catch((err)=> {
            console.log(err);
            alert("서버 에러")
        })
    }

    const articleDislikeCount = ref('')
    const getArticleDislike = function(articleId){
        axios({
            url : `${REST_API}/${articleId}/dislike`,
            method : "GET"
        })
        .then((res) => {
            articleDislikeCount.value = res.data
        })
        .catch((err) => {
            console.log(err);
            alert("서버 에러")
        })
    }

    const isDisliked = ref('');
    const checkDisliked = function(articleId) {
        axios({
            url : `${REST_API}/${articleId}/isDisliked`,
            method : "GET",
            headers : {
                "userSeq" : sessionStorage.getItem("userSeq")
            }
        })
        .then((res) => {
            console.log("isDisliked => ", res.data)
            isDisliked.value = res.data
        })
        .catch((err) => {
            console.log(err);
        })
    }

    const doArticleDislike = function(articleDislike) {
        axios({
            url : `${REST_API}/${articleDislike.articleId}/like`,
            method : "POST",
            headers : {
                "Content-Type" : "application/json",
                "userSeq" : sessionStorage.getItem("userSeq")
            },
            data : articleLike
        })
        .then((res) => {
            alert("좋아요 완료")
        })
        .catch((err)=> {
            console.log(err);
            alert("서버 에러")
        })
    }



    return {
        article,
        getArticle,
        writeArticle,
        updateArticle,
        deleteArticle,
        articleLikeCount,
        isLiked,checkLiked,
        getArticleLike,
        doArticleLike,
        articleDislikeCount,
        isDisliked, checkDisliked,
        getArticleDislike,
        doArticleDislike
    }
})