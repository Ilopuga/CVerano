//Función para obtener el valor de un parametro en el GET. Expresiones regulares
function getParameterByName(name) {
  name = name.replace(/[\[\]]/g, "\\$&")
  var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
    results = regex.exec(window.location.href)
  if (!results) return null
  if (!results[2]) return ""
  return decodeURIComponent(results[2].replace(/\+/g, " "))
}

fetch("SelectActividad")
  .then((response) => {
    if (!response.ok) {
      throw new Error("La solicitud no se pudo completar.")
    }
    return response.text() // Obtener el texto de la respuesta
  })
  .then((text) => {
    const options = text.split(",") // Dividir el texto en un array de opciones
    const select = document.getElementById("miSelect")

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
  })
  .catch((error) => {
    console.error("Error al obtener los datos:", error)
  })
