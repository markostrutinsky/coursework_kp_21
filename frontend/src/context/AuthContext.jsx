import { createContext, useContext, useState, useEffect } from "react";
import axios from "axios";

const AuthContext = createContext(null);

export function AuthProvider({ children }) {
  const [user, setUser] = useState(() => {
    try {
      const stored = localStorage.getItem("pawnshop_user");
      return stored ? JSON.parse(stored) : null;
    } catch {
      return null;
    }
  });

  // On mount, verify the stored token is still accepted by the backend.
  // If the DB was wiped (down -v) the user no longer exists → 401 → logout.
  useEffect(() => {
    const stored = localStorage.getItem("pawnshop_user");
    if (!stored) return;
    let parsed;
    try { parsed = JSON.parse(stored); } catch { return; }
    if (!parsed?.token) return;

    axios.get("/api/users/all", {
      headers: { Authorization: `Bearer ${parsed.token}` },
    }).catch((err) => {
      if (err.response?.status === 401) {
        localStorage.removeItem("pawnshop_user");
        setUser(null);
      }
    });
  }, []);

  const login = (userData) => {
    localStorage.setItem("pawnshop_user", JSON.stringify(userData));
    setUser(userData);
  };

  const logout = () => {
    localStorage.removeItem("pawnshop_user");
    setUser(null);
  };

  return (
    <AuthContext.Provider value={{ user, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
}

export function useAuth() {
  return useContext(AuthContext);
}
