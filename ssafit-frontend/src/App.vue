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

const router = useRouter();
const user = ref(null);

const boardStore = useBoardStore()


onMounted(() => {
  const savedUser = localStorage.getItem("loginUser");
  if (savedUser) {
    user.value = JSON.parse(savedUser);
  }

  console.log("App onMounted")
  boardStore.getBoardList()
  axios({
    url : 'http://localhost:8080/board',
    method : "GET",
    })
    .then((res) => {
      // console.log(JSON.stringify(res.data))
      localStorage.setItem("boardList", JSON.stringify(res.data))
    })
    .catch((err) => {
      console.log(err)
      alert("서버 에러")
    })
    
    console.log(JSON.parse(localStorage.getItem("boardList")))
});






</script>

<style scoped>

</style>