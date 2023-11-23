<template>
    <div class="container">
      <div id="form-container">
        <div id="form-inner-container">
          <div id="sign-up-container">
            <h3>회원 가입</h3>
            <fieldset style="width:500px">
              <div>
                <label for="id">아이디</label>
                <input type="text" id="id" v-model="id" class="form-control" /><br />
                <button @click="checkId(id)">중복 확인</button><br>
              </div>
                <label for="password">비밀번호</label>
                <input type="password" id="password" v-model="password" class="form-control" /><br />
                <label for="password">비밀번호 확인</label>
                <input type="password" id="password2" v-model="password2" class="form-control"/><br />
                <label for="name">이름</label>
                <input type="text" id="name" v-model="name" class="form-control" /><br />
                <label for="email">이메일</label>
                <input type="email" id="email" v-model="email" class="form-control" /><br />
                <label for="nickName">닉네임</label>
                <input type="text" id="nickName" v-model="nickName" class="form-control" /><br />
                <div id="form-controls">
                  <button id="registBtn" class="btn btn-outline-success" @click="regist">등록</button>
                </div>
            </fieldset>
          </div>
        </div>
      </div>
    </div>
</template>

<script setup>
import { ref, computed, onMounted } from "vue";
import { useUserStore } from "../../stores/user";
import axios from 'axios'

const userStore = useUserStore()

const id = ref("");
const password = ref("");
const password2 = ref("");
const name = ref("");
const email = ref("");
const nickName = ref("");
const users = computed(() => userStore.users);
const isUserIdValid = ref("");
const isEmailUsable = ref("");
const isNickNameUsable = ref("");

const checkId = (userId) => {
      axios({
          url : `http://localhost:8080/user/validateId`,
          method : "POST",
          data : {
              userId : userId
          }
      })
      .then((res) => {
          console.log("checkId => ",res.data)
          isUserIdValid.value = res.data.isValid;
          alert(res.data.message)
      })
    }

const checkEmail = (email) => {
  axios({
      url : `http://localhost:8080/user/validateEmail`,
      method : "POST",
      data : {
          email : email
      }
  })
  .then((res) => {
      console.log("checkEmail => ", res.data)
      isEmailUsable.value = res.data.isValid;
  })
}

const checkNickName = (nickName) => {
    axios({
        url : `http://localhost:8080/user/validateNickName`,
        method : "POST",
        data : {
            nickName : nickName
        }
    })
    .then((res) => {
        isNickNameUsable.value = res.data.isValid;
    })
  }

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



  if (!isUserIdValid.value) {
    alert("이미 사용 중인 아이디입니다. 다른 아이디를 사용해주세요.")
    return;
  }

  checkEmail(email.value)
  if (!isEmailUsable.value){
    alert("이미 사용 중인 이메일입니다. 다른 이메일을 사용해주세요.")
    return;
  }

  checkNickName(nickName.value)
  if (!isNickNameUsable.value) {
    alert("이미 사용 중인 닉네임입니다. 다른 닉네임을 사용해주세요.")
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