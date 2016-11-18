import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Echantillonneur {
	
	public static void help() {
		System.out.println("java -cp Echantillonneur.jar Echantillonneur $Path_BDD $Path_Sortie $Nombre_Echantillonnage $Mode_Verbeux(Optionnel)");
		System.out.println("$Mode_Verbeux : 0 -> rien(default) | 1 -> avancement");
	}

	public static void main(String[] args) throws IOException {

		//Variables d'éxécution 
		int nbEchan = 0;
		int modeVerbeux = 0;
		String pathIn = null;
		String pathOut = null;
		
		//Traitement entrées
		if (args.length < 3 && args.length > 4) {
			Echantillonneur.help();
		} else {
			pathIn = args[0];
			pathOut = args[1];
			nbEchan = Integer.parseInt(args[2]);
			
			if (args.length == 4) {
				modeVerbeux = Integer.parseInt(args[3]);
			}
		}
		
		
		//Variable de ligne
		String ligne = null;
		
		//Structure de fichier
		FileWriter out = new FileWriter(pathOut);
		
		//Mise en place de l'ecriture sur le fichier
		BufferedWriter writer = new BufferedWriter(out);
		
		//Mise en place de l'entete de fichier
		int nbLigneEntete = 0;
		BufferedReader reader1 = new BufferedReader(new FileReader(pathIn));
		while ( (ligne = reader1.readLine()).trim().compareTo("@data") != 0) {
			writer.write(ligne+"\n");
			nbLigneEntete++;
		}
		writer.write(ligne+"\n");
		reader1.close();
		
		//Borne pour aleatoire
		int nbLigne = 0;
		BufferedReader reader2 = new BufferedReader(new FileReader(pathIn));
		while ( (ligne = reader2.readLine()) != null) {
			nbLigne++;
		}		
		reader2.close();
		//Echantillonnage aléatoire
		for (int j = 0; j < nbEchan; j++) {
						
			BufferedReader reader3 = new BufferedReader(new FileReader(pathIn));
			Random rand = new Random();
			int nombreAleatoire = rand.nextInt(nbLigne - nbLigneEntete + 1) + nbLigneEntete;

			if (modeVerbeux == 1) {
				System.out.println("-----------------------");
				System.out.println("Pioche : "+j);
				System.out.println("Aléatoire : "+nombreAleatoire);
				System.out.println("-----------------------");
			}
			
			
			for (int k = 0; k < nombreAleatoire; k++) {
				ligne = reader3.readLine();
			}
						
			writer.write(ligne+"\n");
			reader3.close();
						
		}
		
		//Close des fichiers
		writer.close();
		out.close();
	}

}
