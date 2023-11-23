import {ref, computed} from 'vue'
import {defineStore} from 'pinia'
import axios from 'axios'
import {useRouter, useRoute} from 'vue-router'


const REST_API = 'http://localhost:8080/user'

export const useUserStore = defineStore('user', () => {
    const router = useRouter()
    const route = useRoute()
    const prevPage = ref(null)

    const userList = ref([])
    const loginUser = ref({})
    const currentUser = ref({})

    const getUserList = () => {
        axios({
            url : REST_API,
            method : "GET"
        })
        .then((res) => {
            // console.log(res.data)
            userList.value = res.data
        })
        .catch((err) => {
            console.log(err)
        })
    }

    const getUser = (userSeq) => {
        axios({
            url : `${REST_API}/${userSeq}`,
            method : "GET"
        })
        .then((res) => {
            currentUser.value = res.data
        })
        .catch((err) => {
            console.log(err)
        })
    }

    const createUser = (user) => {
        axios({
            url : `${REST_API}/signup`,
            method : "POST",
            headers : {
                "Content-Type" : "application/json"
            },
            data : user
        })
        .then((res) => {
            res.data === true ? 
            alert("회원 가입 완료!") :
            alert("회원 가입 실패")

            router.push("/")
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
                "Content-Type" : "application/json",
                
            },
            data : user
        })
        .then((res) => {
            console.log(res.data)
            res.data === true ? 
            alert("회원 정보 수정 완료!") :
            alert("회원 정보 수정 실패")
            router.push("/mypage")
        })
        .catch((err) => {
            console.log(err);
            alert("서버 에러");
        })

    }
    
    const deleteUser = (userSeq) => {
        axios({
            url : `${API_URL}/${userSeq}`,
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

    const login = (user) => {
        const API_URL = `http://localhost:8080/user/login`;
        axios({
          url : `http://localhost:8080/user/login`,
          method : "POST",
          data : {
            userId: user.id,
            userPwd : user.password,
          }
        })
          .then((response) => {
            if(response.data["access-token"]) {
                sessionStorage.setItem('access-token', response.data["access-token"])
                sessionStorage.setItem("userSeq",response.data["userSeq"])
                getUser(response.data["userSeq"])
                alert("로그인 성공!")
                router.go(-1)
            } else {
                alert("아이디 혹은 비밀번호를 확인해주세요.");
            }
          })
          .catch((err) => {
            console.log(err);
          });
      };

      const logout = () => {
        sessionStorage.removeItem("access-token")
        sessionStorage.removeItem("userSeq")
        loginUser.value = null;
        alert("로그아웃!")
        window.location.replace("/")
      };
    
      const sendMail = (userIdEmail) => {
        console.log(userIdEmail.value.userId)
        console.log(userIdEmail.value.email)
        axios({
            url : `http://localhost:8080/user/sendMail`,
            method : "POST",
            data : userIdEmail.value
        })
          .then((res) => {
            console.log(res.data)
            alert(res.data)
            window.location.replace("/login")
          })
      }

      

    return {
        userList,
        loginUser,
        currentUser,
        getUserList,
        getUser,
        updateUser,
        createUser,
        deleteUser,
        login,
        logout,
        sendMail,
        prevPage,
    }
})