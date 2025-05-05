window.addEventListener('DOMContentLoaded', () => {
  const form = document.getElementById('createForm');
  const msg  = document.getElementById('message');

  form.addEventListener('submit', async e => {
    e.preventDefault();
    msg.textContent = '';
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
        msg.textContent = body.message || `Error ${res.status}`;
        msg.style.color = 'red';
      } else {
        msg.textContent = `Incidencia creada con ID ${body.id}`;
        msg.style.color = 'green';
        form.reset();
      }
    } catch {
      msg.textContent = 'Error de red';
      msg.style.color = 'red';
    }
  });
});
