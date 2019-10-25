<?php

$value1 = $_GET['selectedSubject'];

$con3=mysqli_connect("localhost", "root", "") or die();

mysqli_select_db($con3,"questionpaperdatabaser");

$result3="select chapter_title from chapter_table,subject_table where subject_table.subject_id = chapter_table.subject_id AND subject_table.Subject_name LIKE '$value1'";

$display3=mysqli_query($con3,$result3);

	$response4 = "";

	while($row = mysqli_fetch_row($display3)) {
		$response4 = $response4 . $row[0]."-";
	}

	mysqli_close($con3);
	echo $response4;

?>