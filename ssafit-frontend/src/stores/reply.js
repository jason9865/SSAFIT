import {ref, computed} from 'vue'
import {defineStore} from 'pinia'
import axios from 'axios'
import router from '@router'

const REST_API = 'http://localhost:8080/reply'

export const useReplyStore = defineStore('reply', () => {
    // 댓글 별 대댓글 조회
    const ReplyList = ref('')

    // const getReplyList = 


    return {
        
    }
})