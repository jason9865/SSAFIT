<template>
  <div>
    <HeaderNav :user="user" @logout="logout" />
    <RouterView @loing-user="loginUser"/>
    <FooterNav/>
  </div>
</template>

<script setup>
import FooterNav from './components/common/FooterNav.vue';
import HeaderNav from './components/common/HeaderNav.vue';
import { ref, onMounted } from "vue";
import { useRouter } from "vue-router";
import axios from "axios";

const router = useRouter();
const user = ref(null);

onMounted(() => {
  const savedUser = localStorage.getItem("loginUser");
  if (savedUser) {
    user.value = JSON.parse(savedUser);
  }
});

const logout = () => {
  user.value = null;
  localStorage.removeItem("loginUser");
};

const loginUser = (loginUser) => {
  const API_URL = `http://localhost:8080/user/login`;

  axios({
    url : `http://localhost:8080/user/login`,
    method : "POST",
    data : {
      userId: loginUser.id,
      userPwd : loginUser.password,
    }
  })
    .then((res) => {
      user.value = res.data
      if(user.value) {
        localStorage.setItem("loginUser", JSON.stringify(user))
        alert("로그인 성공");
        router.push("/");
      } else {
        alert("로그인 실패");
      }
    })
    .catch((err) => {
      console.log(err);
      alert("로그인 실패: 서버 에러");
    });
};


</script>

<style scoped>

</style>