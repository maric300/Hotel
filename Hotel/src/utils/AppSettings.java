package utils;

public class AppSettings {
	private String dodatneUslugeFilename;
	private String gostFilename;
	private String zaposlenFilename;
	private String tipSobeFilename;
	
	
	public AppSettings(String gostFilename, String zaposlenFilename, String dodatneUslugeFilename, String tipSobeFilename) {
		super();
		this.gostFilename = gostFilename;
		this.zaposlenFilename = zaposlenFilename;
		this.dodatneUslugeFilename = dodatneUslugeFilename;
		this.tipSobeFilename = tipSobeFilename;
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


	
}
