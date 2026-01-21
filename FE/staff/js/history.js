import { getAttendanceHistory } from "../../api/attendance.api.js";
import {
  formatScheduleTime,
  getAttendanceStatusText,
  getStatusClass,
} from "../../layout/format.js";

document.addEventListener("DOMContentLoaded", () => {
  loadHistory();
});

async function loadHistory() {
  try {
    const res = await getAttendanceHistory();
    const list = res.result || [];

    const tbody = document.getElementById("data-table");
    tbody.innerHTML = "";

    if (list.length === 0) {
      tbody.innerHTML = `
        <tr>
          <td colspan="5" style="text-align:center">
            Không có dữ liệu
          </td>
        </tr>
      `;
      return;
    }

    list.forEach((item) => {
      const tr = document.createElement("tr");

      tr.innerHTML = `
        <td>${item.id}</td>
        <td>${item.workDate}</td>
        <td>${formatScheduleTime(item.checkInTime)}</td>
        <td>${formatScheduleTime(item.checkOutTime)}</td>
        <td>
          <span class="status ${getStatusClass(item.status)}">
            ${item.status?.map(getAttendanceStatusText).join(", ") || "--"}
          </span>
        </td>
        <td>
      <button
        class="btn secondary"
        onclick="window.location.href='../staff/request.html?attendanceId=${item.id}'"
      >
        Gửi
      </button>
</td>

      `;

      tbody.appendChild(tr);
    });
  } catch (e) {
    console.error("Load history error:", e);
  }
}
