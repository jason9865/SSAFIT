function login() {
    let id = document.querySelector("input[type='text']").value;
    let password = document.querySelector("input[type='password']").value;

    const user = {
        id: "sosu5",
        password: "soyoungsua5",
        name: "정소영",
        email: "sosu5@gmail.com",
    };

    if (id === user.id && password === user.password) {
        alert(`로그인 성공! 환영합니다, ${user.name}님`);
        window.location.href = "main.html";

    } else {
        alert("아이디나 비밀번호를 확인해주세요.");
    }
}
