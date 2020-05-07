package util;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import modele.Permis;

public abstract class XMLioUtil {

	public static void write(List<Permis> liste, String filepath) {
		try {
			XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(filepath)));
			encoder.writeObject(liste);
			encoder.close();
		} catch (IOException e) {
			System.err.println("Erreur d'écriture, impossible d'écrire dans le fichier : " + filepath);
		}
	}
	
	public static List<Permis> read(String filepath) {
		List<Permis> retour = null;
		try {
			XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(filepath)));
			retour = (List<Permis>)decoder.readObject();
			decoder.close();
		} catch (Exception e) {
			System.err.println("Erreur de Lecture, impossible de lire le fichier : " + filepath);
		}
		return retour;
	}

}
