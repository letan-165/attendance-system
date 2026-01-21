document.addEventListener("DOMContentLoaded", () => {
  loadHeader();
  loadSidebar("../layout/sidebar_staff.html", "sidebar-container");
  loadSidebar("../layout/sidebar_manager.html", "sidebar-container-admin");
});

function loadHeader() {
  fetch("../layout/header.html")
    .then((res) => res.text())
    .then((html) => {
      const header = document.getElementById("header-container");
      if (!header) return;

      header.innerHTML = html;

      const user = JSON.parse(localStorage.getItem("user"));
      const userNameEl = document.getElementById("user-name");

      if (userNameEl) {
        userNameEl.innerText = user?.name || "Guest";
      }
    });
}

function loadSidebar(sidebarPath, containerId) {
  fetch(sidebarPath)
    .then((res) => res.text())
    .then((html) => {
      const container = document.getElementById(containerId);
      if (!container) return;

      container.innerHTML = html;

      const links = container.querySelectorAll("a");
      const currentPage = window.location.pathname.split("/").pop();

      links.forEach((link) => {
        const href = link.getAttribute("href");
        if (href === currentPage) {
          link.classList.add("active");
        }
      });
    });
}
