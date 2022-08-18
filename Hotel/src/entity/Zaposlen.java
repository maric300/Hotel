package entity;

public class Zaposlen extends Gost {

	public enum Posao {
		RECEPCIONER,
		SOBARICA
	}
	private Posao posao;
	private int nivoStrucneSpreme;
	private int staz;
	private int plata;

	public Zaposlen(Posao posao, String ime, String prezime, String pol, String datumStr, String adresa, String brojTelefona,
			String email, String brPasosa, int nivoStrucneSpreme, int staz) {
		super(ime, prezime, pol, datumStr, adresa, brojTelefona, email, brPasosa);
		this.nivoStrucneSpreme = nivoStrucneSpreme;
		this.staz = staz;
		this.plata = 15000 * this.nivoStrucneSpreme + 3000 * this.staz;
		this.posao = posao;
		// TODO Auto-generated constructor stub
	}
	
	

	public Posao getPosao() {
		return posao;
	}



	public void setPosao(Posao posao) {
		this.posao = posao;
	}



	public int getNivoStrucneSpreme() {
		return nivoStrucneSpreme;
	}

	public void setNivoStrucneSpreme(int nivoStrucneSpreme) {
		this.nivoStrucneSpreme = nivoStrucneSpreme;
	}

	public int getStaz() {
		return staz;
	}

	public void setStaz(int staz) {
		this.staz = staz;
	}

	public int getPlata() {
		return plata;
	}

	public void setPlata(int plata) {
		this.plata = plata;
	}



	@Override
	public String toString() {
		return "Zaposlen " + this.getIme() + " " + this.getPrezime();
	}
	
	public String toFileString() {
		return "zaposlen;" + this.getPosao().name() + ";" + this.getIme() + ";" + this.getPrezime() + ";" + this.getPol() + ";" + this.getDatumStr() + ";" + this.getAdresa() + ";" + this.getBrojTelefona() + ";" + this.getEmail() + ";" + this.getBrPasosa() + ";" + this.getNivoStrucneSpreme() + ";" + this.getStaz() + ";" + this.getPlata();
	}
	
	

//	public static void main(String[] args) {
//		Zaposlen nekiLik = new Zaposlen("neki", "lik", "musko", "12.5.2022", "ostojiceva 5", "06122314", "nekilik@gmai.com", "123", 3, 3);
//		System.out.println("Plata od nekog lika je " + nekiLik.getPlata());
//	}
}
