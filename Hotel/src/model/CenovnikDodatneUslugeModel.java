package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.table.AbstractTableModel;

import entity.CenovnikDodatneUsluge;
import entity.CenovnikTipSobe;
import entity.Gost;
import entity.Soba;
import entity.Soba.StatusSobe;
import manage.CenovnikDodatneUslugeManager;
import manage.CenovnikTipSobeManager;
import manage.DodatnaUslugaManager;
import manage.SobaManager;
import manage.TipSobeManager;

public class CenovnikDodatneUslugeModel extends AbstractTableModel {
	private static final long serialVersionUID = 173122351138550735L;
	private CenovnikDodatneUslugeManager mng;
	private DodatnaUslugaManager dodatnaUslugaMng;
	private String[] columnNames = { "Naziv", "Datum ", "Aktuelna cena"};

	public CenovnikDodatneUslugeModel(CenovnikDodatneUslugeManager mng, DodatnaUslugaManager dodatnaUslugaMng) {
		this.mng = mng;
		this.dodatnaUslugaMng = dodatnaUslugaMng;
				
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
			CenovnikDodatneUsluge s = mng.getCenovnici().get(rowIndex);
			Map<LocalDate, Integer> hmCene = s.getCene();
			LocalDate latestD = mng.getLatestDate(hmCene);
			int aktuelnaCena = 0;
			if (latestD.equals(LocalDate.now())) {
				aktuelnaCena = dodatnaUslugaMng.NameToObject(s.getNaziv()).getCena();
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
