package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import modele.Permis;
import modele.Territoire;
import modele.Type;

public class CSVioUtil {

	/**
	 * Lit l'entiereté du CSV pour remplir la base de données avec des permis,des
	 * animaux, des territoires et des types. Si des éléments ont été modifiés ou
	 * des entrées correspondent à des entrées qui sont déja dans la base de
	 * données, ils seront mis à jour.
	 * 
	 * @param file
	 * @return une array long [nb d'enregistrement lu, nb retenu]
	 */
	public long[] read(File file) {
		int readed = 0;
		Set<String> territoires = new HashSet<String>();
		Set<String> types = new HashSet<String>();
		Set<String[]> permis = new HashSet<String[]>();
		long execStart = System.currentTimeMillis();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String ligne;
			br.readLine();
			while ((ligne = br.readLine()) != null) {
				String[] data = ligne.split("\",");
				readed++;
				for (int i = 0; i < data.length; i++) {
					data[i] = data[i].replace("\"", ""); // OK
				}

				try {
					Integer.parseInt(data[0]);
				} catch (NumberFormatException e) {
					continue;
				}

				permis.add(data);
				if (!data[3].equalsIgnoreCase("inconnu") && !data[3].equalsIgnoreCase("null")) {
					territoires.add(data[3]);
				}
				if (!data[4].equalsIgnoreCase("inconnu") && !data[4].equalsIgnoreCase("null")) {
					types.add(data[4]);
				}
			}
			Territoire.importerTerritoires(territoires);
			Type.importerTypes(types);

			for (String[] list : permis) {
				Permis.creerPermis(list);
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new long[] { readed, permis.size(), System.currentTimeMillis() - execStart };
	}

}
