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
import entity.Soba.StatusSobe;
import entity.TipSobe;
import entity.Zaposlen;
import entity.Zaposlen.Posao;
import entity.Zaposlen.Posao.*; 

public class SobaManager {
	private List<Soba> sobe;
	
	private String sobaFile;
	private TipSobeManager tipSobeMng;
	
	public SobaManager(String sobaFile, TipSobeManager tipSobeMng) {
		super();
		this.sobaFile = sobaFile;
		this.tipSobeMng = tipSobeMng;
		this.sobe = new ArrayList<Soba>();
	}
	
	
	
	public List<Soba> getSobe() {
		return sobe;
	}
	
	public Soba IdToObject(int id) {
		for (Soba soba : sobe) {
			if (soba.getId() == id) {
				return soba;
			}
		}
		return null;
	}
	
	public void edit(int oldId, int id, StatusSobe status, TipSobe tipSobe) {
		
		if (id == oldId) {
			Soba s = this.IdToObject(id);
			s.setId(id);
			s.setStatus(status);
			s.setTipSobe(tipSobe);
		}
		else {
			Soba s = this.IdToObject(oldId);
			s.setId(id);
			s.setStatus(status);
			s.setTipSobe(tipSobe);
		}

	}
	
	public void remove(int id) {
		Soba s = this.IdToObject(id);
		sobe.remove(s);
	}



	public boolean loadData() {
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(this.sobaFile));
			String linija = null;
			while ((linija = br.readLine()) != null) {
				System.out.println(linija);
				String[] tokeni = linija.split(";");
				this.sobe.add(new Soba(Integer.parseInt(tokeni[0]), StatusSobe.valueOf(tokeni[1]), tipSobeMng.NameToObject(tokeni[2])));
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
			pw = new PrintWriter(new FileWriter(this.sobaFile, false));
			for (Soba s : sobe) {
				pw.println(s.toFileString());
			}
			pw.close();
		} catch (IOException e) {
			return false;
		}
		return true;
	}



	
}
