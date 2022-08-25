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

import javax.net.ssl.SSLEngineResult.Status;

import entity.Gost;
import entity.Osoba;
import entity.Soba;
import entity.TipSobe;
import entity.Zaposlen;
import entity.Zaposlen.Posao;
import entity.Zaposlen.Posao.*; 

public class SobaManager {
	private List<Soba> sobe;
	
	private String sobaFile;
	
	public SobaManager(String sobaFile) {
		super();
		this.sobaFile = sobaFile;
		this.sobe = new ArrayList<Soba>();
	}
	
	
	
	public List<Soba> getSobe() {
		return sobe;
	}
	
//	public Soba NameToObject(String name) {
//		for (TipSobe tip : tipoviSobe) {
//			if (tip.getNaziv().equals(name)) {
//				return tip;
//			}
//		}
//		return null;
//	}



	public boolean loadData() {
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(this.sobaFile));
			String linija = null;
			while ((linija = br.readLine()) != null) {
				System.out.println(linija);
				String[] tokeni = linija.split(";");
				this.sobe.add(new Soba(Integer.parseInt(tokeni[0]), ,Status.valueOf(tokeni[1]), ));
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
