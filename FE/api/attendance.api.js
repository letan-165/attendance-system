import { request } from "./http.js";

export const checkIn = () =>
  request("/attendance/check-in", { method: "POST" });

export const checkOut = () =>
  request("/attendance/check-out", { method: "POST" });

export const getTodayAttendance = () =>
  request("/attendance/today");

export const getAttendanceHistory = () =>
  request("/attendance/history");
export const getAttendanceStatic = () =>
  request("/attendance/static");