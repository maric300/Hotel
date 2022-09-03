package view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
import entity.SredjivanjeSoba;
import manage.GostManager;
import manage.ManagerFactory;
import model.GostModel;
import model.RezervacijaModel;
import model.SobaModel;
import model.SobaricaModel;
import model.RezervacijaModel;
import view.CheckInWindow;
import view.NapraviRezervaciju;
import view.NapraviSobu;
import view.RegistracijaZaposlenog;
import viewTable.TabelaSoba;

public class SobaricaFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	
	private ManagerFactory factoryMng;

	protected JToolBar mainToolbar = new JToolBar();
	protected JButton btnOcistiSobu = new JButton("Ocisti sobu");
	protected JTextField tfSearch = new JTextField(20);
	protected TableRowSorter<AbstractTableModel> tableSorter = new TableRowSorter<AbstractTableModel>();
	protected JTable table;

	public SobaricaFrame(ManagerFactory factoryMng, Zaposlen sobarica) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				factoryMng.saveData();
			}
		});
		this.factoryMng = factoryMng;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setLocationRelativeTo(null);
		setTitle("Sobarica");
		setContentPane(contentPane);
		setLocationRelativeTo(null);		
		setIconImage(new ImageIcon("img/icon.png").getImage());
		
		ImageIcon addIcon = new ImageIcon("img/add.png");		
		ImageIcon scaled = new ImageIcon(addIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		addIcon = scaled;
		mainToolbar.add(btnOcistiSobu);
		ImageIcon editIcon = new ImageIcon("img/edit.gif");//		mainToolbar.add(btnDelete);
		mainToolbar.setFloatable(false);		
		add(mainToolbar, BorderLayout.NORTH);
		
		SobaricaModel mdl = new SobaricaModel(factoryMng.getSobaMng(), sobarica.getEmail());
		table = new JTable(mdl);
		table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setReorderingAllowed(false);
		// podesavanje manuelnog sortera tabele, potrebno i za pretragu
		if (factoryMng.getSobaMng().VratiSobeZaSpremanje(sobarica.getEmail()).size() != 0) {
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
		
		btnOcistiSobu.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				int red = table.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}
				else {
					int id = (int) table.getValueAt(red, 0);
					Soba sobaCiscenja = factoryMng.getSobaMng().IdToObject(id);
					String DanasStr = LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy."));
					int brojCiscenja = 1;
					Boolean postoji = false;
					for (SredjivanjeSoba sredjivanjeSoba : factoryMng.getSredjivanjeMng().getSredjivanjeSobaLista()) {
						if (sredjivanjeSoba.getDatumCiscenjaStr().equals(DanasStr) && sredjivanjeSoba.getImeSobarice().equals(sobarica.getEmail())) {
							sredjivanjeSoba.setBrOciscenjihSoba(sredjivanjeSoba.getBrOciscenjihSoba() + brojCiscenja);
							postoji = true;
						}
					}
					if(postoji == false) {
						factoryMng.getSredjivanjeMng().getSredjivanjeSobaLista().add(new SredjivanjeSoba(DanasStr, sobarica.getEmail(), brojCiscenja));
					}
					sobaCiscenja.setStatus(StatusSobe.SLOBODNA);
					refreshData();
				}
				
			}
		});
		
		
		
	}
		

	// potrebno osvezavanje podataka u tabeli bez gasenja prozora
	public void refreshData() {
		SobaModel sm = (SobaModel)this.table.getModel();
		sm.fireTableDataChanged();
	}
	
	// Pamcenje redosleda sortiranja za svaku kolonu posebno - primer
		@SuppressWarnings("serial")
		private Map<Integer, Integer> sortOrder = new HashMap<Integer, Integer>() {{put(0, 1);put(1, 1);put(2, 1);put(3, 1);}};

// Manuelni sorter - potrebno za razumevanje rada podrazumevanog sortera tabele
	protected void sort(int index) {
		// index of table column
		this.factoryMng.getSobaMng().getSobe().sort(new Comparator<Soba>() {
			int retVal = 0;
			@Override
			public int compare(Soba o1, Soba o2) {
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
