-- Creo un nuevo atributo edición, ya que lo implementé a posteriori. Lo hago utilizando el asistente
-- Tras varios errores averiguo que estoy en "safe update mode", con lo cual utilizo 
SET SQL_SAFE_UPDATES = 0;
-- Relleno el campo
UPDATE solicitud SET edicion = 2024 WHERE edicion IS NULL;