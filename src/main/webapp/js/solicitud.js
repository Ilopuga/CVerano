document.addEventListener("DOMContentLoaded", function () {
  // Llama a la función para iniciar el carrusel
  iniciarCarrusel()

  // Obtener referencia al botón de búsqueda por su ID
  const buscarBtn = document.getElementById("buscarBtn")

  // Agregar un evento de clic al botón de búsqueda
  buscarBtn.addEventListener("click", function (event) {
    // Evitar que el formulario se envíe automáticamente
    event.preventDefault()

    // Obtener el valor del DNI ingresado por el usuario
    const dni = document.getElementById("dniInput").value

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
function pintarResultados(data) {
  // Aquí puedes escribir la lógica para pintar los resultados
  // Por ejemplo, actualizar los elementos HTML con los datos recibidos
  document.getElementById("nombre").innerText = "Nombre: " + data.nombre
  document.getElementById("dni").innerText = "DNI: " + data.dni
  document.getElementById("cod_actividad").innerText = data.cod_actividad
  document.getElementById("email").innerText = data.email
  document.getElementById("teléfono").innerText = data.teléfono
  document.getElementById("estado").innerText = data.estado
}

// Formulario de solicitud. Recibe el json con el cod_actividad, lo muestra en el select
// y lo asigna al campo de entrada cod_actividad
// Fetch para obtener las actividades desde el lson
//1 Recibe el json
fetch("SelectActividad")
  .then((response) => response.json())
  .then((json) => {
    // Obtiene el select
    const select = document.getElementById("actividadSelect")
    // Para que aparezca el mensaje y no la primera opción que me trae el json. No se puede seleccionar
    select.innerHTML =
      "<option value='' disabled selected>Seleccione actividad</option>"
    // Itera sobre los elementos del json para crear opciones en el select
    json.forEach((optionText) => {
      // Agrega una opción al select para cada elemento del json
      select.innerHTML += `<option value="${optionText}">${optionText}</option>`
    })

    // Escucha para cuando haya cambiado el select
    select.addEventListener("change", () => {
      // Coge la opción seleccionada y la manada a cod_actividad para que tenga ese valor.
      document.getElementById("cod_actividad").value = select.value
    })
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
