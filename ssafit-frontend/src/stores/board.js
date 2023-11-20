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
    const articleListLength = ref(null)

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

    const searchCondition = ref(null)

    const getArticlesBySearchInfo = function(searchInfo) {
      axios({
        url : `${REST_API}/${boardId.value}`,
        method : "GET",
        params : {
          "key" : searchInfo.key,
          "word" : searchInfo.word,
          "orderBy" : searchInfo.orderBy,
          "orderByDir" : searchInfo.orderByDir,
        }
    })
      .then((res) => {
        articleListLength.value = res.data.length
      })
      .catch((err) => {
        console.log(err)
      })
    }

    const getArticlesBySearchInfoWithPage = function(currentPage) {
      axios({
        url : `${REST_API}/${boardId.value}`,
        method : "GET",
        headers : {
          'currentPage' : currentPage,
        },
        params : {
          "key" : searchCondition.value.key,
          "word" : searchCondition.value.word,
          "orderBy" : searchCondition.value.orderBy,
          "orderByDir" : searchCondition.value.orderByDir,
        }
    })
      .then((res) => {
        articleList.value = res.data
      })
      .catch((err) => {
        console.log(err)
      })
    }

    return {
        board,
        boardList,
        getBoardList,
        articleList,
        getArticleList,
        articleListLength,
        getBoard,
        boardId,
        getArticlesByPage,
        searchCondition,
        getArticlesBySearchInfo,
        getArticlesBySearchInfoWithPage,
    }
})