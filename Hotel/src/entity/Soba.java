package entity;

public class Soba {
	private int id;
	public enum StatusSobe {
		SLOBODNA,
		SPREMANJE,
		ZAUZETO
	}
	private StatusSobe status;
	private TipSobe tipSobe;
	private String emailSobarice;

	public Soba(int id, StatusSobe status, TipSobe tipSobe, String emailSobarice) {
		this.id = id;
		this.status = status;
		this.tipSobe = tipSobe;
		this.emailSobarice = emailSobarice;
		
	}

	public StatusSobe getStatus() {
		return status;
	}

	public void setStatus(StatusSobe status) {
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public String getEmailSobarice() {
		return emailSobarice;
	}

	public void setEmailSobarice(String emailSobarice) {
		this.emailSobarice = emailSobarice;
	}

	public void setId(int id) {
		this.id = id;
	}

	public TipSobe getTipSobe() {
		return tipSobe;
	}

	public void setTipSobe(TipSobe tipSobe) {
		this.tipSobe = tipSobe;
	}

	public String toFileString() {
		return String.valueOf(this.getId()) + ";" + this.getStatus().name() + ";" + this.getTipSobe().getNaziv() + ";" + this.getEmailSobarice();
	}
	
}