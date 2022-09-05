package view;

import java.awt.Choice;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import entity.CenovnikDodatneUsluge;
import entity.CenovnikTipSobe;
import manage.ManagerFactory;
import net.miginfocom.swing.MigLayout;
import viewTable.DetaljnaTabelaCenovnikaDodatneUsluge;
import viewTable.DetaljnaTabelaCenovnikaTipSobe;

public class NapraviDetaljanCenovnikDodatneUsluge extends JFrame{
	
	private JLabel lbDodatnaUsluga = new JLabel("Dodatna usluga");
	private JComboBox comboBoxDodatnaUsluga;
	private JLabel lbCena = new JLabel("Cena po nocenju");
	private JTextField tfCena = new JTextField(20);
	private JLabel lbDatum = new JLabel("Datum važenja");
	private JLabel lbError = new JLabel(" ");
	private JButton btnOk = new JButton("OK");
	private JButton btnCancel = new JButton("Cancel");
	
	private CenovnikDodatneUsluge trenutniCenovnik;

public NapraviDetaljanCenovnikDodatneUsluge (JFrame parent, ManagerFactory factoryMng, LocalDate datum, CenovnikDodatneUsluge trenutniCenovnik) {
		this.trenutniCenovnik = trenutniCenovnik;
		if (datum == null) {
			setTitle("Napravi cenovnik");
		}
		else {
			setTitle("Izmena cenovnika");
		}
		
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

					if (datum != null) {
						CenovnikDodatneUsluge cenovnik1 = factoryMng.getCenovnikDodatneUslugeMng().NameToObject(trenutniCenovnik.getNaziv());
						cenovnik1.getCene().remove(datum);
						cenovnik1.getCene().put(ldDatum, Integer.parseInt(tfCena.getText()));
					}
					else {
						CenovnikDodatneUsluge cenovnik1 = factoryMng.getCenovnikDodatneUslugeMng().NameToObject(trenutniCenovnik.getNaziv());
						cenovnik1.getCene().put(ldDatum, Integer.parseInt(tfCena.getText()));
					}
					
					
					((DetaljnaTabelaCenovnikaDodatneUsluge) parent) .refreshData();
					dispose();	
					}
					


				}
		});
		
		
		pack();
		
	}
}

