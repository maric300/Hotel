package entity;
public class Gost extends Osoba {

	public Gost(String ime, String prezime, String pol, String datumStr, String adresa, String brojTelefona,
			String email, String brPasosa) {
		super(ime, prezime, pol, datumStr, adresa, brojTelefona, email, brPasosa);
		

		
	}

	@Override
	public String toString() {
		return "Gost " + this.getIme() + " " + this.getPrezime();
	}
	
	public String toFileString() {
		return "gost;" + this.getIme() + ";" + this.getPrezime() + ";" + this.getPol() + ";" + this.getDatumStr() + ";" + this.getAdresa() + ";" + this.getBrojTelefona() + ";" + this.getEmail() + ";" + this.getBrPasosa();
	}
	
}