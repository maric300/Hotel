package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.table.AbstractTableModel;

import entity.CenovnikDodatneUsluge;
import manage.CenovnikDodatneUslugeManager;

public class DetaljanCenovnikDodatneUslugeModel extends AbstractTableModel {
	private static final long serialVersionUID = 173122351138550735L;
	private CenovnikDodatneUsluge trenutniCenovnik;
	private CenovnikDodatneUslugeManager mng;
	private List<LocalDate> listaDatuma = new ArrayList<LocalDate>();
	private List<Integer> listaIntedzera = new ArrayList<Integer>();
	private String[] columnNames = { "Datum", "Cena"};

	public DetaljanCenovnikDodatneUslugeModel(CenovnikDodatneUslugeManager mng,CenovnikDodatneUsluge trenutniCenovnik) {
		this.mng = mng;
		this.trenutniCenovnik = mng.NameToObject(trenutniCenovnik.getNaziv());
		this.listaDatuma.addAll(trenutniCenovnik.getCene().keySet());
		this.listaIntedzera.addAll(trenutniCenovnik.getCene().values());
	}

	@Override
	public int getRowCount() {
		return listaIntedzera.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		if (listaIntedzera.size() != 0) {
//		DatumICena s = datumiICene.get(rowIndex);
		LocalDate datum = listaDatuma.get(rowIndex);
		String formattedDate = datum.format(DateTimeFormatter.ofPattern("dd.MM.yyyy."));
		Integer inti = listaIntedzera.get(rowIndex);
			switch (columnIndex) {
			case 0:
				return formattedDate;
			case 1:
				return inti;
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
