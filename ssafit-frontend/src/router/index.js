import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import LoginView from '../views/LoginView.vue'
import BoardView from '../views/BoardView.vue'
import VideoView from '../views/VideoView.vue'
import ArticleList from '../components/ArticleList.vue'
import ArticleCreate from '../components/ArticleCreate.vue'
import ArticleDetail from '../components/ArticleDetail.vue'
import ArticleModify from '../components/ArticleModify.vue'
import VideoList from '../components/VideoList.vue'
import VideoDetail from '../components/VideoDetail.vue'


const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
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
