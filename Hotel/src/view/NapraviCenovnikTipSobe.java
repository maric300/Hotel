package view;

import java.awt.Choice;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import entity.CenovnikTipSobe;
import entity.Soba;
import entity.TipSobe;
import manage.ManagerFactory;
import net.miginfocom.swing.MigLayout;
import viewTable.DetaljnaTabelaCenovnikaTipSobe;
import viewTable.TabelaSoba;
import viewTable.TabelaTipovaSoba;

public class NapraviCenovnikTipSobe extends JFrame {
	private JLabel lbTipSobe = new JLabel("Tip sobe");
	private JComboBox comboBoxTipSobe;
	private JLabel lbCena = new JLabel("Cena po nocenju");
	private JTextField tfCena = new JTextField(20);
	private JLabel lbDatum = new JLabel("Datum važenja");
	private JLabel lbError = new JLabel(" ");
	private JButton btnOk = new JButton("OK");
	private JButton btnCancel = new JButton("Cancel");
	
	private CenovnikTipSobe cenovnikTipSobeZaEdit;
	
	public NapraviCenovnikTipSobe (JFrame parent, ManagerFactory factoryMng, CenovnikTipSobe cenovnikTipSobeZaEdit) {
		this.cenovnikTipSobeZaEdit = cenovnikTipSobeZaEdit;
		if (cenovnikTipSobeZaEdit != null) {
			setTitle("Izmena cenovnika tipa sobe");
		}
		else {
			setTitle("Dodavanje cenovnika tipa sobe");
		}
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		
		MigLayout ml = new MigLayout("wrap, fill", "[][][][]", "[][][][][]");
		setLayout(ml);
		
		add(lbTipSobe);
		
		List<TipSobe> tipoviSobe = factoryMng.getTipSobeMng().getTipoviSobe();
		String[] s1 = new String[tipoviSobe.size()];
		for (int i = 0; i < tipoviSobe.size(); i++) {
			s1[i] = tipoviSobe.get(i).getNaziv();
		}
		comboBoxTipSobe = new JComboBox(s1);
		add(comboBoxTipSobe, "span 3");
		
		add(lbDatum);
		
		Choice choiceDay = new Choice();
		add(choiceDay);
		
		Choice choiceMonth = new Choice();
		add(choiceMonth);
		
		Choice choiceYear = new Choice();
		add(choiceYear);
		
		choiceDay.add("01");
		choiceDay.add("02");
		choiceDay.add("03");
		choiceDay.add("04");
		choiceDay.add("05");
		choiceDay.add("06");
		choiceDay.add("07");
		choiceDay.add("08");
		choiceDay.add("09");
		
		for(int i = 10;i < 32;i++) {
			choiceDay.add(String.valueOf(i));
		}
		
		choiceMonth.add("01");
		choiceMonth.add("02");
		choiceMonth.add("03");
		choiceMonth.add("04");
		choiceMonth.add("05");
		choiceMonth.add("06");
		choiceMonth.add("07");
		choiceMonth.add("08");
		choiceMonth.add("09");
		
		for(int i = 10;i < 13;i++) {
			choiceMonth.add(String.valueOf(i));
		}

		for(int i = Year.now().getValue();i < Year.now().getValue() + 1;i++) {
			choiceYear.add(String.valueOf(i));
		}
		
		add(lbCena);
		add(tfCena, "span 3");
		add(lbError, "span 4");
		lbError.setForeground(Color.RED);
		add(btnOk, "span 2");
		btnOk.setSize(40, 20);
		add(btnCancel, "span 2");
		btnCancel.setSize(40, 20);
		
		if (cenovnikTipSobeZaEdit != null) {
			comboBoxTipSobe.setSelectedItem(cenovnikTipSobeZaEdit.getNaziv());
		}
		
		btnOk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Boolean isOk = true;
				String datumStr = choiceDay.getSelectedItem() + "." + choiceMonth.getSelectedItem() + "." + choiceYear.getSelectedItem() + ".";
				

				
				if (comboBoxTipSobe.getSelectedIndex() == -1) {
					isOk = false;
					lbError.setText("Morate izabrati tip sobe!");
				}
				
				try {
				    SimpleDateFormat df = new java.text.SimpleDateFormat("dd.MM.yyyy.");
				    df.setLenient(false);
				    df.parse(datumStr);
				} catch (java.text.ParseException e1) {
				  lbError.setText("Neispravan check-in datum");
				  isOk = false;
				}
				
				if (isOk.equals(true)) {
					LocalDate ldD = LocalDate.parse(datumStr, DateTimeFormatter.ofPattern("dd.MM.uuuu."));
					for (CenovnikTipSobe cenovnik : factoryMng.getCenovnikTipSobeMng().getCenovnici()) {
						if (cenovnik.getNaziv().equals(comboBoxTipSobe.getSelectedItem().toString())) {
							for (LocalDate ldf : cenovnik.getCene().keySet()) {
								if (ldf.equals(ldD)) {
									isOk = false;
									lbError.setText("Vec postoji cenovnik sa tim datumom.");
								}
							}
						}
					}
					if (LocalDate.now().isAfter(ldD)) {
						isOk = false;
						lbError.setText("Novi cenovnik moze da vazi najranije od sutra.");
					}
				}
				
				if (tfCena.getText().matches("-?\\d+(\\.\\d+)?")) {
					if (Integer.parseInt(tfCena.getText()) <= 0) {
						isOk = false;
						lbError.setText("Cena moze biti samo pozitivan broj!");
					}
				}
				else {
					isOk = false;
					lbError.setText("Cena moze biti samo pozitivan broj!");
				}
				
				if (isOk.equals(true)) {
					LocalDate ldDatum = LocalDate.parse(datumStr, DateTimeFormatter.ofPattern("dd.MM.uuuu."));

					if (cenovnikTipSobeZaEdit == null) {
						CenovnikTipSobe cenovnik = factoryMng.getCenovnikTipSobeMng().NameToObject(comboBoxTipSobe.getSelectedItem().toString());
						if (cenovnik != null) {
							cenovnik.getCene().put(ldDatum, Integer.parseInt(tfCena.getText().toString()) );
						}
						else {
							Map<LocalDate, Integer> mapa = new HashMap<LocalDate, Integer>();
							mapa.put(ldDatum, Integer.parseInt(tfCena.getText().toString()));
							factoryMng.getCenovnikTipSobeMng().getCenovnici().add(new CenovnikTipSobe(comboBoxTipSobe.getSelectedItem().toString(), mapa));
						}
					}
					
					else {
//						factoryMng.getTipSobeMng().edit(Integer.parseInt(tfBrojMesta.getText()), oldNaziv, tfTipSobe.getText(), Integer.parseInt(tfCena.getText()));
					}
//					((TabelaTipovaSoba) parent) .refreshData();
					dispose();
				}
				
			}
		});
		
		
		pack();
		
	}
	
	//___________________________________________________________________
	
	public NapraviCenovnikTipSobe (JFrame parent, ManagerFactory factoryMng, LocalDate datum, CenovnikTipSobe trenutniCenovnik) {
		this.cenovnikTipSobeZaEdit = cenovnikTipSobeZaEdit;
		setTitle("Izmena cenovnika");
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		
		MigLayout ml = new MigLayout("wrap, fill", "[][][][]", "[][][][][]");
		setLayout(ml);
		
		add(lbDatum);
		
		Choice choiceDay = new Choice();
		add(choiceDay);
		
		Choice choiceMonth = new Choice();
		add(choiceMonth);
		
		Choice choiceYear = new Choice();
		add(choiceYear);
		
		choiceDay.add("01");
		choiceDay.add("02");
		choiceDay.add("03");
		choiceDay.add("04");
		choiceDay.add("05");
		choiceDay.add("06");
		choiceDay.add("07");
		choiceDay.add("08");
		choiceDay.add("09");
		
		for(int i = 10;i < 32;i++) {
			choiceDay.add(String.valueOf(i));
		}
		
		choiceMonth.add("01");
		choiceMonth.add("02");
		choiceMonth.add("03");
		choiceMonth.add("04");
		choiceMonth.add("05");
		choiceMonth.add("06");
		choiceMonth.add("07");
		choiceMonth.add("08");
		choiceMonth.add("09");
		
		for(int i = 10;i < 13;i++) {
			choiceMonth.add(String.valueOf(i));
		}

		for(int i = Year.now().getValue();i < Year.now().getValue() + 1;i++) {
			choiceYear.add(String.valueOf(i));
		}
		
		add(lbCena);
		add(tfCena, "span 3");
		add(lbError, "span 4");
		lbError.setForeground(Color.RED);
		add(btnOk, "span 2");
		btnOk.setSize(40, 20);
		add(btnCancel, "span 2");
		btnCancel.setSize(40, 20);
		
		if (datum != null) {
			tfCena.setText(String.valueOf(trenutniCenovnik.getCene().get(datum)));
			choiceDay.select(datum.getDayOfMonth() - 1);
			choiceMonth.select(datum.getMonthValue() - 1);
			choiceYear.select(String.valueOf(datum.getYear()));
		}
		
		btnOk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Boolean isOk = true;
				String oldDatumStr = factoryMng.getCenovnikTipSobeMng().getDatumString(datum);
				String datumStr = choiceDay.getSelectedItem() + "." + choiceMonth.getSelectedItem() + "." + choiceYear.getSelectedItem() + ".";

				try {
				    SimpleDateFormat df = new java.text.SimpleDateFormat("dd.MM.yyyy.");
				    df.setLenient(false);
				    df.parse(datumStr);
				} catch (java.text.ParseException e1) {
				  lbError.setText("Neispravan datum");
				  isOk = false;
				}
				
				if (isOk.equals(true) && !oldDatumStr.equals(datumStr)) {
					LocalDate ldD = LocalDate.parse(datumStr, DateTimeFormatter.ofPattern("dd.MM.uuuu."));
					for (LocalDate ldf : trenutniCenovnik.getCene().keySet()) {
						if (ldf.equals(ldD)) {
							isOk = false;
							lbError.setText("Vec postoji cenovnik sa tim datumom.");

						}
					}
//					if (LocalDate.now().isAfter(ldD)) {
//						isOk = false;
//						lbError.setText("Novi cenovnik moze da vazi najranije od sutra.");
//					}
				}
				
				if (tfCena.getText().matches("-?\\d+(\\.\\d+)?")) {
					if (Integer.parseInt(tfCena.getText()) <= 0) {
						isOk = false;
						lbError.setText("Cena moze biti samo pozitivan broj!");
					}
				}
				else {
					isOk = false;
					lbError.setText("Cena moze biti samo pozitivan broj!");
				}
				
				if (isOk.equals(true)) {
					LocalDate ldDatum = LocalDate.parse(datumStr, DateTimeFormatter.ofPattern("dd.MM.uuuu."));

					CenovnikTipSobe cenovnik1 = factoryMng.getCenovnikTipSobeMng().NameToObject(trenutniCenovnik.getNaziv());
					cenovnik1.getCene().remove(datum);
					cenovnik1.getCene().put(ldDatum, Integer.parseInt(tfCena.getText()));
					
					((DetaljnaTabelaCenovnikaTipSobe) parent) .refreshData();
					dispose();	
					}
					


				}
		});
		
		
		pack();
		
	}
}

