import java.sql.*;
public class MyConnection
{
 public Connection connection; 
  public MyConnection()
  {
     //configs 
  try{

 Class.forName("com.mysql.cj.jdbc.Driver"); //load jdbc driver 
 //connection
   this.connection = DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/java_pos",
    "root",
    ""); 
  }
  catch(ClassNotFoundException e)
  {
 System.out.println("JDBC Driver  error: " + e.getMessage());
  }
  catch (SQLException e) {
    System.out.println("Database error: " + e.getMessage());
}
   
  } 

}