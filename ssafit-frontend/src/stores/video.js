import {ref, computed} from 'vue'
import {defineStore} from 'pinia'
import axios from 'axios'

const REST_API = 'http://localhost:8080/video'

export const useVideoStore = defineStore('video', () => {

    const video = ref({})
    const getVideo = function(videoId) {
        axios({
            url : `${REST_API}/${videoId}/detail`,
            method : "GET"
        })
        .then((res) => {
            video.value = res.data
        })
        .catch((err) => {
            console.log(err)
            alert("서버 에러")
          })
    }

    return {
        video,
        getVideo,
    }
})