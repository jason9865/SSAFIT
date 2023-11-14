<template>
    <div class="container">
        <h1>마이페이지</h1>
        <h3>회원 아이디 : {{ loginUser.userId }}</h3>
        <h3>회원 이름 : {{ loginUser.userName }}</h3>
        <h3>회원 닉네임 : {{ loginUser.nickName }}</h3>
        <h3>회원 이메일 : {{ loginUser.email }}</h3>
        <h3>최근 등록/수정날짜 : {{ loginUser.joinedAt }}</h3>
        <RouterLink to="/update">회원 정보 수정</RouterLink>
    </div>
</template>

<script setup>
import {useUserStore} from '@/stores/user.js'
// import UserUpdateForm from '../components/user/UserUpdateForm.vue';
const store = useUserStore()
const loginUser = store.loginUser


</script>

<style scoped>

</style>