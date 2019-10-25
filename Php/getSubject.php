<?php

$value1 = $_GET['sem'];
$value2 = $_GET['stream'];


$con3=mysqli_connect("localhost", "root", "") or die();

mysqli_select_db($con3,"questionpaperdatabaser");

$result3="select Subject_name from course_table,subject_table,question_subject_course where course_table.course_id = question_subject_course.course_id AND subject_table.subject_id = question_subject_course.subject_id AND question_subject_course.sem = $value1 AND course_table.Course_name LIKE '$value2'";

$display3=mysqli_query($con3,$result3);

	$response3 = "";

	while($row = mysqli_fetch_row($display3)) {
		$response3 = $response3 . $row[0]."-";
	}

	mysqli_close($con3);
	echo $response3;

?>