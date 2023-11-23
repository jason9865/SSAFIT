<template>
    <div class="form-control p-2" style="padding:5px;">
        <b>댓글 작성</b>
        <textarea class="form-control" v-model="comment.content"></textarea>
        <button title="Button fade green" class="button btnFade btnGreen" @click="writeComment">댓글 등록</button>
    </div>
</template>

<script setup>
import {ref} from 'vue'
import { useRouter } from 'vue-router';
import { useCommentStore } from '../../stores/comment';
import { jwtDecode } from 'jwt-decode';

const router = useRouter()

const props = defineProps({
    articleId:String
})

const commentStore = useCommentStore()

const aId = props.articleId

const comment = ref({
    articleId : aId,
    content : "",
})

// const token = jwtDecode(JSON.stringify(sessionStorage.getItem("access-token")))
const currUserSeq = JSON.parse(sessionStorage.getItem("userSeq"))

function writeComment() {
    if (!currUserSeq) {
        if (confirm("로그인이 필요한 서비스입니다. 로그인하시겠습니까?") === true) {
            router.push("/login")
        }
        return;
    }
    if(comment.value.content == ""){
        alert("내용을 입력해주세요.")
        return ;
    }
    if(confirm("댓글을 등록하시겠습니까?") === true){
        commentStore.createComment(comment.value);
        comment.value.content=""
    }
    else{
        return;
    }
}

</script>

<style scoped>
button.button {
width: 100px;
/* padding: 0; */
margin: 10px 10px 10px 0;
font-weight: 600;
text-align: center;
line-height: 50px;
color: #FFF;
border-radius: 5px;
transition: all 0.2s ;
text-decoration: none;
border:none
}

.btnGreen {
background: #219C90;
}

.btnFade.btnGreen:hover {
background: #557C55;
}

</style>