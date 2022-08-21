package entity;

public class Rezervacija {
	public enum Status {
		NA_CEKANJU,
		POTVRDJENA,
		ODBIJENA,
		OTKAZANA,
	};
	private Status status;
	private TipSobe tipSobe;
	private DodatnaUsluga dodatnaUsluga;
	private String checkInDateStr;
	private String checkOutDateStr;
	private String usernameGosta;
	
	public Rezervacija(Status status,String usernameGosta, TipSobe tipSobe, DodatnaUsluga dodatnaUsluga, String checkInDateStr, String checkOutDateStr) {
		this.status = status;
		this.usernameGosta = usernameGosta;
		this.tipSobe = tipSobe;
		this.dodatnaUsluga = dodatnaUsluga;
		this.checkInDateStr = checkInDateStr;
		this.checkOutDateStr = checkOutDateStr;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public TipSobe getTipSobe() {
		return tipSobe;
	}

	public void setTipSobe(TipSobe tipSobe) {
		this.tipSobe = tipSobe;
	}

	public DodatnaUsluga getDodatnaUsluga() {
		return dodatnaUsluga;
	}

	public void setDodatnaUsluga(DodatnaUsluga dodatnaUsluga) {
		this.dodatnaUsluga = dodatnaUsluga;
	}

	public String getCheckInDateStr() {
		return checkInDateStr;
	}

	public void setCheckInDateStr(String checkInDateStr) {
		this.checkInDateStr = checkInDateStr;
	}

	public String getCheckOutDateStr() {
		return checkOutDateStr;
	}

	public void setCheckOutDateStr(String checkOutDateStr) {
		this.checkOutDateStr = checkOutDateStr;
	}

	public String getUsernameGosta() {
		return usernameGosta;
	}

	public void setUsernameGosta(String usernameGosta) {
		this.usernameGosta = usernameGosta;
	}

	public String toFileString() {
		// TODO Auto-generated method stub
		return this.getStatus().name() + ";" + this.getUsernameGosta() + ";" + this.getTipSobe().getNaziv() + ";" + this.getDodatnaUsluga().getNaziv() + ";" + this.getCheckInDateStr() + ";" + this.getCheckOutDateStr();
	}
}