package accesBDDAgathe;
//importation des classes pour JDBC

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class ControleConnexionAgathe{
// PROPRIETES
static ParametresAgathe lesParametres;
static boolean etatConnexion;
static Connection laConnectionStatiqueAgathe;
	static {
	boolean ok = true;
	
	lesParametres = new ParametresAgathe();
	
	try{
		Class.forName("org.firebirdsql.jdbc.FBDriver");
		etatConnexion = true;
	}
	catch(ClassNotFoundException e){
	JOptionPane.showMessageDialog(null, "Classes non trouv�es"
	+ " pour le chargement du pilote Firebird",
	"ALERTE", JOptionPane.ERROR_MESSAGE);
	ok = false;
	etatConnexion = false;
	}

	if (ok == true){
		try {
		// r�cup�ration des param�tres pr�sents dans la classe Parametres
		//String urlBD = lesParametres.getServeurBD();
		String nomUtilisateur = lesParametres.getNomUtilisateur();
		String MDP = lesParametres.getMotDePasse();
		String DriverSGBD = lesParametres.getDriverSGBD();
		String Emplacement = lesParametres.getEmplacementBase();
		String Serveur = lesParametres.getHostName();
		
		String UrlDeConnexion = DriverSGBD+":"+Serveur+":"+Emplacement+"?encoding=UTF8&user="+nomUtilisateur+"&password="+MDP;
		
		laConnectionStatiqueAgathe = DriverManager.getConnection(UrlDeConnexion);
		
//Connection laConnectionStatique = DriverManager.getConnection("jdbc:firebirdsql:MICPTDVP:outilhotline?encoding=UTF8&user=sysdba&password=masterkey");
		
		//laConnectionStatiqueAgathe = DriverManager.getConnection(urlBD, nomUtilisateur, MDP);
		etatConnexion = true;
		}
	
		catch (Exception e) {
		JOptionPane.showMessageDialog(null, "Impossible de se connecter" +
		" � la base de donn�es\n\r" + e,
		"ALERTE", JOptionPane.ERROR_MESSAGE);
		etatConnexion = false;
		JOptionPane.showMessageDialog(null, "     Pour des raisons de s�curit�, le programme va maintenant �tre ferm�" +
				" \n\r  il faudra le relancer et v�rifier les login, mot de passe, nom du serveur et chemin de la base de donn�es avant de lancer l'export",
				"ALERTE", JOptionPane.ERROR_MESSAGE);
		
		System.exit(0);
		}
	}
}
// CONSTRUCTEUR
	public ControleConnexionAgathe(){
	}
// METHODES
// les accesseurs statiques
// ------------------------
	public static ParametresAgathe getParametres(){
	return lesParametres;
	}
	public static boolean getControleConnexion(){
	return etatConnexion;
	}
	public static Connection getConnexion(){
	return laConnectionStatiqueAgathe;
	}
// les autres m�thodes
// -------------------
	public static boolean controle(String Nom, String MotDePasse){
		boolean verificationSaisie;
		if (Nom.equals(lesParametres.getNomUtilisateur())&& MotDePasse.equals(lesParametres.getMotDePasse())){
		verificationSaisie = true;
		}
		else {
		JOptionPane.showMessageDialog(null, "V�rifier votre saisie.",
		"ERREUR", JOptionPane.ERROR_MESSAGE);
		verificationSaisie = false;
		}
		return verificationSaisie;
	}
	public static void fermetureSession(){
		try {
		laConnectionStatiqueAgathe.close();
		}
		catch (SQLException e) {
		JOptionPane.showMessageDialog(null, "Probl�me rencontr�" +
		"� la fermeture de la connexion",
		"ERREUR", JOptionPane.ERROR_MESSAGE);
		}
	}
	public boolean getControleSaisie() {
		// TODO Auto-generated method stub
		return true;
	}



}