document.addEventListener("DOMContentLoaded", function () {
  gestionarAdministradores()
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

function gestionarAdministradores() {
  llamada() // Llama a cargar la tabla de administradores

  // Parámetros desde la URL para editar
  let id = getParameterByName("id")
  let op = getParameterByName("op")
  if (id && op) {
    llamadaEd(id, op)
  }
}

function llamada() {
  fetch("../ListarAdministrador?op=1")
    .then((response) => response.json())
    .then((data) => pintarResultados(data))
}

//Pinta la tabla
function pintarResultados(datos) {
  // Busca "resultados" en el DOM, ya que uso admin.js en otras páginas que no tienen esta tabla y me da error
  let tabla = document.getElementById("resultados")

  if (tabla) {
    let html = "<table>"
    html += "<tr><th>ID</th><th>Usuario</th><th>Pass</th></tr>"
    for (let i = 0; i < datos.length; i++) {
      html +=
        "<tr></tr><td>" +
        datos[i].id +
        "</td><td>" +
        datos[i].usuario +
        "</td><td>" +
        datos[i].pass +
        "</td><td><a href='add_admin.html?id=" +
        datos[i].id +
        "&op=2'>Editar</a></td><td><a href='javascript:borrar(" +
        datos[i].id +
        ")'>Borrar</a></td></tr>"
    }
    html += "</table>"

    document.getElementById("resultados").innerHTML = html
  } else {
    console.log("El elemento 'resultados' no está presente en esta página.")
  }
}

//Editar admin
function llamadaEd(id, op) {
  fetch("../ListarAdministrador?id=" + id + "&op=" + op)
    .then((response) => response.json())
    .then((data) => pintarFormulario(data))
}

function pintarFormulario(datos) {
  document.getElementById("id").value = datos.id
  document.getElementById("usuario").value = datos.usuario
  document.getElementById("pass").value = datos.pass
}

function borrar(id) {
  if (
    confirm("¿Seguro que quieres borrar? Esta acción no se puede deshacer.")
  ) {
    fetch("../ListarAdministrador?id=" + id + "&op=3")
      .then((response) => response.json())
      .then((data) => pintarTabla(data))
  } else {
    alert("Acción cancelada")
  }
}
