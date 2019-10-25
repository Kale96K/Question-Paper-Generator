<?php
$value1 = $_GET['stream'];
$value2 = $_GET['subject'];
$value3 = $_GET['sem'];


$con=mysqli_connect("localhost", "root", "") or die();
mysqli_select_db($con,"questionpaperdatabaser");

$sql="INSERT INTO subject_table(subject_table.Subject_id,subject_table.Subject_name,subject_table.Course_id,subject_table.sem)SELECT NULL,'$value2',course_table.Course_id,$value3 FROM course_table WHERE course_table.Course_name LIKE '$value1'";

echo $sql;
mysqli_query($con,$sql);

mysqli_close($con);

echo "Success!";
?>