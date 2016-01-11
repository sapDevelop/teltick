<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
    <%@ page import="modell.interfaces.DBZugriff"  %>
     <%@ page import="modell.factory.HSqlDbZugriffFactory"  %>
    <%@ page import="java.sql.PreparedStatement"  %>
   <%@ page import="java.sql.ResultSet"  %>
    <%@ page import="java.sql.Connection"  %>
     <%@ page import="java.sql.SQLException"  %>
      <%@ page import="java.sql.Statement"  %>
     <%@ page import="java.sql.ResultSetMetaData"  %>
   
   
   
   <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
   
   
   
   
   <%  
  
   
   DBZugriff dbZugriff1 = HSqlDbZugriffFactory.getInstance(); 
   try {
		
	   int update = 13;
	   Connection verbindung = dbZugriff1.verbinden();
	   if (update == 1)
	   {
		   
			
			Statement stmt = verbindung.createStatement();
			stmt.executeUpdate("update fenster set icon = 'bilder/fenstericons/ticket_verwaltung.png' where fenster_id = '2'"); 
			
	   }else
		   
	   {
		//Lädt den Mitarbeiter aus der DB
		String abfrage = "select * from ticket";
		
		PreparedStatement pstmt = verbindung.prepareStatement(abfrage);
		ResultSet result = pstmt.executeQuery();
		
		while (result.next())
		{
			out.println("$$" + result.getString("ticket_id")+ "$$" + "\n");
		}
		
	    
		result.close();
		
	   }
	   verbindung.close();
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		out.println(e);
	}
   
   
   
   %> 
    
    


</body>
</html>