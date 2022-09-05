package entity;

public class SobaIzvestaj {
	private int id;
	private String tipSobeNaziv;
	private int brojNocenja;
	private int prihodi;
	public SobaIzvestaj(int id, String tipSobeNaziv, int brojNocenja, int prihodi) {
		super();
		this.id = id;
		this.tipSobeNaziv = tipSobeNaziv;
		this.brojNocenja = brojNocenja;
		this.prihodi = prihodi;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTipSobeNaziv() {
		return tipSobeNaziv;
	}
	public void setTipSobeNaziv(String tipSobeNaziv) {
		this.tipSobeNaziv = tipSobeNaziv;
	}
	public int getBrojNocenja() {
		return brojNocenja;
	}
	public void setBrojNocenja(int brojNocenja) {
		this.brojNocenja = brojNocenja;
	}
	public int getPrihodi() {
		return prihodi;
	}
	public void setPrihodi(int prihodi) {
		this.prihodi = prihodi;
	}
	@Override
	public String toString() {
		return "id - " + this.id + "|" + this.tipSobeNaziv + "|" + "Broj nocenja - " + this.brojNocenja + "|Ukupan prihod - " + this.prihodi;
	}
	
	
	
}