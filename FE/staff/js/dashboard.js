import {
  checkIn,
  checkOut,
  getTodayAttendance,
} from "../../api/attendance.api.js";

import { getWorkScheduleHistory } from "../../api/workSchedule.api.js";
import {
  formatScheduleTime,
  getAttendanceStatusText,
} from "../../layout/format.js";

/* ===== Init ===== */

document.addEventListener("DOMContentLoaded", () => {
  loadTodayAttendance();
  loadWorkSchedule();

  document.getElementById("btn-checkin").onclick = handleCheckIn;
  document.getElementById("btn-checkout").onclick = handleCheckOut;
});

/* ===== Attendance Today ===== */

async function loadTodayAttendance() {
  try {
    const res = await getTodayAttendance();
    const data = res.result;

    if (!data) return;

    document.getElementById("check-in").innerText = formatScheduleTime(
      data.checkInTime,
    );

    document.getElementById("check-out").innerText = formatScheduleTime(
      data.checkOutTime,
    );

    document.getElementById("attendance-status").innerText =
      data.status?.map(getAttendanceStatusText).join(", ") || "--";

    document.getElementById("attendance-time").innerText =
      data.total + " Phút" || "Chưa check-out";

    document.getElementById("server-name").innerText =
      res.serverName || "backend";

    document.getElementById("server-status").innerText = "Ổn định";

    toggleButtons(data);
  } catch (e) {
    console.error("Load today attendance error:", e);
  }
}

/* ===== Work Schedule ===== */

async function loadWorkSchedule() {
  try {
    const res = await getWorkScheduleHistory();
    const list = res.result || [];

    if (list.length === 0) {
      document.getElementById("start-time").innerText = "--";
      document.getElementById("end-time").innerText = "--";
      return;
    }

    // bản ghi mới nhất
    const latest = list[0];

    document.getElementById("start-time").innerText = formatScheduleTime(
      latest.startTime,
    );

    document.getElementById("end-time").innerText = formatScheduleTime(
      latest.endTime,
    );
  } catch (e) {
    console.error("Load work schedule error:", e);
  }
}

/* ===== Actions ===== */

async function handleCheckIn() {
  try {
    await checkIn();
    loadTodayAttendance();
  } catch {
    alert("Check-in thất bại");
  }
}

async function handleCheckOut() {
  try {
    await checkOut();
    loadTodayAttendance();
  } catch {
    alert("Check-out thất bại");
  }
}

/* ===== UI Logic ===== */

function toggleButtons(data) {
  const btnCheckIn = document.getElementById("btn-checkin");
  const btnCheckOut = document.getElementById("btn-checkout");

  btnCheckIn.disabled = !!data.checkInTime;
  btnCheckOut.disabled = !data.checkInTime || !!data.checkOutTime;
}
