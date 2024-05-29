-- Creo BDD
create database verano;

-- Indico BDD en la que voy a crear las tablas
use verano;

-- Creo la tabla Actividad
CREATE TABLE actividad (
cod_actividad int auto_increment not null,
nombreA varchar(200) not null,
lugar varchar(50) not null,
tema varchar(15) not null,
descripcion text,
imagen varchar(250),
f_inicio varchar(10) not null,
f_fin varchar(10) not null,
e_min numeric (2) not null,
e_max numeric (2) not null,
plazas numeric (2)not null,
primary key (cod_actividad),
check (tema in ('Acuático', 'Aventura', 'Cooperación', 'Deporte', 'Internacional', 'Ocio'))
);

CREATE TABLE solicitud (
id int auto_increment not null,
dni char(9) not null,
cod_actividad int not null,
nombre varchar(50) not null,
apellido1 varchar(50) not null,
apellido2 varchar(50),
email varchar(100) not null,
direccion text,
telefono varchar(15),
f_nacimiento varchar(10) not null,
num_sorteo numeric(4),
seleccionado boolean,
pago boolean,
estado varchar(10),
primary key (id, dni),
foreign key (cod_actividad) references actividad(cod_actividad)
);
