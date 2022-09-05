package viewTable;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;

import entity.Gost;
import entity.Rezervacija;
import entity.Soba;
import entity.TipSobe;
import entity.Zaposlen;
import entity.Rezervacija.Status;
import entity.Soba.StatusSobe;
import entity.Zaposlen.Posao;
import manage.GostManager;
import manage.ManagerFactory;
import model.GostModel;
import model.RezervacijaModel;
import model.RezervacijaModel;
import view.CheckInWindow;
import view.NapraviRezervaciju;
import view.NapraviSobu;
import view.RegistracijaZaposlenog;

public class TabelaRezervacijaRecepcioner extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	
	private ManagerFactory factoryMng;

	protected JToolBar mainToolbar = new JToolBar();
	protected JButton btnPotvrdi = new JButton("Potvrdi");
	protected JButton btnOdbij = new JButton("Odbij");
//	protected JButton btnDelete = new JButton();
	protected JButton btnCheckIn = new JButton("CheckIn");
	protected JButton btnCheckOut = new JButton("CheckOut");

	protected JTextField tfSearch = new JTextField(20);
	protected TableRowSorter<AbstractTableModel> tableSorter = new TableRowSorter<AbstractTableModel>();
	protected JTable table;

	public TabelaRezervacijaRecepcioner(ManagerFactory factoryMng) {
		this.factoryMng = factoryMng;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setLocationRelativeTo(null);
		setTitle("Rezervacije");
		setContentPane(contentPane);
		setLocationRelativeTo(null);		
		
		ImageIcon addIcon = new ImageIcon("img/add.png");		
		ImageIcon scaled = new ImageIcon(addIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		addIcon = scaled;
		mainToolbar.add(btnPotvrdi);
		ImageIcon editIcon = new ImageIcon("img/edit.gif");
		btnOdbij.setIcon(editIcon);
		mainToolbar.add(btnOdbij);
		ImageIcon deleteIcon = new ImageIcon("img/remove.gif");
		mainToolbar.add(btnCheckIn);
		mainToolbar.add(btnCheckOut);
//		btnDelete.setIcon(deleteIcon);
//		mainToolbar.add(btnDelete);
		mainToolbar.setFloatable(false);		
		add(mainToolbar, BorderLayout.NORTH);
		
		RezervacijaModel mdl = new RezervacijaModel(factoryMng.getRezervacijaMng());
		table = new JTable(mdl);
		table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setReorderingAllowed(false);
		// podesavanje manuelnog sortera tabele, potrebno i za pretragu
		if (factoryMng.getRezervacijaMng().getRezervacije().size() != 0) {
			tableSorter.setModel((AbstractTableModel) table.getModel());
		}
		table.setRowSorter(tableSorter);
		JScrollPane sc = new JScrollPane(table);
		add(sc, BorderLayout.CENTER);
		
		table.getTableHeader().addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// preuzimanje indeksa kolone potrebnog za sortiranje
				int index = table.getTableHeader().columnAtPoint(arg0.getPoint());
				
				// call abstract sort method
//				sort(index);
			}
			
		});
	
		JPanel pSearch = new JPanel(new FlowLayout(FlowLayout.LEFT));		
		pSearch.setBackground(Color.cyan);
		pSearch.add(new JLabel("Search:"));
		pSearch.add(tfSearch);
		
		add(pSearch, BorderLayout.SOUTH);
		
		tfSearch.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				changedUpdate(e);
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				changedUpdate(e);
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				//System.out.println("~ "+tfSearch.getText());
				if (tfSearch.getText().trim().length() == 0) {
				     tableSorter.setRowFilter(null);
				  } else {
					  tableSorter.setRowFilter(RowFilter.regexFilter("(?i)" + tfSearch.getText().trim()));
				  }
			}
		});
		
		btnPotvrdi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int red = table.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					int id = (int) table.getValueAt(red, 0);
					Rezervacija s = factoryMng.getRezervacijaMng().IdToObject(id);
					List<Soba> sobe = new ArrayList<Soba>();
					List<Rezervacija> rezervacije = new ArrayList<Rezervacija>();
					sobe = factoryMng.getSobaMng().getSobe();
					rezervacije = factoryMng.getRezervacijaMng().getRezervacije();
					if (s.getStatus().equals(Status.NA_CEKANJU)) {
						int izbor = JOptionPane.showConfirmDialog(null,"Da li ste sigurni da zelite da potvrdite rezervaciju?", 
								"Rezervacija id " + String.valueOf(s.getId()) + " - Potvrda rezervacije", JOptionPane.YES_NO_OPTION);
						if(izbor == JOptionPane.YES_OPTION) {
							TipSobe tipSobeRez = s.getTipSobe();
							String checkInDateRezStr = s.getCheckInDateStr();
							String checkOutDateRezStr = s.getCheckOutDateStr();
							LocalDate NaCekanjuldIn = LocalDate.parse(checkInDateRezStr, DateTimeFormatter.ofPattern("dd.MM.uuuu."));
							LocalDate NaCekanjuldOut = LocalDate.parse(checkOutDateRezStr, DateTimeFormatter.ofPattern("dd.MM.uuuu."));
							Boolean cont = false;
							System.out.println("uspelo1!!");

							for (Soba soba : sobe) {
								if (soba.getTipSobe().equals(tipSobeRez)) {
									cont = true;
									break;
								}
							}
							if (cont.equals(true)) {
								int rezCnt = 0;
								for (Rezervacija rezervacija : rezervacije) {
									if (rezervacija.getStatus().equals(Status.POTVRDJENA) && rezervacija.getTipSobe().equals(tipSobeRez)) {
										rezCnt++;
										LocalDate PotvrdjenldIn = LocalDate.parse(rezervacija.getCheckInDateStr(), DateTimeFormatter.ofPattern("dd.MM.uuuu."));
										LocalDate PotvrdjenldOut = LocalDate.parse(rezervacija.getCheckOutDateStr(), DateTimeFormatter.ofPattern("dd.MM.uuuu."));
										if (NaCekanjuldIn.isAfter(PotvrdjenldOut) && NaCekanjuldOut.isAfter(PotvrdjenldOut)) {
											s.setStatus(Status.POTVRDJENA);
											refreshData();
											System.out.println("1");
											break;
										}
										else if (PotvrdjenldIn.isAfter(NaCekanjuldIn) && PotvrdjenldIn.isAfter(NaCekanjuldOut)) {
											s.setStatus(Status.POTVRDJENA);
											refreshData();
											System.out.println("2");
											break;
										}
										else {
											JOptionPane.showMessageDialog(null, "Ne postoji slobodna soba u tom opsegu datuma!", "Greska", JOptionPane.ERROR_MESSAGE);
										}
									}									
								}
								if (rezCnt == 0) {
									s.setStatus(Status.POTVRDJENA);
									refreshData();
									System.out.println("3");
								}
							}
							else {
								JOptionPane.showMessageDialog(null, "Soba odabranog tipa nije u funkciji!", "Greska", JOptionPane.ERROR_MESSAGE);
							}
						
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "Rezervacija nije na čekanju!", "Greska", JOptionPane.ERROR_MESSAGE);

					}
				}
				
			}
		});
		
		btnOdbij.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = table.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}
				else {
					int id = (int) table.getValueAt(red, 0);
					Rezervacija s = factoryMng.getRezervacijaMng().IdToObject(id);
					if (s.getStatus().equals(Status.NA_CEKANJU)) {
						int izbor = JOptionPane.showConfirmDialog(null,"Da li ste sigurni da zelite da odbijete rezervaciju?", 
								"Rezervacija id " + String.valueOf(s.getId()) + " - Potvrda odbijanja", JOptionPane.YES_NO_OPTION);
						if(izbor == JOptionPane.YES_OPTION) {
							s.setStatus(Status.ODBIJENA);
							refreshData();
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "Rezervacija nije na čekanju!", "Greska", JOptionPane.ERROR_MESSAGE);

					}
					
				}
			}
		});
		
		btnCheckIn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = table.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}
				else {
					int id = (int) table.getValueAt(red, 0);
					Rezervacija s = factoryMng.getRezervacijaMng().IdToObject(id);
					LocalDate ldIn = LocalDate.parse(s.getCheckInDateStr(), DateTimeFormatter.ofPattern("dd.MM.uuuu."));
					if(ldIn.equals(LocalDate.now())) {
						if (s.getStatus().equals(Status.POTVRDJENA) && s.getIdSobe() == -1) {
							CheckInWindow ciw = new CheckInWindow(factoryMng, s);
							ciw.setVisible(true);
						}
						else {
							JOptionPane.showMessageDialog(null, "Rezervacija nije potvrđena ili je već check-in-ovana", "Greska", JOptionPane.WARNING_MESSAGE);

						}
					}
					else {
						JOptionPane.showMessageDialog(null, "Gost može da se check-inuje samo na dan rezervacije", "Greska", JOptionPane.WARNING_MESSAGE);

					}

					
				}
			}
		});

		btnCheckOut.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				int red = table.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}
				else {
					int id = (int) table.getValueAt(red, 0);
					Rezervacija s = factoryMng.getRezervacijaMng().IdToObject(id);
					LocalDate ldOut = LocalDate.parse(s.getCheckOutDateStr(), DateTimeFormatter.ofPattern("dd.MM.uuuu."));
					if(ldOut.equals(LocalDate.now())) {
						HashMap<String, Integer> hmSobarice = new HashMap<String, Integer>();
						for (Zaposlen z : factoryMng.getZaposlenManager().getZaposleni()) {
							if (z.getPosao().equals(Posao.SOBARICA)) {
								hmSobarice.put(z.getEmail(), 0);
							}
						}

						for (Soba soba : factoryMng.getSobaMng().getSobe()) {
							if (soba.getStatus().equals(StatusSobe.SPREMANJE)) {
								System.out.println(soba.getId());
								hmSobarice.replace(soba.getEmailSobarice(), hmSobarice.get(soba.getEmailSobarice()) + 1);
							}
						}
						Integer min = -1;
						String minSobarica = null;
						for (String key : hmSobarice.keySet()) {
							if (hmSobarice.get(key) < min || min == -1) {
								min = hmSobarice.get(key);
								minSobarica = key;
							}
						}
						factoryMng.getSobaMng().IdToObject(s.getIdSobe()).setEmailSobarice(minSobarica);
						factoryMng.getSobaMng().IdToObject(s.getIdSobe()).setStatus(StatusSobe.SPREMANJE);


					}
					else {
						JOptionPane.showMessageDialog(null, "Gost može da se check-outuje samo na poslednji dan rezervacije", "Greska", JOptionPane.WARNING_MESSAGE);

					}

					
				}
			}
		});
	}


	
	
	
	// potrebno osvezavanje podataka u tabeli bez gasenja prozora
	public void refreshData() {
		RezervacijaModel sm = (RezervacijaModel)this.table.getModel();
		sm.fireTableDataChanged();
	}
	
	// Pamcenje redosleda sortiranja za svaku kolonu posebno - primer
		@SuppressWarnings("serial")
		private Map<Integer, Integer> sortOrder = new HashMap<Integer, Integer>() {{put(0, 1);put(1, 1);put(2, 1);put(3, 1);}};

// Manuelni sorter - potrebno za razumevanje rada podrazumevanog sortera tabele
	protected void sort(int index) {
		// index of table column
		this.factoryMng.getRezervacijaMng().getRezervacije().sort(new Comparator<Rezervacija>() {
			int retVal = 0;
			@Override
			public int compare(Rezervacija o1, Rezervacija o2) {
				switch (index) {
				case 0:
//					retVal = o1.getId().compareTo(o2.getId());
					break;
				case 1:
//					retVal = o1.getPrezime().compareTo(o2.getPrezime());
					break;
				case 2:
//					retVal = o1.getPrezime().compareTo(o2.getPrezime());
					break;
				case 3:
//					retVal = o1.getIndeks().compareTo(o2.getIndeks());
					break;
				default:
					System.out.println("Prosirena tabela");
					System.exit(1);
					break;
				}
				return retVal*sortOrder.get(index);
			}
		});
		
		System.out.println("kolona "+index+" smer "+sortOrder.get(index));
		sortOrder.put(index, sortOrder.get(index)*-1);
		refreshData();	
		
	}
	

	
}
