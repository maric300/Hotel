package entity;

public class TipSobe {
	private int brojMesta;
	private String naziv;
	private int cenaPoNocenju;
	public TipSobe(int brojMesta, String naziv, int cenaPoNocenju) {
		super();
		this.brojMesta = brojMesta;
		this.naziv = naziv;
		this.cenaPoNocenju = cenaPoNocenju;
	}
	public int getBrojMesta() {
		return brojMesta;
	}
	public void setBrojMesta(int brojMesta) {
		this.brojMesta = brojMesta;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	
	public int getCenaPoNocenju() {
		return cenaPoNocenju;
	}
	public void setCenaPoNocenju(int cenaPoNocenju) {
		this.cenaPoNocenju = cenaPoNocenju;
	}
	public String toFileString() {
		return this.getBrojMesta() + ";" + this.getNaziv() + ";" + this.getCenaPoNocenju();
	}
	
	
}