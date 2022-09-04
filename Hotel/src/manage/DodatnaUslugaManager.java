package manage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import entity.DodatnaUsluga;
import entity.Gost;
import entity.TipSobe;

public class DodatnaUslugaManager {
	
	private String dodatnaUslugaFile;
	private List<DodatnaUsluga> listaDodatnihUsluga;
	
	public DodatnaUslugaManager(String dodatnaUslugaFile) {
		this.dodatnaUslugaFile = dodatnaUslugaFile;
		this.listaDodatnihUsluga = new ArrayList<DodatnaUsluga>();
		
	}
	
	public List<DodatnaUsluga> getDodatneUsluge() {
		return listaDodatnihUsluga;
	}
	
	public DodatnaUsluga NameToObject(String name) {
		for (DodatnaUsluga dodatnaUsluga : listaDodatnihUsluga) {
			if (dodatnaUsluga.getNaziv().equals(name)) {
				return dodatnaUsluga;
			}
		}
		return null;
	}
	
	public List<DodatnaUsluga> ListToObject(List<String> dodatnaUslugaStrList) {
		List<DodatnaUsluga> listaUslugaRet = new ArrayList<DodatnaUsluga>();
		for(int i = 0;i< dodatnaUslugaStrList.size(); i++) {
			for (DodatnaUsluga dodatnaUsluga : listaDodatnihUsluga) {
				if (dodatnaUsluga.getNaziv().equals(dodatnaUslugaStrList.get(i))) {
					listaUslugaRet.add(dodatnaUsluga);
				}
			}
		}
		return listaUslugaRet;
	}

	
	public boolean loadData() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(this.dodatnaUslugaFile));
			String linija = null;
			while ((linija = br.readLine()) != null) {
				String[] tokeni = linija.split(";");
				this.listaDodatnihUsluga.add(new DodatnaUsluga(tokeni[0], Integer.parseInt(tokeni[1])));
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
			pw = new PrintWriter(new FileWriter(this.dodatnaUslugaFile, false));
			for (DodatnaUsluga t : listaDodatnihUsluga) {
				pw.println(t.toFileString());
			}
			pw.close();
		} catch (IOException e) {
			return false;
		}
		return true;
	}

	public void edit(String oldNaziv, String naziv, int cena) {
		if (oldNaziv.equals(naziv)) {
			DodatnaUsluga usluga = this.NameToObject(naziv);
			usluga.setNaziv(naziv);
			usluga.setCena(cena);
		}
		else {
			DodatnaUsluga usluga = this.NameToObject(oldNaziv);
			usluga.setNaziv(naziv);
			usluga.setCena(cena);
		}
		
	}
	
	public void remove(String naziv) {
		DodatnaUsluga usluga = this.NameToObject(naziv);
		this.listaDodatnihUsluga.remove(usluga);
		
	}
}