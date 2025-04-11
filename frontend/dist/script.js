const API_URL = "http://localhost:8080/api/incidencias";

async function cargarIncidencias() {
  const res = await fetch(API_URL);
  const data = await res.json();

  const contenedor = document.getElementById("incidencias");
  contenedor.innerHTML = "";

  data.forEach((inc) => {
    const div = document.createElement("div");
    div.className = "incidencia";
    div.innerHTML = `
      <h3>${inc.titulo}</h3>
      <p>${inc.descripcion}</p>
      <p><strong>Prioridad:</strong> ${inc.prioridad}</p>
      <p><strong>Estado:</strong> ${inc.estado}</p>
      <p><strong>Remitente:</strong> ${inc.nombre_remitente} (${inc.email_remitente})</p>
    `;
    contenedor.appendChild(div);
  });
}

document.getElementById("incidencia-form").addEventListener("submit", async (e) => {
  e.preventDefault();

  const nuevaIncidencia = {
    titulo: document.getElementById("titulo").value,
    descripcion: document.getElementById("descripcion").value,
    nombre_remitente: document.getElementById("nombre_remitente").value,
    email_remitente: document.getElementById("email_remitente").value,
    prioridad: document.getElementById("prioridad").value
  };

  await fetch(API_URL, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(nuevaIncidencia)
  });

  e.target.reset();
  cargarIncidencias();
});

cargarIncidencias();
