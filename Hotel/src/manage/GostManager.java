package manage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entity.Gost;
import entity.Osoba;
import entity.Zaposlen;

public class GostManager {
	private List<Gost> gosti;
	
	private String gostiFile;
	
	public GostManager(String gostiFile) {
		super();
		this.gostiFile = gostiFile;
		this.gosti = new ArrayList<Gost>();
	}
	
	
	
	public List<Gost> getGosti() {
		return gosti;
	}



	public void setGosti(List<Gost> gosti) {
		this.gosti = gosti;
	}



	public boolean loadData() {

		try {
			BufferedReader br = new BufferedReader(new FileReader(this.gostiFile));
			String linija = null;
			while ((linija = br.readLine()) != null) {
				String[] tokeni = linija.split(";");
				this.gosti.add(new Gost(tokeni[1], tokeni[2], tokeni[3], tokeni[4], tokeni[5], tokeni[6], tokeni[7], tokeni[8]));
			}
			br.close();
		} catch (IOException e) {
			return false;
		}
		return true;
	}



	public boolean saveData() {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new FileWriter(this.gostiFile, false));
			for (Gost g : gosti) {
				pw.println(g.toFileString());
			}
			pw.close();
		} catch (IOException e) {
			return false;
		}
		return true;
	}
	
	
}
