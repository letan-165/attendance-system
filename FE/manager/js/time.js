import {
  getWorkScheduleHistory,
  createWorkSchedule,
} from "../../api/workSchedule.api.js";
import { formatScheduleTime, formatDate } from "../../layout/format.js";

/* ===== Init ===== */

document.addEventListener("DOMContentLoaded", () => {
  loadWorkSchedule();
});

/* ===== Load table ===== */

async function loadWorkSchedule() {
  try {
    const res = await getWorkScheduleHistory();
    const list = res.result || [];

    const tbody = document.getElementById("work-table");
    tbody.innerHTML = "";

    if (list.length === 0) {
      tbody.innerHTML = `
        <tr>
          <td colspan="3" style="text-align:center">
            Không có dữ liệu
          </td>
        </tr>
      `;
      return;
    }

    list.forEach((item) => {
      const tr = document.createElement("tr");

      tr.innerHTML = `
        <td>${formatDate(item.createdAt)}</td>
        <td>${formatScheduleTime(item.startTime)}</td>
        <td>${formatScheduleTime(item.endTime)}</td>
      `;

      tbody.appendChild(tr);
    });
  } catch (e) {
    console.error("Load work schedule error:", e);
  }
}

/* ===== Dialog ===== */

window.openDialog = () => {
  const dialog = document.getElementById("work-dialog");
  if (dialog) dialog.style.display = "flex";
};

window.closeDialog = () => {
  const dialog = document.getElementById("work-dialog");
  if (dialog) dialog.style.display = "none";
};

/* ===== Submit ===== */

window.submitWorkSchedule = async () => {
  const startTime = document.getElementById("startTime").value;
  const endTime = document.getElementById("endTime").value;

  if (!startTime || !endTime) {
    alert("Vui lòng nhập đầy đủ thời gian");
    return;
  }

  try {
    await createWorkSchedule(startTime, endTime);
    alert("Lưu thành công");
    closeDialog();
    loadWorkSchedule();
  } catch (e) {
    console.error(e);
    alert("Lưu thất bại");
  }
};
