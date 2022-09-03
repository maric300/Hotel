package entity;

public class SredjivanjeSoba {
	
	private String datumCiscenjaStr;
	private String imeSobarice;
	private int brOciscenjihSoba;
	
	public SredjivanjeSoba(String datumCiscenjaStr, String imeSobarice, int brOciscenjihSoba) {
		this.datumCiscenjaStr = datumCiscenjaStr;
		this.imeSobarice = imeSobarice;
		this.brOciscenjihSoba = brOciscenjihSoba;
	}
	
	public String getImeSobarice() {
		return imeSobarice;
	}

	public void setImeSobarice(String imeSobarice) {
		this.imeSobarice = imeSobarice;
	}

	public String getDatumCiscenjaStr() {
		return datumCiscenjaStr;
	}

	public void setDatumCiscenjaStr(String datumCiscenjaStr) {
		this.datumCiscenjaStr = datumCiscenjaStr;
	}

	public int getBrOciscenjihSoba() {
		return brOciscenjihSoba;
	}

	public void setBrOciscenjihSoba(int brOciscenjihSoba) {
		this.brOciscenjihSoba = brOciscenjihSoba;
	}

	public String toFileString() {
		return this.getDatumCiscenjaStr() + ";" + this.getImeSobarice() + ";" + String.valueOf(this.brOciscenjihSoba);
	}
	
	
	
}