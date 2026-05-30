import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { register as apiRegister } from "../api/api.js";

export default function Register() {
  const navigate = useNavigate();
  const [form, setForm] = useState({
    firstName: "",
    lastName: "",
    email: "",
    password: "",
    confirm: "",
  });
  const [error, setError] = useState("");
  const [success, setSuccess] = useState("");
  const [loading, setLoading] = useState(false);

  const handleChange = (e) =>
    setForm((prev) => ({ ...prev, [e.target.name]: e.target.value }));

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");
    if (form.password !== form.confirm) {
      setError("Паролі не збігаються");
      return;
    }
    setLoading(true);
    try {
      await apiRegister(form.firstName, form.lastName, form.email, form.password);
      setSuccess("Реєстрація успішна! Перенаправлення…");
      setTimeout(() => navigate("/login"), 1500);
    } catch (err) {
      setError(
        err.response?.data?.message ||
        (typeof err.response?.data === "string" ? err.response.data : null) ||
        "Помилка реєстрації"
      );
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="auth-wrap">
      <div className="auth-card">
        <h2>Реєстрація</h2>

        {error   && <div className="alert alert-error">{error}</div>}
        {success && <div className="alert alert-success">{success}</div>}

        <form onSubmit={handleSubmit}>
          <div className="form-group">
            <label>Ім'я</label>
            <input
              name="firstName"
              value={form.firstName}
              onChange={handleChange}
              placeholder="Іван"
              required
            />
          </div>
          <div className="form-group">
            <label>Прізвище</label>
            <input
              name="lastName"
              value={form.lastName}
              onChange={handleChange}
              placeholder="Петренко"
              required
            />
          </div>
          <div className="form-group">
            <label>Email</label>
            <input
              type="email"
              name="email"
              value={form.email}
              onChange={handleChange}
              placeholder="you@example.com"
              required
            />
          </div>
          <div className="form-group">
            <label>Пароль</label>
            <input
              type="password"
              name="password"
              value={form.password}
              onChange={handleChange}
              placeholder="••••••••"
              required
            />
          </div>
          <div className="form-group">
            <label>Підтвердіть пароль</label>
            <input
              type="password"
              name="confirm"
              value={form.confirm}
              onChange={handleChange}
              placeholder="••••••••"
              required
            />
          </div>
          <button className="btn btn-primary btn-full" disabled={loading}>
            {loading ? "Завантаження…" : "Зареєструватися"}
          </button>
        </form>

        <p style={{ marginTop: "1rem", textAlign: "center", fontSize: ".9rem" }}>
          Вже є акаунт?{" "}
          <Link to="/login" style={{ color: "#4a90d9" }}>
            Увійти
          </Link>
        </p>
      </div>
    </div>
  );
}
