import { submitSupport } from "../../api/support.api.js";

document.addEventListener("DOMContentLoaded", () => {
  const params = new URLSearchParams(window.location.search);
  const attendanceId = params.get("attendanceId");

  if (!attendanceId) {
    alert("Không tìm thấy mã chấm công");
    return;
  }

  document.getElementById("attendance-id").innerText = attendanceId;

  document
    .getElementById("btn-submit")
    .addEventListener("click", () => handleSubmit(attendanceId));
});

async function handleSubmit(attendanceId) {
  const reason = document.getElementById("reason").value.trim();

  if (!reason) {
    alert("Vui lòng nhập lý do");
    return;
  }

  try {
    await submitSupport(attendanceId, reason);
    alert("Gửi yêu cầu thành công");
    window.location.href = "../staff/support_status.html";
  } catch (e) {
    console.error(e);
    alert("Gửi yêu cầu thất bại");
  }
}
