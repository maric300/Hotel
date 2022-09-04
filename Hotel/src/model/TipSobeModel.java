package model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import entity.Gost;
import entity.Soba;
import entity.Soba.StatusSobe;
import entity.TipSobe;
import manage.TipSobeManager;

public class TipSobeModel extends AbstractTableModel {
	private static final long serialVersionUID = 173122351138550735L;
	private TipSobeManager tipSobeMng;
	private String[] columnNames = { "Broj mesta", "Naziv", "Cena po nocenju"};

	public TipSobeModel(TipSobeManager tipSobeMng) {
		this.tipSobeMng = tipSobeMng;
	}

	@Override
	public int getRowCount() {
		return tipSobeMng.getTipoviSobe().size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		if (tipSobeMng.getTipoviSobe().size() != 0) {
			TipSobe s = tipSobeMng.getTipoviSobe().get(rowIndex);

			switch (columnIndex) {
			case 0:
				return s.getBrojMesta();
			case 1:
				return s.getNaziv();
			case 2:
				return s.getCenaPoNocenju();
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
