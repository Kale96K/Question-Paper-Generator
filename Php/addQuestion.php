<?php

$value1 = $_GET['question'];
$value2 = $_GET['marks'];
$value3 = $_GET['subject'];
$value4 = $_GET['chapter'];
$value5 = $_GET['level'];

$con=mysqli_connect("localhost", "root", "") or die();
mysqli_select_db($con,"questionpaperdatabaser");

$sql="INSERT INTO questions (questions.Question_id,questions.Question_text,questions.Question_marks,questions.Subject_id,questions.chapter_id,questions.level_id)
SELECT NULL,'$value1',$value2,subject_table.Subject_id,chapter_table.chapter_id,question_levels.level_id FROM subject_table,chapter_table,question_levels WHERE subject_table.Subject_name LIKE '$value3' AND chapter_table.chapter_title LIKE '$value4' AND question_levels.level_name LIKE '$value5'";
echo $sql;
mysqli_query($con,$sql);

mysqli_close($con);

echo "Success!";

?>
