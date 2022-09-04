package entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class CenovnikDodatneUsluge {
	
	private String naziv;
	private Map<LocalDate, Integer> cene = new HashMap<LocalDate, Integer>();
	
	public CenovnikDodatneUsluge(String naziv, Map<LocalDate, Integer> cene) {
		this.naziv = naziv;
		this.cene = cene;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public Map<LocalDate, Integer> getCene() {
		return cene;
	}

	public void setCene(Map<LocalDate, Integer> cene) {
		this.cene = cene;
	}
	
	public String mapToString(Map<LocalDate, Integer> mapa) {
		String str = "";
		for (Entry<LocalDate, Integer> entry : mapa.entrySet()) {
		    LocalDate key = entry.getKey();
//		    String keyStr = String.valueOf(key.getDayOfMonth().) + "." + String.valueOf(key.getMonthValue() + "." + String.valueOf(key.getYear() + ".-"));
		    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
		    String keyStr = key.format(formatter); 
		    Integer value = entry.getValue();
		    
		    str = str + String.join("-", keyStr, String.valueOf(value) + ":");
		    System.out.println("kljuc: " + keyStr);
		    System.out.println(str);
		}
		return str;
		
	}
	
	public String toFileString() {
		return String.valueOf(this.getNaziv()) + ";" + this.mapToString(cene);
	}
	
}