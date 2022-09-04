package model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import entity.DodatnaUsluga;
import entity.Gost;
import entity.Soba;
import entity.Soba.StatusSobe;
import manage.DodatnaUslugaManager;

public class DodatnaUslugaModel extends AbstractTableModel {
	private static final long serialVersionUID = 173122351138550735L;
	private DodatnaUslugaManager dodatnaUslugaManager;
	private String[] columnNames = { "Naziv", "Cena po nocenju"};

	public DodatnaUslugaModel(DodatnaUslugaManager dodatnaUslugaManager) {
		this.dodatnaUslugaManager = dodatnaUslugaManager;
	}

	@Override
	public int getRowCount() {
		return dodatnaUslugaManager.getDodatneUsluge().size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		if (dodatnaUslugaManager.getDodatneUsluge().size() != 0) {
			DodatnaUsluga s = dodatnaUslugaManager.getDodatneUsluge().get(rowIndex);

			switch (columnIndex) {
			case 0:
				return s.getNaziv();
			case 1:
				return s.getCena();
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
