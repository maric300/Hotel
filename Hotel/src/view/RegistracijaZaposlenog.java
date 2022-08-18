package view;
import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import entity.Zaposlen;
import entity.Zaposlen.Posao;
import manage.ManagerFactory;

import javax.swing.JList;
import javax.swing.JComboBox;

public class RegistracijaZaposlenog extends JFrame {

	private JPanel contentPane;
	private JTextField tfIme;
	private JTextField tfPrezime;
	private JTextField tfAdresa;
	private JTextField tfBrTelefona;
	private JTextField tfEmail;
	private JTextField tfBrPasosa;
	private JLabel lbError;
	private JTextField tfNivoStrucneSpreme;
	private JTextField tfStaz;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					RegistracijaZaposlenog frame = new RegistracijaZaposlenog();
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
	public RegistracijaZaposlenog(ManagerFactory factoryMng) {
		setTitle("Registracija zaposlenog");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 478);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbWelcome = new JLabel("Unesite sve potrebne podatke zaposlenog kojeg registrujete");
		lbWelcome.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbWelcome.setBounds(10, 11, 378, 14);
		contentPane.add(lbWelcome);
		
		JLabel lbIme = new JLabel("Ime");
		lbIme.setBounds(10, 84, 46, 14);
		contentPane.add(lbIme);
		
		JLabel lbPrezime = new JLabel("Prezime");
		lbPrezime.setBounds(10, 109, 46, 14);
		contentPane.add(lbPrezime);
		
		tfIme = new JTextField();
		tfIme.setBounds(111, 81, 116, 20);
		contentPane.add(tfIme);
		tfIme.setColumns(10);
		
		tfPrezime = new JTextField();
		tfPrezime.setBounds(111, 106, 116, 20);
		contentPane.add(tfPrezime);
		tfPrezime.setColumns(10);
		
		JLabel lbPol = new JLabel("Pol");
		lbPol.setBounds(10, 134, 46, 14);
		contentPane.add(lbPol);
		
		JRadioButton rdbtnMuski = new JRadioButton("Muško");
		rdbtnMuski.setBounds(85, 130, 85, 23);
		contentPane.add(rdbtnMuski);
		
		JRadioButton rdbtnZenski = new JRadioButton("Žensko");
		rdbtnZenski.setBounds(172, 130, 85, 23);
		contentPane.add(rdbtnZenski);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnZenski);
		group.add(rdbtnMuski);
		
		JLabel lblNewLabel_3 = new JLabel("Datum rođenja");
		lblNewLabel_3.setBounds(10, 165, 86, 14);
		contentPane.add(lblNewLabel_3);
		
		Choice choiceDay = new Choice();
		choiceDay.setBounds(99, 163, 39, 20);
		contentPane.add(choiceDay);
		
		Choice choiceMonth = new Choice();
		choiceMonth.setBounds(147, 163, 73, 20);
		contentPane.add(choiceMonth);
		
		Choice choiceYear = new Choice();
		choiceYear.setBounds(231, 163, 51, 20);
		contentPane.add(choiceYear);
		
		JLabel lbAdresa = new JLabel("Adresa");
		lbAdresa.setBounds(10, 202, 46, 14);
		contentPane.add(lbAdresa);
		
		JLabel lbBrTelefona = new JLabel("Broj telefona");
		lbBrTelefona.setBounds(10, 230, 86, 14);
		contentPane.add(lbBrTelefona);
		
		tfAdresa = new JTextField();
		tfAdresa.setBounds(111, 202, 116, 20);
		contentPane.add(tfAdresa);
		tfAdresa.setColumns(10);
		
		tfBrTelefona = new JTextField();
		tfBrTelefona.setBounds(111, 224, 116, 20);
		contentPane.add(tfBrTelefona);
		tfBrTelefona.setColumns(10);
		
		JLabel lbEmail = new JLabel("Email");
		lbEmail.setBounds(10, 255, 46, 14);
		contentPane.add(lbEmail);
		
		JLabel lbBrPasosa = new JLabel("Broj Pasoša");
		lbBrPasosa.setBounds(10, 280, 86, 14);
		contentPane.add(lbBrPasosa);
		
		tfEmail = new JTextField();
		tfEmail.setBounds(111, 249, 146, 20);
		contentPane.add(tfEmail);
		tfEmail.setColumns(10);
		
		tfBrPasosa = new JTextField();
		tfBrPasosa.setBounds(111, 274, 116, 20);
		contentPane.add(tfBrPasosa);
		tfBrPasosa.setColumns(10);
		
		JLabel lbPosao = new JLabel("Posao");
		lbPosao.setBounds(10, 53, 46, 14);
		contentPane.add(lbPosao);
		
		JLabel lbNivoStrucneSpreme = new JLabel("Nivo Stručne spreme");
		lbNivoStrucneSpreme.setBounds(10, 305, 146, 14);
		contentPane.add(lbNivoStrucneSpreme);
		
		JLabel lbStaz = new JLabel("Staž");
		lbStaz.setBounds(10, 330, 46, 14);
		contentPane.add(lbStaz);
		
		tfNivoStrucneSpreme = new JTextField();
		tfNivoStrucneSpreme.setBounds(141, 302, 116, 20);
		contentPane.add(tfNivoStrucneSpreme);
		tfNivoStrucneSpreme.setColumns(10);
		
		tfStaz = new JTextField();
		tfStaz.setBounds(111, 327, 116, 20);
		contentPane.add(tfStaz);
		tfStaz.setColumns(10);
		
		String s1[] = { "RECEPCIONER", "SOBARICA"};
		JComboBox comboBoxPosao = new JComboBox(s1);
		comboBoxPosao.setBounds(111, 48, 116, 22);
		contentPane.add(comboBoxPosao);
		String posao = comboBoxPosao.getSelectedItem().toString();
		
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Boolean isOk = true;
				String ime = tfIme.getText();
				String prezime = tfPrezime.getText();
				String pol = null;
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
				
				String adresa = tfAdresa.getText();
				String brojTelefona = tfBrTelefona.getText();
				String email = tfEmail.getText();
				String brPasosa = tfBrPasosa.getText();
				String nivoStrucneSpreme = tfNivoStrucneSpreme.getText();
				String staz = tfStaz.getText();
//				String plata = null;
				
				
				
				if (ime.equals("")) {
					lbError.setText("Ime ne sme biti prazno");
					isOk = false;
				}
				
				char[] chars = ime.toCharArray();
				for(char c : chars) {
					if (Character.isDigit(c)) {
						isOk = false;
						break;
					}
				}
				
				if (prezime.equals("")) {
					lbError.setText("Prezime ne sme biti prazno");
					isOk = false;
				}
				
				try {
				    SimpleDateFormat df = new java.text.SimpleDateFormat("dd.MM.yyyy.");
				    df.setLenient(false);
				    df.parse(datumRodjenjaStr);
				} catch (java.text.ParseException e1) {
				  lbError.setText("Neispravan datum");
				  isOk = false;
				}
				
				if (isOk.equals(true)) {
					
					factoryMng.getZaposlenManager().getZaposleni().add(new Zaposlen(Posao.valueOf(posao), ime, prezime, pol, datumRodjenjaStr, adresa, brojTelefona, email, brPasosa, Integer.parseInt(nivoStrucneSpreme), Integer.parseInt(staz)));
					dispose();
				}
			}
		});
		btnOk.setBounds(228, 405, 89, 23);
		contentPane.add(btnOk);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(335, 405, 89, 23);
		contentPane.add(btnCancel);
		
		lbError = new JLabel("");
		lbError.setForeground(Color.RED);
		lbError.setBounds(10, 387, 210, 14);
		contentPane.add(lbError);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(111, 49, 116, 22);
		contentPane.add(comboBox);
		
		 
		
		for(int i = 1;i < 32;i++) {
			choiceDay.add(String.valueOf(i));
		}
		
		for(int i = 1;i < 13;i++) {
			choiceMonth.add(String.valueOf(i));
		}

		for(int i = 1900;i < Year.now().getValue() - 14;i++) {
			choiceYear.add(String.valueOf(i));
		}

	}
}
