<template>
    <div class="container py-3">
        <div class="input-group mb-3">
            <span class="input-group-text"><i class="bi bi-youtube" style="color: red;"></i></span>
            <input type="text" class="form-control" aria-label="Amount (to the nearest dollar)"
                v-model="keyword" @keyup.enter="search">
            <span class="input-group-text" @click="search" style="cursor: pointer;">검색</span>
        </div>
    </div>
</template>

<script setup>
import { ref } from 'vue'
import { useYoutubeStore } from '@/stores/youtube'
const keyword = ref('')

const store = useYoutubeStore()

const search = function () {
    store.youtubeSearch(keyword.value + "운동")
}

</script>

<style  scoped></style>