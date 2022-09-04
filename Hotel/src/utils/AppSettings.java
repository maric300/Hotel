package utils;

public class AppSettings {
	private String dodatneUslugeFilename;
	private String gostFilename;
	private String zaposlenFilename;
	private String tipSobeFilename;
	private String rezervacijaFilename;
	private String sobaFilename;
	private String sredjivanjeSobaFilename;
	private String cenovnikTipSobeFilename;
	private String cenovnikDodatneUslugeFilename;
	
	
	public AppSettings(String gostFilename, String zaposlenFilename, String dodatneUslugeFilename, String tipSobeFilename, String rezervacijaFilename, String sobaFilename, String sredjivanjeSobaFilename, String cenovnikTipSobeFilename, String cenovnikDodatneUslugeFilename) {
		super();
		this.gostFilename = gostFilename;
		this.zaposlenFilename = zaposlenFilename;
		this.dodatneUslugeFilename = dodatneUslugeFilename;
		this.tipSobeFilename = tipSobeFilename;
		this.rezervacijaFilename = rezervacijaFilename;
		this.sobaFilename = sobaFilename;
		this.sredjivanjeSobaFilename = sredjivanjeSobaFilename;
		this.cenovnikTipSobeFilename = cenovnikTipSobeFilename;
		this.cenovnikDodatneUslugeFilename = cenovnikDodatneUslugeFilename;

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
	
	public String getCenovnikTipSobeFilename() {
		return cenovnikTipSobeFilename;
	}
	public String getCenovnikDodatneUslugeFilename() {
		return cenovnikDodatneUslugeFilename;
	}


	
}
