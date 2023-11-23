<template>
    <div>
        <h4> 댓글 {{ videoCommentList.length }} 개</h4>
        <div v-for="videoComment in videoCommentList" :key="videoComment.videoCommentId">
            <VideoCommentListItem :video-comment="videoComment" :video-id="videoId"/>
        </div>
        <VideoCommentWrite :video-id="videoId"/>
    </div>
</template>

<script setup>
import {ref, onMounted} from 'vue'
import VideoCommentListItem from './VideoCommentListItem.vue';
import VideoCommentWrite from './VideoCommentWrite.vue';
import { useVideoCommentStore } from '../../stores/videoComment';
import { storeToRefs } from 'pinia';

const videoCommentStore = useVideoCommentStore()
const {videoCommentList} = storeToRefs(videoCommentStore)

const props = defineProps({
    videoId : String
})

onMounted(() => {
    videoCommentStore.getVideoCommentList(props.videoId)
})

</script>

<style scoped>

</style>