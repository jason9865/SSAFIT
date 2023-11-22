<template>
    <div container>

        <div class="row" >
            <div class="col-2" style="margin-left:30px;">
                <Sidebar/>
            </div>
            <div id="userInfo" class="card border-dark col-9" style="margin-top:100px;margin-bottom:200px;">
                <div class="card-header"><h3>마이페이지</h3></div>
                <div class="card-body text-dark">
                    <h5>회원 아이디 : {{ user.userId }}</h5>
                    <h5>회원 이름 : {{ user.userName }}</h5>
                    <h5>회원 닉네임 : {{ user.nickName }}</h5>
                    <h5>회원 이메일 : {{ user.email }}</h5>
                    <h5>최근 등록/수정날짜 : {{ user.joinedAt }}</h5>
                </div>
            </div>
    
        </div>
    </div>
</template>

<script setup>
import { computed,onMounted } from 'vue';
import {useUserStore} from '@/stores/user.js'
import VideoLikeList from '@/components/video/VideoLikeList.vue';
import ArticleLikeList from '@/components/article/ArticleLikeList.vue';
import MyArticleList from '@/components/article/MyArticleList.vue';
import Sidebar from '@/components/user/Sidebar.vue'

const store = useUserStore()
const user = computed(() => store.currentUser)
const userSeq = JSON.parse(sessionStorage.getItem("userSeq"))

onMounted(() => {
    store.getUser(userSeq)
})

</script>

<style scoped>

a {
    text-decoration-line: none;
}

h1,h2,h3,h4,h5,h6 {
    margin-left : 200px;
    font-weight:bold

}


</style>