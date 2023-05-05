<?php

include './conexion.php';

if($_SERVER['REQUEST_METHOD'] == 'POST') {
    
    $id_image = $_POST['idImage'];
    $nom_image = $_POST['nomImage'];
    $image = $_POST['image'];

    $path = "img/$nom_image.png";
    $actual_path = "http://localhost/upload_image/$path";
    file_put_contents($path, base64_decode($image));

    $sql_insert = "INSERT INTO imagenes VALUES('$id_image', '$nom_image')";
    $query_insert = $mysqli->query($sql_insert);

    if($query_insert) {
        echo json_encode("SE INSERTO CORRECTAMENTE LA FOTO");
    } else {
        echo json_encode("OCURRIO UN ERROR");
    }
}