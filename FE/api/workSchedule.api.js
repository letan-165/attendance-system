import { request } from "./http.js";

export const createWorkSchedule = (startTime, endTime) =>
  request(
    `/work-schedule?startTime=${startTime}&endTime=${endTime}`,
    { method: "POST" }
  );

export const getWorkScheduleHistory = () =>
  request("/work-schedule/history");
