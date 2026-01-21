import { request } from "./http.js";

export const getUsers = () =>
  request("/user");

export const updateUserStatus = (id, status) =>
  request(`/user/${id}/status?status=${status}`, {
    method: "PUT",
  });
