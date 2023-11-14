<template>
      <div class="container">
    <h2>회원 가입</h2>
    <fieldset class="text-center">
      <label for="id">아이디</label>
      <input type="text" id="id" v-model="id" class="view" /><br />
      <label for="password">비밀번호</label>
      <input type="password" id="password" v-model="password" class="view"/> <br />
      <label for="password">비밀번호 확인</label>
      <input type="password" id="password2" v-model="password2" class="view"/><br />
      <label for="name">이름</label>
      <input type="text" id="name" v-model="name" class="view" /><br />
      <label for="email">이메일</label>
      <input type="email" id="email" v-model="email" class="view" /><br />
      <label for="age">나이</label>
      <input type="number" id="age" v-model="age" class="view" /><br />
      <button class="btn" @click="regist">등록</button>
    </fieldset>
  </div>
</template>

<script setup>
import { ref, computed, onMounted } from "vue";
import { useUserStore } from "@/stores/userStore";

const userStore = useUserStore();

const id = ref("");
const password = ref("");
const password2 = ref("");
const name = ref("");
const email = ref("");
const age = ref(0);
const user = computed(() => userStore.users);

</script>

<style scoped>

</style>