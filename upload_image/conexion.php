<?php

// CAMBIAR LA INFORMACION SEGUN SEA NECESARIO PARA LA BASE DE DATOS Y LAS CREDENCIALES
$mysqli = new mysqli("localhost","root","","prueba_upload_image");
    
// Comprobamos la conexion
if($mysqli->connect_errno) {
    die("Fallo la conexion");
} else {
    //echo "Conexion exitosa";
}