package Utilitaires;


import java.io.IOException;
import java.util.Map;

public class VariableEnvironement {
	
	
	
	/**
	 * Affiche dans la console de debug ttes les variables d'environnement java
	 * 
	 * 
	 */
	@SuppressWarnings("unchecked")
	public static void VarEnvJavaTotal (){
		java.util.Properties p = System.getProperties();
	     java.util.Enumeration keys = p.keys();
	     String key = null;
	     while( keys.hasMoreElements() ) {
	         key = keys.nextElement().toString();
	         System.out.println( key + ": " + System.getProperty(key) );
	         try {
	 			Historique.ecrire (key + ": " + System.getProperty(key));
	 		} catch (IOException e2) {
	 			// TODO Auto-generated catch block
	 			e2.printStackTrace();
	 		}
	     }
		
	}
	
	/**
	 * Affiche dans la console de debug ttes les variables d'environnement systeme
	 * 
	 * 
	 */
	public static void VarEnvSystemTotal (){
		Map<String, String> env = System.getenv();
        
		for (String envName : env.keySet()) {
            System.out.format("%s=%s%n", envName, env.get(envName));
            try {
	 			Historique.ecrire (envName + ": " + env.get(envName));
	 		} catch (IOException e2) {
	 			// TODO Auto-generated catch block
	 			e2.printStackTrace();
	 		}
        }
	}
	/**
	 * Recupere sous forme de String variables d'environnement systeme souhait�e.
	 * @param VarEnvRecherch�e -String
	 * @return env -String
	 * 
	 */
	public static String VarEnvSystem (String VarEnvRecherch�e){
		String env = System.getenv(VarEnvRecherch�e);
        	
		return env;
	}
	/**
	 * Recupere sous forme de String variables d'environnement java souhait�e.
	 * @param VarEnvRecherch�e -String
	 * @return var -String
	 * 
	 */
	public static String VarEnvJava (String VarEnvRecherch�e){
		String var = System.getProperty(VarEnvRecherch�e);
		return var;
	}

}