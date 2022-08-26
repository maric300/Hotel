package model;

import javax.swing.table.AbstractTableModel;

import entity.Gost;
import entity.Soba;
import manage.SobaManager;

public class SobaModel extends AbstractTableModel {
	private static final long serialVersionUID = 173122351138550735L;
	private SobaManager sobaMng;
	private String[] columnNames = { "ID", "Status", "Tip sobe"};

	public SobaModel(SobaManager mng) {
		this.sobaMng = mng;
	}

	@Override
	public int getRowCount() {
		return sobaMng.getSobe().size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (sobaMng.getSobe().size() != 0) {
			Soba s = sobaMng.getSobe().get(rowIndex);

			switch (columnIndex) {
			case 0:
				return s.getId();
			case 1:
				return s.getStatus();
			case 2:
				return s.getTipSobe().getNaziv();
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