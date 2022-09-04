package model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import entity.Gost;
import entity.Rezervacija;
import entity.Soba;
import manage.RezervacijaManager;
import manage.SobaManager;

public class GostRezervacijaModel extends AbstractTableModel {
	private static final long serialVersionUID = 173122351138550735L;
	private RezervacijaManager rezervacijaMng;
	private String emailGosta;
	private String[] columnNames = { "ID", "Status", "Gost", "Tip sobe", "Dodatne usluge", "Check-in", "Check-out", "Id sobe", "Cena"};

	public GostRezervacijaModel(RezervacijaManager mng, String emailGosta) {
		this.rezervacijaMng = mng;
		this.emailGosta = emailGosta;
	}

	@Override
	public int getRowCount() {
		return rezervacijaMng.getRezervacijeGosta(emailGosta).size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (rezervacijaMng.getRezervacijeGosta(emailGosta).size() != 0) {
			Rezervacija g = rezervacijaMng.getRezervacijeGosta(emailGosta).get(rowIndex);

			switch (columnIndex) {
			case 0:
				return g.getId();
			case 1:
				return g.getStatus();
			case 2:
				return g.getUsernameGosta();
			case 3:
				return g.getTipSobe().getNaziv();
			case 4:
				List<String> listaStringova = new ArrayList<String>();
				for(int i = 0; i < g.getDodatnaUslugaList().size(); i++) {
					listaStringova.add(g.getDodatnaUslugaList().get(i).getNaziv());
				}
				String result = String.join(",", listaStringova);
				return result;
			case 5:
				return g.getCheckInDateStr();
			case 6:
				return g.getCheckOutDateStr();
			case 7:
				return g.getIdSobe();
			case 8:
				return g.getUkupnaCena();
			default:
				return null;
			}
		}
		
		else {
			return null;
		}

	}

	@Override
	public String getColumnName(int column) {
		return this.columnNames[column];
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return this.getValueAt(0, columnIndex).getClass();
	}

}
