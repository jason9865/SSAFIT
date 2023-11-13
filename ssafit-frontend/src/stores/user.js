import {ref, computed} from 'vue'
import {defineStore} from 'pinia'
import axios from 'axios'
import router from '@router'

const REST_API = 'http://localhost:8080/user'

export const useUserStore = defineStore('user', () => {

    const loginUser = ref(null);

    

    return {
        setLogin, setLogout
    }
})