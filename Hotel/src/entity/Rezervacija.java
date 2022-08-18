package entity;

public class Rezervacija {
	private String status;
	private int idRezervacije;
	private int idSobe;
	
	public Rezervacija(String status, int idRezervacije, int idSobe) {
		this.status = status;
		this.idRezervacije = idRezervacije;
		this.idSobe = idSobe;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getIdRezervacije() {
		return idRezervacije;
	}

	public void setIdRezervacije(int idRezervacije) {
		this.idRezervacije = idRezervacije;
	}

	public int getIdSobe() {
		return idSobe;
	}

	public void setIdSobe(int idSobe) {
		this.idSobe = idSobe;
	}
}