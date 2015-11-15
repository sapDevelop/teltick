<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<form action="./LoginController" method="post">
	<table>
		<tr>
			<th>Login</th>
		</tr>
		<tr>
			<td>Benutzername:</td>
			<td><input type="text" name="username"></td>
		</tr>
		<tr>
			<td>Passwort:</td>
			<td><input type="password" name="password"></td>
		</tr>
		<tr>
			<td></td>
			<td>
				<button type="submit" id="LoginButton">Einloggen</button>
			</td>
		</tr>

	</table>
</form>
