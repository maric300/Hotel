package model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import entity.Gost;
import entity.Soba;
import entity.Soba.StatusSobe;
import manage.SobaManager;

public class SobaricaModel extends AbstractTableModel {
	private static final long serialVersionUID = 173122351138550735L;
	private SobaManager sobaMng;
	private String[] columnNames = { "ID", "Status", "Tip sobe", "Sobarica"};
	private String emailSobarice;

	public SobaricaModel(SobaManager mng, String emailSobarice) {
		this.sobaMng = mng;
		this.emailSobarice = emailSobarice;
	}

	@Override
	public int getRowCount() {
		return sobaMng.VratiSobeZaSpremanje(emailSobarice).size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		List<Soba> SobeSpremanje = sobaMng.VratiSobeZaSpremanje(emailSobarice);
		if (SobeSpremanje.size() != 0) {
			Soba s = SobeSpremanje.get(rowIndex);

			switch (columnIndex) {
			case 0:
				return s.getId();
			case 1:
				return s.getStatus();
			case 2:
				return s.getTipSobe().getNaziv();
			case 3:
				return s.getEmailSobarice();
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
