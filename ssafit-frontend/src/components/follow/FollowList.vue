<template>
    <div class="container justify-content-center p-3">
        <h3>팔로우 유저 목록</h3>
        <h4>팔로우 중인 유저 수 : {{ followList.length }}명</h4>
        <div class="row">
            <div class="card border-dark mb-3 col-md-3 m-5 p-1"  v-for="user in followList">
                <div class="card-header"><b>{{ user.nickName }}</b></div>
                <div class="card-body text-dark">
                    <h5 class="card-title">{{ user.userName }}</h5>
                    <p class="card-text">{{ user.usedId }}</p>
                    <p class="card-text">{{ user.email }}</p>
                    <p class="card-text">{{ user.joinedAt }}</p>
                    <button class="btn" @click="doUnfollow(user.userSeq)" style="background-color:#cb99c5; color:white; padding:5px; font-weight: bold;">팔로우 취소</button>
                </div>
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