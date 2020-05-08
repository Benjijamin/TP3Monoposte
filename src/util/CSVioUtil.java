package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import java.sql.Date;

import modele.Permis;

public class CSVioUtil {

	/**
	 * Lit l'entiereté du CSV pour remplir la base de données avec des permis.
	 * Si des éléments ont été modifiés ou des entrées correspondent à des entrées qui sont déja dans la base de données, ils seront mises à jour.
	 * @param file
	 */
	public void read(File file) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String ligne;

			br.readLine();
			while ((ligne = br.readLine()) != null) {

				System.out.println();
				String[] data = ligne.split("\",");

				for (int i = 0; i < data.length; i++) {
					data[i] = data[i].replace("\"", "");
					// System.out.println(data[i]);
				}

				int numero = Integer.parseInt(data[0]);
				Date dateDebut = Date.valueOf(LocalDate.parse(data[1]));
				Date dateFin = Date.valueOf(LocalDate.parse(data[2]));
				String territoire = data[3];
				String type = data[4];
				String nom = data[5];
				String sexe = data[9];
				String couleur = data[10];
				Date dateNaissance;
				try {
					dateNaissance = Date.valueOf(LocalDate.parse(data[11]));
				} catch (DateTimeException e) {
					dateNaissance = null;
				}
				boolean vaccine = data[12] == "1";
				boolean sterelise = data[13] == "1";
				float poids = Float.parseFloat(data[14].replace(',', '.'));
				boolean micropuce = data[15] == "1";
				boolean dangereux = data[16] == "1";

				Permis p = new Permis(numero, territoire, dateDebut, dateFin, nom, type, sexe, poids, dateNaissance,
						couleur, vaccine, sterelise, micropuce, dangereux);
				
				session.saveOrUpdate(p);

			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.getTransaction().commit();
	}
	
	public static void main(String[] args) {
		CSVioUtil c = new CSVioUtil();
		File f = new File(c.getClass().getResource("/Fichiers_CSV/permis-animaux.csv").getFile());
		c.read(f);
	}
}
