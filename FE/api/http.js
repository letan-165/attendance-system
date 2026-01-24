export const BASE_URL = "http://136.111.204.250/api";

export const request = async (url, options = {}) => {
  const token = localStorage.getItem("token");
  console.log("Token:", token);

  const res = await fetch(BASE_URL + url, {
    headers: {
      "Content-Type": "application/json",
      ...(token && { Authorization: `Bearer ${token}` }),
    },
    ...options,
  });

  const data = await res.json();
  console.log("Data:", data);
  return data;
};
