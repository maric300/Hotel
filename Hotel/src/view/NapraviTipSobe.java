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

import entity.Soba;
import entity.TipSobe;
import manage.ManagerFactory;
import net.miginfocom.swing.MigLayout;
import viewTable.TabelaSoba;
import viewTable.TabelaTipovaSoba;

public class NapraviTipSobe extends JFrame {
	private JLabel lbTipSobe = new JLabel("Tip sobe");
	private JTextField tfTipSobe = new JTextField(20);
	private JLabel lbCena = new JLabel("Cena po nocenju");
	private JTextField tfCena = new JTextField(20);
	private JLabel lbBrojMesta = new JLabel("Broj kreveta");
	private JTextField tfBrojMesta = new JTextField(20);
	private JLabel lbError = new JLabel(" ");
	private JButton btnOk = new JButton("OK");
	private JButton btnCancel = new JButton("Cancel");
	
	private TipSobe tipSobeZaEdit;
	
	public NapraviTipSobe (JFrame parent, ManagerFactory factoryMng, TipSobe tipSobeZaEdit) {
		this.tipSobeZaEdit = tipSobeZaEdit;
		if (tipSobeZaEdit != null) {
			setTitle("Izmena tipa sobe");
		}
		else {
			setTitle("Dodavanje tipa sobe");
		}
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		
		MigLayout ml = new MigLayout("wrap, fill", "[][][][]", "[][][][][]");
		setLayout(ml);
		
		add(lbTipSobe);
		add(tfTipSobe, "span 3");
		add(lbBrojMesta);
		add(tfBrojMesta, "span 3");
		add(lbCena);
		add(tfCena, "span 3");
		add(lbError, "span 4");
		lbError.setForeground(Color.RED);
		add(btnOk, "span 2");
		btnOk.setSize(40, 20);
		add(btnCancel, "span 2");
		btnCancel.setSize(40, 20);
		
		if (tipSobeZaEdit != null) {
			tfTipSobe.setText(tipSobeZaEdit.getNaziv());
			tfBrojMesta.setText(String.valueOf(tipSobeZaEdit.getBrojMesta()));
			tfCena.setText(String.valueOf(tipSobeZaEdit.getCenaPoNocenju()));
		}
		
		btnOk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String oldNaziv;
				Boolean isOk = true;
				if (tipSobeZaEdit != null) {
					oldNaziv = tipSobeZaEdit.getNaziv();
					
				}
				else {
					oldNaziv = "";
				}
				if (!oldNaziv.equals(tfTipSobe.getText())) {
					for (TipSobe tipSobe : factoryMng.getTipSobeMng().getTipoviSobe()) {
						if (tipSobe.getNaziv().equals(tfTipSobe.getText())) {
							isOk = false;
							lbError.setText("Taj naziv vec postoji.");
						}
					}
				}
				
				
				if (tfTipSobe.getText().equals("")) {
					isOk = false;
					lbError.setText("Tip Sobe ne moze biti prazno polje!");
				}
				
				if (tfBrojMesta.getText().matches("-?\\d+(\\.\\d+)?")) {
					if (Integer.parseInt(tfBrojMesta.getText()) <= 0) {
						isOk = false;
						lbError.setText("Broj Mesta moze biti samo pozitivan broj!");
					}
				}
				else {
					isOk = false;
					lbError.setText("Broj Mesta moze biti samo pozitivan broj!");
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
					if (tipSobeZaEdit == null) {
						factoryMng.getTipSobeMng().getTipoviSobe().add(new TipSobe(Integer.parseInt(tfBrojMesta.getText()), tfTipSobe.getText(), Integer.parseInt(tfCena.getText())));
						
					}
					
					else {
						factoryMng.getTipSobeMng().edit(Integer.parseInt(tfBrojMesta.getText()), oldNaziv, tfTipSobe.getText(), Integer.parseInt(tfCena.getText()));
					}
					((TabelaTipovaSoba) parent) .refreshData();
					dispose();
				}
				
			}
		});
		
		
		pack();
		
	}
}

