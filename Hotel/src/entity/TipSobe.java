package entity;

public class TipSobe {
	private int brojMesta;
	private String naziv;
	public TipSobe(int brojMesta, String naziv) {
		super();
		this.brojMesta = brojMesta;
		this.naziv = naziv;
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
	
}