<template>
    <div class="container text-center">
        <RouterLink :to="{ name: 'free' }">자유게시판</RouterLink> | 
        <RouterLink :to="{ name: 'announce' }">공지사항</RouterLink> | 
    </div>
</template>

<script setup>
import { ref,computed,onMounted } from 'vue';
import { useRouter } from 'vue-router';
import { useBoardStore } from '@/stores/board.js'


</script>

<style scoped>

</style>