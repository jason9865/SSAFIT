import {ref, computed} from 'vue'
import {defineStore} from 'pinia'
import axios from 'axios'

const REST_API = 'http://localhost:8080/board'

export const useBoardStore = defineStore('board', () => {

    const board = ref(null)
    const boardId = ref(null)

    const getBoard = (boardId) => {
      axios({
        url : `${REST_API}/${boardId}/detail`,
        method : "GET",
      })
      .then((res) => {
        board.value = res.data;
      })
      .catch((err) => {
        console.log(err)
      })
    }

    const boardList = ref([])

    const getBoardList = () => {
        axios({
            url : REST_API,
            method : "GET",
        })
          .then((res) => {
            boardList.value = res.data
          })
          .catch((err) => {
            console.log(err)
            alert("서버 에러")
          })
    }

    const articleList = ref([])

    const getArticleList = (boardId) => {
        axios({
            url : `${REST_API}/${boardId}`,
            method : "GET",
        })
          .then((res) => {
            articleList.value = res.data
          })
          .catch((err) => {
            console.log(err)
          })

    }

    const getArticlesByPage = function(currentPage, boardId) {
      axios({
          url : `${REST_API}/${boardId}`,
          method : "GET", 
          headers : {
              'currentPage' : currentPage,
          }
      })
        .then((res) => {
          articleList.value = res.data
        })
        .catch((err) => {
          console.log(err)
        })
    }

    // const searchCondition = ref(null)

    const getArticlesBySearchInfo = function(searchCondition) {
      console.log(boardId.value)
      console.log(searchCondition)
      axios({
        url : `${REST_API}/${boardId.value}`,
        method : "GET",
        headers : {
          "Content-Type" : "application/json",
        },
        params : {
          "key" : searchCondition.key,
          "word" : searchCondition.word,
          "orderBy" : searchCondition.orderBy,
          "orderByDir" : searchCondition.orderByDir,
        }
    })
      .then((res) => {
        articleList.value = res.data
      })
      .catch((err) => {
        console.log(err)
      })
    }

    const getArticlesBySearchInfoWithPage = function() {

    }

    return {
        board,
        boardList,
        getBoardList,
        articleList,
        getArticleList,
        getBoard,
        boardId,
        getArticlesByPage,
        // searchCondition,
        getArticlesBySearchInfo,
        getArticlesBySearchInfoWithPage,
    }
})