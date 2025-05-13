window.addEventListener('DOMContentLoaded', () => {
  const form = document.getElementById('loginForm');
  const modal = document.getElementById('modal');
  const modalMsg = document.getElementById('loginMessage'); // ← ID correcto
  const closeBtn = document.getElementById('closeModal');

  function showModal(message, color = 'red') {
    modalMsg.textContent = message;
    modalMsg.style.color = color;
    modal.classList.remove('hidden');
  }

  function hideModal() {
    modal.classList.add('hidden');
  }

  closeBtn.addEventListener('click', hideModal);
  window.addEventListener('keydown', e => {
    if (e.key === 'Escape') hideModal();
  });

  form.addEventListener('submit', async e => {
    e.preventDefault();

    const email    = form.email.value.trim();
    const password = form.password.value;

    try {
      const res = await api.login(email, password);
      if (!res.ok) {
        showModal('Credenciales incorrectas', 'red');
        return;
      }

      const body = await res.json();
      const token = body.accessToken || body.token;
      localStorage.setItem('token', token);
      window.location.href = 'incidencias.html';
    } catch {
      showModal('Error de red', 'red');
    }
  });
});
