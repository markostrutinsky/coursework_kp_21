import { useEffect, useState } from "react";
import { fetchAllRoles, createRole, deleteRole } from "../api/api.js";
import { useAuth } from "../context/AuthContext.jsx";

export default function Roles() {
  const { user } = useAuth();
  const [roles, setRoles] = useState([]);
  const [newRoleName, setNewRoleName] = useState("");
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");
  const [success, setSuccess] = useState("");

  const load = async () => {
    setLoading(true);
    setError("");
    try {
      const data = await fetchAllRoles(user.token);
      setRoles(Array.isArray(data) ? data : []);
    } catch (err) {
      setError(
        err.response?.data?.message ||
        (typeof err.response?.data === "string" ? err.response.data : null) ||
        "Не вдалося завантажити ролі"
      );
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => { load(); }, []);

  const handleCreate = async (e) => {
    e.preventDefault();
    if (!newRoleName.trim()) return;
    setError("");
    try {
      await createRole(newRoleName.trim().toUpperCase().startsWith("ROLE_")
        ? newRoleName.trim()
        : `ROLE_${newRoleName.trim().toUpperCase()}`, user.token);
      setSuccess("Роль створено");
      setNewRoleName("");
      load();
    } catch (err) {
      setError(
        err.response?.data?.message ||
        (typeof err.response?.data === "string" ? err.response.data : null) ||
        "Помилка при створенні ролі"
      );
    }
  };

  const handleDelete = async (roleId, roleName) => {
    if (!window.confirm(`Видалити роль ${roleName}?`)) return;
    setError("");
    try {
      await deleteRole(roleId, user.token);
      setSuccess(`Роль ${roleName} видалено`);
      setRoles((prev) => prev.filter((r) => r.id !== roleId));
    } catch (err) {
      setError(
        err.response?.data?.message ||
        (typeof err.response?.data === "string" ? err.response.data : null) ||
        "Помилка при видаленні ролі"
      );
    }
  };

  const PROTECTED = ["ROLE_USER", "ROLE_ADMIN"];

  return (
    <>
      <div className="page-header">
        <h1>Ролі</h1>
      </div>

      {error   && <div className="alert alert-error">{error}</div>}
      {success && <div className="alert alert-success">{success}</div>}

      <div className="card" style={{ marginBottom: "1.5rem" }}>
        <h3 style={{ marginBottom: "1rem" }}>Додати нову роль</h3>
        <form onSubmit={handleCreate} style={{ display: "flex", gap: ".75rem" }}>
          <input
            value={newRoleName}
            onChange={(e) => setNewRoleName(e.target.value)}
            placeholder="Назва ролі (напр. MANAGER)"
            style={{ flex: 1 }}
          />
          <button className="btn btn-primary" type="submit">
            Додати
          </button>
        </form>
      </div>

      {loading ? (
        <div className="spinner-wrap"><div className="spinner" /></div>
      ) : roles.length === 0 ? (
        <div className="empty">
          <div className="icon">🔐</div>
          <p>Ролей не знайдено</p>
        </div>
      ) : (
        <div className="card">
          <div className="table-wrap">
            <table>
              <thead>
                <tr>
                  <th>#</th>
                  <th>ID</th>
                  <th>Назва ролі</th>
                  <th>Дія</th>
                </tr>
              </thead>
              <tbody>
                {roles.map((r, i) => (
                  <tr key={r.id}>
                    <td>{i + 1}</td>
                    <td>{r.id}</td>
                    <td><code>{r.name}</code></td>
                    <td>
                      <button
                        className="btn btn-danger"
                        onClick={() => handleDelete(r.id, r.name)}
                        disabled={PROTECTED.includes(r.name)}
                        title={PROTECTED.includes(r.name) ? "Системну роль не можна видалити" : ""}
                      >
                        Видалити
                      </button>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        </div>
      )}
    </>
  );
}
