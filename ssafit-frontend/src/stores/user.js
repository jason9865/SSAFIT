import {ref, computed} from 'vue'
import {defineStore} from 'pinia'
import axios from 'axios'
import {useRouter} from 'vue-router'


const REST_API = 'http://localhost:8080/user'

export const useUserStore = defineStore('user', () => {
    const router = useRouter()

    const userList = ref([])
    const loginUser = ref({})
    const currentUser = ref({})

    const getUserList = () => {
        axios({
            url : REST_API,
            method : "GET"
        })
        .then((res) => {
            console.log(res.data)
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
            // console.log(atob(response.data["access-token"].split('.')[1]))
            // console.log(response.data["loginUser"])

            sessionStorage.setItem('access-token', response.data["access-token"])
            sessionStorage.setItem('userSeq', response.data["loginUser"].userSeq)
            sessionStorage.setItem('loginUser', JSON.stringify(response.data["loginUser"]))
            // console.log(sessionStorage.getItem("userSeq"))
            // console.log(JSON.parse(sessionStorage.getItem("loginUser")))

            alert("로그인 성공!")
            // 일단은 유저 정보 전체 저장
            loginUser.value = response.data["loginUser"]
            router.push("/")
          })
          .catch((err) => {
            console.log(err);
            alert("아이디 혹은 비밀번호를 확인해주세요.");
          });
      };

      const logout = () => {
        sessionStorage.removeItem("loginUser")
        sessionStorage.removeItem("userSeq")
        sessionStorage.removeItem("access-token")
        loginUser.value = null;
        alert("로그아웃!")
        router.push("/")
      };
    

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
    }
})