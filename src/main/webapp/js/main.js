// Evento que espera a que el DOM esté completamente cargado
document.addEventListener("DOMContentLoaded", function () {
  iniciarCarrusel()
  iniciarActividad()
  ErrorLogin()
  error()
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

function iniciarActividad() {
  let tema = getParameterByName("tema")
  if (tema) {
    llamoAct(tema)
  }
}

function llamoAct(tema) {
  fetch("BuscarActividad?tema=" + tema)
    .then((response) => response.json())
    .then((data) => pintarActividad(data))
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
//Cargo la imagen de la base de datos para mostrarla en el editar. No relleno el campo, solo la muestro
function cargarImagenBdd(imagenBdd) {
  document.getElementById("imagenBdd").src = "files/" + imagenBdd
  //  document.getElementById("imagenBdd").src = `files/${imagenBdd}`
}

function pintarActividad(datos) {
  document.getElementById("cod_actividad").textContent = datos.cod_actividad
  document.getElementById("nombreA").textContent = datos.nombreA
  document.getElementById("lugar").textContent = datos.lugar
  document.getElementById("tema").textContent = datos.tema
  document.getElementById("descripcion").textContent = datos.descripcion
  cargarImagenBdd(datos.imagen)
  document.getElementById("f_inicio").textContent = datos.f_inicio
  document.getElementById("f_fin").textContent = datos.f_fin
  document.getElementById("e_min").textContent = datos.e_min
  document.getElementById("e_max").textContent = datos.e_max
  document.getElementById("plazas").textContent = datos.plazas
}

//error del login. Busca error=true en la URL, sis e encuentra, muestra el error en casa campo
function ErrorLogin() {
  if (new URLSearchParams(window.location.search).get("error") === "true") {
    const campoUsuario = document.getElementById("usuario")
    const campoPass = document.getElementById("password")
    campoUsuario.placeholder = "Usuario incorrecto"
    campoPass.placeholder = "Contraseña incorrecta"
    campoUsuario.classList.add("error-input") // Añade clase de error para reconocer el campo. CSS
    campoPass.classList.add("error-input")
  }
}

// Obtener el mensaje de error de la URL
function error() {
  let error = getParameterByName("error")
  let mensajeError = document.getElementById("errorMensaje")

  if (error) {
    switch (error) {
      case "1":
        mensajeError.textContent =
          "Error. Usuario duplicado, este DNI ya ha realizado una solicitud. Si desea modificar algún dato, contacte con ayudame@verano.com"
        break
      case "2":
        mensajeError.textContent =
          "Error. Error en la base de datos. No se ha guardado. Contacte con el administrador."
        break
      case "3":
        mensajeError.textContent =
          "Error. No se ha podido cargar el listado. Contacte con el administrador."
        break
      case "4":
        mensajeError.textContent =
          "Error. No existe DNI, este DNI no ha realizado ninguna solicitud."
        break
      default:
        mensajeError.textContent =
          "Error desconocido. Por favor, contacte con el administrador."
        break
    }
  }
}
