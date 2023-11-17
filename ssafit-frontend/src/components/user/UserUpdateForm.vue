<template>
    <div class="container">
        <h2 class="text-center">회원 정보 수정</h2>
        <fieldset class="text-center">
            <label for="id">아이디</label>
            <input readonly type="text" id="id" v-model="userDetail.userId" class="form-control view"/><br />
            <label for="password">비밀번호</label>
            <input type="password" id="password" v-model="userDetail.userPwd" class="form-control view" /><br />
            <label for="password">비밀번호 확인</label>
            <input type="password" id="password2" v-model="userDetail.userConfirmedPwd" class="form-control view"/><br />
            <label for="name">이름</label>
            <input type="text" id="name" v-model="userDetail.userName" class="form-control view" /><br />
            <label for="email">이메일</label>
            <input type="email" id="email" v-model="userDetail.email" class="form-control view" /><br />
            <label for="nickName">닉네임</label>
            <input type="text" id="nickName" v-model="userDetail.nickName" class="form-control view" /><br />
            <button class="btn btn-outline-success" @click="update">회원 정보 수정</button>
        </fieldset>
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

</style>