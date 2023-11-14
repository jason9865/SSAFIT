<template>
    <header>
      <nav class="header-nav">
        <div>
          <RouterLink to="/" class="logo">SSAFIT </RouterLink>
        </div>
        <div>
          <RouterLink to="/video">운동영상</RouterLink>
          <RouterLink to="/board">커뮤니티</RouterLink>
          <RouterLink to="/login" v-if="!getUser">로그인</RouterLink>
          <RouterLink to="/signup" v-if="!getUser">회원가입</RouterLink>
          <span v-else>
            <!-- <span> {{ d}} 님 환영합니다.</span> -->
            <a href="#" class="mx-3" @click="logout">로그아웃</a>
            <RouterLink to="/mypage">마이페이지</RouterLink>
          </span>
        </div>
      </nav>
    </header>
  </template>

<script setup>
import { storeToRefs } from "pinia";
import { ref,computed } from "vue";
import { RouterLink } from "vue-router";
import { useUserStore } from "@/stores/user";

const props = defineProps(["user"]);
const emits = defineEmits(["logout"]);

const store = useUserStore()
const getUser = computed(() => store.loginUser);

// const userName = ref(JSON.parse(localStorage.getItem("loginUser"))?.data.userName)

const logout = () => {
  store.logout()
};
</script>

<style scoped>
header {
  height: 70px;
  background-color: rgb(90, 223, 247);
  line-height: 70px;
  padding: 0px 30px;
}

header a {
  margin: 10px;
  text-decoration: none;
  color: white;
}

.header-nav {
  display: flex;
  justify-content: space-between;
}

.logo {
  display: inline-block;
  font-size: 2rem;
  font-weight: bold;
  color: white;
  margin: 0;
}
</style>