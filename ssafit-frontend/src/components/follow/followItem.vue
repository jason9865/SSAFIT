<template>
    <div>
        <!-- Button trigger modal 누르면 모달창이 뜸 -->
        <button @click="seeUserInfo" type="button" class="btn btn-dark" data-bs-toggle="modal" data-bs-target="#exampleModal">
            팔로우 하기
        </button>

        <!-- Modal -->
        <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="exampleModalLabel">유저 상세 정보</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <p>닉네임 : {{ currentUser.nickName }}</p>
                    <p>이름 : {{ currentUser.userName }}</p>
                    <p>아이디 : {{ currentUser.userId }}</p>
                    <p>이메일 : {{ currentUser.email }}</p>
                    <p>회원가입날짜 : {{ currentUser.joinedAt }}</p>
                    <p>마이페이지 가기</p>
                </div>
                <div class="modal-footer">
                    <button v-show="isFollowing === 0" type="button" class="btn btn-outline-secondary" @click="doFollow(currentUser.userSeq)">팔로우</button>
                    <button v-show="isFollowing === 1" type="button" class="btn btn-secondary" @click="doUnfollow(currentUser.userSeq)">팔로우 취소</button>
                    <button type="button" class="btn btn-light" data-bs-dismiss="modal">닫기</button>
                </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import axios from 'axios';
import { storeToRefs } from 'pinia';
import {ref,computed,onMounted,onUpdated} from 'vue'
import {useFollowStore} from '../../stores/follow'
import {useUserStore} from '../../stores/user'

const props = defineProps({
    "userSeq" : Number
})


const followStore = useFollowStore();
const userStore = useUserStore();
const {currentUser} = storeToRefs(userStore);
const {isFollowing} = storeToRefs(followStore);

function seeUserInfo() {
    userStore.getUser(props.userSeq);
    followStore.checkFollow(props.userSeq);
    console.log(currentUser)
}

function doFollow(userSeq) {
    console.log("히히 팔로우 발사")
    followStore.doFollow(userSeq) 
}

function doUnfollow(userSeq) {
    console.log("함께해서 더러웠고 다신 만나지 말자")
    followStore.doUnfollow(userSeq)
}





</script>

<style lang="scss" scoped>

</style>