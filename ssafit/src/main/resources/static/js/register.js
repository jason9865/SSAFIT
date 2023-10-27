function regist() {
    let id = document.querySelector("#id").value;
    let password = document.querySelector("#password").value;
    let email = document.querySelector("#email").value;
    let name = document.querySelector("#name").value;

    const user = {
        id: id,
        password: password,
        name: name,
        email: email,
    };

    alert("회원가입 되었습니다.");
}