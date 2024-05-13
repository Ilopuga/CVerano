document.addEventListener("DOMContentLoaded", function () {
  // Controlador para asignar número para el sorteo
  document
    .getElementById("asignarNumero")
    .addEventListener("click", function () {
      window.location.href = "../AsignarNumero"
    })
})

document
  .getElementById("realizarSorteo")
  .addEventListener("click", function () {
    // Ingreso el número máximo permitido. Corresponde a las solicitudes recibidas
    var maximoPermitido = prompt(
      "Ingrese el número máximo permitido para el sorteo:"
    )
    // Conveierto a int
    maximoPermitido = parseInt(maximoPermitido)
    // Generar el número aleatorio, desde 1 hasta el que le doy
    var numeroAleatorio = Math.floor(Math.random() * maximoPermitido) + 1
    // Muestro en ese id
    document.getElementById("numeroAleatorio").textContent = numeroAleatorio
  })
