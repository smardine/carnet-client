package accesBDDAgathe;

import java.io.IOException;

import ini_Manager.ConfigMgt;
import Utilitaires.GestionRepertoire;

public class ParametresAgathe {
	private String USER;
	private String PASSWORD;
	//private String serveurBD;
	
	private String driverSGBD;
	private String HOSTNAME;
	private String EmplacementBase;
	// Constructeur
	public ParametresAgathe () {
	
		
		ConfigMgt Base = null;
		try {
			Base = new ConfigMgt("AccesBdd.ini",GestionRepertoire.RecupRepTravail()+"\\IniFile\\",'[');
		} catch (NullPointerException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		USER = Base.getValeurDe ("user");
		PASSWORD = Base.getValeurDe("password");
		HOSTNAME = Base.getValeurDe("serveur");
		driverSGBD ="jdbc:firebirdsql";
		EmplacementBase = GestionRepertoire.RecupRepTravail()+"\\Database\\CARNETCLIENT.FDB";
	
}
	public String getNomUtilisateur() {
		return USER;
	}
	public String getMotDePasse() {
		return PASSWORD;
	}
	public String getEmplacementBase() {
		return EmplacementBase;
	}
	public String getDriverSGBD() {
		return driverSGBD;
	}
	public String getHostName(){
		return HOSTNAME;
	}
	
}

