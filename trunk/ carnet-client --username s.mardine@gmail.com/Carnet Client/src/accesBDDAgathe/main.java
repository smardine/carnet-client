package accesBDDAgathe;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;


public class main {

	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			Class.forName("org.firebirdsql.jdbc.FBDriver");
		}
		catch(ClassNotFoundException e){
			JOptionPane.showMessageDialog(null, "Classes non trouv�es"
			+ " pour le chargement du pilote JDBC/ODBC MySQL",
			"ALERTE", JOptionPane.ERROR_MESSAGE);
		}
			
		try {
			@SuppressWarnings("unused")
			//String urlBD = Driver+"UID="+nomUtilisateur+";PWD="+MDP+";DBNAME="+host+":"+alias;
			//laConnectionStatique = DriverManager.getConnection(urlBD);
			Connection laConnectionStatique = DriverManager.getConnection("jdbc:firebirdsql:MICPTDVP:outilhotline?encoding=UTF8&user=sysdba&password=masterkey");
			boolean etatConnexion = true;
			JOptionPane.showMessageDialog(null, "Connexion Ok  � la base de donn�es\n\r " +
					"url de connexion: jdbc:firebirdsql:MICPTDVP:outilhotline?encoding=UTF8&user=sysdba&password=masterkey" ,
					"etatConnexion", JOptionPane.INFORMATION_MESSAGE
);
			
		} 
			// TODO Auto-generated catch block
			catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Impossible de se connecter" +
				" � la base de donn�es\n\r" + e,
				"ALERTE", JOptionPane.ERROR_MESSAGE);
		}
	}

}
