import { request } from "./http.js";

export const submitSupport = (attendanceId, reason) =>
  request(`/support?attendanceId=${attendanceId}&reason=${reason}`, {
    method: "POST",
  });

export const getSupportByAttendance = (attendanceId) =>
  request(`/support/attendance/${attendanceId}`);

export const getSupportByUser = () => request(`/support/user`);

export const getPendingSupports = () => request("/support/pending");

export const handleSupport = (id, status) =>
  request(`/support/${id}?status=${status}`, {
    method: "PUT",
  });
