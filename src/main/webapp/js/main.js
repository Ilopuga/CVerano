// Evento que espera a que el DOM esté completamente cargado
document.addEventListener("DOMContentLoaded", function () {
  iniciarCarrusel()
  iniciarActividad()
  ErrorLogin()
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

function cargarImagenBdd(imagenBdd) {
  document.getElementById("imagenBdd").src = `files/${imagenBdd}`
}

function pintarActividad(datos) {
  //document.getElementById("cod_actividad").textContent = datos.cod_actividad
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
