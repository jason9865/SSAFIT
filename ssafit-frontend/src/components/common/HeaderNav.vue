<template>
  <div>
    <header>
      <div class="row d-flex justify-content-between">
        <nav class="header-nav">
          <RouterLink to="/" class="logo" style="font-size: 3rem;">SSAFIT </RouterLink>
          <div style="font-size: x-large;">
            <RouterLink to="/video">운동영상</RouterLink>&nbsp;
            <RouterLink to="/board/free" @click="loadBoardList">커뮤니티</RouterLink>&nbsp;
            <RouterLink to="/map">헬스장 검색</RouterLink>
          </div>
          <div>
            <RouterLink to="/login" v-if="!loginUser.userName">로그인</RouterLink>
            <RouterLink to="/signup" v-if="!loginUser.userName">회원가입</RouterLink>
            <span v-else>
              <span style="font-weight:bold;"> {{ loginUser.userName }} 님</span>
              <a href="#" class="mx-3" @click="logout">로그아웃</a>
              <RouterLink to="/mypage">마이페이지</RouterLink>
            </span>
          </div>
        </nav>
      </div>
    </header>
  </div>
</template>

<script setup>
import { storeToRefs } from "pinia";
import { ref, computed } from "vue";
import { RouterLink } from "vue-router";
import { useUserStore } from "@/stores/user";
import { useBoardStore } from "@/stores/board";

const props = defineProps(["savedUser"]);
const emits = defineEmits(["logout"]);

const store = useUserStore()
const boardstore = useBoardStore()
const loginUser = computed(() => {
  return store.loginUser
})

// const userName = ref(JSON.parse(localStorage.getItem("loginUser"))?.data.userName)

const logout = () => {
  store.logout()
};

const loadBoardList = () => {
  boardstore.getBoardList()
}



</script>

<style scoped>
header {
  height: 70px;
  background-color: rgb(170, 182, 250);
  line-height: 70px;
  padding: 0px 30px;
}

header a {
  font-weight:bold;
  margin: 10px;
  text-decoration: none;
  color: white;
}

.header-nav {
  display: flex;
  justify-content: space-between;
  color: white;
}

.logo {
  display: inline-block;
  font-size: 2rem;
  font-weight: bold;
  color: white;
  margin: 0;
}
</style>