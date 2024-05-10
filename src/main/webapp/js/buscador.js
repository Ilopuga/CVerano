document.addEventListener("DOMContentLoaded", function () {
  // Llama a la función para iniciar el carrusel
  iniciarCarrusel()

  // Obtener referencia al formulario por su ID
  const formBusqueda = document.getElementById("BuscarSolicitud")

  // Agregar un evento de envío al formulario
  formBusqueda.addEventListener("submit", function (event) {
    // Evita que se envíe el formulario de manera predeterminada
    event.preventDefault()

    // Obtener el valor del DNI ingresado por el usuario
    const dni = document.querySelector("input[name='dni']").value

    // Llamar a la función "llamada" con el DNI como parámetro
    llamada(dni)
  })
})

// mando al servlet
function llamada(dni) {
  fetch("BuscarSolicitud?dni=" + dni)
    .then((response) => response.json())
    .then((data) => pintarResultados(data))
}

// Definir la función para pintar los resultados en la página
function pintarResultados(datos) {
  // Aquí puedes escribir la lógica para pintar los resultados
  // Por ejemplo, actualizar los elementos HTML con los datos recibidos
  document.getElementById("id").innerText = datos.id
  document.getElementById("nombre").innerText = datos.nombre
  document.getElementById("dni").innerText = datos.dni
  document.getElementById("cod_actividad").innerText = datos.cod_actividad
  document.getElementById("email").innerText = datos.email
  document.getElementById("telefono").innerText = datos.telefono
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
  //si es 0 mostrar "No seleccionado", si es 1 mostrar "Seleccionado"

  document.getElementById("estado").innerText = datos.estado
    ? +datos.estado
    : "Recibida"
}

// Función para actualizar el campo de texto en la página
function actualizarCampo(idCampo, prefijo, valor) {
  const elemento = document.getElementById(idCampo)
  if (valor !== null && valor !== undefined) {
    elemento.innerText = prefijo + valor
  } else {
    elemento.innerText = ""
  }
}

function iniciarCarrusel() {
  const imagenes = document.querySelectorAll(".carrusel li")
  let indice = 0

  setInterval(() => {
    imagenes[indice].style.opacity = 0
    indice = (indice + 1) % imagenes.length
    imagenes[indice].style.opacity = 1
  }, 3500)
}
