package model;

import javax.swing.table.AbstractTableModel;

import entity.Zaposlen;
import manage.ZaposlenManager;

public class ZaposlenModel extends AbstractTableModel {
	private static final long serialVersionUID = 173122351138550735L;
	private ZaposlenManager zaposlenMng;
	private String[] columnNames = { "Posao", "Ime", "Prezime", "pol", "datum rođenja", "Adresa", "Broj telefona", "Korisnicko ime", "Lozinka", "Nivo strucne spreme", "Staž", "Plata" };

	public ZaposlenModel(ZaposlenManager mng) {
		this.zaposlenMng = mng;
	}

	@Override
	public int getRowCount() {
		return zaposlenMng.getZaposleni().size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Zaposlen z = zaposlenMng.getZaposleni().get(rowIndex);
		switch (columnIndex) {
		case 0:
			return z.getPosao();
		case 1:
			return z.getIme();
		case 2:
			return z.getPrezime();
		case 3:
			return z.getPol();
		case 4:
			return z.getDatumStr();
		case 5:
			return z.getAdresa();
		case 6:
			return z.getBrojTelefona();
		case 7:
			return z.getEmail();
		case 8:
			return z.getBrPasosa();
		case 9:
			return z.getNivoStrucneSpreme();
		case 10:
			return z.getStaz();
		case 11:
			return z.getPlata();
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
