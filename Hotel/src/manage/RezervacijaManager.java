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
import entity.Rezervacija;
import entity.Rezervacija.Status;
import entity.Zaposlen;
import entity.Zaposlen.Posao;
import entity.Zaposlen.Posao.*; 

public class RezervacijaManager {
	private List<Rezervacija> rezervacije;
	private TipSobeManager tipSobeMng;
	private DodatnaUslugaManager dodatnaUslugaMng;
	
	private String rezervacijaFile;
	
	public RezervacijaManager(String rezervacijaFile, TipSobeManager tipSobeMng, DodatnaUslugaManager dodatnaUslugaMng) {
		super();
		this.rezervacijaFile = rezervacijaFile;
		this.rezervacije = new ArrayList<Rezervacija>();
		this.tipSobeMng = tipSobeMng;
		this.dodatnaUslugaMng = dodatnaUslugaMng;
	}
	
	
	
	public List<Rezervacija> getRezervacije() {
		return rezervacije;
	}



	public void setRezervacije(List<Rezervacija> rezervacije) {
		this.rezervacije = rezervacije;
	}



	public boolean loadData() {
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(this.rezervacijaFile));
			String linija = null;
			while ((linija = br.readLine()) != null) {
				String[] tokeni = linija.split(";");
				String[] tokeniUsluge = tokeni[4].split(":");
				List<String> tokeniUslugeList = new ArrayList<String>();
				for (String usluga : tokeniUsluge) {
					tokeniUslugeList.add(usluga);
				}
				this.rezervacije.add(new Rezervacija(Integer.parseInt(tokeni[0]), Status.valueOf(tokeni[1]), tokeni[2] ,tipSobeMng.NameToObject(tokeni[3]), dodatnaUslugaMng.ListToObject(tokeniUslugeList), tokeni[4], tokeni[6]));
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
			pw = new PrintWriter(new FileWriter(this.rezervacijaFile, false));
			for (Rezervacija r: rezervacije) {
				pw.println(r.toFileString());
			}
			pw.close();
		} catch (IOException e) {
			return false;
		}
		return true;
	}
	
	
}
