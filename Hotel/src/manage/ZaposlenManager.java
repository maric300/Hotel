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
import entity.Zaposlen.Posao;
import entity.Zaposlen.Posao.*; 

public class ZaposlenManager {
	private List<Zaposlen> zaposleni;
	
	private String osobeFile;
	private String zaposleniFile;
	
	public ZaposlenManager(String zaposleniFile) {
		super();
		this.osobeFile = osobeFile;
		this.zaposleniFile = zaposleniFile;
		this.zaposleni = new ArrayList<Zaposlen>();
	}
	
	
	
	public List<Zaposlen> getZaposleni() {
		return zaposleni;
	}



	public void setZaposleni(List<Zaposlen> zaposleni) {
		this.zaposleni = zaposleni;
	}



	public boolean loadData() {
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(this.zaposleniFile));
			String linija = null;
			while ((linija = br.readLine()) != null) {
				System.out.println(linija);
				String[] tokeni = linija.split(";");
				this.zaposleni.add(new Zaposlen(Posao.valueOf(tokeni[1]), tokeni[2], tokeni[3], tokeni[4], tokeni[5], tokeni[6], tokeni[7], tokeni[8], tokeni[9], Integer.parseInt(tokeni[10]), Integer.parseInt(tokeni[11])));
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
			pw = new PrintWriter(new FileWriter(this.zaposleniFile, false));
			for (Zaposlen z : zaposleni) {
				pw.println(z.toFileString());
			}
			pw.close();
		} catch (IOException e) {
			return false;
		}
		return true;
	}



	public Zaposlen NameToObject(String name) {
		for (Zaposlen zaposlen : zaposleni) {
			if (zaposlen.getEmail().equals(name)) {
				return zaposlen;
			}
		}
		return null;
	}



	public void remove(String email) {
		Zaposlen z = this.NameToObject(email);
		zaposleni.remove(z);
		
	}



	public void edit(Posao valueOf, String ime, String prezime, String pol, String datumRodjenjaStr, String adresa,
			String brojTelefona, String email, String brPasosa, int nivoStrucneSpreme, int staz) {
		Zaposlen z = this.NameToObject(email);
		z.setPosao(valueOf);
		z.setIme(ime);
		z.setPrezime(prezime);
		z.setPol(pol);
		z.setDatumStr(datumRodjenjaStr);
		z.setAdresa(adresa);
		z.setBrojTelefona(brojTelefona);
		z.setEmail(email);
		z.setBrPasosa(brPasosa);
		z.setNivoStrucneSpreme(nivoStrucneSpreme);
		z.setStaz(staz);
		z.setPlata(z.getPlata());
		
	}
	
	
}
