import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { fetchAllItems, fetchItemsByCategory } from "../api/api.js";
import { useAuth } from "../context/AuthContext.jsx";

const CATEGORIES = [
  "ALL",
  "JEWELRY",
  "ANTIQUES",
  "FURNITURE",
  "ELECTRONICS",
  "SPORTSEQUIPMENT",
  "CLOTHES",
  "MUSICALINSTRUMENTS",
  "HOUSEHOLDGOODS",
];

const CATEGORY_LABELS = {
  ALL: "Всі",
  JEWELRY: "Ювелірні",
  ANTIQUES: "Антикваріат",
  FURNITURE: "Меблі",
  ELECTRONICS: "Електроніка",
  SPORTSEQUIPMENT: "Спортивне обладнання",
  CLOTHES: "Одяг",
  MUSICALINSTRUMENTS: "Музичні інструменти",
  HOUSEHOLDGOODS: "Побутові товари",
};

const CATEGORY_ICONS = {
  ALL: "📦",
  JEWELRY: "💍",
  ANTIQUES: "🏺",
  FURNITURE: "🪑",
  ELECTRONICS: "💻",
  SPORTSEQUIPMENT: "⚽",
  CLOTHES: "👗",
  MUSICALINSTRUMENTS: "🎸",
  HOUSEHOLDGOODS: "🏠",
};

export default function Items() {
  const { user } = useAuth();
  const [items, setItems] = useState([]);
  const [active, setActive] = useState("ALL");
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");

  const load = async (category) => {
    setLoading(true);
    setError("");
    try {
      const data =
        category === "ALL"
          ? await fetchAllItems(user.token)
          : await fetchItemsByCategory(category, user.token);
      setItems(data);
    } catch {
      setError("Не вдалося завантажити товари");
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    load(active);
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [active]);

  return (
    <>
      <div className="page-header">
        <h1>Товари ломбарду</h1>
        <Link to="/add-item" className="btn btn-primary">
          + Додати товар
        </Link>
      </div>

      {error && <div className="alert alert-error">{error}</div>}

      <div className="filter-bar">
        {CATEGORIES.map((cat) => (
          <button
            key={cat}
            className={`filter-btn ${active === cat ? "active" : ""}`}
            onClick={() => setActive(cat)}
          >
            {CATEGORY_ICONS[cat]} {CATEGORY_LABELS[cat]}
          </button>
        ))}
      </div>

      {loading ? (
        <div className="spinner-wrap">
          <div className="spinner" />
        </div>
      ) : items.length === 0 ? (
        <div className="empty">
          <div className="icon">📭</div>
          <p>Товарів не знайдено</p>
        </div>
      ) : (
        <div className="items-grid">
          {items.map((item) => (
            <div className="item-card" key={item.id}>
              {item.photo ? (
                <img
                  src={`data:image/jpeg;base64,${item.photo}`}
                  alt={item.name}
                />
              ) : (
                <div className="item-placeholder">
                  {CATEGORY_ICONS[item.category] ?? "📦"}
                </div>
              )}
              <div className="item-body">
                <div className="item-name">{item.name ?? item.pawnItemName}</div>
                <span className="item-category">
                  {CATEGORY_LABELS[item.category] ?? item.category}
                </span>
              </div>
            </div>
          ))}
        </div>
      )}
    </>
  );
}
