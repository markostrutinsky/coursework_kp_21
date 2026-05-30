import axios from "axios";

const BASE = "/api";

// Auto-logout on 401: clear storage and redirect to /login
axios.interceptors.response.use(
  (response) => response,
  (error) => {
    if (error.response?.status === 401) {
      const isAuthEndpoint = error.config?.url?.includes("/api/auth/");
      if (!isAuthEndpoint) {
        localStorage.removeItem("pawnshop_user");
        window.location.href = "/login";
      }
    }
    return Promise.reject(error);
  }
);

export function getAuthHeaders(token) {
  return { Authorization: `Bearer ${token}` };
}

// ── Auth ──────────────────────────────────────────────────────────────────────
export async function login(email, password) {
  const { data } = await axios.post(`${BASE}/auth/login`, { email, password });
  return data;
}

export async function register(firstName, lastName, email, password) {
  const { data } = await axios.post(`${BASE}/auth/register-user`, {
    firstName, lastName, email, password,
  });
  return data;
}

export async function registerAdmin(firstName, lastName, email, password) {
  const { data } = await axios.post(`${BASE}/auth/register-admin`, {
    firstName, lastName, email, password,
  });
  return data;
}

// ── Items ─────────────────────────────────────────────────────────────────────
export async function fetchAllItems(token) {
  const { data } = await axios.get(`${BASE}/pawnshop/all-items`, {
    headers: getAuthHeaders(token),
  });
  return data;
}

export async function fetchItemsByCategory(category, token) {
  const { data } = await axios.get(
    `${BASE}/pawnshop/items/by-category/${category}`,
    { headers: getAuthHeaders(token) }
  );
  return data;
}

export async function addItem(formData, token) {
  const { data } = await axios.post(`${BASE}/pawnshop/add-item`, formData, {
    headers: getAuthHeaders(token),
  });
  return data;
}

// ── Users ─────────────────────────────────────────────────────────────────────
export async function fetchAllUsers(token) {
  const { data } = await axios.get(`${BASE}/users/all`, {
    headers: getAuthHeaders(token),
  });
  return data;
}

export async function fetchUserByEmail(email, token) {
  const { data } = await axios.get(`${BASE}/users/${encodeURIComponent(email)}`, {
    headers: getAuthHeaders(token),
  });
  return data;
}

export async function deleteUser(email, token) {
  const { data } = await axios.delete(`${BASE}/users/delete/${encodeURIComponent(email)}`, {
    headers: getAuthHeaders(token),
  });
  return data;
}

// ── Roles ─────────────────────────────────────────────────────────────────────
export async function fetchAllRoles(token) {
  const { data } = await axios.get(`${BASE}/roles/all-roles`, {
    headers: getAuthHeaders(token),
  });
  return data;
}

export async function createRole(name, token) {
  const { data } = await axios.post(`${BASE}/roles/create-new-role`, { name }, {
    headers: getAuthHeaders(token),
  });
  return data;
}

export async function deleteRole(roleId, token) {
  await axios.delete(`${BASE}/roles/delete/${roleId}`, {
    headers: getAuthHeaders(token),
  });
}

export async function assignRoleToUser(userId, roleId, token) {
  const { data } = await axios.post(
    `${BASE}/roles/assign-user-to-role?userId=${userId}&roleId=${roleId}`,
    {},
    { headers: getAuthHeaders(token) }
  );
  return data;
}

export async function removeUserFromRole(userId, roleId, token) {
  const { data } = await axios.post(
    `${BASE}/roles/remove-user-from-role?userId=${userId}&roleId=${roleId}`,
    {},
    { headers: getAuthHeaders(token) }
  );
  return data;
}
