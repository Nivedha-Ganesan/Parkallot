<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action = "AddParkingLot" method = "post">
	Enter Name : <input type="text" name="p_lot_name"><br>
	Enter Address : <input type="text" name="address"><br>
	Enter longitude : <input type="text" name="longitude"><br>
	Enter latitude : <input type="text" name="latitude"><br>
	Enter slot counts : <input type="text" name="slot_count"><br>
	Enter cost per hour : <input type="text" name="cost_per_hour"><br>
	<input type="submit" value="UPDATE" ><br>
	</form>
	<form action="Logout">
		<input type="submit" value="LOGOUT">
	</form>
</body>
</html>