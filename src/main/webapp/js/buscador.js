// Evento que espera a que el DOM esté completamente cargado
document.addEventListener("DOMContentLoaded", function () {
  iniciarCarrusel()
  iniciarSolicitud()
})

function iniciarCarrusel() {
  const imagenes = document.querySelectorAll(".carrusel li")
  let indice = 0

  setInterval(() => {
    imagenes[indice].style.opacity = 0
    indice = (indice + 1) % imagenes.length
    imagenes[indice].style.opacity = 1
  }, 3500)
}
function iniciarSolicitud() {
  let dni = getParameterByName("dni")
  if (dni) {
    llamoSol(dni)
  }
}

function llamoSol(dni) {
  fetch("BuscarSolicitud?dni=" + dni)
    .then((response) => {
      if (!response.ok) {
        throw new Error("Problema con la respuesta del servidor")
      }
      return response.json()
    })
    .then((data) => {
      if (data.error) {
        alert(data.error) // Muestra el mensaje de error al usuario
      } else {
        pintarResultados(data) // Función para procesar y mostrar los datos
      }
    })
}

//Función para obtener el valor de un parametro en el GET. Expresiones regulares
function getParameterByName(name) {
  name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]")
  var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
    results = regex.exec(location.search)
  return results === null
    ? ""
    : decodeURIComponent(results[1].replace(/\+/g, " "))
}

// Definir la función para pintar los resultados en la página
function pintarResultados(datos) {
  // Aquí puedes escribir la lógica para pintar los resultados
  // Por ejemplo, actualizar los elementos HTML con los datos recibidos
  document.getElementById("id").innerText = datos.id
  document.getElementById("dni").innerText = datos.dni
  document.getElementById("cod_actividad").innerText = datos.cod_actividad
  document.getElementById("nombre").innerText = datos.nombre
  document.getElementById("apellido1").innerText = datos.apellido1
  document.getElementById("apellido2").innerText = datos.apellido2
  document.getElementById("email").innerText = datos.email
  document.getElementById("direccion").innerText = datos.direccion
  document.getElementById("telefono").innerText = datos.telefono
  document.getElementById("f_nacimiento").innerText = datos.f_nacimiento
  //Pregunto si el valor es nulo, indefinido, etc.(falsy) me muestra el texto "alternativo" que le indico
  //El admin modificará en el futuro y mostrará otra cosa
  document.getElementById("num_sorteo").innerText = datos.num_sorteo
    ? +datos.num_sorteo
    : "No asignado"
  document.getElementById("pago").innerText = datos.pago
    ? +datos.pago
    : "No corresponde"
  document.getElementById("seleccionado").innerText = datos.seleccionado
    ? +datos.seleccionado
    : "Sorteo no realizado"
  //Los `predefino en el insert de la clase DAo
  document.getElementById("estado").innerText = datos.estado
  document.getElementById("edicion").innerText = datos.edicion
}
