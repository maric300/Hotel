package manage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.net.ssl.SSLEngineResult.Status;

import entity.CenovnikDodatneUsluge;
import entity.CenovnikTipSobe;
import entity.Gost;
import entity.Osoba;
import entity.Soba;
import entity.Soba.StatusSobe;
import entity.TipSobe;
import entity.Zaposlen;
import entity.Zaposlen.Posao;
import entity.Zaposlen.Posao.*; 

public class CenovnikDodatneUslugeManager {
	private List<CenovnikDodatneUsluge> cenovnici;
	
	private String cenovnikFile;
	private CenovnikDodatneUslugeManager cenovnikDodatneUslugeMng;
	
	public CenovnikDodatneUslugeManager(String cenovnikFile) {
		super();
		this.cenovnikFile = cenovnikFile;
		this.cenovnikDodatneUslugeMng = cenovnikDodatneUslugeMng;
		this.cenovnici = new ArrayList<CenovnikDodatneUsluge>();
	}
	
	
	
	public List<CenovnikDodatneUsluge> getCenovnici() {
		return cenovnici;
	}
	
	public CenovnikDodatneUsluge NameToObject(String naziv) {
		for (CenovnikDodatneUsluge cenovnik : cenovnici) {
			if (cenovnik.getNaziv().equals(naziv)) {
				return cenovnik;
			}
		}
		return null;
	}
	

	public void remove(String naziv) {
		CenovnikDodatneUsluge s = this.NameToObject(naziv);
		cenovnici.remove(s);
	}
	
	public LocalDate getLatestDate(Map<LocalDate, Integer> mapa) {
		LocalDate latest = LocalDate.now();
		for (LocalDate datum : mapa.keySet()) {
			if (LocalDate.now().isAfter(datum)) {
				latest = datum;
			}
		}
		System.out.println(latest.toString());
		return latest;
	}



	public boolean loadData() {
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(this.cenovnikFile));
			String linija = null;
			while ((linija = br.readLine()) != null) {
				String[] tokeni = linija.split(";");
				Map<LocalDate, Integer> mapa = new HashMap<LocalDate, Integer>();
				if (tokeni.length > 1) {
					String[] tokeniHash = tokeni[1].split(":");
					for (String s : tokeniHash) {
						String[] tokeniPar = s.split("-");
						LocalDate ld = LocalDate.parse(tokeniPar[0], DateTimeFormatter.ofPattern("dd.MM.yyyy."));
						mapa.put(ld, Integer.parseInt(tokeniPar[1]));
					}
				}		
				this.cenovnici.add(new CenovnikDodatneUsluge(tokeni[0], mapa));
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
			pw = new PrintWriter(new FileWriter(this.cenovnikFile, false));
			for (CenovnikDodatneUsluge s : cenovnici) {
				pw.println(s.toFileString());
			}
			pw.close();
		} catch (IOException e) {
			return false;
		}
		return true;
	}



	
}
