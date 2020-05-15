package util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import modele.Permis;

public abstract class XMLioUtil {

	public static void write(List<Permis> liste, String filepath) {
		try {
			XMLtextWriter t;
			FileWriter encoder = new FileWriter(filepath);
			encoder.write("<XML-GestionPermis/>");
			for(Permis p : liste) {
				
			}
			encoder.close();
		} catch (IOException e) {
			System.err.println("Erreur d'écriture, impossible d'écrire dans le fichier : " + filepath);
		}
	}
	
/**
 * @param indent le niveau d'indentation de l'élément
 * @param type le type de balise
 * @param values les valeurs interne de la balise sous forme de string XML valeur="123"
 * @return
 */
	private String xmlbaliseStart(int indent, String type, String...values) {
		String indentation = "";
		String params;
		
		
		return indentation + "<"+type+">";

	}
	
	private String xmlbaliseEnd(int indent) {
		String indentation = "";
		while(indent>0) {
			indentation += "\t";
			indent--;
		}
		return indentation + "\n</>";
	}

}
