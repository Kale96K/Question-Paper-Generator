<?php
$value1 = $_GET['course'];

$con=mysqli_connect("localhost", "root", "") or die();
mysqli_select_db($con,"questionpaperdatabaser");

$sql="Insert into course_table values(NULL,'$value1')";

echo $sql;
mysqli_query($con,$sql);

mysqli_close($con);

echo "Success!";
?>