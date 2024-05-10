// Evento que espera a que el DOM esté completamente cargado
document.addEventListener("DOMContentLoaded", function () {
  iniciarCarrusel()
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

fetch("SelectActividad")
  .then((response) => {
    if (!response.ok) {
      throw new Error("La solicitud no se pudo completar.")
    }
    return response.json() // Obtener el JSON de la respuesta
  })
  .then((json) => {
    const options = json // Utilizar directamente el array JSON
    const select = document.getElementById("actividadSelect")

    // Agregar la opción predeterminada "Seleccione actividad"
    const defaultOption = document.createElement("option")
    defaultOption.text = "Seleccione actividad"
    defaultOption.disabled = true
    defaultOption.selected = true
    select.appendChild(defaultOption)

    // Agregar las opciones del JSON
    options.forEach((optionText) => {
      const option = document.createElement("option")
      option.text = optionText
      option.value = optionText
      select.appendChild(option)
    })

    // Escuchar el evento de cambio en el select
    select.addEventListener("change", function () {
      // Obtener el valor seleccionado del select
      var selectedValue = this.value

      // Actualizar el valor del campo de entrada
      document.getElementById("cod_actividad").value = selectedValue
    })
  })
  .catch((error) => {
    console.error("Error al obtener los datos:", error)
  })
