import { useEffect, useState } from "react";
import { fetchAllUsers, deleteUser, assignRoleToUser, removeUserFromRole, fetchAllRoles } from "../api/api.js";
import { useAuth } from "../context/AuthContext.jsx";

export default function Users() {
  const { user } = useAuth();
  const [users, setUsers]   = useState([]);
  const [roles, setRoles]   = useState([]);
  const [loading, setLoading] = useState(false);
  const [error,   setError]   = useState("");
  const [success, setSuccess] = useState("");

  const load = async () => {
    setLoading(true);
    setError("");
    try {
      const [usersData, rolesData] = await Promise.all([
        fetchAllUsers(user.token),
        fetchAllRoles(user.token),
      ]);
      setUsers(Array.isArray(usersData) ? usersData : []);
      setRoles(Array.isArray(rolesData) ? rolesData : []);
    } catch (err) {
      setError(
        err.response?.data?.message ||
        (typeof err.response?.data === "string" ? err.response.data : null) ||
        "Не вдалося завантажити користувачів"
      );
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => { load(); }, []);

  const handleDelete = async (email) => {
    if (!window.confirm(`Видалити користувача ${email}?`)) return;
    setError(""); setSuccess("");
    try {
      await deleteUser(email, user.token);
      setSuccess(`Користувача ${email} видалено`);
      setUsers((prev) => prev.filter((u) => u.email !== email));
    } catch (err) {
      setError(
        err.response?.data?.message ||
        (typeof err.response?.data === "string" ? err.response.data : null) ||
        "Помилка при видаленні"
      );
    }
  };

  const hasRole = (u, roleName) => u.roles?.some((r) => (r.name ?? r) === roleName);

  const handleToggleAdmin = async (u) => {
    setError(""); setSuccess("");
    const adminRole = roles.find((r) => r.name === "ROLE_ADMIN");
    if (!adminRole) return;
    try {
      if (hasRole(u, "ROLE_ADMIN")) {
        await removeUserFromRole(u.id, adminRole.id, user.token);
        setSuccess(`Роль адміна знята з ${u.email}`);
      } else {
        await assignRoleToUser(u.id, adminRole.id, user.token);
        setSuccess(`${u.email} тепер адмін`);
      }
      load();
    } catch (err) {
      setError(
        err.response?.data?.message ||
        (typeof err.response?.data === "string" ? err.response.data : null) ||
        "Помилка при зміні ролі"
      );
    }
  };

  return (
    <>
      <div className="page-header">
        <h1>Користувачі</h1>
      </div>

      {error   && <div className="alert alert-error">{error}</div>}
      {success && <div className="alert alert-success">{success}</div>}

      {loading ? (
        <div className="spinner-wrap"><div className="spinner" /></div>
      ) : users.length === 0 ? (
        <div className="empty">
          <div className="icon">👤</div>
          <p>Користувачів не знайдено</p>
        </div>
      ) : (
        <div className="card">
          <div className="table-wrap">
            <table>
              <thead>
                <tr>
                  <th>#</th>
                  <th>Ім'я</th>
                  <th>Прізвище</th>
                  <th>Email</th>
                  <th>Ролі</th>
                  <th style={{ minWidth: 220 }}>Дії</th>
                </tr>
              </thead>
              <tbody>
                {users.map((u, i) => {
                  const isAdmin = hasRole(u, "ROLE_ADMIN");
                  const isSelf  = u.email === user.email;
                  return (
                    <tr key={u.id}>
                      <td>{i + 1}</td>
                      <td>{u.firstName}</td>
                      <td>{u.lastName}</td>
                      <td>{u.email}</td>
                      <td>
                        {u.roles?.map((r) => {
                          const name = r.name ?? r;
                          return (
                            <span key={name} style={{
                              display: "inline-block",
                              padding: "2px 8px",
                              borderRadius: 12,
                              fontSize: ".75rem",
                              marginRight: 4,
                              background: name === "ROLE_ADMIN" ? "#fde8e8" : "#e8f5e9",
                              color:      name === "ROLE_ADMIN" ? "#c0392b" : "#2e7d32",
                              fontWeight: 600,
                            }}>
                              {name === "ROLE_ADMIN" ? "🔴 Admin" : "🟢 User"}
                            </span>
                          );
                        }) || "—"}
                      </td>
                      <td style={{ display: "flex", gap: ".4rem", flexWrap: "wrap" }}>
                        <button
                          className="btn btn-primary"
                          style={{ fontSize: ".78rem", padding: "4px 10px",
                            background: isAdmin ? "#e67e22" : undefined }}
                          onClick={() => handleToggleAdmin(u)}
                          disabled={isSelf && isAdmin}
                          title={isSelf && isAdmin ? "Не можна зняти адміна з себе" : ""}
                        >
                          {isAdmin ? "➖ Зняти адміна" : "➕ Зробити адміном"}
                        </button>
                        <button
                          className="btn btn-danger"
                          style={{ fontSize: ".78rem", padding: "4px 10px" }}
                          onClick={() => handleDelete(u.email)}
                          disabled={isSelf}
                          title={isSelf ? "Не можна видалити себе" : ""}
                        >
                          🗑 Видалити
                        </button>
                      </td>
                    </tr>
                  );
                })}
              </tbody>
            </table>
          </div>
        </div>
      )}
    </>
  );
}

