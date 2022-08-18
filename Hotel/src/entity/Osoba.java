package entity;

public class Osoba {
	private String ime, prezime, pol, datumStr, adresa, brojTelefona, email, brPasosa;

	public Osoba(String ime, String prezime, String pol, String datumStr, String adresa, String brojTelefona,
			String email, String brPasosa) {
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.pol = pol;
		this.datumStr = datumStr;
		this.adresa = adresa;
		this.brojTelefona = brojTelefona;
		this.email = email;
		this.brPasosa = brPasosa;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getPol() {
		return pol;
	}

	public void setPol(String pol) {
		this.pol = pol;
	}

	public String getDatumStr() {
		return datumStr;
	}

	public void setDatumStr(String datumStr) {
		this.datumStr = datumStr;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getBrojTelefona() {
		return brojTelefona;
	}

	public void setBrojTelefona(String brojTelefona) {
		this.brojTelefona = brojTelefona;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBrPasosa() {
		return brPasosa;
	}

	public void setBrPasosa(String brPasosa) {
		this.brPasosa = brPasosa;
	}

}