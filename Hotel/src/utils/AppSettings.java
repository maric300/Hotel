package utils;

public class AppSettings {
	private String dodatneUslugeFilename;
	private String gostFilename;
	private String zaposlenFilename;
	private String tipSobeFilename;
	private String rezervacijaFilename;
	private String sobaFilename;
	private String sredjivanjeSobaFilename;
	
	
	public AppSettings(String gostFilename, String zaposlenFilename, String dodatneUslugeFilename, String tipSobeFilename, String rezervacijaFilename, String sobaFilename, String sredjivanjeSobaFilename) {
		super();
		this.gostFilename = gostFilename;
		this.zaposlenFilename = zaposlenFilename;
		this.dodatneUslugeFilename = dodatneUslugeFilename;
		this.tipSobeFilename = tipSobeFilename;
		this.rezervacijaFilename = rezervacijaFilename;
		this.sobaFilename = sobaFilename;
		this.sredjivanjeSobaFilename = sredjivanjeSobaFilename;
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
	
	public String getSobaFilename() {
		return sobaFilename;
	}
	public String getSredjivanjeSobaFilename() {
		return sredjivanjeSobaFilename;
	}


	
}
