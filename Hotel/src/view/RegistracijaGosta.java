package view;
import java.awt.BorderLayout;
import java.time.LocalDate;
import java.time.Year;
//import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import manage.ManagerFactory;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JList;
import java.awt.Choice;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.awt.Color;

import entity.Gost;

public class RegistracijaGosta extends JFrame {

	private JPanel contentPane;
	private JTextField tfIme;
	private JTextField tfPrezime;
	private JTextField tfAdresa;
	private JTextField tfBrTelefona;
	private JTextField tfEmail;
	private JTextField tfBrPasosa;
	private JLabel lbError;
	private Gost gostZaEdit;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					RegistracijaGosta frame = new RegistracijaGosta();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public RegistracijaGosta(JFrame parent, ManagerFactory factoryMng, Gost gostZaEdit) {
		this.gostZaEdit = gostZaEdit;
		List<Gost> gosti;
		gosti = factoryMng.getGostMng().getGosti();
		if (gostZaEdit == null) {
			setTitle("Registracija gosta");
		}
		else {
			setTitle("Izmena gosta");
		}
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 354);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbWelcome = new JLabel("Unesite sve potrebne podatke gosta kojeg registrujete");
		lbWelcome.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbWelcome.setBounds(10, 11, 378, 14);
		contentPane.add(lbWelcome);
		
		JLabel lbIme = new JLabel("Ime");
		lbIme.setBounds(10, 53, 46, 14);
		contentPane.add(lbIme);
		
		JLabel lbPrezime = new JLabel("Prezime");
		lbPrezime.setBounds(10, 78, 46, 14);
		contentPane.add(lbPrezime);
		
		tfIme = new JTextField();
		tfIme.setBounds(111, 50, 116, 20);
		contentPane.add(tfIme);
		tfIme.setColumns(10);
		
		tfPrezime = new JTextField();
		tfPrezime.setBounds(111, 75, 116, 20);
		contentPane.add(tfPrezime);
		tfPrezime.setColumns(10);
		
		JLabel lbPol = new JLabel("Pol");
		lbPol.setBounds(10, 103, 46, 14);
		contentPane.add(lbPol);
		
		JRadioButton rdbtnMuski = new JRadioButton("Muško");
		rdbtnMuski.setBounds(85, 99, 85, 23);
		contentPane.add(rdbtnMuski);
		
		JRadioButton rdbtnZenski = new JRadioButton("Žensko");
		rdbtnZenski.setBounds(172, 99, 85, 23);
		contentPane.add(rdbtnZenski);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnZenski);
		group.add(rdbtnMuski);
		
		JLabel lblNewLabel_3 = new JLabel("Datum rođenja");
		lblNewLabel_3.setBounds(10, 134, 86, 14);
		contentPane.add(lblNewLabel_3);
		
		Choice choiceDay = new Choice();
		choiceDay.setBounds(99, 132, 39, 20);
		contentPane.add(choiceDay);
		
		Choice choiceMonth = new Choice();
		choiceMonth.setBounds(147, 132, 73, 20);
		contentPane.add(choiceMonth);
		
		Choice choiceYear = new Choice();
		choiceYear.setBounds(231, 132, 51, 20);
		contentPane.add(choiceYear);
		
		JLabel lbAdresa = new JLabel("Adresa");
		lbAdresa.setBounds(10, 171, 46, 14);
		contentPane.add(lbAdresa);
		
		JLabel lbBrTelefona = new JLabel("Broj telefona");
		lbBrTelefona.setBounds(10, 199, 86, 14);
		contentPane.add(lbBrTelefona);
		
		tfAdresa = new JTextField();
		tfAdresa.setBounds(111, 171, 116, 20);
		contentPane.add(tfAdresa);
		tfAdresa.setColumns(10);
		
		tfBrTelefona = new JTextField();
		tfBrTelefona.setBounds(111, 193, 116, 20);
		contentPane.add(tfBrTelefona);
		tfBrTelefona.setColumns(10);
		
		JLabel lbEmail = new JLabel("Email");
		lbEmail.setBounds(10, 224, 46, 14);
		contentPane.add(lbEmail);
		
		JLabel lbBrPasosa = new JLabel("Broj Pasoša");
		lbBrPasosa.setBounds(10, 249, 86, 14);
		contentPane.add(lbBrPasosa);
		
		tfEmail = new JTextField();
		tfEmail.setBounds(111, 218, 146, 20);
		contentPane.add(tfEmail);
		tfEmail.setColumns(10);
		
		tfBrPasosa = new JTextField();
		tfBrPasosa.setBounds(111, 243, 116, 20);
		contentPane.add(tfBrPasosa);
		tfBrPasosa.setColumns(10);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Boolean isOk = true;
				String ime = tfIme.getText().trim();
				String prezime = tfPrezime.getText().trim();
				String pol = null;
				String adresa = tfAdresa.getText().trim();
				String brojTelefona = tfBrTelefona.getText().trim();
				String email = tfEmail.getText().trim();
				String brPasosa = tfBrPasosa.getText().trim();
				
				if (ime.equals("")) {
					isOk = false;
					lbError.setText("Ime ne sme biti prazno!");
				}
				
				if (prezime.equals("")) {
					isOk = false;
					lbError.setText("prezime ne sme biti prazno!");
				}
				
				if (adresa.equals("")) {
					isOk = false;
					lbError.setText("polje adresa ne sme biti prazno!");
				}
				
				if (brojTelefona.equals("")) {
					isOk = false;
					lbError.setText("polje brojTelefona ne sme biti prazno!");
				}
				
				if (email.equals("")) {
					isOk = false;
					lbError.setText("polje email ne sme biti prazno!");
				}
				
				if (brPasosa.equals("")) {
					isOk = false;
					lbError.setText("polje brPasosa ne sme biti prazno!");
				}
				
				
				if (rdbtnZenski.isSelected()) {
					pol = "Zensko";
				}
				else if (rdbtnMuski.isSelected()) {
					pol = "Musko";
				}
				else {
					isOk = false;
					lbError.setText("Niste izabrali pol");
				}
				
				String datumRodjenjaStr = choiceDay.getSelectedItem() + "." + choiceMonth.getSelectedItem() + "." + choiceYear.getSelectedItem() + ".";
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.uuuu.");
				

				
//				try {
//					LocalDate datumRodjenja = LocalDate.parse(datumRodjenjaStr, dtf);
//					System.out.println(datumRodjenja);
//				} catch (Exception e1) {
//					// TODO Auto-generated catch block
//					System.out.println("invalid");
//				}
				
				if (isValidEmailAddress(email)) {
					
				}
				else {
					isOk = false;
					lbError.setText("Nije validan email");
				}
				
				char[] chars = ime.toCharArray();
				for(char c : chars) {
					if (Character.isDigit(c)) {
						isOk = false;
						lbError.setText("Ime ne moze biti cifra!");
						break;
					}
				}
				
			
				

				
				try {
				    SimpleDateFormat df = new java.text.SimpleDateFormat("dd.MM.yyyy.");
				    df.setLenient(false);
				    df.parse(datumRodjenjaStr);
				} catch (java.text.ParseException e1) {
				  lbError.setText("Neispravan datum");
				  isOk = false;
				}
				
				if(!isNumeric(brojTelefona)) {
					isOk = false;
					lbError.setText("Nevalidan broj telefona");
				}
				
				if(!isNumeric(brPasosa)) {
					isOk = false;
					lbError.setText("Nevalidan broj pasosa");
				}
				
				if (isOk.equals(true)) {
					
					if (gostZaEdit == null) {
						gosti.add(new Gost(ime, prezime, pol, datumRodjenjaStr, adresa, brojTelefona, email, brPasosa));
					}
					else {
						factoryMng.getGostMng().edit(ime, prezime, pol, datumRodjenjaStr, adresa, brojTelefona, email, brPasosa);
					}
					
					try {
            ((TabelaKorisnika) parent).refreshData();
          } catch (Exception e1) {
        
          }
					dispose();
					
				}
//				
			}
		});
		btnOk.setBounds(228, 281, 89, 23);
		contentPane.add(btnOk);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(335, 281, 89, 23);
		contentPane.add(btnCancel);
		
		lbError = new JLabel("");
		lbError.setForeground(Color.RED);
		lbError.setBounds(10, 285, 210, 14);
		contentPane.add(lbError);
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

		for(int i = 1900;i < Year.now().getValue() - 14;i++) {
			choiceYear.add(String.valueOf(i));
		}
		
		if (gostZaEdit != null) {
			tfIme.setText(gostZaEdit.getIme());
			tfPrezime.setText(gostZaEdit.getPrezime());
			if (gostZaEdit.getPol().equals("Musko")) {
				rdbtnMuski.setSelected(true);
			}
			else {
				rdbtnZenski.setSelected(true);
			}
			
//			String datumRodjenjaStr = choiceDay.getSelectedItem() + "." + choiceMonth.getSelectedItem() + "." + choiceYear.getSelectedItem() + ".";
			String datumRodjenjaStr = gostZaEdit.getDatumStr();
			LocalDate ld = LocalDate.parse(datumRodjenjaStr, DateTimeFormatter.ofPattern("dd.MM.uuuu."));
			choiceDay.select(ld.getDayOfMonth() - 1);
			choiceMonth.select(ld.getMonthValue() - 1);
			choiceYear.select(String.valueOf(ld.getYear()));
			tfAdresa.setText(gostZaEdit.getAdresa());
			tfBrTelefona.setText(gostZaEdit.getBrojTelefona());
			tfEmail.setText(gostZaEdit.getEmail());
			tfBrPasosa.setText(gostZaEdit.getBrPasosa());
		}
		
	}
	
	public boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
 }
	
	public static boolean isNumeric(String str) { 
		  try {  
		    Integer.parseInt(str);  
		    return true;
		  } catch(NumberFormatException e){  
		    return false;  
		  }  
	}
}
