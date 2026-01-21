import { getPendingSupports, handleSupport } from "../../api/support.api.js";
import { formatDate } from "../../layout/format.js";

document.addEventListener("DOMContentLoaded", () => {
  loadPendingSupports();
});

async function loadPendingSupports() {
  try {
    const res = await getPendingSupports();
    const list = res.result || [];

    const tbody = document.getElementById("support-table");
    tbody.innerHTML = "";

    if (list.length === 0) {
      tbody.innerHTML = `
        <tr>
          <td colspan="6" style="text-align:center">
            Không có yêu cầu chờ xử lý
          </td>
        </tr>
      `;
      return;
    }

    list.forEach((item, index) => {
      const tr = document.createElement("tr");

      tr.innerHTML = `
        <td>${index + 1}</td>
        <td>${item.attendanceId}</td>
        <td>${formatDate(item.time)}</td>
        <td>${item.reason}</td>
        <td class="status-processing">Chờ xử lý</td>
        <td>
          <button class="btn primary" onclick="approve('${item.id}')">
            Duyệt
          </button>
          <button class="btn danger" onclick="reject('${item.id}')">
            Từ chối
          </button>
        </td>
      `;

      tbody.appendChild(tr);
    });
  } catch (e) {
    console.error("Load pending supports error:", e);
  }
}

window.approve = async (id) => {
  if (!confirm("Xác nhận duyệt yêu cầu?")) return;

  await handleSupport(id, "APPROVED");
  alert("Đã duyệt");
  loadPendingSupports();
};

window.reject = async (id) => {
  if (!confirm("Xác nhận từ chối yêu cầu?")) return;

  await handleSupport(id, "REJECTED");
  alert("Đã từ chối");
  loadPendingSupports();
};
