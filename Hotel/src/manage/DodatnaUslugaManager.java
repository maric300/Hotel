package manage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import entity.DodatnaUsluga;
import entity.Gost;

public class DodatnaUslugaManager {
	
	private String dodatnaUslugaFile;
	private List<DodatnaUsluga> listaDodatnihUsluga;
	
	public DodatnaUslugaManager(String dodatnaUslugaFile) {
		this.dodatnaUslugaFile = dodatnaUslugaFile;
		this.listaDodatnihUsluga = new ArrayList<DodatnaUsluga>();
		
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
}