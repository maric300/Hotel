package entity;

import java.util.List;

public class Soba {
	private int id;
	public enum StatusSobe {
		SLOBODNA,
		SPREMANJE,
		ZAUZETO
	}
	private StatusSobe status;
	private TipSobe tipSobe;

	public Soba(int id, StatusSobe status, TipSobe tipSobe) {
		this.id = id;
		this.status = status;
		this.tipSobe = tipSobe;
		
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
		return String.valueOf(this.getId()) + ";" + this.getStatus().name() + ";" + this.getTipSobe().getNaziv();
	}
	
}