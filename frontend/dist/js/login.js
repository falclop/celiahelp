window.addEventListener('DOMContentLoaded', () => {
  const form = document.getElementById('loginForm');
  const msg  = document.getElementById('loginMessage');

  form.addEventListener('submit', async e => {
    e.preventDefault();
    msg.textContent = '';
    const email    = form.email.value.trim();
    const password = form.password.value;

    try {
      const res = await api.login(email, password);
      if (!res.ok) {
        msg.textContent = 'Credenciales incorrectas';
        msg.style.color = 'red';
        return;
      }
      const body = await res.json();
      // tu AuthResponse usa `.accessToken` o `.token`
      const token = body.accessToken || body.token;
      localStorage.setItem('token', token);
      window.location.href = 'incidencias.html';
    } catch {
      msg.textContent = 'Error de red';
      msg.style.color = 'red';
    }
  });
});
