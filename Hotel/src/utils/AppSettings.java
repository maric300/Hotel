package utils;

public class AppSettings {
	private String dodatneUslugeFilename;
	private String gostFilename;
	private String zaposlenFilename;
	private String tipSobeFilename;
	private String rezervacijaFilename;
	
	
	public AppSettings(String gostFilename, String zaposlenFilename, String dodatneUslugeFilename, String tipSobeFilename, String rezervacijaFilename) {
		super();
		this.gostFilename = gostFilename;
		this.zaposlenFilename = zaposlenFilename;
		this.dodatneUslugeFilename = dodatneUslugeFilename;
		this.tipSobeFilename = tipSobeFilename;
		this.rezervacijaFilename = rezervacijaFilename;
	}


	public String getGostFilename() {
		return gostFilename;
	}
	
	public String getZaposlenFilename() {
		return zaposlenFilename;
	}
	
	public String getDodatneUslugeFilename() {
		return dodatneUslugeFilename;
	}


	public String getTipSobeFilename() {
		return tipSobeFilename;
	}

	public String getRezervacijaFilename() {
		return rezervacijaFilename;
	}


	
}
