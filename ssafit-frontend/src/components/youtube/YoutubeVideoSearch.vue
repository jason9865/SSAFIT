<template>
    <div class="container">
        <span class="text-center"><i class="bi bi-youtube" style="color: red;"></i>유튜브 영상</span>
        <input type="text" v-model="keyword" @keyup.enter="search">
        <button @click="search">검색</button>
    </div>
</template>

<script setup>
import {ref} from 'vue'
import {useYoutubeStore} from '@/stores/youtube'
const keyword = ref('')

const store = useYoutubeStore()

const search = function() {
    store.youtubeSearch(keyword.value + "운동")
}

</script>

<style  scoped>

</style>