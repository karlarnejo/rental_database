import java.sql.*;
public class DatabaseConnection 
{
	private static String url = "jdbc:postgresql://localhost:5432/RentalDatabase";
	private static String driverName = "org.postgresql.Driver";
	private static String username = "postgres";
	private static String password = "karl";
	private static Connection con;
	
	public static Connection connectDB()
	{
		try{
			Class.forName(driverName);
			con = DriverManager.getConnection(url, username, password);
		
			
		} catch (Exception e){
			e.printStackTrace();
		}
		return con;
	}
		
}
