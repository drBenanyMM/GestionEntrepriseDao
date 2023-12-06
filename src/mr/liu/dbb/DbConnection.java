package mr.liu.dbb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

	// Variables de connectionn à la BDD
	private static final String URL = "jdbc:mysql://localhost:3306/gestionEntreprise";
	private static final String USER = "root";
	private static final String PASSWORD = "";

	//Creer une instance de connection
	private static Connection connection = null;

	//Crrer la methode permettant de retourner une instance de connection pour les classes Dao
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connecttion etablie.");
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
		} catch (SQLException e) {
			// TODO: handle exception
		}

		return connection;
	}

//Tester la methode de connection
	public static void main(String[] args) throws SQLException {
		DbConnection.getConnection();
	}
}
