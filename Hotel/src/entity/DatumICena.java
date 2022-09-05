package entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DatumICena {
	
	private LocalDate datum;
	private Integer cena;
	
	
	public DatumICena(LocalDate datum, Integer cena) {
		this.datum = datum;
		this.cena = cena;
		
	}


	public LocalDate getDatum() {
		return datum;
	}


	public void setDatum(LocalDate datum) {
		this.datum = datum;
	}


	public Integer getCena() {
		return cena;
	}


	public void setCena(Integer cena) {
		this.cena = cena;
	}


	public String getDatumString() {
		String formattedDate = this.datum.format(DateTimeFormatter.ofPattern("dd.MM.yyyy."));
		return formattedDate;
	}
	
}