const historyData = [
  {
    date: "01/01/2026",
    checkIn: "08:00",
    checkOut: "17:00",
  },
  {
    date: "02/01/2026",
    checkIn: "08:05",
    checkOut: "17:02",
  },
  {
    date: "03/01/2026",
    checkIn: "08:10",
    checkOut: "17:00",
  },
  {
    date: "04/01/2026",
    checkIn: "08:00",
    checkOut: "17:00",
  },
  {
    date: "05/01/2026",
    checkIn: "08:02",
    checkOut: "17:01",
  },
  {
    date: "06/01/2026",
    checkIn: "08:07",
    checkOut: "17:00",
  },
];

const tbody = document.getElementById("data-table");

historyData.forEach((item) => {
  const tr = document.createElement("tr");

  tr.innerHTML = `
    <td>${item.date}</td>
    <td>${item.checkIn}</td>
    <td>${item.checkOut}</td>
    <td>
      <button onclick="goToRequest()">Gửi yêu cầu</button>
    </td>
  `;

  tbody.appendChild(tr);
});
function goToRequest() {
  window.location.href = "../staff/request.html";
}
