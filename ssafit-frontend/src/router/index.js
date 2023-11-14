import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import LoginView from '../views/LoginView.vue'
import BoardView from '../views/BoardView.vue'
import VideoView from '../views/VideoView.vue'
import MypageView from '../views/MyPageView.vue'

import ArticleList from '../components/article/ArticleList.vue'
import ArticleCreate from '../components/article/ArticleCreate.vue'
import ArticleDetail from '../components/article/ArticleDetail.vue'
import ArticleModify from '../components/article/ArticleModify.vue'
import VideoList from '../components/video/VideoList.vue'
import VideoDetail from '../components/video/VideoDetail.vue'

import UserInfo from "../components/user/UserInfo.vue"
import UserSignupForm from "../components/user/UserSignupForm.vue"
import UserUpdateForm from "../components/user/UserUpdateForm.vue"

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView,
    },
    {
      path : "/signup",
      name : "Signup",
      component : UserSignupForm
    },
    {
      path: '/login',
      name: 'Login',
      component: LoginView
    },
    {
      path: '/board',
      name: 'Board',
      component: BoardView,
      children : [
        {
          path : "",
          name : "ArticleList-default",
          component : ArticleList,
        },
        {
          path : ":boardName",
          name : "ArticleList",
          component : ArticleList,
        },
        {
          path : ":boardName/create",
          name : "ArticleCreate",
          component : ArticleCreate,
        },
        {
          path : ":boardName/:articleId",
          name : "ArticleDetail",
          component : ArticleDetail,
        },
        {
          path : ":boardName/:articleId/modify",
          name : "ArticleModify",
          component : ArticleModify,
        },
      ]
    },
    {
      path : "/mypage",
      name : "Mypage",
      component : MypageView,
      children : [
        {
          path : "",
          name : "userName",
          component : UserInfo,
        },
        {
          path : "/update",
          name : "userUpdate",
          component : UserUpdateForm,
        }
        ,
      ]
    },
    
    {
      path: '/video',
      name: 'Video',
      component: VideoView,
      children : [
        {
          path : "",
          name : "VideoList",
          component : VideoList,
        },
        {
          path : ":videoId",
          name : "VideoDetail",
          component : VideoDetail,
        },
      ]
    },

  ]
})

export default router
