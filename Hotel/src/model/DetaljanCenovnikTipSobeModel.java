package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.table.AbstractTableModel;

import entity.CenovnikTipSobe;
import entity.Gost;
import entity.Soba;
import entity.Soba.StatusSobe;
import manage.CenovnikTipSobeManager;
import manage.SobaManager;
import manage.TipSobeManager;

public class DetaljanCenovnikTipSobeModel extends AbstractTableModel {
	private static final long serialVersionUID = 173122351138550735L;
	private CenovnikTipSobeManager mng;
	private TipSobeManager tipSobeMng;
	private Map<LocalDate, Integer> mapa;
	private String[] columnNames = { "Datum", "Cena"};

	public DetaljanCenovnikTipSobeModel(CenovnikTipSobeManager mng, TipSobeManager tipSobeMng, Map<LocalDate, Integer> mapa) {
		this.mng = mng;
		this.tipSobeMng = tipSobeMng;
		this.mapa = mapa;
				
	}

	@Override
	public int getRowCount() {
		return mapa.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		if (mng.getCenovnici().size() != 0) {
			String formattedDate = datum.format(DateTimeFormatter.ofPattern("dd.MMM.yyyy."));
			switch (columnIndex) {
			case 0:
				return formattedDate;
			case 1:
				return aktuelnaCena;
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
