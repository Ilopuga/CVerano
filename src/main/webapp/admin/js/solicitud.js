document.addEventListener("DOMContentLoaded", function () {
  gestionarSolicitudes()
})

//Función para otener el valor de un parametro en el GET. Expresiones regulares
function getParameterByName(name) {
  name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]")
  var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
    results = regex.exec(location.search)
  return results === null
    ? ""
    : decodeURIComponent(results[1].replace(/\+/g, " "))
}

function gestionarSolicitudes() {
  llamada() // Llama a cargar la tabla de solicitudes

  // Parámetros desde la URL para editar
  let id = getParameterByName("id")
  let op = getParameterByName("op")
  if (id && op) {
    llamadaEd(id, op)
  }
}

function llamada() {
  fetch("../ListaSolicitud?op=1")
    .then((response) => response.json())
    .then((data) => pintarResultados(data))
}

//Pinta la tabla
function pintarResultados(datos) {
  let html = "<table>"
  html +=
    "<tr><th>ID</th><th>DNI</th><th>Código Actividad</th><th>Nombre</th><th>Primer apellido</th><th>Segundo apellido</th><th>Email</th><th>Dirección</th><th>Teléfono</th><th>Fecha de nacimiento</th><th>Número de sorteo</th><th>Seleccionado</th><th>Pago</th><th>Estado</th></tr>"
  for (let i = 0; i < datos.length; i++) {
    html +=
      "<tr><td>" +
      datos[i].id +
      "</td><td>" +
      datos[i].dni +
      "</td><td>" +
      datos[i].cod_actividad +
      "</td><td>" +
      datos[i].nombre +
      "</td><td>" +
      datos[i].apellido1 +
      "</td><td>" +
      datos[i].apellido2 +
      "</td><td>" +
      datos[i].email +
      "</td><td>" +
      datos[i].direccion +
      "</td><td>" +
      datos[i].telefono +
      "</td><td>" +
      datos[i].f_nacimiento +
      "</td><td>" +
      datos[i].num_sorteo +
      "</td><td>" +
      datos[i].seleccionado +
      "</td><td>" +
      datos[i].pago +
      "</td><td>" +
      datos[i].estado +
      "</td><td><a href='solicitud.html?id=" +
      datos[i].id +
      "&op=2'>Editar</a></td><td><a href='javascript:borrar(" +
      datos[i].id +
      ")'>Borrar</a></td></tr>"
  }

  html += "</table>"

  document.getElementById("resultados").innerHTML = html
}

//Editar solicitud
function llamadaEd(id, op) {
  fetch("../ListaSolicitud?id=" + id + "&op=" + op)
    .then((response) => response.json())
    .then((data) => pintarFormulario(data))
}

function pintarFormulario(datos) {
  document.getElementById("id").value = datos.id
  document.getElementById("dni").value = datos.dni
  document.getElementById("cod_actividad").value = datos.cod_actividad
  document.getElementById("nombre").value = datos.nombre
  document.getElementById("apellido1").value = datos.apellido1
  document.getElementById("apellido2").value = datos.apellido2
  document.getElementById("email").value = datos.email
  document.getElementById("direccion").value = datos.direccion
  document.getElementById("telefono").value = datos.telefono
  document.getElementById("f_nacimiento").value = datos.f_nacimiento
  document.getElementById("num_sorteo").value = datos.num_sorteo
  document.getElementById("seleccionado").value = datos.seleccionado
  document.getElementById("pago").value = datos.pago
  document.getElementById("estado").value = datos.estado
}

function borrar(id) {
  if (
    confirm("¿Seguro que quieres borrar? Esta acción no se puede deshacer.")
  ) {
    fetch("../ListaSolicitud?id=" + id + "&op=3")
      .then((response) => response.json())
      .then((data) => pintarTabla(data))
  } else {
    alert("Acción cancelada")
  }
}
