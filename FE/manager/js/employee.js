import { getUsers, updateUserStatus } from "../../api/user.api.js";

/* ===== Init ===== */
document.addEventListener("DOMContentLoaded", () => {
  loadUsers();
});

/* ===== Load users ===== */
async function loadUsers() {
  try {
    const res = await getUsers();
    const list = res.result || [];

    const tbody = document.getElementById("user-table");
    tbody.innerHTML = "";

    if (list.length === 0) {
      tbody.innerHTML = `
        <tr>
          <td colspan="5" style="text-align:center">
            Không có nhân viên
          </td>
        </tr>
      `;
      return;
    }

    list.forEach((user) => {
      const tr = document.createElement("tr");

      tr.innerHTML = `
        <td>${user.id}</td>
        <td>${user.username}</td>
        <td>${user.fullName}</td>
        <td>${user.email}</td>
        <td>${formatRole(user.role)}</td>
        <td class="${getStatusClass(user.status)}">
          ${formatStatus(user.status)}
        </td>
        <td>
          <button
            class="btn primary"
            onclick="changeStatus('${user.id}', 'ACTIVE')"
            ${user.status === "ACTIVE" ? "disabled" : ""}
          >
            ACTIVE
          </button>

          <button
            class="btn danger"
            onclick="changeStatus('${user.id}', 'BLOCKED')"
            ${user.status === "BLOCKED" ? "disabled" : ""}
          >
            BLOCKED
          </button>
        </td>
      `;

      tbody.appendChild(tr);
    });
  } catch (e) {
    console.error("Load users error:", e);
  }
}

/* ===== Update status ===== */
window.changeStatus = async (id, newStatus) => {
  if (!confirm(`Bạn có chắc muốn chuyển trạng thái sang ${newStatus}?`)) return;

  try {
    await updateUserStatus(id, newStatus);
    alert("Cập nhật trạng thái thành công");
    loadUsers();
  } catch (e) {
    console.error(e);
    alert("Cập nhật trạng thái thất bại");
  }
};

/* ===== Helper ===== */

function formatStatus(status) {
  switch (status) {
    case "ACTIVE":
      return "Còn hoạt động";
    case "BLOCKED":
      return "Bị khóa";
    default:
      return status;
  }
}

function getStatusClass(status) {
  switch (status) {
    case "ACTIVE":
      return "status-approved";
    case "BLOCKED":
      return "status-rejected";
    default:
      return "";
  }
}

function formatRole(role) {
  switch (role) {
    case "ADMIN":
      return "Quản trị";
    case "STAFF":
      return "Nhân viên";
    case "MANAGER":
      return "Quản lý";
    default:
      return role;
  }
}
