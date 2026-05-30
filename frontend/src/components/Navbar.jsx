import { Link, useNavigate } from "react-router-dom";
import { useAuth } from "../context/AuthContext.jsx";

export default function Navbar() {
  const { user, logout } = useAuth();
  const navigate = useNavigate();

  const handleLogout = () => {
    logout();
    navigate("/login");
  };

  return (
    <nav className="navbar">
      <Link to="/items" className="brand">🏦 PawnShop</Link>

      {user ? (
        <>
          <Link to="/items">Товари</Link>
          <Link to="/add-item">Додати товар</Link>
          <Link to="/users">Користувачі</Link>
          <Link to="/roles">Ролі</Link>
          <span style={{ color: "#7a8ea0", fontSize: ".85rem" }}>
            {user.email}
          </span>
          <button className="btn-link" onClick={handleLogout}>Вийти</button>
        </>
      ) : (
        <>
          <Link to="/login">Увійти</Link>
          <Link to="/register">Реєстрація</Link>
        </>
      )}
    </nav>
  );
}
