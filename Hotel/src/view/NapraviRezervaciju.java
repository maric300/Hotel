package view;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.EventQueue;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import entity.TipSobe;
import entity.CenovnikDodatneUsluge;
import entity.CenovnikTipSobe;
import entity.DodatnaUsluga;
import entity.Gost;
import entity.Rezervacija;
import entity.Rezervacija.Status;
import manage.ManagerFactory;
import net.miginfocom.swing.MigLayout;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.time.temporal.ChronoUnit;

public class NapraviRezervaciju extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					NapraviRezervaciju frame = new NapraviRezervaciju();
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
	public NapraviRezervaciju(ManagerFactory factoryMng,Gost ulogovaniGost) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Napravi rezervaciju");
		setBounds(100, 100, 404, 345);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("wrap, fill", "[][][][]", "[][][][][][]"));
		JLabel lbTipSobe = new JLabel("Tip sobe");
		contentPane.add(lbTipSobe, "cell 0 0");
		
		List<TipSobe> tipoviSobe = factoryMng.getTipSobeMng().getTipoviSobe();
		String[] s1 = new String[tipoviSobe.size()];
		for (int i = 0; i < tipoviSobe.size(); i++) {
			s1[i] = tipoviSobe.get(i).getNaziv();
		}
		JComboBox comboBoxTipSobe = new JComboBox(s1);
		contentPane.add(comboBoxTipSobe, "cell 1 0, span 3");
		
		JLabel lbDodatnaUsluga = new JLabel("Dodatne usluge");
		contentPane.add(lbDodatnaUsluga);
		
		List<DodatnaUsluga> dodatneUsluge = factoryMng.getUslugaMng().getDodatneUsluge();
		String[] s2 = new String[dodatneUsluge.size()];
		for (int i = 0; i < dodatneUsluge.size(); i++) {
			s2[i] = dodatneUsluge.get(i).getNaziv();
		}
		JList comboBoxUsluga = new JList(s2);
		comboBoxUsluga.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		contentPane.add(comboBoxUsluga, "span 3");
		
		JLabel lbCheckInDate = new JLabel("check-in datum");
		contentPane.add(lbCheckInDate);
		
		Choice choiceDay = new Choice();
		contentPane.add(choiceDay);
		
		Choice choiceMonth = new Choice();
		contentPane.add(choiceMonth);
		
		Choice choiceYear = new Choice();
		contentPane.add(choiceYear);
		
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
		
		JLabel lbCheckOutDate = new JLabel("check-out datum");
		contentPane.add(lbCheckOutDate);
		
		Choice choiceDay1 = new Choice();
		contentPane.add(choiceDay1);
		
		Choice choiceMonth1 = new Choice();
		contentPane.add(choiceMonth1);
		
		Choice choiceYear1 = new Choice();
		contentPane.add(choiceYear1);
		
		choiceDay1.add("01");
		choiceDay1.add("02");
		choiceDay1.add("03");
		choiceDay1.add("04");
		choiceDay1.add("05");
		choiceDay1.add("06");
		choiceDay1.add("07");
		choiceDay1.add("08");
		choiceDay1.add("09");
		
		for(int i = 10;i < 32;i++) {
			choiceDay1.add(String.valueOf(i));
		}
		
		choiceMonth1.add("01");
		choiceMonth1.add("02");
		choiceMonth1.add("03");
		choiceMonth1.add("04");
		choiceMonth1.add("05");
		choiceMonth1.add("06");
		choiceMonth1.add("07");
		choiceMonth1.add("08");
		choiceMonth1.add("09");
		
		for(int i = 10;i < 13;i++) {
			choiceMonth1.add(String.valueOf(i));
		}

		for(int i = Year.now().getValue();i < Year.now().getValue() + 2;i++) {
			choiceYear1.add(String.valueOf(i));
		}
		
		JLabel lbError = new JLabel(" ");
		contentPane.add(lbError, "span 4");
		JButton btnRezervisi = new JButton("Rezerviši");
		contentPane.add(btnRezervisi, "span 2, gaptop 20px, alignx center");
		btnRezervisi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Status status = Status.NA_CEKANJU;
				String tipSobestr = comboBoxTipSobe.getSelectedItem().toString();
				TipSobe tipSobe = factoryMng.getTipSobeMng().NameToObject(tipSobestr);
				List<String> dodatnaUslugaStrList = comboBoxUsluga.getSelectedValuesList();
				System.out.println(dodatnaUslugaStrList);
				List<DodatnaUsluga> dodatnaUslugaList = factoryMng.getUslugaMng().ListToObject(dodatnaUslugaStrList);
				String checkInDateStr = choiceDay.getSelectedItem() + "." + choiceMonth.getSelectedItem() + "." + choiceYear.getSelectedItem() + ".";
				String checkOutdateStr = choiceDay1.getSelectedItem() + "." + choiceMonth1.getSelectedItem() + "." + choiceYear1.getSelectedItem() + ".";
				List<Rezervacija> rezervacije = factoryMng.getRezervacijaMng().getRezervacije();
				int id;
				Boolean isOk = true;
				int ukupnaCena = 0;
				while (true) {
					Boolean isValid = true;
					id = ThreadLocalRandom.current().nextInt(0, 9999);
					for (Rezervacija r : rezervacije) {
						if (r.getId() == id) {
							isValid = false;
						}
					}
					if (isValid == true) {
						break;
					}
				}
				
				try {
				    SimpleDateFormat df = new java.text.SimpleDateFormat("dd.MM.yyyy.");
				    df.setLenient(false);
				    df.parse(checkInDateStr);
				} catch (java.text.ParseException e1) {
				  lbError.setText("Neispravan check-in datum");
				  isOk = false;
				}
				
				try {
				    SimpleDateFormat df = new java.text.SimpleDateFormat("dd.MM.yyyy.");
				    df.setLenient(false);
				    df.parse(checkOutdateStr);
				} catch (java.text.ParseException e1) {
				  lbError.setText("Neispravan check-out datum");
				  isOk = false;
				}
				
				if (isOk == true) {
					LocalDate ldIn = LocalDate.parse(checkInDateStr, DateTimeFormatter.ofPattern("dd.MM.uuuu."));
					LocalDate ldOut = LocalDate.parse(checkOutdateStr, DateTimeFormatter.ofPattern("dd.MM.uuuu."));
					if (ldIn.isAfter(ldOut)) {
						lbError.setText("check-out datum mora biti nakon check-in datuma!");
						isOk = false;
					}
					
					if (LocalDate.now().isAfter(ldIn)) {
						lbError.setText("check-in datum mora biti nakon danasnjeg datuma!");
						isOk = false;
					}
					int tempCena = 0;
					long opseg = ChronoUnit.DAYS.between(ldIn, ldOut);
					CenovnikTipSobe cenovnik = factoryMng.getCenovnikTipSobeMng().NameToObject(tipSobe.getNaziv());
					if (cenovnik != null) {
						Map<LocalDate, Integer> hmCenaTipSobe = cenovnik.getCene();
						for (LocalDate startDate = ldIn; startDate.isBefore(ldOut); startDate = startDate.plusDays(1)) {
							tempCena = factoryMng.getCenovnikTipSobeMng().DateToInt(startDate, hmCenaTipSobe);
							if (tempCena == 0) {
								ukupnaCena = ukupnaCena + tipSobe.getCenaPoNocenju();
							}
							else {
						    	ukupnaCena = ukupnaCena + tempCena;

							}
					    	System.out.println(startDate.toString() + "---" + String.valueOf(ukupnaCena));
							
						}
					}
					else {
						ukupnaCena = (int) (tipSobe.getCenaPoNocenju() * opseg);
					}
					System.out.println("_________");
					if (!dodatnaUslugaList.isEmpty()) {
						for (DodatnaUsluga usluga : dodatnaUslugaList) {
							CenovnikDodatneUsluge cenovnik1 = factoryMng.getCenovnikDodatneUslugeMng().NameToObject(usluga.getNaziv());
							if (cenovnik1 != null) {
								Map<LocalDate, Integer> hmCenaDodatnaUsluga = cenovnik1.getCene();
								for (LocalDate startDate = ldIn; startDate.isBefore(ldOut); startDate = startDate.plusDays(1)) {
									tempCena = factoryMng.getCenovnikTipSobeMng().DateToInt(startDate, hmCenaDodatnaUsluga);
									if (tempCena == 0) {
										ukupnaCena = ukupnaCena + usluga.getCena();
									}
									else {
								    	ukupnaCena = ukupnaCena + tempCena;
									}
//									ukupnaCena = ukupnaCena + factoryMng.getCenovnikTipSobeMng().DateToInt(startDate, hmCenaTipSobe);
									System.out.println(usluga.getNaziv() + "---" + startDate.toString() + "---" + String.valueOf(ukupnaCena));
								}
							}
						}
					}
					
					
				}
				
				
				if (isOk == true) {
					
					if (ulogovaniGost == null) {
						factoryMng.getRezervacijaMng().getRezervacije().add(new Rezervacija(id, status, "admin", tipSobe, dodatnaUslugaList, checkInDateStr, checkOutdateStr, -1, ukupnaCena));
					}
					else {
						
						factoryMng.getRezervacijaMng().getRezervacije().add(new Rezervacija(id, status, ulogovaniGost.getEmail(), tipSobe, dodatnaUslugaList, checkInDateStr, checkOutdateStr, -1, ukupnaCena));
					}
					dispose();
				}
			}
		});
		
		
		JButton btnOdustani = new JButton("Odustani");
		btnOdustani.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		contentPane.add(btnOdustani, "span 2, gaptop 20px, alignx center");
	}
//-------------------------------------------------------------------------------------------------
	public NapraviRezervaciju(JFrame parent, ManagerFactory factoryMng, Rezervacija editRez){
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Izmeni rezervaciju");
		setBounds(100, 100, 1100, 345);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("wrap, fill", "[][][][]", "[][][][][][]"));
		JLabel lbTipSobe = new JLabel("Tip sobe");
		contentPane.add(lbTipSobe, "cell 0 0");
		
		List<TipSobe> tipoviSobe = factoryMng.getTipSobeMng().getTipoviSobe();
		String[] s1 = new String[tipoviSobe.size()];
		for (int i = 0; i < tipoviSobe.size(); i++) {
			s1[i] = tipoviSobe.get(i).getNaziv();
		}
		JComboBox comboBoxTipSobe = new JComboBox(s1);
		contentPane.add(comboBoxTipSobe, "cell 1 0, span 3");
		
		JLabel lbDodatnaUsluga = new JLabel("Dodatne usluge");
		contentPane.add(lbDodatnaUsluga);
		
		List<DodatnaUsluga> dodatneUsluge = factoryMng.getUslugaMng().getDodatneUsluge();
		String[] s2 = new String[dodatneUsluge.size()];
		for (int i = 0; i < dodatneUsluge.size(); i++) {
			s2[i] = dodatneUsluge.get(i).getNaziv();
		}
		JList comboBoxUsluga = new JList(s2);
		comboBoxUsluga.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		contentPane.add(comboBoxUsluga, "span 3");
		
		JLabel lbCheckInDate = new JLabel("check-in datum");
		contentPane.add(lbCheckInDate);
		
		Choice choiceDay = new Choice();
		contentPane.add(choiceDay);
		
		Choice choiceMonth = new Choice();
		contentPane.add(choiceMonth);
		
		Choice choiceYear = new Choice();
		contentPane.add(choiceYear);
		
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
		
		JLabel lbCheckOutDate = new JLabel("check-out datum");
		contentPane.add(lbCheckOutDate);
		
		Choice choiceDay1 = new Choice();
		contentPane.add(choiceDay1);
		
		Choice choiceMonth1 = new Choice();
		contentPane.add(choiceMonth1);
		
		Choice choiceYear1 = new Choice();
		contentPane.add(choiceYear1);
		
		choiceDay1.add("01");
		choiceDay1.add("02");
		choiceDay1.add("03");
		choiceDay1.add("04");
		choiceDay1.add("05");
		choiceDay1.add("06");
		choiceDay1.add("07");
		choiceDay1.add("08");
		choiceDay1.add("09");
		
		for(int i = 10;i < 32;i++) {
			choiceDay1.add(String.valueOf(i));
		}
		
		choiceMonth1.add("01");
		choiceMonth1.add("02");
		choiceMonth1.add("03");
		choiceMonth1.add("04");
		choiceMonth1.add("05");
		choiceMonth1.add("06");
		choiceMonth1.add("07");
		choiceMonth1.add("08");
		choiceMonth1.add("09");
		
		for(int i = 10;i < 13;i++) {
			choiceMonth1.add(String.valueOf(i));
		}

		for(int i = Year.now().getValue();i < Year.now().getValue() + 2;i++) {
			choiceYear1.add(String.valueOf(i));
		}
		
		JLabel lbError = new JLabel(" ");
		contentPane.add(lbError, "span 4");
		JButton btnRezervisi = new JButton("Rezerviši");
		contentPane.add(btnRezervisi, "span 2, gaptop 20px, alignx center");
		
		
		
		btnRezervisi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Status status = Status.NA_CEKANJU;
				String tipSobestr = comboBoxTipSobe.getSelectedItem().toString();
				TipSobe tipSobe = factoryMng.getTipSobeMng().NameToObject(tipSobestr);
				List<String> dodatnaUslugaStrList = comboBoxUsluga.getSelectedValuesList();
				System.out.println(dodatnaUslugaStrList);
				List<DodatnaUsluga> dodatnaUslugaList = factoryMng.getUslugaMng().ListToObject(dodatnaUslugaStrList);
				String checkInDateStr = choiceDay.getSelectedItem() + "." + choiceMonth.getSelectedItem() + "." + choiceYear.getSelectedItem() + ".";
				String checkOutdateStr = choiceDay1.getSelectedItem() + "." + choiceMonth1.getSelectedItem() + "." + choiceYear1.getSelectedItem() + ".";
				List<Rezervacija> rezervacije = factoryMng.getRezervacijaMng().getRezervacije();
				int id;
				Boolean isOk = true;
				int ukupnaCena = 0;
				while (true) {
					Boolean isValid = true;
					id = ThreadLocalRandom.current().nextInt(0, 9999);
					for (Rezervacija r : rezervacije) {
						if (r.getId() == id) {
							isValid = false;
						}
					}
					if (isValid == true) {
						break;
					}
				}
				
				try {
				    SimpleDateFormat df = new java.text.SimpleDateFormat("dd.MM.yyyy.");
				    df.setLenient(false);
				    df.parse(checkInDateStr);
				} catch (java.text.ParseException e1) {
				  lbError.setText("Neispravan check-in datum");
				  isOk = false;
				}
				
				try {
				    SimpleDateFormat df = new java.text.SimpleDateFormat("dd.MM.yyyy.");
				    df.setLenient(false);
				    df.parse(checkOutdateStr);
				} catch (java.text.ParseException e1) {
				  lbError.setText("Neispravan check-out datum");
				  isOk = false;
				}
				
				if (isOk == true) {
					LocalDate ldIn = LocalDate.parse(checkInDateStr, DateTimeFormatter.ofPattern("dd.MM.uuuu."));
					LocalDate ldOut = LocalDate.parse(checkOutdateStr, DateTimeFormatter.ofPattern("dd.MM.uuuu."));
					if (ldIn.isAfter(ldOut)) {
						lbError.setText("check-out datum mora biti nakon check-in datuma!");
						isOk = false;
					}
					
					if (LocalDate.now().isAfter(ldIn)) {
						lbError.setText("check-in datum mora biti nakon danasnjeg datuma!");
						isOk = false;
					}
					long opseg = ChronoUnit.DAYS.between(ldIn, ldOut);
					CenovnikTipSobe cenovnik = factoryMng.getCenovnikTipSobeMng().NameToObject(tipSobe.getNaziv());
					if (cenovnik != null) {
						Map<LocalDate, Integer> hmCenaTipSobe = cenovnik.getCene();
						LocalDate latestDate = factoryMng.getCenovnikTipSobeMng().getLatestDate(hmCenaTipSobe);
						int cenaTipa = hmCenaTipSobe.get(latestDate);
						ukupnaCena = (int) (cenaTipa * opseg);
					}
					else {
						ukupnaCena = (int) (tipSobe.getCenaPoNocenju() * opseg);
					}
				}
				
				
				if (isOk == true) {
					
					if (ulogovaniGost == null) {
						factoryMng.getRezervacijaMng().getRezervacije().add(new Rezervacija(id, status, "admin", tipSobe, dodatnaUslugaList, checkInDateStr, checkOutdateStr, -1, ukupnaCena));
					}
					else {
						
						factoryMng.getRezervacijaMng().getRezervacije().add(new Rezervacija(id, status, ulogovaniGost.getEmail(), tipSobe, dodatnaUslugaList, checkInDateStr, checkOutdateStr, -1, ukupnaCena));
					}
					dispose();
				}
			}
		});
		
		
		JButton btnOdustani = new JButton("Odustani");
		btnOdustani.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		contentPane.add(btnOdustani, "span 2, gaptop 20px, alignx center");
	}

}
