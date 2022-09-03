package manage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.net.ssl.SSLEngineResult.Status;

import entity.SredjivanjeSoba;


public class SredjivanjeSobaManager {
	private List<SredjivanjeSoba> sredjivanjeSobaLista;
	
	private String sredjivanjeSobaFile;
	
	public SredjivanjeSobaManager(String sredjivanjeSobaFile) {
		super();
		this.sredjivanjeSobaFile = sredjivanjeSobaFile;
		this.sredjivanjeSobaLista = new ArrayList<SredjivanjeSoba>();
	}
	
	
	
	public List<SredjivanjeSoba> getSredjivanjeSobaLista() {
		return sredjivanjeSobaLista;
	}
	
	public SredjivanjeSoba DateToObject(String datumStr) {
		for(SredjivanjeSoba sredjivanjeSoba : this.sredjivanjeSobaLista) {
			if (sredjivanjeSoba.getDatumCiscenjaStr().equals(datumStr)) {
				return sredjivanjeSoba;
			}
		}
		return null;
	}
	public boolean loadData() {
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(this.sredjivanjeSobaFile));
			String linija = null;
			while ((linija = br.readLine()) != null) {
				String[] tokeni = linija.split(";");
				this.sredjivanjeSobaLista.add(new SredjivanjeSoba(tokeni[0], tokeni[1], Integer.parseInt(tokeni[2])));
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
			pw = new PrintWriter(new FileWriter(this.sredjivanjeSobaFile, false));
			for (SredjivanjeSoba s : sredjivanjeSobaLista) {
				pw.println(s.toFileString());
			}
			pw.close();
		} catch (IOException e) {
			return false;
		}
		return true;
	}



	
}
