package entity;

import java.util.List;

public class Soba {
	private int id;
	private TipSobe tipSobe;
	private List<Integer> idRezervacija;

	public Soba(int id, TipSobe tipSobe, List<Integer> idRezervacija) {
		this.id = id;
		this.tipSobe = tipSobe;
		this.idRezervacija = idRezervacija;
		
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

	public List<Integer> getIdRezervacija() {
		return idRezervacija;
	}

	public void setIdRezervacija(List<Integer> idRezervacija) {
		this.idRezervacija = idRezervacija;
	}
	
}