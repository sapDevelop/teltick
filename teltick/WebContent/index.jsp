<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Fenster</title>
		<link rel="stylesheet"  href="style/style.css" type='text/css'  />
		<script type="text/javascript" src="js/fenster.js" >
		</script>
	</head>
	<body>
		<jsp:include page="fenster.jsp" >
			<jsp:param name="titel" value="Beispiel Submit-Umleitung mit Ajax ©Benedikt Brüntrup" />
			<jsp:param name="id" value="0" />
			<jsp:param name="inhalt" value="beispielFormular.jsp" />
			<jsp:param name="left" value ="60px" />
			<jsp:param name="top" value="60px" />
			<jsp:param name="minWidth" value="434px" />
		</jsp:include>
	</body>
</html>