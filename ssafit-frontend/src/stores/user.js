import {ref, computed} from 'vue'
import {defineStore} from 'pinia'
import axios from 'axios'

const REST_API = 'http://localhost:8080/user'

export const useUserStore = defineStore('user', () => {
    const loginUser = ref(null)

    const createUser = (user) => {
        axios({
            url : `${REST_API}/signup`,
            method : "POST",
            headers : {
                "Content-Type" : "application/json"
            },
            data : {
                userId : user.id,
                userPwd : user.pwd,
                userConfirmedPwd : user.confirmedPwd,
                userName : user.name,
                nickName : user.nickName,
                email : user.email,
            }
        })
        .then((res) => {
            res.data === true ? 
            alert("회원 가입 완료!") :
            alert("회원 가입 실패")
        })
        .catch((err) => {
            console.log(err);
            alert("서버 에러");
        })
    }

    const updateUser = (user) => {
        axios({
            url : `${REST_API}/update/${user.userSeq}`,
            method : "PUT",
            headers : {
                "Content-Type" : "application/json"
            },
            data : {
                userPwd : user.pwd,
                userConfirmedPwd : user.confirmedPwd,
                userName : user.name,
                nickName : user.nickName,
                email : user.email,
            }
        })
        .then((res) => {
            res.data === true ? 
            alert("회원 정보 수정 완료!") :
            alert("회원 정보 수정 실패")
        })
        .catch((err) => {
            console.log(err);
            alert("서버 에러");
        })

        const deleteUser = (userSeq) => {
            axios({
                url : `${API_URL}/${$userSeq}`,
                method : "DELETE"
            })
            .then((res) => {
                res.data === true ? 
                alert("회원 삭제 완료!") :
                alert("회원 삭제 실패")
            })
            .catch((err) => {
                console.log(err);
                alert("서버 에러");
            })
        }


    }


    

    return {
        signup, updateUser, createUser, deleteUser
    }
})