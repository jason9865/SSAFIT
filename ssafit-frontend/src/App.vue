<template>
  <div style="background-color: rgb(170, 182, 250);">
    <HeaderNav />
    <hr style="margin: 0px;">
    <RouterView />
    <FooterNav class="footer" />
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
  // if (sessionStorage.getItem("loginUser")) {
  //   userStore.loginUser = JSON.parse(sessionStorage.getItem("loginUser"))
  // }
  // console.log(sessionStorage.getItem("access-token"))

  axios({
    url : `http://localhost:8080/user/userInfo`,
    method : "GET",
    headers : {
      "access-token" : sessionStorage.getItem("access-token"),
    }
  })
    .then((res) => {
      console.log(res.data)
      userStore.currentUser = res.data
      userStore.loginUser = res.data
    })
    .catch((err) => {
      console.log(err)
    })
});


</script>

<style scoped></style>