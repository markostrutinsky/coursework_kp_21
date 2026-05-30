import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { addItem } from "../api/api.js";
import { useAuth } from "../context/AuthContext.jsx";

const CATEGORIES = [
  "JEWELRY","ANTIQUES","FURNITURE","ELECTRONICS",
  "SPORTSEQUIPMENT","CLOTHES","MUSICALINSTRUMENTS","HOUSEHOLDGOODS",
];

const CATEGORY_LABELS = {
  JEWELRY:"Ювелірні вироби", ANTIQUES:"Антикваріат", FURNITURE:"Меблі",
  ELECTRONICS:"Електроніка", SPORTSEQUIPMENT:"Спортивне обладнання",
  CLOTHES:"Одяг", MUSICALINSTRUMENTS:"Музичні інструменти", HOUSEHOLDGOODS:"Побутові товари",
};

// Extra fields per category: [{ key, label, type, defaultValue }]
const EXTRA_FIELDS = {
  JEWELRY: [
    { key:"metal_sample",      label:"Проба металу",        type:"number", defaultValue:"585" },
    { key:"weight",            label:"Вага (г)",             type:"number", defaultValue:"" },
    { key:"size",              label:"Розмір",               type:"number", defaultValue:"" },
    { key:"is_precious_stones",label:"Є дорогоцінні камені",type:"text",   defaultValue:"false" },
    { key:"stones_count",      label:"Кількість каменів",   type:"number", defaultValue:"0" },
  ],
  ELECTRONICS: [
    { key:"year",            label:"Рік випуску",   type:"number", defaultValue:"" },
    { key:"brand",           label:"Бренд",         type:"text",   defaultValue:"" },
    { key:"model",           label:"Модель",        type:"text",   defaultValue:"" },
    { key:"productType",     label:"Тип товару",    type:"text",   defaultValue:"" },
    { key:"screenSize",      label:"Розмір екрану", type:"number", defaultValue:"0" },
    { key:"storageCapacity", label:"Пам'ять (ГБ)",  type:"number", defaultValue:"0" },
  ],
  ANTIQUES: [
    { key:"age",         label:"Вік (років)",    type:"number", defaultValue:"" },
    { key:"originality", label:"Оригінальність", type:"text",   defaultValue:"" },
    { key:"condition",   label:"Стан",           type:"text",   defaultValue:"" },
    { key:"material",    label:"Матеріал",       type:"text",   defaultValue:"" },
    { key:"maker",       label:"Виробник",       type:"text",   defaultValue:"" },
    { key:"provenance",  label:"Походження",     type:"text",   defaultValue:"" },
  ],
  CLOTHES: [
    { key:"size",        label:"Розмір",      type:"number", defaultValue:"" },
    { key:"brand",       label:"Бренд",       type:"text",   defaultValue:"" },
    { key:"clothingType",label:"Тип одягу",   type:"text",   defaultValue:"" },
    { key:"material",    label:"Матеріал",    type:"text",   defaultValue:"" },
    { key:"condition",   label:"Стан",        type:"text",   defaultValue:"" },
  ],
  FURNITURE: [
    { key:"material",  label:"Матеріал",  type:"text",   defaultValue:"" },
    { key:"style",     label:"Стиль",     type:"text",   defaultValue:"" },
    { key:"width",     label:"Ширина",    type:"number", defaultValue:"" },
    { key:"height",    label:"Висота",    type:"number", defaultValue:"" },
    { key:"depth",     label:"Глибина",   type:"number", defaultValue:"" },
    { key:"age",       label:"Вік (р.)",  type:"number", defaultValue:"" },
    { key:"brand",     label:"Бренд",     type:"text",   defaultValue:"" },
    { key:"condition", label:"Стан",      type:"text",   defaultValue:"" },
  ],
  SPORTSEQUIPMENT: [
    { key:"sport_type",     label:"Вид спорту",    type:"text",   defaultValue:"" },
    { key:"brand",          label:"Бренд",         type:"text",   defaultValue:"" },
    { key:"equipment_type", label:"Тип обладнання",type:"text",   defaultValue:"" },
    { key:"size",           label:"Розмір",        type:"number", defaultValue:"" },
    { key:"condition",      label:"Стан",          type:"text",   defaultValue:"" },
    { key:"model",          label:"Модель",        type:"text",   defaultValue:"" },
  ],
  MUSICALINSTRUMENTS: [
    { key:"instrumentType", label:"Тип інструменту", type:"text",   defaultValue:"" },
    { key:"brand",          label:"Бренд",           type:"text",   defaultValue:"" },
    { key:"condition",      label:"Стан",            type:"text",   defaultValue:"" },
    { key:"age",            label:"Вік (р.)",         type:"number", defaultValue:"" },
    { key:"model",          label:"Модель",          type:"text",   defaultValue:"" },
  ],
  HOUSEHOLDGOODS: [
    { key:"metalSample",  label:"Проба металу",  type:"number", defaultValue:"0" },
    { key:"productType",  label:"Тип товару",    type:"text",   defaultValue:"" },
    { key:"brand",        label:"Бренд",         type:"text",   defaultValue:"" },
    { key:"material",     label:"Матеріал",      type:"text",   defaultValue:"" },
    { key:"size",         label:"Розмір",        type:"number", defaultValue:"" },
    { key:"condition",    label:"Стан",          type:"text",   defaultValue:"" },
    { key:"model",        label:"Модель",        type:"text",   defaultValue:"" },
  ],
};

function buildInitial(category) {
  const base = { name:"", category, photo:"", firstName:"", lastName:"", email:"", loanAmount:"", interestRate:"" };
  (EXTRA_FIELDS[category] || []).forEach(f => { base[f.key] = f.defaultValue; });
  return base;
}

function toBase64(file) {
  return new Promise((resolve, reject) => {
    const reader = new FileReader();
    reader.onload = () => resolve(reader.result.split(",")[1]);
    reader.onerror = reject;
    reader.readAsDataURL(file);
  });
}

export default function AddItem() {
  const { user } = useAuth();
  const navigate = useNavigate();
  const [form, setForm] = useState(() => buildInitial("JEWELRY"));
  const [preview, setPreview] = useState(null);
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);

  const handleChange = (e) =>
    setForm((prev) => ({ ...prev, [e.target.name]: e.target.value }));

  const handleCategoryChange = (e) => {
    const cat = e.target.value;
    setForm(buildInitial(cat));
  };

  const handleFile = async (e) => {
    const file = e.target.files[0];
    if (!file) return;
    const b64 = await toBase64(file);
    setForm((prev) => ({ ...prev, photo: b64 }));
    setPreview(URL.createObjectURL(file));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");
    setLoading(true);
    try {
      await addItem(form, user.token);
      navigate("/items");
    } catch (err) {
      setError(
        err.response?.data?.message ||
        (typeof err.response?.data === "string" ? err.response.data : null) ||
        "Помилка при додаванні товару"
      );
    } finally {
      setLoading(false);
    }
  };

  const extraFields = EXTRA_FIELDS[form.category] || [];

  return (
    <div style={{ maxWidth: 680, margin: "2rem auto" }}>
      <div className="card">
        <h2 style={{ marginBottom: "1.5rem" }}>Додати товар до ломбарду</h2>
        {error && <div className="alert alert-error">{error}</div>}

        <form onSubmit={handleSubmit}>
          {/* ── Базова інформація ── */}
          <p style={{ fontWeight:700, color:"#3a4a5c", marginBottom:".75rem" }}>📦 Товар</p>

          <div className="form-group">
            <label>Назва товару</label>
            <input name="name" value={form.name} onChange={handleChange} placeholder="Наприклад: Золоте кільце" required />
          </div>

          <div className="form-group">
            <label>Категорія</label>
            <select name="category" value={form.category} onChange={handleCategoryChange}>
              {CATEGORIES.map(cat => <option key={cat} value={cat}>{CATEGORY_LABELS[cat]}</option>)}
            </select>
          </div>

          <div className="form-group">
            <label>Фото товару</label>
            <input type="file" accept="image/*" onChange={handleFile} />
            {preview && <img src={preview} alt="preview" style={{ marginTop:".5rem", maxHeight:150, borderRadius:8, objectFit:"cover", width:"100%" }} />}
          </div>

          {/* ── Специфічні поля категорії ── */}
          {extraFields.length > 0 && (
            <>
              <hr style={{ margin:"1.1rem 0", borderColor:"#e8edf3" }} />
              <p style={{ fontWeight:700, color:"#3a4a5c", marginBottom:".75rem" }}>🔧 Характеристики</p>
              <div style={{ display:"grid", gridTemplateColumns:"1fr 1fr", gap:"0 1rem" }}>
                {extraFields.map(f => (
                  <div className="form-group" key={f.key}>
                    <label>{f.label}</label>
                    <input
                      type={f.type}
                      name={f.key}
                      value={form[f.key] ?? ""}
                      onChange={handleChange}
                      placeholder={f.label}
                    />
                  </div>
                ))}
              </div>
            </>
          )}

          {/* ── Угода ── */}
          <hr style={{ margin:"1.1rem 0", borderColor:"#e8edf3" }} />
          <p style={{ fontWeight:700, color:"#3a4a5c", marginBottom:".75rem" }}>📄 Клієнт та угода</p>

          <div style={{ display:"grid", gridTemplateColumns:"1fr 1fr", gap:"0 1rem" }}>
            <div className="form-group">
              <label>Ім'я клієнта</label>
              <input name="firstName" value={form.firstName} onChange={handleChange} placeholder="Іван" required />
            </div>
            <div className="form-group">
              <label>Прізвище клієнта</label>
              <input name="lastName" value={form.lastName} onChange={handleChange} placeholder="Петренко" required />
            </div>
          </div>

          <div className="form-group">
            <label>Email клієнта</label>
            <input type="email" name="email" value={form.email} onChange={handleChange} placeholder="client@example.com" required />
          </div>

          <div style={{ display:"grid", gridTemplateColumns:"1fr 1fr", gap:"0 1rem" }}>
            <div className="form-group">
              <label>Сума позики (грн)</label>
              <input type="number" name="loanAmount" value={form.loanAmount} onChange={handleChange} placeholder="5000" min="1" required />
            </div>
            <div className="form-group">
              <label>Відсоткова ставка (%)</label>
              <input type="number" name="interestRate" value={form.interestRate} onChange={handleChange} placeholder="10" min="1" max="100" required />
            </div>
          </div>

          <div style={{ display:"flex", gap:".75rem", marginTop:".5rem" }}>
            <button type="button" className="btn btn-full" style={{ background:"#e8edf3", color:"#3a4a5c" }} onClick={() => navigate("/items")}>
              Скасувати
            </button>
            <button className="btn btn-primary btn-full" disabled={loading}>
              {loading ? "Збереження…" : "Зберегти товар"}
            </button>
          </div>
        </form>
      </div>
    </div>
  );
}
