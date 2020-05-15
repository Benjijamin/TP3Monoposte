package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;


import modele.Permis;
import modele.Territoire;
import modele.Type;
import modele.manager.PermisManager;
import modele.manager.TerritoireManager;
import modele.manager.TypeManager;

public class CSVioUtil {

	/**
	 * Lit l'entiereté du CSV pour remplir la base de données avec des permis,des animaux, des territoires et des types. Si
	 * des éléments ont été modifiés ou des entrées correspondent à des entrées qui
	 * sont déja dans la base de données, ils seront mis à jour.
	 * 
	 * @param file
	 */
	public void read(File file) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String ligne;
			Set<String> territoires = new HashSet<String>();
			Set<String> types = new HashSet<String>();
			Set<String[]> permis = new HashSet<String[]>();
			br.readLine();
			while ((ligne = br.readLine()) != null) {
				String[] data = ligne.split("\",");

				for (int i = 0; i < data.length; i++) {
					data[i] = data[i].replace("\"", "");
				}
				
				try {
					Integer.parseInt(data[0]);
				} catch (NumberFormatException e) {
					continue;
				}

				

				permis.add(data);
				territoires.add(data[3]);
				types.add(data[4]);
				
			}
			new Territoire().importerTerritoires(territoires);
			new Type().importerTypes(types);
			
			Permis p = new Permis();
			for (String[] list : permis) {
				p.creerPermisDB(list);
			}
			
			
			System.out.println("importation fini");
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
