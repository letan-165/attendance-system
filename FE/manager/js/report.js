import { getAttendanceStatic } from "../../api/attendance.api.js";
import {
  formatScheduleTime,
  getStatusClass,
  getAttendanceStatusText,
} from "../../layout/format.js";

document.addEventListener("DOMContentLoaded", loadAttendanceStatic);

async function loadAttendanceStatic() {
  try {
    const res = await getAttendanceStatic();
    const data = res.result;

    /* ===== Cards ===== */
    document.getElementById("late-count").innerText = data.lateCount;
    document.getElementById("ontime-count").innerText = data.onTimeCount;
    document.getElementById("early-leave-count").innerText =
      data.earlyLeaveCount;
    document.getElementById("absent-count").innerText = data.absentCount;
    document.getElementById("total-working").innerText = data.totalWorking ?? 0;

    /* ===== Table ===== */
    const tbody = document.getElementById("attendance-table-body");
    tbody.innerHTML = "";

    data.attendances.forEach((a) => {
      const tr = document.createElement("tr");

      tr.innerHTML = `
        <td>${a.id}</td>
        <td>${a.userId}</td>
        <td>${a.workDate}</td>
        <td>${formatScheduleTime(a.checkInTime)}</td>
        <td>${formatScheduleTime(a.checkOutTime)}</td>
        <td>${a.total ?? 0}</td>
        <td class="status ${getStatusClass(a.status)}">${a.status.map(getAttendanceStatusText).join(", ")}</td>
      `;

      tbody.appendChild(tr);
    });
  } catch (e) {
    console.error("Load attendance static error:", e);
  }
}
