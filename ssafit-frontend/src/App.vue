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
  if (sessionStorage.getItem("loginUser")) {
    userStore.loginUser = JSON.parse(sessionStorage.getItem("loginUser"))
  }

});


</script>

<style scoped></style>