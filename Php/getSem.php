<?php
	$con=mysqli_connect("localhost", "root", "") or die();

	mysqli_select_db($con,"questionpaperdatabaser");

	$result ="select sem from question_subject_course";

	$display=mysqli_query($con,$result);

	$response = "";

	while($row = mysqli_fetch_row($display)) {
		$response = $response . $row[0] . "-";
	}

	mysqli_close($con);
	echo $response;
?>