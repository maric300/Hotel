package entity;

import java.util.ArrayList;
import java.util.List;

public class Rezervacija {
	public enum Status {
		NA_CEKANJU,
		POTVRDJENA,
		ODBIJENA,
		OTKAZANA,
	};
	private int id;
	private Status status;
	private TipSobe tipSobe;
	private List<DodatnaUsluga> dodatnaUslugaList;
	private String checkInDateStr;
	private String checkOutDateStr;
	private String usernameGosta;
	private int idSobe;
	
	public Rezervacija(int id, Status status,String usernameGosta, TipSobe tipSobe, List<DodatnaUsluga> dodatnaUslugaList, String checkInDateStr, String checkOutDateStr, int idSobe) {
		this.id = id;
		this.status = status;
		this.usernameGosta = usernameGosta;
		this.tipSobe = tipSobe;
		this.dodatnaUslugaList = dodatnaUslugaList;
		this.checkInDateStr = checkInDateStr;
		this.checkOutDateStr = checkOutDateStr;
		this.idSobe = idSobe;
	}

	public int getIdSobe() {
		return idSobe;
	}

	public void setIdSobe(int idSobe) {
		this.idSobe = idSobe;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public List<DodatnaUsluga> getDodatnaUslugaList() {
		return dodatnaUslugaList;
	}

	public void setDodatnaUslugaList(List<DodatnaUsluga> dodatnaUslugaList) {
		this.dodatnaUslugaList = dodatnaUslugaList;
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
		List<DodatnaUsluga> listaUsluga = this.getDodatnaUslugaList();
		List<String> listaStringova = new ArrayList<String>();
		for(int i = 0; i < listaUsluga.size(); i++) {
			listaStringova.add(listaUsluga.get(i).getNaziv());
		}
//		System.out.println(listaStringova);
		String result = String.join(":", listaStringova);
		System.out.println("rezervacija.java 95 - " + result);
		System.out.println(this.getCheckInDateStr());
		return this.getId() + ";" + this.getStatus().name() + ";" + this.getUsernameGosta() + ";" + this.getTipSobe().getNaziv() + ";" + result + ";" + this.getCheckInDateStr() + ";" + this.getCheckOutDateStr() + ";" + this.getIdSobe();
	}
}