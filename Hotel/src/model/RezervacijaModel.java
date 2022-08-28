package model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import entity.Gost;
import entity.Rezervacija;
import entity.Soba;
import manage.RezervacijaManager;
import manage.SobaManager;

public class RezervacijaModel extends AbstractTableModel {
	private static final long serialVersionUID = 173122351138550735L;
	private RezervacijaManager rezervacijaMng;
	private String[] columnNames = { "ID", "Status", "Gost", "Tip sobe", "Dodatne usluge", "Check-in", "Check-out"};

	public RezervacijaModel(RezervacijaManager mng) {
		this.rezervacijaMng = mng;
	}

	@Override
	public int getRowCount() {
		return rezervacijaMng.getRezervacije().size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (rezervacijaMng.getRezervacije().size() != 0) {
			Rezervacija g = rezervacijaMng.getRezervacije().get(rowIndex);

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
