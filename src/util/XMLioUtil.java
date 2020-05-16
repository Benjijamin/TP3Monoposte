package util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import modele.Permis;

public abstract class XMLioUtil {

	public static void write(List<Permis> liste, String filepath) {
		try {
			BufferedWriter encoder = new BufferedWriter(new FileWriter(filepath));
			encoder.write("<XML-GestionPermis/>\n");
			int progress = 0;

			for (Permis p : liste) {
				String toencode = "";
				toencode += xmlbaliseStart(0, "Permis", new XmlKeyV("numero", p.getNumero()),
						new XmlKeyV("dateDebut", p.getDateDebut()), new XmlKeyV("dateFin", p.getDateFin()));

				toencode += xmlbaliseStart(1, "Territoire", new XmlKeyV("nom", p.getTerritoire().getNom()));
				toencode += xmlbaliseEnd(1, "Territoire");

				toencode += xmlbaliseStart(1, "Animal", new XmlKeyV("nom", p.getAnimal().getNom()),
						new XmlKeyV("sexe", p.getAnimal().getSexe()), new XmlKeyV("poids", p.getAnimal().getPoids()),
						new XmlKeyV("dateNaissance", p.getAnimal().getDateNaissance()),
						new XmlKeyV("couleur", p.getAnimal().getCouleur()));
				toencode += xmlbaliseStart(2, "Type", new XmlKeyV("nom", p.getAnimal().getType().getNom()));
				toencode += xmlbaliseEnd(2, "Type");
				toencode += xmlbaliseStart(2, "Caracteristique", new XmlKeyV("vaccine", p.getAnimal().isVaccine()),
						new XmlKeyV("sterelise", p.getAnimal().isSterelise()),
						new XmlKeyV("micropuce", p.getAnimal().isMicropuce()),
						new XmlKeyV("dangereux", p.getAnimal().isDangereux()));
				toencode += xmlbaliseEnd(2, "Caracteristique");
				toencode += xmlbaliseEnd(1, "Animal");

				toencode += xmlbaliseEnd(0, "Permis");
				encoder.append(toencode);
				encoder.flush();
				System.out.println(++progress + "/" + liste.size());
				System.out.println("TEST");
			}
			encoder.close();
		} catch (IOException e) {
			System.err.println("Erreur d'écriture, impossible d'écrire dans le fichier : " + filepath);
		}
	}

	/**
	 * @param indent le niveau d'indentation de l'élément
	 * @param type   le type de balise
	 * @param values les valeurs interne de la balise sous forme d'objet Key/Value
	 * @return
	 */
	private static String xmlbaliseStart(int indent, String type, XmlKeyV... values) {
		String indentation = "";
		String params = "";
		while (indent > 0) {
			indentation += "\t";
			indent--;
		}
		for (XmlKeyV x : values) {
			params += x.toString();
		}

		return indentation + "<" + type + params + ">\n";

	}

	private static String xmlbaliseEnd(int indent, String type) {
		String indentation = "";
		while (indent > 0) {
			indentation += "\t";
			indent--;
		}
		return indentation + "</" + type + ">\n";
	}

	private static class XmlKeyV {
		private final String K;
		private final String V;

		public XmlKeyV(String K, Object V) {
			this.K = K;
			this.V = V.toString();
		}

		@Override
		public String toString() {
			return " " + K + "=\"" + V + "\"";
		}
	}

}
