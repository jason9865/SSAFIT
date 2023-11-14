<template>
  <div class="container">
    <h2>로그인</h2>
    <fieldset>
      <label for="id">아이디</label>
      <input class= "form-control view" type="text" id="id" v-model="id" /><br />
      <label for="password">비밀번호</label>
      <input
        type="password"
        id="password"
        v-model="password"
        class="form-control view"
      /><br />
      <button class="btn btn-outline-success" @click="login">로그인</button>
    </fieldset>
  </div>
</template>

<script setup>
import { ref } from "vue";
import { useUserStore } from '@/stores/user.js'

const id = ref("");
const password = ref("");

const store = useUserStore()

const login = () => {
  let user = {
    id: id.value,
    password: password.value,
  };
  store.login(user)
};
</script>
