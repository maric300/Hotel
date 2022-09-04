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

import entity.CenovnikTipSobe;
import entity.Gost;
import entity.Osoba;
import entity.Soba;
import entity.Soba.StatusSobe;
import entity.TipSobe;
import entity.Zaposlen;
import entity.Zaposlen.Posao;
import entity.Zaposlen.Posao.*; 

public class CenovnikTipSobeManager {
	private List<CenovnikTipSobe> cenovnici;
	
	private String cenovnikFile;
	private CenovnikTipSobeManager cenovnikTipSobeMng;
	
	public CenovnikTipSobeManager(String cenovnikFile) {
		super();
		this.cenovnikFile = cenovnikFile;
		this.cenovnikTipSobeMng = cenovnikTipSobeMng;
		this.cenovnici = new ArrayList<CenovnikTipSobe>();
	}
	
	
	
	public List<CenovnikTipSobe> getCenovnici() {
		return cenovnici;
	}
	
	public CenovnikTipSobe NameToObject(String naziv) {
		for (CenovnikTipSobe cenovnik : cenovnici) {
			if (cenovnik.getNaziv().equals(naziv)) {
				return cenovnik;
			}
		}
		return null;
	}
	

	public void remove(String naziv) {
		CenovnikTipSobe s = this.NameToObject(naziv);
		cenovnici.remove(s);
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
				this.cenovnici.add(new CenovnikTipSobe(tokeni[0], mapa));
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
			for (CenovnikTipSobe s : cenovnici) {
				pw.println(s.toFileString());
			}
			pw.close();
		} catch (IOException e) {
			return false;
		}
		return true;
	}



	
}
