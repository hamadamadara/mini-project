package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Models.User;

public class conn {

	
	static Connection myCnx;
	String url,driver;
	private static final String INSERT_LIVRE_SQL = "INSERT INTO Livre (ISBN, Titre, date_Edition,Editeur,MotsCles,Resume,Auteurs) "
			+ "VALUES  (?, ?, ?,?,?,?,?);";
	
	public UserDAO() {
	try {
		driver=new String("com.mysql.cj.jdbc.Driver");
		Class.forName(driver);
		url = new String("jdbc:mysql://localhost:8080/library?serverTimezone=UTC");
		} 
	catch( ClassNotFoundException e) {
			System.err.println("Erreur lors du chargement du pilote : " + e); 
		}	
	}
	
	public Connection getConnection() throws SQLException {
		try {
			myCnx = DriverManager.getConnection(url,"root","");
			//JOptionPane.showMessageDialog(null, "CONNEXION ETABLIE","Connextion",JOptionPane.WARNING_MESSAGE);
	
		} catch (SQLException e) {
			System.err.println("Erreur de syntaxe SQL :" + e.getMessage());
		} 	
		return myCnx;
	}
//List controller

 public void insertLivre(Livre Livre) throws SQLException {
	        //System.out.println(INSERT_USERS_SQL);
	        try {
	        	Connection connection = getConnection(); 	        
	        	 PreparedStatement preparedStatement = connection.prepareStatement(INSERT_LIVRE_SQL);
	        
	            preparedStatement.setString(1, Livre.getISBN());
	            preparedStatement.setString(2, Livre.getTitre());
	            preparedStatement.setString(3, Livre.getDateEdition());
				preparedStatement.setString(4, Livre.getEditeur());
				preparedStatement.setString(5, Livre.getMotsClesString());
				preparedStatement.setString(6, Livre.getResume());
				preparedStatement.setString(7, Livre.getAuteurs());
	            //System.out.println(preparedStatement);
	            preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	            System.err.println(e.getMessage());
	        }
	    }
	
}

