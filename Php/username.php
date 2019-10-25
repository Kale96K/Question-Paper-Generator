<?php
	$con1=mysqli_connect("localhost", "root", "") or die();

	mysqli_select_db($con1,"questionpaperdatabaser");

	$result1 ="select Username from admin_table";

	$display1=mysqli_query($con1,$result1);

	$response1 = "";

	while($row = mysqli_fetch_row($display1)) {
		$response1 = $response1 . $row[0];
	}

	mysqli_close($con1);
	echo $response1;

?>