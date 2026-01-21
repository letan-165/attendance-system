import { request } from "./http.js";

export const login = async (data) => {
  const res = await request("/auth/public/login", {
    method: "POST",
    body: JSON.stringify(data),
  });

  if (res?.result?.token) localStorage.setItem("token", res.result.token);

  return res;
};
