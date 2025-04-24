const API_BASE = 'http://localhost:8080';

window.api = {
  login(email, password) {
    return fetch(`${API_BASE}/auth/login`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ email, password })
    });
  },

  createIncidencia(data) {
    return fetch(`${API_BASE}/api/incidencias`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(data)
    });
  },

  getIncidencias(token) {
    return fetch(`${API_BASE}/api/incidencias`, {
      headers: { 'Authorization': `Bearer ${token}` }
    });
  }
};
