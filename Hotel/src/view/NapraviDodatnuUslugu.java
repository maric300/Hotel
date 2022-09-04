package view;

import java.awt.Choice;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import entity.DodatnaUsluga;
import entity.Soba;
import entity.TipSobe;
import manage.ManagerFactory;
import net.miginfocom.swing.MigLayout;
import viewTable.TabelaDodatnihUsluga;
import viewTable.TabelaSoba;
import viewTable.TabelaTipovaSoba;

public class NapraviDodatnuUslugu extends JFrame {
	private JLabel lbDodatnaUsluga = new JLabel("Dodatna usluga");
	private JTextField tfDodatnaUsluga = new JTextField(20);
	private JLabel lbCena = new JLabel("Cena po nocenju");
	private JTextField tfCena = new JTextField(20);
	private JLabel lbError = new JLabel(" ");
	private JButton btnOk = new JButton("OK");
	private JButton btnCancel = new JButton("Cancel");
	
	private DodatnaUsluga dodatnaUslugaZaEdit;
	
	public NapraviDodatnuUslugu (JFrame parent, ManagerFactory factoryMng, DodatnaUsluga dodatnaUslugaZaEdit) {
		this.dodatnaUslugaZaEdit = dodatnaUslugaZaEdit;
		if (dodatnaUslugaZaEdit != null) {
			setTitle("Izmena dodatne usluge");
		}
		else {
			setTitle("Dodavanje dodatne usluge");
		}
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		
		MigLayout ml = new MigLayout("wrap, fill", "[][][][]", "[][][][]");
		setLayout(ml);
		
		add(lbDodatnaUsluga);
		add(tfDodatnaUsluga, "span 3");
		add(lbCena);
		add(tfCena, "span 3");
		add(lbError, "span 4");
		lbError.setForeground(Color.RED);
		add(btnOk, "span 2");
		btnOk.setSize(40, 20);
		add(btnCancel, "span 2");
		btnCancel.setSize(40, 20);
		
		if (dodatnaUslugaZaEdit != null) {
			tfDodatnaUsluga.setText(dodatnaUslugaZaEdit.getNaziv());
			tfCena.setText(String.valueOf(dodatnaUslugaZaEdit.getCena()));
		}
		
		btnOk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String oldNaziv;
				Boolean isOk = true;
				if (!(dodatnaUslugaZaEdit == null)) {
					oldNaziv = dodatnaUslugaZaEdit.getNaziv();
					
				}
				else {
					oldNaziv = "";
				}
				
				if (!oldNaziv.equals(tfDodatnaUsluga.getText().toString()))
				for (DodatnaUsluga dodatnaUsluga: factoryMng.getUslugaMng().getDodatneUsluge()) {
					if (dodatnaUsluga.getNaziv().equals(tfDodatnaUsluga.getText())) {
						isOk = false;
						lbError.setText("Taj naziv vec postoji.");
					}
				}
				
				
				if (tfDodatnaUsluga.getText().equals("")) {
					isOk = false;
					lbError.setText("Tip Sobe ne moze biti prazno polje!");
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
					if (dodatnaUslugaZaEdit == null) {
						factoryMng.getUslugaMng().getDodatneUsluge().add(new DodatnaUsluga(tfDodatnaUsluga.getText(), Integer.parseInt(tfCena.getText())));
						
					}
					
					else {
						factoryMng.getUslugaMng().edit(oldNaziv, tfDodatnaUsluga.getText(), Integer.parseInt(tfCena.getText()));
					}
					((TabelaDodatnihUsluga) parent) .refreshData();
					dispose();
				}
				
			}
		});
		
		
		pack();
		
	}
}

