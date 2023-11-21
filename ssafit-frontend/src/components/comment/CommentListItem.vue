<template>
    <div class="card p-2">
        <div v-show="!modifyMode">
            <div class="d-flex; justify-content-space;">
                <!-- <p style="font-size: large; margin: 0;">{{ comment.nickName }}</p> -->
                <FollowItem :user-seq="comment.userSeq" :nick-name="comment.nickName" style="cursor:pointer">
                  {{ comment.nickName }}
                </FollowItem>
            </div>
            <p style="font-size: large; margin: 0; padding: 5px;">{{ comment.content  }}</p>
            <p style="font-size: small; margin: 0; padding: 5px;">{{ comment.createdAt }}</p>

            <div v-show="currUserSeq === comment.userSeq" style="padding:5px">
                <button title="Button fade blue/green" class="button btnFade btnBlueGreen" @click="modifyModeChange">수정</button>
                <button title="Button fade orange" class="button btnFade btnOrange" @click="deleteComment(comment.commentId)">삭제</button>
            </div>
        </div>
        <div v-show="modifyMode" style="padding:5px;">
            <textarea class="form-control" v-model="comment.content"></textarea>
            <button title="Button fade lightblue" class="button btnFade btnLightBlue"  @click="modifyComment(comment.commentId,comment.content)">수정</button>    
            <button title="Button fade orange" class="button btnFade btnOrange" @click="modifyModeChange">취소</button>
        </div>
    </div>
</template>

<script setup>
import {ref} from 'vue'
import {useCommentStore} from '../../stores/comment'
import FollowItem from '@/components/follow/FollowItem.vue';

const props = defineProps({
    comment:Object,
    articleId : String
})

const currUserSeq = ref(JSON.parse(sessionStorage.getItem("userSeq")))
const modifyMode = ref(false)
const commentStore = useCommentStore()

function modifyModeChange() {
    modifyMode.value = !modifyMode.value
}

function modifyComment(commentId,content) {
    let updatedComment = {
        commentId : commentId,
        content : content
    }
    if(confirm("댓글을 수정하시겠습니까?") === true){
        commentStore.updateComment(updatedComment,props.articleId)
        modifyModeChange()
    }
    else{
        return;
    }
}

function deleteComment(commentId) {
    if (confirm("댓글을 삭제하시겠습니까") === true){
        commentStore.deleteComment(commentId,props.articleId)
        alert("댓글 삭제 완료")
    }
    else{
        return;
    }
  }

</script>

<style scoped>

button.button {

  width: 50px;
  /* padding: 0; */
  margin: 5px 5px 5px 0;
  font-weight: 600;
  text-align: center;
  /* line-height: 50px; */
  color: #FFF;
  border-radius: 5px;
  transition: all 0.2s ;
  text-decoration: none;
  border:none
}

.btnBlueGreen {
  background: #00AE68;
}

.btnLightBlue {
  background: #5DC8CD;
}

.btnOrange {
  background: #FFAA40;
}

.btnPurple {
  background: #A74982;
}

/* FADE */
.btnFade.btnBlueGreen:hover {
  background: #21825B;
}

.btnFade.btnLightBlue:hover {
  background: #01939A;
}

.btnFade.btnOrange:hover {
  background: #FF8E00;
}

.btnFade.btnPurple:hover {
  background: #6D184B;
}


</style>