<template>

<div class="container">
      <div id="form-container">
        <div id="form-inner-container">
          <div id="sign-up-container">
            <h3 style="text-align: center;">회원 정보 수정</h3>
            <fieldset style="width:500px">
                <label for="id">아이디</label>
                <input readonly type="text" id="id" v-model="userDetail.userId" class="form-control"/><br />
                <label for="password">비밀번호</label>
                <input type="password" id="password" v-model="userDetail.userPwd" class="form-control" /><br />
                <label for="password">비밀번호 확인</label>
                <input type="password" id="password2" v-model="userDetail.userConfirmedPwd" class="form-control"/><br />
                <label for="name">이름</label>
                <input type="text" id="name" v-model="userDetail.userName" class="form-control" /><br />
                <label for="email">이메일</label>
                <input type="email" id="email" v-model="userDetail.email" class="form-control" /><br />
                <label for="nickName">닉네임</label>
                <input type="text" id="nickName" v-model="userDetail.nickName" class="form-control" /><br />
                <div id="form-controls" style="text-align: center;">
                  <button id="registBtn" class="btn btn-outline-success" @click="update">회원 정보 수정</button>
                </div>
            </fieldset>
          </div>
        </div>
      </div>
    </div>

</template>

<script setup>
import {computed} from 'vue';
import { useUserStore } from '../../stores/user';

const userStore = useUserStore()
const userDetail = userStore.loginUser // store에서 가져온 것 -> value필요없다
console.log("userDetail => ",userDetail)

const isEmailValid = (email) => {
  const re =
    /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
  return re.test(String(email).toLowerCase());
};

const isPasswordValid = (pwd) => {
  return pwd.length >= 8 && /[!@#$%^&*(),.?":{}|<>]/g.test(pwd);
};

const update = () => {
  if (
    userDetail.userId === "" ||
    userDetail.userName === "" ||
    userDetail.email === ""
  ) {
    alert("모든 내용을 입력해주세요");
    return;
  }

  if (userDetail.userPwd !== userDetail.userConfirmedPwd) {
    console.log(userDetail.userPwd, "<->",userDetail.userConfirmedPwd)
    alert("비밀번호가 일치하지 않습니다.");
    return;
  }

  if (!isPasswordValid(userDetail.userPwd)) {
    alert("비밀번호는 8자리 이상이며, 특수문자를 포함해야 합니다.");
    return;
  }

  if (!isEmailValid(userDetail.email)) {
    alert("올바른 이메일 형식을 입력해주세요.");
    return;
  }

  console.log(userDetail)
  userStore.updateUser(userDetail);
};


</script>

<style scoped>
body {
    background-color: #9b9ca0;
    font-family: Verdana, Geneva, Tahoma, sans-serif;
}

#form-container {
    display: flex;
    justify-content: center;
    align-items: center;
    margin:20px;
}

#form-inner-container {
    background-color: white;
    border-radius: 8px;
    box-shadow: 0 0 20px gainsboro;
}

#sign-up-container, #sign-in-container {
    padding: 15px 20px;
    display: inline-block;
}

fieldset input:not(:last-of-type) {
    display: block;
    margin-bottom: 20px;
    border: 1px solid #E5E9F5;
    background-color: #F6F7FA;
    padding: 20px;
    margin-top: 10px;
    border-radius: 10px;
}

#form-controls {
    margin-bottom: 20px;
}


h3 {
    font-size: 150%;
    font-weight: 500;
}

label {
    color: #7369AB;
}

::placeholder {
    color: #C0C7DB;
    font-size: larger;
    letter-spacing: 1.2px;
}

#form-controls button {
    border: none;
    font-size: 120%;
}

#form-controls button:hover {
    cursor: pointer;
}

button[id="registBtn"] {
    padding: 16px 75px;
    background-color: rgb(170, 182, 250);
    border-radius: 10px;
    color: white;
    border-color:rgb(170, 182, 250)
}

button[id="registBtn"]:hover {
    background-color: rgb(170, 100, 250);
}


#terms {
    width: 30px;
    height: 30px;
    appearance: none;
    border: 2px solid #7369AB;
    border-radius: 4px;
    position: relative;
}

#terms:checked:after {
    content: '\2713';
    color: #7369AB;
    font-size: 24px;
    position: absolute;
    top: 0;
    left: 3px;
}

label[for="terms"] {
    display: inline-block;
    width: 80%;
    margin-left: 10px;
}

.termsLink {
    color: #EF7886;
    text-decoration: none;
}

.hide {
    display: none!important;
}

#animation-container {
    display: inline-block;
}

/* responsive display */

@media(max-width:1438px) {
    lottie-player {
        width: 300px!important;
    }
}

@media(max-width:1124px) {
    #animation-container {
        display: none;
    }

    #form-inner-container{
        display: flex;
        justify-content: center;
    }
}

@media(max-width: 684px) {
    #form-controls {
        text-align: center;
        margin: 0;
        padding: 0;
    }

    button {
        width: 100%;
    }

    form input:not(:last-of-type) {
        width: 85%;
    }

    #toggleSignIn, #toggleSignUp {
        padding: 16px 75px;
    }

    #terms {
        width: 20px;
        height: 20px;
    }
    
    label[for="terms"] {
        display: inline-block;
        font-size: smaller;
    }
}

</style>