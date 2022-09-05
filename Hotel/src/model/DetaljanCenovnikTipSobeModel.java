package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.table.AbstractTableModel;

import entity.CenovnikTipSobe;
import entity.DatumICena;
import entity.Gost;
import entity.Soba;
import entity.Soba.StatusSobe;
import manage.CenovnikTipSobeManager;
import manage.SobaManager;
import manage.TipSobeManager;

public class DetaljanCenovnikTipSobeModel extends AbstractTableModel {
	private static final long serialVersionUID = 173122351138550735L;
	private List<DatumICena> datumiICene;
	private String[] columnNames = { "Datum", "Cena"};

	public DetaljanCenovnikTipSobeModel(List<DatumICena> datumiICene) {
		this.datumiICene = datumiICene;
	}

	@Override
	public int getRowCount() {
		return datumiICene.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		if (datumiICene.size() != 0) {
		DatumICena s = datumiICene.get(rowIndex);
			switch (columnIndex) {
			case 0:
				return s.getDatumString();
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
