window.addEventListener('DOMContentLoaded', () => {
  const form = document.getElementById('createForm');
  const msg  = document.getElementById('message');
  const modal = document.getElementById('modal');
  const closeBtn = document.getElementById('closeModal');

  function showModal(message, color) {
    msg.textContent = message;
    msg.style.color = color;
    modal.classList.remove('hidden');
  }

  function hideModal() {
    modal.classList.add('hidden');
  }

  closeBtn.addEventListener('click', hideModal);
  window.addEventListener('keydown', (e) => {
    if (e.key === 'Escape') hideModal();
  });

  form.addEventListener('submit', async e => {
    e.preventDefault();
    const data = {
      titulo:         form.titulo.value.trim(),
      descripcion:    form.descripcion.value.trim(),
      nombreRemitente: form.nombreRemitente.value.trim(),
      emailRemitente: form.emailRemitente.value.trim()
    };
    try {
      const res = await api.createIncidencia(data);
      const body = await res.json();
      if (!res.ok) {
        showModal(body.message || `Error ${res.status}`, 'red');
      } else {
        showModal(`Incidencia creada con ID ${body.id}`, 'green');
        form.reset();
      }
    } catch {
      showModal('Error de red', 'red');
    }
  });
});
