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
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import entity.CenovnikTipSobe;
import entity.Gost;
import entity.Osoba;
import entity.Rezervacija;
import entity.Rezervacija.Status;
import entity.Soba;
import entity.TipSobe;
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

	public List<Rezervacija> getRezervacijeGosta(String email) {
		List<Rezervacija> returnList = new ArrayList<Rezervacija>();
		for (Rezervacija rezervacija : this.getRezervacije()) {
			if (rezervacija.getUsernameGosta().equals(email)) {
				returnList.add(rezervacija);
			}
		}
		return returnList;
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
				this.rezervacije.add(new Rezervacija(Integer.parseInt(tokeni[0]), Status.valueOf(tokeni[1]), tokeni[2] ,tipSobeMng.NameToObject(tokeni[3]), dodatnaUslugaMng.ListToObject(tokeniUslugeList), tokeni[5], tokeni[6], Integer.parseInt(tokeni[7]), Integer.parseInt(tokeni[8])));
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



	public Rezervacija IdToObject(int id) {
		for (Rezervacija rezervacija : rezervacije) {
			if (rezervacija.getId() == id) {
				return rezervacija;
			}
		}
		return null;
	}



	public void remove(int id) {
		Rezervacija r = this.IdToObject(id);
		rezervacije.remove(r);
		
	}
	
	public int CenaIzOpsegaRezervacija(LocalDate opsegDate1, LocalDate opsegDate2, LocalDate ldRezIn, LocalDate ldRezOut, Rezervacija rezervacija, ManagerFactory factoryMng) {
		int ukupnaCena = 0;
//			LocalDate ldRezIn = LocalDate.parse(rezervacija.getCheckInDateStr(), DateTimeFormatter.ofPattern("dd.MM.uuuu."));
//			LocalDate ldRezOut = LocalDate.parse(rezervacija.getCheckOutDateStr(), DateTimeFormatter.ofPattern("dd.MM.uuuu."));
		for(LocalDate startDate = opsegDate1; startDate.isBefore(opsegDate2); startDate = startDate.plusDays(1)) {
			int tempCena = 0;
			if((startDate.isAfter(ldRezIn) || startDate.isEqual(ldRezIn)) && (startDate.isBefore(ldRezOut) || startDate.isEqual(ldRezOut)) && (rezervacija.getStatus().equals(Status.POTVRDJENA) || rezervacija.getStatus().equals(Status.OTKAZANA))) {
				Boolean found = false;
				TipSobe tipSobe = rezervacija.getTipSobe();
				CenovnikTipSobe cenovnik = factoryMng.getCenovnikTipSobeMng().NameToObject(tipSobe.getNaziv());
//				-------------------------------
				if (cenovnik != null) {
					Map<LocalDate, Integer> hmCenaTipSobe = cenovnik.getCene();
						tempCena = factoryMng.getCenovnikTipSobeMng().DateToInt(startDate, hmCenaTipSobe);
						if (tempCena == 0) {
							ukupnaCena += tipSobe.getCenaPoNocenju();
						}
						else {
					    	ukupnaCena += tempCena;

						}
						
				}
				else {
					ukupnaCena += tipSobe.getCenaPoNocenju();
				}
		}
			
		
		
		}
		return ukupnaCena;
	}
		
	
	
}
