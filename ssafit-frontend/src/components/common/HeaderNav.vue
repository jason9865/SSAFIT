<template>
    <header>
      <nav class="header-nav">
        <div>
          <RouterLink to="/" class="logo">SSAFIT </RouterLink>
        </div>
        <div>
          <a href="#" v-if="getUser" @click="logout">로그아웃</a>
          <RouterLink to="/login" v-else>로그인</RouterLink>
          <!-- <RouterLink :to="{ name: 'Regist' }">회원가입</RouterLink> -->
        </div>
      </nav>
    </header>
  </template>

<script setup>
import { computed } from "vue";
import { RouterLink } from "vue-router";

const props = defineProps(["user"]);
const emits = defineEmits(["logout"]);

const getUser = computed(() => !!props.user);

const logout = () => {
  emits("logout");
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