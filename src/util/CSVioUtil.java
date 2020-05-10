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

import org.hibernate.Session;

import manager.PermisManager;

import java.sql.Date;

import modele.Animal;
import modele.Permis;

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
					// System.out.println(data[i]);
				}

				int numero = Integer.parseInt(data[0]);
				
				Date dateDebut;
				try {
					dateDebut = Date.valueOf(LocalDate.parse(data[1]));
				} catch (DateTimeException e) {
					dateDebut = new Date(System.currentTimeMillis());
				}
				Date dateFin;
				try {
					dateFin = Date.valueOf(LocalDate.parse(data[2]));
					if(dateFin.getTime() < dateDebut.getTime()) {
						dateFin = new Date(dateDebut.getTime()+31536000000l);
					}
				} catch (DateTimeException e) {
					dateFin = new Date(dateDebut.getTime()+31536000000l);
				}
				
				//L'association avec les objets se fait au niveau du modele
				String territoire = data[3];
				String type = data[4];

				//fix les accents mal encodés
				byte[] bytes = data[5].getBytes(Charset.forName("windows-1252"));
				String nom = new String(bytes,StandardCharsets.UTF_8);
				
				bytes = data[9].getBytes(Charset.forName("windows-1252"));
				String sexe = new String(bytes,StandardCharsets.UTF_8);
				
				bytes = data[10].getBytes(Charset.forName("windows-1252"));
				String couleur = new String(bytes,StandardCharsets.UTF_8);
				
				Date dateNaissance;
				try {
					dateNaissance = Date.valueOf(LocalDate.parse(data[11]));
				} catch (DateTimeException e) {
					dateNaissance = dateDebut;
				}
				
				boolean vaccine = Integer.parseInt(data[12]) == 1;
				boolean sterelise = Integer.parseInt(data[13]) == 1;
				float poids = Float.parseFloat(data[14].replace(',', '.'));
				boolean micropuce = Integer.parseInt(data[15]) == 1;
				boolean dangereux = Integer.parseInt(data[16]) == 1;

				Animal a = new Animal(nom, type, sexe, poids, dateNaissance,
						couleur, vaccine, sterelise, micropuce, dangereux);
				Permis p = new Permis(numero, territoire, dateDebut, dateFin, a);
				
				managerPermis.ajouterPermis(p);
				System.out.println("Permis "+ p + " ajouté dans bd");
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
