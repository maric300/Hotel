package model;

import javax.swing.table.AbstractTableModel;

import entity.Gost;
import manage.GostManager;

public class GostModel extends AbstractTableModel {
	private static final long serialVersionUID = 173122351138550735L;
	private GostManager gostMng;
	private String[] columnNames = { "Ime", "Prezime", "pol", "datum rođenja", "Adresa", "Broj telefona", "E-mail", "Broj pasosa" };

	public GostModel(GostManager mng) {
		this.gostMng = mng;
	}

	@Override
	public int getRowCount() {
		return gostMng.getGosti().size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Gost g = gostMng.getGosti().get(rowIndex);
		switch (columnIndex) {
		case 0:
			return g.getIme();
		case 1:
			return g.getPrezime();
		case 2:
			return g.getPol();
		case 3:
			return g.getDatumStr();
		case 4:
			return g.getAdresa();
		case 5:
			return g.getBrojTelefona();
		case 6:
			return g.getEmail();
		case 7:
			return g.getBrPasosa();
		default:
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
