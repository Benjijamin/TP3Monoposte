package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import org.hibernate.Session;

import java.sql.Date;

import modele.Animal;
import modele.Permis;
import modele.manager.PermisManager;

public class CSVioUtil {

	/**
	 * Lit l'entiereté du CSV pour remplir la base de données avec des permis. Si
	 * des éléments ont été modifiés ou des entrées correspondent à des entrées qui
	 * sont déja dans la base de données, ils seront mis à jour.
	 * 
	 * @param file
	 */
	public void read(File file) {
		PermisManager managerPermis = new PermisManager();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String ligne;

			br.readLine();
			while ((ligne = br.readLine()) != null) {

				System.out.println();
				String[] data = ligne.split("\",");

				for (int i = 0; i < data.length; i++) {
					data[i] = data[i].replace("\"", "");
				}
				
				try {
					Integer.parseInt(data[0]);
				} catch (NumberFormatException e) {
					continue;
				}

				List<String> stringData = new ArrayList<String>();
				stringData.add(data[0]);
				stringData.add(data[3]);
				stringData.add(data[1]);
				stringData.add(data[2]);
				stringData.add(data[5]);
				stringData.add(data[4]);
				stringData.add(data[9]);
				stringData.add(data[14]);
				stringData.add(data[11]);
				stringData.add(data[10]);
				stringData.add(data[12]);
				stringData.add(data[13]);
				stringData.add(data[15]);
				stringData.add(data[16]);


				Permis.creerPermisDB(stringData);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		CSVioUtil c = new CSVioUtil();
		File f = new File(c.getClass().getResource("/Fichiers_CSV/permis-animaux.csv").getFile());
		c.read(f);
	}
}
