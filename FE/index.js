import { login } from "./api/auth.api.js";

const btnStaff = document.getElementById("btn-staff");
const btnAdmin = document.getElementById("btn-admin");

const usernameInput = document.getElementById("username");
const passwordInput = document.getElementById("password");

async function handleLogin() {
  const data = {
    username: usernameInput.value,
    password: passwordInput.value,
  };

  localStorage.removeItem("token");

  try {
    const res = await login(data);

    if (!res?.result?.token) {
      alert("Đăng nhập thất bại");
      return;
    }
    const role = res.result.role;
    localStorage.setItem(
      "user",
      JSON.stringify({
        userID: res.result.userID,
        name: res.result.name,
        role: res.result.role,
      }),
    );

    if (role === "MANAGER") {
      window.location.href = "../manager/support.html";
    } else if (role === "STAFF") {
      window.location.href = "../staff/dashboard.html";
    } else {
      alert("Role không hợp lệ");
    }
  } catch (error) {
    console.error(error);
    alert("Lỗi hệ thống");
  }
}

btnStaff.onclick = handleLogin;
btnAdmin.onclick = handleLogin;
