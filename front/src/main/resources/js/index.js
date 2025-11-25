// 다크모드 초기 상태 적용
function initTheme() {
    const saved = localStorage.getItem("theme");

    if (saved === "dark") {
        document.body.classList.add("dark");
        document.getElementById("theme-toggle").textContent = "☀️ Light";
    } else {
        document.body.classList.remove("dark");
        document.getElementById("theme-toggle").textContent = "🌙 Dark";
    }
}

// 다크모드 토글 이벤트
document.addEventListener("DOMContentLoaded", () => {
    initTheme();

    document.getElementById("theme-toggle").addEventListener("click", () => {
        document.body.classList.toggle("dark");
        const isDark = document.body.classList.contains("dark");

        if (isDark) {
            localStorage.setItem("theme", "dark");
            document.getElementById("theme-toggle").textContent = "☀️ Light";
        } else {
            localStorage.setItem("theme", "light");
            document.getElementById("theme-toggle").textContent = "🌙 Dark";
        }
    });

    loadPosts();
    document.getElementById("refresh-btn").addEventListener("click", loadPosts);
});

// 알림 띄우기
function showAlert(message, type = 'success') {
    const container = document.getElementById('alert-container');
    container.innerHTML = `
        <div class="alert alert-${type} alert-dismissible fade show" role="alert">
            ${message}
            <button class="btn-close" data-bs-dismiss="alert"></button>
        </div>
    `;
}

// HTML 특수문자 이스케이프
function escapeHtml(str) {
    if (!str) return "";
    return String(str)
        .replace(/&/g, "&amp;")
        .replace(/</g, "&lt;")
        .replace(/>/g, "&gt;")
        .replace(/"/g, "&quot;")
        .replace(/'/g, "&#39;");
}

// 글 목록 로드
async function loadPosts() {
    try {
        const res = await fetch(API_BASE);
        if (!res.ok) throw new Error();

        const posts = await res.json();
        renderPostList(posts);

    } catch (e) {
        console.error(e);
        showAlert("글 목록을 불러오는 중 오류가 발생했습니다.", "danger");
    }
}

// 목록 렌더링
function renderPostList(posts) {
    const tbody = document.getElementById("post-list");
    tbody.innerHTML = "";

    if (!posts || posts.length === 0) {
        tbody.innerHTML = `
            <tr><td colspan="5" class="text-center text-muted">등록된 글이 없습니다.</td></tr>
        `;
        return;
    }

    posts.forEach(post => {
        const postTime = post.postTime ? new Date(post.postTime).toLocaleString() : "-";

        const tr = document.createElement("tr");
        tr.innerHTML = `
            <td>${post.id}</td>
            <td>${escapeHtml(post.title)}</td>
            <td>${escapeHtml(post.author)}</td>
            <td>${postTime}</td>
            <td>${escapeHtml(post.category || "")}</td>
        `;
        tbody.appendChild(tr);
    });
}
