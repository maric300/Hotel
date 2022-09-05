package view;
import java.awt.Choice;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import charts.ChartSobarice;
import charts.ChartStatusRezervacija;
import charts.ChartTipoviSoba;
import entity.CenovnikTipSobe;
import entity.Rezervacija;
import entity.Rezervacija.Status;
import entity.SobaIzvestaj;
import entity.SredjivanjeSoba;
import entity.TipSobe;
import entity.Zaposlen;
import manage.ManagerFactory;
import model.GostModel;
import net.miginfocom.swing.MigLayout;
import viewTable.TabelaCenovnikaDodatneUsluge;
import viewTable.TabelaCenovnikaTipaSobe;
import viewTable.TabelaDodatnihUsluga;
import viewTable.TabelaRezervacija;
import viewTable.TabelaSoba;
import viewTable.TabelaTipovaSoba;
import viewTable.TabelaZaposlenih;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminFrame extends JFrame {

//	private JPanel contentPane;
//	/**
//	 * @wbp.nonvisual location=132,369
//	 */
//	private final JPopupMenu popupMenu = new JPopupMenu();
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					AdminFrame frame = new AdminFrame();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	
	JLabel lbOdDatuma = new JLabel("Od datuma");
	JLabel lbDoDatma = new JLabel("Do datuma");
	JButton btnPrihodRashod= new JButton("Prihodi i rashodi");
	JButton btnSredjeneSObe = new JButton("Sredjene sobe");
	JButton btnPrihvaceneRez = new JButton("Prihvacene rezervacije");
	JButton btnOtkazaneOdbijeneRez = new JButton("otkazane/odbijene rezervacije");
	JButton btnPrikazSoba = new JButton("Izvestaj o sobama");
	JLabel lbError = new JLabel(" ");
	

	/**
	 * Create the frame.
	 */
	public AdminFrame(ManagerFactory factoryMng) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				factoryMng.saveData();
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setTitle("Admin");
		setLayout(new MigLayout("fill, wrap", "[][][][][]", "[][][][]"));
		
		
		add(lbOdDatuma);
		
		Choice choiceDay = new Choice();
		add(choiceDay);
		
		Choice choiceMonth = new Choice();
		add(choiceMonth);
		
		Choice choiceYear = new Choice();
		add(choiceYear, "span 2");
		
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
		
		add(lbDoDatma);
		
		Choice choiceDay1 = new Choice();
		add(choiceDay1);
		
		Choice choiceMonth1 = new Choice();
		add(choiceMonth1);
		
		Choice choiceYear1 = new Choice();
		add(choiceYear1, "span 2");
		
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
		
		add(lbError, "span 5");
		lbError.setForeground(Color.red);
		
		add(btnPrihodRashod);
		add(btnSredjeneSObe);
		add(btnPrihvaceneRez);
		add(btnOtkazaneOdbijeneRez);
		add(btnPrikazSoba);
		
		btnPrihodRashod.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String odDatuma = choiceDay.getSelectedItem() + "." + choiceMonth.getSelectedItem() + "." + choiceYear.getSelectedItem() + ".";
				String doDatuma = choiceDay1.getSelectedItem() + "." + choiceMonth1.getSelectedItem() + "." + choiceYear1.getSelectedItem() + ".";
				LocalDate ldIn = LocalDate.parse(odDatuma, DateTimeFormatter.ofPattern("dd.MM.uuuu."));
				LocalDate ldOut = LocalDate.parse(doDatuma, DateTimeFormatter.ofPattern("dd.MM.uuuu."));
				long opseg = ChronoUnit.DAYS.between(ldIn, ldOut);
				if(areDatesValid(odDatuma, doDatuma).equals(true)) {
					String printer = "";
					int rashod = 0;
					int prihod = 0;
					int tempCena = 0;
					List<Rezervacija> rezervacije = factoryMng.getRezervacijaMng().getRezervacije();
					for (Rezervacija rezervacija : rezervacije) {
						if (rezervacija.getStatus().equals(Status.POTVRDJENA) || rezervacija.getStatus().equals(Status.OTKAZANA)) {

							String rezDatumStr1 = rezervacija.getCheckInDateStr();
							String rezDatumStr2 = rezervacija.getCheckOutDateStr();
							LocalDate ldRez1 = LocalDate.parse(rezDatumStr1, DateTimeFormatter.ofPattern("dd.MM.uuuu."));
							LocalDate ldRez2 = LocalDate.parse(rezDatumStr2, DateTimeFormatter.ofPattern("dd.MM.uuuu."));
							
							long opsegRez = ChronoUnit.DAYS.between(ldRez1, ldRez2);
							TipSobe tipSobe = rezervacija.getTipSobe();
							CenovnikTipSobe cenovnik = factoryMng.getCenovnikTipSobeMng().NameToObject(tipSobe.getNaziv());
							if (cenovnik != null) {
								for(LocalDate startDate = ldRez1; startDate.isBefore(ldRez2);startDate = startDate.plusDays(1)) {
									System.out.println(startDate);
									if (startDate.isBefore(ldOut) && startDate.isAfter(ldIn)) {
										Map<LocalDate, Integer> hmCenaTipSobe = cenovnik.getCene();
		
										tempCena = factoryMng.getCenovnikTipSobeMng().DateToInt(startDate, hmCenaTipSobe);
										if (tempCena == 0) {
											prihod = prihod + tipSobe.getCenaPoNocenju();
										}
										else {
											prihod = prihod + tempCena;
		
										}
									}
								}
							}
							else {
								prihod = (int) (tipSobe.getCenaPoNocenju() * opsegRez);
							}

						}
						
						if (rezervacija.getStatus().equals(Status.ODBIJENA)) {
							tempCena = 0;
							String rezDatumStr1 = rezervacija.getCheckInDateStr();
							String rezDatumStr2 = rezervacija.getCheckOutDateStr();
							LocalDate ldRez1 = LocalDate.parse(rezDatumStr1, DateTimeFormatter.ofPattern("dd.MM.uuuu."));
							LocalDate ldRez2 = LocalDate.parse(rezDatumStr2, DateTimeFormatter.ofPattern("dd.MM.uuuu."));
							
							long opsegRez = ChronoUnit.DAYS.between(ldRez1, ldRez2);
							TipSobe tipSobe = rezervacija.getTipSobe();
							CenovnikTipSobe cenovnik = factoryMng.getCenovnikTipSobeMng().NameToObject(tipSobe.getNaziv());
							if (cenovnik != null) {
								for(LocalDate startDate = ldRez1; startDate.isBefore(ldRez2);startDate = startDate.plusDays(1)) {
									System.out.println(startDate);
									if (startDate.isBefore(ldOut) && startDate.isAfter(ldIn)) {
										Map<LocalDate, Integer> hmCenaTipSobe = cenovnik.getCene();
		
										tempCena = factoryMng.getCenovnikTipSobeMng().DateToInt(startDate, hmCenaTipSobe);
										if (tempCena == 0) {
											rashod = rashod + tipSobe.getCenaPoNocenju();
										}
										else {
											rashod = rashod + tempCena;
		
										}
									}
								}
							}
							else {
								rashod = (int) (tipSobe.getCenaPoNocenju() * opsegRez);
							}

						}
					}
					int brojPlata = isFirstDayOfMonthInRange(odDatuma, doDatuma);
					if (brojPlata > 1) {
						List<Zaposlen> zaposleni = factoryMng.getZaposlenManager().getZaposleni();
						for (Zaposlen zaposlen : zaposleni) {
							rashod = rashod + zaposlen.getPlata() * brojPlata;
							System.out.println("Zaposlen rashod = " + rashod);
						}
					}
					printer = "Prihod: " + prihod + "\nRashod: " + rashod;
					
					JOptionPane.showMessageDialog(null, printer, "Prihod i rashod", JOptionPane.INFORMATION_MESSAGE);
				}
				
				
			}
		});
		
		btnSredjeneSObe.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String odDatuma = choiceDay.getSelectedItem() + "." + choiceMonth.getSelectedItem() + "." + choiceYear.getSelectedItem() + ".";
				String doDatuma = choiceDay1.getSelectedItem() + "." + choiceMonth1.getSelectedItem() + "." + choiceYear1.getSelectedItem() + ".";
				LocalDate ldIn = LocalDate.parse(odDatuma, DateTimeFormatter.ofPattern("dd.MM.uuuu."));
				LocalDate ldOut = LocalDate.parse(doDatuma, DateTimeFormatter.ofPattern("dd.MM.uuuu."));
				long opseg = ChronoUnit.DAYS.between(ldIn, ldOut);
				if(areDatesValid(odDatuma, doDatuma).equals(true)) {
					String printer = "";
					HashMap<String, Integer> hmSredjivanje = factoryMng.getSredjivanjeMng().SredjivanjeSobaUOpsegu(ldIn, ldOut);
					
					if (!hmSredjivanje.isEmpty()) {
						for(String key : hmSredjivanje.keySet()) {
							printer += key + " - broj ociscenjih soba: " + hmSredjivanje.get(key) + "\n";
						}
						JOptionPane.showMessageDialog(null, printer, "Ciscenje soba", JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						JOptionPane.showMessageDialog(null, "Nema ciscenja soba u odabranom opsegu datuma.", "Ciscenje soba", JOptionPane.INFORMATION_MESSAGE);
					}
					

				}
				
			}
		});
		
		btnPrihvaceneRez.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String odDatuma = choiceDay.getSelectedItem() + "." + choiceMonth.getSelectedItem() + "." + choiceYear.getSelectedItem() + ".";
				String doDatuma = choiceDay1.getSelectedItem() + "." + choiceMonth1.getSelectedItem() + "." + choiceYear1.getSelectedItem() + ".";
				LocalDate ldIn = LocalDate.parse(odDatuma, DateTimeFormatter.ofPattern("dd.MM.uuuu."));
				LocalDate ldOut = LocalDate.parse(doDatuma, DateTimeFormatter.ofPattern("dd.MM.uuuu."));
				long opseg = ChronoUnit.DAYS.between(ldIn, ldOut);
				if(areDatesValid(odDatuma, doDatuma).equals(true)) {
					String printer = "";
					int brojPonavljanja = 0;
					for (Rezervacija rezervacija : factoryMng.getRezervacijaMng().getRezervacije()) {
						LocalDate ldRezIn = LocalDate.parse(rezervacija.getCheckInDateStr(), DateTimeFormatter.ofPattern("dd.MM.uuuu."));
						LocalDate ldRezOut = LocalDate.parse(rezervacija.getCheckOutDateStr(), DateTimeFormatter.ofPattern("dd.MM.uuuu."));

						for(LocalDate startDate = ldIn; startDate.isBefore(ldOut); startDate = startDate.plusDays(1)) {
							if ((startDate.isBefore(ldRezOut) || startDate.isEqual(ldRezOut)) && (startDate.isAfter(ldRezIn) || startDate.isEqual(ldRezIn)) && rezervacija.getStatus().equals(Status.POTVRDJENA)) {
								brojPonavljanja++;
								break;
							}
						}
					}
					JOptionPane.showMessageDialog(null, "Broj potvrdjenih rezervacija - " + brojPonavljanja, "Broj potvrdjenih rezervacija", JOptionPane.INFORMATION_MESSAGE);

				}
			}
		});
		
		btnOtkazaneOdbijeneRez.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String odDatuma = choiceDay.getSelectedItem() + "." + choiceMonth.getSelectedItem() + "." + choiceYear.getSelectedItem() + ".";
				String doDatuma = choiceDay1.getSelectedItem() + "." + choiceMonth1.getSelectedItem() + "." + choiceYear1.getSelectedItem() + ".";
				LocalDate ldIn = LocalDate.parse(odDatuma, DateTimeFormatter.ofPattern("dd.MM.uuuu."));
				LocalDate ldOut = LocalDate.parse(doDatuma, DateTimeFormatter.ofPattern("dd.MM.uuuu."));
				long opseg = ChronoUnit.DAYS.between(ldIn, ldOut);
				if(areDatesValid(odDatuma, doDatuma).equals(true)) {
					String printer = "";
					int brojPonavljanjaOtkazane = 0;
					int brojPonavljanjaOdbijene = 0;

					for (Rezervacija rezervacija : factoryMng.getRezervacijaMng().getRezervacije()) {
						LocalDate ldRezIn = LocalDate.parse(rezervacija.getCheckInDateStr(), DateTimeFormatter.ofPattern("dd.MM.uuuu."));
						LocalDate ldRezOut = LocalDate.parse(rezervacija.getCheckOutDateStr(), DateTimeFormatter.ofPattern("dd.MM.uuuu."));

						for(LocalDate startDate = ldIn; startDate.isBefore(ldOut); startDate = startDate.plusDays(1)) {
							if ((startDate.isBefore(ldRezOut) || startDate.isEqual(ldRezOut)) && (startDate.isAfter(ldRezIn) || startDate.isEqual(ldRezIn)) && (rezervacija.getStatus().equals(Status.OTKAZANA))) {
								brojPonavljanjaOtkazane++;
								break;
							}
							else if ((startDate.isBefore(ldRezOut) || startDate.isEqual(ldRezOut)) && (startDate.isAfter(ldRezIn) || startDate.isEqual(ldRezIn)) && (rezervacija.getStatus().equals(Status.ODBIJENA))) {
								brojPonavljanjaOdbijene++;
								break;
							}
						}
					}
					printer = "Broj otkazanih - " + brojPonavljanjaOtkazane + "\nBroj odbijenih - " + brojPonavljanjaOdbijene;
					JOptionPane.showMessageDialog(null, printer, "Broj otkazanih/odbijenih rezervacija", JOptionPane.INFORMATION_MESSAGE);

				}
			}
		});
		
		btnPrikazSoba.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String odDatuma = choiceDay.getSelectedItem() + "." + choiceMonth.getSelectedItem() + "." + choiceYear.getSelectedItem() + ".";
				String doDatuma = choiceDay1.getSelectedItem() + "." + choiceMonth1.getSelectedItem() + "." + choiceYear1.getSelectedItem() + ".";
				LocalDate ldIn = LocalDate.parse(odDatuma, DateTimeFormatter.ofPattern("dd.MM.uuuu."));
				LocalDate ldOut = LocalDate.parse(doDatuma, DateTimeFormatter.ofPattern("dd.MM.uuuu."));
				long opseg = ChronoUnit.DAYS.between(ldIn, ldOut);
				List<SobaIzvestaj> izvestajiSoba = new ArrayList<SobaIzvestaj>();
				String printer = "";
				int tempCena = 0;
				int ukupnaCena = 0;
				if(areDatesValid(odDatuma, doDatuma).equals(true)) {
					for(LocalDate startDate = ldIn; startDate.isBefore(ldOut); startDate = startDate.plusDays(1)) {
						for (Rezervacija rezervacija : factoryMng.getRezervacijaMng().getRezervacije()) {
							LocalDate ldRezIn = LocalDate.parse(rezervacija.getCheckInDateStr(), DateTimeFormatter.ofPattern("dd.MM.uuuu."));
							LocalDate ldRezOut = LocalDate.parse(rezervacija.getCheckOutDateStr(), DateTimeFormatter.ofPattern("dd.MM.uuuu."));
							if((startDate.isAfter(ldRezIn) || startDate.isEqual(ldRezIn)) && (startDate.isBefore(ldRezOut) || startDate.isEqual(ldRezOut)) && (rezervacija.getIdSobe() != -1)) {
								Boolean found = false;
								TipSobe tipSobe = rezervacija.getTipSobe();
								CenovnikTipSobe cenovnik = factoryMng.getCenovnikTipSobeMng().NameToObject(tipSobe.getNaziv());
//								-------------------------------
								if (cenovnik != null) {
									Map<LocalDate, Integer> hmCenaTipSobe = cenovnik.getCene();
										tempCena = factoryMng.getCenovnikTipSobeMng().DateToInt(startDate, hmCenaTipSobe);
										if (tempCena == 0) {
											ukupnaCena = tipSobe.getCenaPoNocenju();
										}
										else {
									    	ukupnaCena = tempCena;

										}
										
								}
								else {
									ukupnaCena = tipSobe.getCenaPoNocenju();
								}
//								-------------------------------
								for (SobaIzvestaj izvestaj : izvestajiSoba) {
									if (izvestaj.getId() == rezervacija.getIdSobe()) {
										found = true;
										izvestaj.setBrojNocenja(izvestaj.getBrojNocenja() + 1);
										izvestaj.setPrihodi(izvestaj.getPrihodi() + ukupnaCena);
										break;
									}
								}
								if (found.equals(false)) {
									izvestajiSoba.add(new SobaIzvestaj(rezervacija.getIdSobe(), rezervacija.getTipSobe().getNaziv(), 1, ukupnaCena));
								}
								
							}
						}
					}
					
					for(SobaIzvestaj izvestaj : izvestajiSoba) {
						printer += izvestaj.toString() + "\n";
					}
					JOptionPane.showMessageDialog(null, printer, "Prikaz soba", JOptionPane.INFORMATION_MESSAGE);

					
				}
				
			}
		});
		
		
		
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnRegistruj = new JMenu("Registruj..");
		menuBar.add(mnRegistruj);
		
		JMenuItem mntmRegistrujGosta = new JMenuItem("Registruj gosta");
		mntmRegistrujGosta.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				RegistracijaGosta rg = new RegistracijaGosta(null, factoryMng, null);
				rg.show();
			}
		});
		mnRegistruj.add(mntmRegistrujGosta);
		
		JMenuItem mntmRegistrujRecepcionera = new JMenuItem("Registruj recepcionera");
		mntmRegistrujRecepcionera.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				RegistracijaZaposlenog rz = new RegistracijaZaposlenog(null, factoryMng, null);
				rz.show();
			}
		});
		mnRegistruj.add(mntmRegistrujRecepcionera);
		
		JMenu mnPrikazi = new JMenu("Prikaži..");
		menuBar.add(mnPrikazi);
		
		JMenuItem mntmPrikaziGoste = new JMenuItem("Prikaži goste");
		mnPrikazi.add(mntmPrikaziGoste);
		mntmPrikaziGoste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TabelaKorisnika tk = new TabelaKorisnika(factoryMng);
				tk.setVisible(true);
			}
		});
		
		JMenuItem mntmPrikaziZaposlene = new JMenuItem("Prikaži zaposlene");
		mnPrikazi.add(mntmPrikaziZaposlene);
		
		JMenuItem mntmPrikaziSobe = new JMenuItem("Prikaži sobe");
		mnPrikazi.add(mntmPrikaziSobe);
		
		JMenuItem mntmRezervacije = new JMenuItem("Prikaži rezervacije");
		mnPrikazi.add(mntmRezervacije);
		
		JMenuItem mntmTipoviSobe = new JMenuItem("Prikaži tipove soba");
		mnPrikazi.add(mntmTipoviSobe);
		
		JMenuItem mntmDodatneUsluge = new JMenuItem("Prikaži dodatne usluge");
		mnPrikazi.add(mntmDodatneUsluge);
				
		JMenuItem mntmCenovnikTipovaSoba = new JMenuItem("Prikaži cenovnik tipova soba");
		mnPrikazi.add(mntmCenovnikTipovaSoba);
		
		JMenuItem mntmCenovnikDodatnihUsluga= new JMenuItem("Prikaži cenovnik dodatnih usluga");
		mnPrikazi.add(mntmCenovnikDodatnihUsluga);
		
		JMenu mnGrafici = new JMenu("Grafici");
		menuBar.add(mnGrafici);
		
		JMenuItem mntmGrafikTipovaSoba= new JMenuItem("Prikazi grafik tipova soba");
		mnGrafici.add(mntmGrafikTipovaSoba);
		
		JMenuItem mntmGrafikSobarice= new JMenuItem("Prikazi grafik opterecenja sobarica");
		mnGrafici.add(mntmGrafikSobarice);
		
		JMenuItem mntmGrafikRezervacija= new JMenuItem("Prikazi grafik statusa rezervacija");
		mnGrafici.add(mntmGrafikRezervacija);
		
		mntmGrafikRezervacija.addActionListener(new ActionListener(){
    
      @Override
      public void actionPerformed(ActionEvent e) {
        ChartStatusRezervacija ctr = new ChartStatusRezervacija(factoryMng);
        ctr.setVisible(true);
        
      }
    });
		
		mntmGrafikSobarice.addActionListener(new ActionListener(){
    
      @Override
      public void actionPerformed(ActionEvent e) {
        ChartSobarice cs = new ChartSobarice(factoryMng);
        cs.setVisible(true);
        
      }
    });
		
		mntmGrafikTipovaSoba.addActionListener(new ActionListener(){
    
      @Override
      public void actionPerformed(ActionEvent e) {
        ChartTipoviSoba cts = new ChartTipoviSoba(factoryMng);
        cts.setVisible(true);
        
      }
    });
		
		mntmCenovnikDodatnihUsluga.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				TabelaCenovnikaDodatneUsluge tcd = new TabelaCenovnikaDodatneUsluge(factoryMng);
				tcd.setVisible(true);
				
			}
		});
		
		mntmDodatneUsluge.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				TabelaDodatnihUsluga tdu = new TabelaDodatnihUsluga(factoryMng);
				tdu.setVisible(true);

			}
		});
		
		mntmCenovnikTipovaSoba.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				TabelaCenovnikaTipaSobe tcts = new TabelaCenovnikaTipaSobe(factoryMng);
				tcts.setVisible(true);

			}
		});
		
		mntmTipoviSobe.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				TabelaTipovaSoba tts = new TabelaTipovaSoba(factoryMng);
				tts.setVisible(true);

			}
		});
		
		mntmRezervacije.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TabelaRezervacija tr = new TabelaRezervacija(factoryMng);
				tr.setVisible(true);
			}
		});
		
		mntmPrikaziSobe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TabelaSoba ts = new TabelaSoba(factoryMng);
				ts.setVisible(true);
			}
		});
		
		mntmPrikaziZaposlene.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TabelaZaposlenih tz = new TabelaZaposlenih(factoryMng);
				tz.setVisible(true);
			}
		});
//		contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//		contentPane.setLayout(new BorderLayout(0, 0));
//		setContentPane(contentPane);
		pack();
	}
	
	Boolean areDatesValid(String datum1, String datum2) {
		try {
		    SimpleDateFormat df = new java.text.SimpleDateFormat("dd.MM.yyyy.");
		    df.setLenient(false);
		    df.parse(datum1);
		} catch (java.text.ParseException e1) {
		  lbError.setText("Neispravan prvi datum");
		  return false;
		}
		
		try {
		    SimpleDateFormat df = new java.text.SimpleDateFormat("dd.MM.yyyy.");
		    df.setLenient(false);
		    df.parse(datum2);
		} catch (java.text.ParseException e1) {
		  lbError.setText("Neispravan drugi datum");
		  return false;
		  
		}
		
		LocalDate ldIn = LocalDate.parse(datum1, DateTimeFormatter.ofPattern("dd.MM.uuuu."));
		LocalDate ldOut = LocalDate.parse(datum2, DateTimeFormatter.ofPattern("dd.MM.uuuu."));
		if (ldIn.isAfter(ldOut)) {
			lbError.setText("drugi datum mora biti nakon prvog datuma!");
			return false;
		}
		return true;
	}
	
	int isFirstDayOfMonthInRange(String datum1, String datum2) {
		int cnt = 0;
		LocalDate ldIn = LocalDate.parse(datum1, DateTimeFormatter.ofPattern("dd.MM.uuuu."));
		LocalDate ldOut = LocalDate.parse(datum2, DateTimeFormatter.ofPattern("dd.MM.uuuu."));
		long opseg = ChronoUnit.DAYS.between(ldIn, ldOut);
		for(LocalDate startDate = ldIn; startDate.isBefore(ldOut); startDate = startDate.plusDays(1)) {
			if(startDate.getDayOfMonth() == 1) {
				cnt++;
			}
		}
		return cnt;
	}
	

}
