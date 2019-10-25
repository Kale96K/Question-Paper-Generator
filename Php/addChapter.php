<?php
$value1 = $_GET['chapter'];
$value2 = $_GET['subject'];


$con=mysqli_connect("localhost", "root", "") or die();
mysqli_select_db($con,"questionpaperdatabaser");

$sql="INSERT INTO chapter_table(chapter_table.chapter_id,chapter_table.chapter_title,chapter_table.Subject_id)SELECT NULL,'$value1',subject_table.Subject_id FROM subject_table WHERE subject_table.Subject_name LIKE '$value2'";

echo $sql;
mysqli_query($con,$sql);

mysqli_close($con);

echo "Success!";
?>