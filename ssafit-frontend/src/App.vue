<template>
  <div>
    <HeaderNav />
    <RouterView />
    <FooterNav/>
  </div>
</template>

<script setup>
import FooterNav from './components/common/FooterNav.vue';
import HeaderNav from './components/common/HeaderNav.vue';
import { ref, onMounted } from "vue";
import { useRouter } from "vue-router";
import axios from "axios";
import { useBoardStore } from './stores/board';
import { useUserStore } from './stores/user';
import { storeToRefs } from 'pinia';

const router = useRouter();
const user = ref(null);

const boardStore = useBoardStore()

const userStore = useUserStore()

onMounted(() => {
  if(sessionStorage.getItem("loginUser")){
    userStore.loginUser = JSON.parse(sessionStorage.getItem("loginUser"))
  }

  // console.log("App onMounted")
  boardStore.getBoardList()
  axios({
    url : 'http://localhost:8080/board',
    method : "GET",
    })
    .then((res) => {
      localStorage.setItem("boardList", JSON.stringify(res.data))
    })
    .catch((err) => {
      console.log(err)
      alert("서버 에러")
    })
    
});






</script>

<style scoped>

</style>