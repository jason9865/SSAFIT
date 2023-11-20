<template>
    <div class="card">
        <h4>{{ videoComment.nickName }}</h4>
        <p>{{ videoComment.content }}</p>
        <p>{{ videoComment.createdAt }}</p>
    </div>
</template>

<script setup>
import {ref} from 'vue'
import { useVideoCommentStore } from '../../stores/videoComment';

const props = defineProps({
    videoComment : Object,
    videoId : String,
})

const currUserSeq = ref(JSON.parse(sessionStorage.getItem("userSeq")))
const modifyMode = ref(false)
const videoCommentStore = useVideoCommentStore()

</script>

<style scoped>

</style>