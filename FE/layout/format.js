export function formatScheduleTime(time) {
  if (!time) return "--";

  const [hour, minute] = time.split(":").map(Number);
  const isPM = hour >= 12;
  const hour12 = hour % 12 || 12;

  return `${String(hour12).padStart(2, "0")}:${String(minute).padStart(
    2,
    "0",
  )} ${isPM ? "CH" : "SA"}`;
}

export function getAttendanceStatusText(status) {
  switch (status) {
    case "ON_TIME":
      return "Đi đúng giờ";
    case "LATE":
      return "Đi muộn";
    case "EARLY_LEAVE":
      return "Về sớm";
    case "ABSENT":
      return "Vắng mặt";
    default:
      return status;
  }
}

export function formatDate(time) {
  return new Date(time).toLocaleDateString("vi-VN");
}
export function getStatusClass(statusArr) {
  if (!statusArr || statusArr.length === 0) return "";

  if (statusArr.includes("ON_TIME")) return "status-approved";
  if (statusArr.includes("LATE")) return "status-rejected";

  return "";
}