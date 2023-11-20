<template>
    <div class="container">
        <h3>팔로우 유저</h3>
        <div class="card border-dark mb-3" style="width: 18rem;"  v-for="user in followList">
            <div class="card-header"><b>{{ user.nickName }}</b></div>
            <div class="card-body text-dark">
                <h5 class="card-title">{{ user.userName }}</h5>
                <p class="card-text">{{ user.usedId }}</p>
                <p class="card-text">{{ user.email }}</p>
                <p class="card-text">{{ user.joinedAt }}</p>
                <button class="btn btn-outline-danger" @click="doUnfollow(user.userSeq)">팔로우 취소</button>
            </div>
        </div>
    </div>
</template>

<script setup>
import { onMounted } from 'vue';
import { useFollowStore } from '../../stores/follow';
import { storeToRefs } from 'pinia';

const followStore = useFollowStore()
const {followList} = storeToRefs(followStore)
const loginUserSeq = JSON.parse(sessionStorage.getItem("userSeq"))

function doUnfollow(userSeq) {
    if(confirm("팔로우를 취소하시겠습니까?") === true){
        console.log("함께해서 더러웠고 다신 만나지 말자")
        followStore.doUnfollow(userSeq)
    }
}


onMounted(() => {
    followStore.getFollowList(loginUserSeq)
})

</script>

<style scoped>

</style>