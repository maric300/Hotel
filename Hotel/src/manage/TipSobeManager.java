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
import entity.TipSobe;
import entity.Zaposlen;
import entity.Zaposlen.Posao;
import entity.Zaposlen.Posao.*; 

public class TipSobeManager {
	private List<TipSobe> tipoviSobe;
	
	private String tipSobeFile;
	
	public TipSobeManager(String tipSobeFile) {
		super();
		this.tipSobeFile = tipSobeFile;
		this.tipoviSobe = new ArrayList<TipSobe>();
	}
	
	
	
	public List<TipSobe> getTipoviSobe() {
		return tipoviSobe;
	}
	
	public TipSobe NameToObject(String name) {
		for (TipSobe tip : tipoviSobe) {
			if (tip.getNaziv().equals(name)) {
				return tip;
			}
		}
		return null;
	}
	
	public void edit(int brojMesta, String oldNaziv, String naziv, int cenaPoNocenju) {
		
		if(oldNaziv.equals(naziv)) {
			TipSobe tipSobe = this.NameToObject(naziv);
			tipSobe.setBrojMesta(brojMesta);
			tipSobe.setCenaPoNocenju(cenaPoNocenju);
			tipSobe.setNaziv(naziv);
		}
		
		else {
			TipSobe tipSobe = this.NameToObject(oldNaziv);
			tipSobe.setBrojMesta(brojMesta);
			tipSobe.setCenaPoNocenju(cenaPoNocenju);
			tipSobe.setNaziv(naziv);
		}
		
		
	}
	public void remove(String naziv) {
		TipSobe tipSobe = this.NameToObject(naziv);
		this.tipoviSobe.remove(tipSobe);
	}



	public boolean loadData() {
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(this.tipSobeFile));
			String linija = null;
			while ((linija = br.readLine()) != null) {
				System.out.println(linija);
				String[] tokeni = linija.split(";");
				this.tipoviSobe.add(new TipSobe(Integer.parseInt(tokeni[0]), tokeni[1], Integer.parseInt(tokeni[2])));
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
			pw = new PrintWriter(new FileWriter(this.tipSobeFile, false));
			for (TipSobe t : tipoviSobe) {
				pw.println(t.toFileString());
			}
			pw.close();
		} catch (IOException e) {
			return false;
		}
		return true;
	}
	
	
}
