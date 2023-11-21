<template>
    <div class="form-control p-2" style="padding:5px;">
        <b>댓글 작성</b>
        <textarea class="form-control" v-model="comment.content"></textarea>
        <button title="Button fade green" class="button btnFade btnGreen" @click="writeComment">댓글 등록</button>
    </div>
</template>

<script setup>
import {ref} from 'vue'
import { useCommentStore } from '../../stores/comment';

const props = defineProps({
    articleId:String
})

const commentStore = useCommentStore()

const aId = props.articleId

const comment = ref({
    articleId : aId,
    content : "",
})

function writeComment() {
    console.log("WriteComment!")
    console.log("Comment의 articleId => ",comment.value.articleId)
    console.log("Comment - content => ", comment.value.content)
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