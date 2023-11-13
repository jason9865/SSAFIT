<template>
    <div>
        <RouterLink :to="`/board/annouce`">공지사항</RouterLink> | 
        <RouterLink :to="`/board/free`">자유게시판</RouterLink>
        <RouterView/>
    </div>
</template>

<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';

const router = useRouter()

const boardName = ref()

const announce = () => {
    boardName.value = "annouce"
}
</script>

<style lang="scss" scoped>

</style>