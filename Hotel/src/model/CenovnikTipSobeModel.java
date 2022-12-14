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

public class CenovnikTipSobeModel extends AbstractTableModel {
	private static final long serialVersionUID = 173122351138550735L;
	private CenovnikTipSobeManager mng;
	private TipSobeManager tipSobeMng;
	private String[] columnNames = { "Naziv", "Datum ", "Aktuelna cena"};

	public CenovnikTipSobeModel(CenovnikTipSobeManager mng, TipSobeManager tipSobeMng) {
		this.mng = mng;
		this.tipSobeMng = tipSobeMng;
				
	}

	@Override
	public int getRowCount() {
		return mng.getCenovnici().size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		if (mng.getCenovnici().size() != 0) {
			CenovnikTipSobe s = mng.getCenovnici().get(rowIndex);
			Map<LocalDate, Integer> hmCene = s.getCene();
			LocalDate latestD = mng.getLatestDate(hmCene);
			int aktuelnaCena = 0;
			if (latestD.equals(LocalDate.now())) {
				aktuelnaCena = tipSobeMng.NameToObject(s.getNaziv()).getCenaPoNocenju();
			}
			else {
				aktuelnaCena = hmCene.get(latestD);
			}
			String formattedDate = latestD.format(DateTimeFormatter.ofPattern("dd.MMM.yyyy."));
			switch (columnIndex) {
			case 0:
				return s.getNaziv();
			case 1:
				return formattedDate;
			case 2:
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
