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
    const articleLikeCount = ref('')
    const isLiked = ref('');
    const articleDislikeCount = ref('')
    const isDisliked = ref('');
    const myArticleList = ref([])
    const articleLikeList = ref([])

    const getMyArticleList = function() {
        axios({
            url : `${REST_API}/userArticleList`,
            method : "GET",
            headers : {
                "userSeq" : sessionStorage.getItem("userSeq")
            },
        })
        .then((res)=>{
            console.log("getUserArticleList", res.data)
            myArticleList.value = res.data
        })
        .catch((err) => {
            console.log(err)
            alert("서버 에러")
        })
    }

    const getArticleLikeList = function() {
        axios({
            url : `${REST_API}/articleLikeList`,
            method : "GET",
            headers : {
                "userSeq":sessionStorage.getItem("userSeq")
            }
        })
        .then((res) => {
            // console.log("getArticleLikeList",res.data)
            articleLikeList.value = res.data
        })
        .catch((err) => {
            console.log(err)
            alert("서버 에러")
        })
    }

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
        console.log("article의 boardID가 뭘까",article.boardId)
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
            alert("게시글 등록 실패")
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

    const checkLiked = function(articleId) {
        axios({
            url : `${REST_API}/${articleId}/isLiked`,
            method : "GET",
            headers : {
                "userSeq" : sessionStorage.getItem("userSeq")
            }
        })
        .then((res) => {
            isLiked.value = res.data
        })
        .catch((err) => {
            console.log(err);
        })
    }

    const doArticleLike = function(articleId) {
        axios({
            url : `${REST_API}/${articleId}/like`,
            method : "POST",
            headers : {
                "Content-Type" : "application/json",
                "userSeq" : sessionStorage.getItem("userSeq")
            },
        })
        .then((res) => {
            // alert("좋아요 완료")
            getArticleLike(articleId)
            checkLiked(articleId)   
        })
        .catch((err)=> {
            console.log(err);
            alert("좋아요 실패")
        })
    }

    const undoArticleLike = function(articleId) {
        axios({
            url : `${REST_API}/${articleId}/like`,
            method : "DELETE",
            headers : {
                "Content-Type" : "application/json",
                "userSeq" : sessionStorage.getItem("userSeq")
            },
        })
        .then((res) => {
            // alert("좋아요 취소")
            getArticleLike(articleId)
            checkLiked(articleId)
        })
        .catch((err)=> {
            console.log(err);
            alert("좋아요 취소 실패")
        })
    }

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

    const checkDisliked = function(articleId) {
        axios({
            url : `${REST_API}/${articleId}/isDisliked`,
            method : "GET",
            headers : {
                "userSeq" : sessionStorage.getItem("userSeq")
            }
        })
        .then((res) => {
            isDisliked.value = res.data

        })
        .catch((err) => {
            console.log(err);
        })
    }

    const doArticleDislike = function(articleId) {
        axios({
            url : `${REST_API}/${articleId}/dislike`,
            method : "POST",
            headers : {
                "Content-Type" : "application/json",
                "userSeq" : sessionStorage.getItem("userSeq")
            }        
        })
        .then((res) => {
            // alert("싫어요 완료")
            getArticleDislike(articleId)
            checkDisliked(articleId)
        })
        .catch((err)=> {
            console.log(err);
            alert("싫어요 실패")
        })
    }

    const undoArticleDislike = function(articleId) {
        axios({
            url : `${REST_API}/${articleId}/dislike`,
            method : "DELETE",
            headers : {
                "Content-Type" : "application/json",
                "userSeq" : sessionStorage.getItem("userSeq")
            },
        })
        .then((res) => {
            // alert("싫어요 취소")
            getArticleDislike(articleId)
            checkDisliked(articleId)
        })
        .catch((err)=> {
            console.log(err);
            alert("싫어요 취소 실패")
        })
    }



    return {
        article,articleLikeCount, isLiked,articleDislikeCount,isDisliked,myArticleList,articleLikeList,
        getArticle,getMyArticleList,getArticleLikeList,
        writeArticle,updateArticle,deleteArticle,
        checkLiked,getArticleLike,doArticleLike, undoArticleLike,
        checkDisliked,getArticleDislike,doArticleDislike,undoArticleDislike,
    }
})