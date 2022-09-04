package entity;

import java.util.List;

public class DodatnaUsluga {
	private String naziv;
	private int cena;
	
	public DodatnaUsluga(String naziv, int cena) {
		super();
		this.naziv = naziv;
		this.cena = cena;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public int getCena() {
		return cena;
	}

	public void setCena(int cena) {
		this.cena = cena;
	}
	
	public String toFileString() {
		return this.getNaziv() + ";" + this.getCena();
	}
	
	
}