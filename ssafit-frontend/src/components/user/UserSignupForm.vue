<template>
    <div class="container">
        <h2>회원 가입</h2>
        <fieldset class="text-center">
            <label for="id">아이디</label>
            <input type="text" id="id" v-model="id" class="form-control view" /><br />
            <label for="password">비밀번호</label>
            <input type="password" id="password" v-model="password" class="form-control view" /><br />
            <label for="password">비밀번호 확인</label>
            <input type="password" id="password2" v-model="password2" class="form-control view"/><br />
            <label for="name">이름</label>
            <input type="text" id="name" v-model="name" class="form-control view" /><br />
            <label for="email">이메일</label>
            <input type="email" id="email" v-model="email" class="form-control view" /><br />
            <label for="nickName">닉네임</label>
            <input type="text" id="nickName" v-model="nickName" class="form-control view" /><br />
            <button class="btn btn-outline-success" @click="regist">등록</button>
        </fieldset>
    </div>
</template>

<script setup>
import { ref, computed, onMounted } from "vue";
import { useUserStore } from "../../stores/user";

const userStore = useUserStore()

const id = ref("");
const password = ref("");
const password2 = ref("");
const name = ref("");
const email = ref("");
const nickName = ref("");
const users = computed(() => userStore.users);

onMounted(() => {
    userStore.getUserList()
})

const isEmailValid = (email) => {
  const re =
    /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
  return re.test(String(email).toLowerCase());
};

const isPasswordValid = (pwd) => {
  return pwd.length >= 8 && /[!@#$%^&*(),.?":{}|<>]/g.test(pwd);
};

const regist = () => {
  if (
    id.value === "" ||
    password.value === "" ||
    name.value === "" ||
    email.value === ""
  ) {
    alert("모든 내용을 입력해주세요");
    return;
  }

  if (password.value !== password2.value) {
    alert("비밀번호가 일치하지 않습니다.");
    return;
  }

  if (!isPasswordValid(password.value)) {
    alert("비밀번호는 8자리 이상이며, 특수문자를 포함해야 합니다.");
    return;
  }

  if (Array.isArray(users.value) && users.value.length > 0) {
    const userExists = users.value.some((user) => user.id === id.value);
    if (userExists) {
      alert("이미 존재하는 아이디입니다.");
      return;
    }
  }

  if (!isEmailValid(email.value)) {
    alert("올바른 이메일 형식을 입력해주세요.");
    return;
  }

  const user = {
    userId: id.value,
    userPwd: password.value,
    userConfirmedPwd : password2.value,
    userName: name.value,
    nickName: nickName.value,
    email: email.value,
  };
  console.log(user)
  userStore.createUser(user);
};

</script>

<style scoped>

</style>