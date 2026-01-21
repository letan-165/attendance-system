import { getSupportByUser } from "../../api/support.api.js";

document.addEventListener("DOMContentLoaded", () => {
  loadSupportStatus();
});

async function loadSupportStatus() {
  try {
    const res = await getSupportByUser();

    const list = res.result || [];

    const tbody = document.getElementById("support-table");
    tbody.innerHTML = "";

    if (list.length === 0) {
      tbody.innerHTML = `
        <tr>
          <td colspan="4" style="text-align:center">
            Chưa có yêu cầu nào
          </td>
        </tr>
      `;
      return;
    }

    list.forEach((item) => {
      const tr = document.createElement("tr");

      tr.innerHTML = `
        <td>${item.attendanceId}</td>
        <td>${item.reason}</td>
        <td>${formatDate(item.time)}</td>
        <td class="${getStatusClass(item.status)}">
          ${getStatusText(item.status)}
        </td>
      `;

      tbody.appendChild(tr);
    });
  } catch (error) {
    console.error("Lỗi load trạng thái yêu cầu:", error);
  }
}

function formatDate(time) {
  if (!time) return "--";
  return new Date(time).toLocaleDateString("vi-VN");
}

function getStatusText(status) {
  switch (status) {
    case "PENDING":
      return "Đang xử lý";
    case "APPROVED":
      return "Đã chấp nhận";
    case "REJECTED":
      return "Từ chối";
    default:
      return status;
  }
}

function getStatusClass(status) {
  switch (status) {
    case "PENDING":
      return "status-processing";
    case "APPROVED":
      return "status-approved";
    case "REJECTED":
      return "status-rejected";
    default:
      return "";
  }
}
