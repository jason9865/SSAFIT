<template>
    <div class="container">
        <h1>마이페이지</h1>
        <h3>회원 아이디 : {{ user.userId }}</h3>
        <h3>회원 이름 : {{ user.userName }}</h3>
        <h3>회원 닉네임 : {{ user.nickName }}</h3>
        <h3>회원 이메일 : {{ user.email }}</h3>
        <h3>최근 등록/수정날짜 : {{ user.joinedAt }}</h3>
        <RouterLink to="/update">회원 정보 수정</RouterLink>
        <br>
        <RouterLink to="/followList">팔로우 중인 유저 보기</RouterLink>
        <br>
        <VideoLikeList class="my-5 mx-3"/>
        <ArticleLikeList class="my-5 mx-3"/>
    </div>
</template>

<script setup>
import { computed, } from 'vue';
import {useUserStore} from '@/stores/user.js'
import VideoLikeList from '../video/VideoLikeList.vue';
import ArticleLikeList from '../article/ArticleLikeList.vue';
// import UserUpdateForm from '../components/user/UserUpdateForm.vue';

const store = useUserStore()
const user = computed(() => store.loginUser)

</script>

<style scoped>

</style>