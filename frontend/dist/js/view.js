window.addEventListener('DOMContentLoaded', () => {
  const token = localStorage.getItem('token');
  if (!token) {
    window.location.href = 'login.html';
    return;
  }

  const tableBody      = document.querySelector('#incidenciasTable tbody');
  const fTitle         = document.getElementById('filterTitle');
  const fPrio          = document.getElementById('filterPrioridad');
  const fEstado        = document.getElementById('filterEstado');
  const btnFilter      = document.getElementById('applyFilters');
  const btnLogout      = document.getElementById('logout');
  let   allIncidencias = [];

  btnLogout.addEventListener('click', () => {
    localStorage.removeItem('token');
    window.location.href = 'index.html';
  });
  btnFilter.addEventListener('click', renderTable);

  fetchIncidencias();

  async function fetchIncidencias() {
    const res = await api.getIncidencias(token);
    if (res.status === 401) {
      localStorage.removeItem('token');
      window.location.href = 'login.html';
      return;
    }
    allIncidencias = await res.json();
    renderTable();
  }

  function renderTable() {
    const tFilter = fTitle.value.trim().toLowerCase();
    const pFilter = fPrio.value;
    const eFilter = fEstado.value;
    tableBody.innerHTML = '';

    allIncidencias
      .filter(i =>
        (!tFilter || i.titulo.toLowerCase().includes(tFilter)) &&
        (!pFilter || i.prioridad === pFilter) &&
        (!eFilter || i.estado === eFilter)
      )
      .forEach(i => {
        const tr = document.createElement('tr');
        tr.innerHTML = `
          <td>${i.id}</td>
          <td>${i.titulo}</td>
          <td>${i.descripcion}</td>
          <td>${i.prioridad}</td>
          <td>${i.estado}</td>
          <td>${i.nombreRemitente}</td>
        `;
        tableBody.appendChild(tr);
      });
  }
});
