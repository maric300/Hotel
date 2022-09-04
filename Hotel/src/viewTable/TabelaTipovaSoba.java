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
import entity.Soba;
import entity.TipSobe;
import entity.Zaposlen;
import manage.GostManager;
import manage.ManagerFactory;
import model.GostModel;
import model.SobaModel;
import model.TipSobeModel;
import view.NapraviSobu;
import view.NapraviTipSobe;
import view.RegistracijaZaposlenog;

public class TabelaTipovaSoba extends JFrame {

	private JPanel contentPane;
	
	private ManagerFactory factoryMng;

	protected JToolBar mainToolbar = new JToolBar();
	protected JButton btnAdd = new JButton();
	protected JButton btnEdit = new JButton();
	protected JButton btnDelete = new JButton();
	protected JTextField tfSearch = new JTextField(20);
	protected TableRowSorter<AbstractTableModel> tableSorter = new TableRowSorter<AbstractTableModel>();
	protected JTable table;

	public TabelaTipovaSoba(ManagerFactory factoryMng) {
		this.factoryMng = factoryMng;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setTitle("Tipovi soba");
		setContentPane(contentPane);
		setLocationRelativeTo(null);		
		
		ImageIcon addIcon = new ImageIcon("img/add.png");		
		ImageIcon scaled = new ImageIcon(addIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		addIcon = scaled;
		btnAdd.setIcon(addIcon);
		mainToolbar.add(btnAdd);
		ImageIcon editIcon = new ImageIcon("img/edit.gif");
		btnEdit.setIcon(editIcon);
		mainToolbar.add(btnEdit);
		ImageIcon deleteIcon = new ImageIcon("img/remove.gif");
		btnDelete.setIcon(deleteIcon);
		mainToolbar.add(btnDelete);
		mainToolbar.setFloatable(false);		
		add(mainToolbar, BorderLayout.NORTH);
		
		TipSobeModel mdl = new TipSobeModel(factoryMng.getTipSobeMng());
		table = new JTable(mdl);
		table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setReorderingAllowed(false);
		// podesavanje manuelnog sortera tabele, potrebno i za pretragu
		if (factoryMng.getTipSobeMng().getTipoviSobe().size() != 0) {
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
		
		
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				NapraviTipSobe ns = new NapraviTipSobe(TabelaTipovaSoba.this, factoryMng, null);
				ns.setVisible(true);
			}
		});
		
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = table.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					String naziv = (String) table.getValueAt(red, 1);
					TipSobe s = factoryMng.getTipSobeMng().NameToObject(naziv);
					if(s != null) {
						NapraviTipSobe ns = new NapraviTipSobe(TabelaTipovaSoba.this, factoryMng, s);
						ns.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabrani tip sobe!", "Greska", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = table.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					String naziv = (String) table.getValueAt(red, 1);
					TipSobe s = factoryMng.getTipSobeMng().NameToObject(naziv);
					if(s != null) {
						int izbor = JOptionPane.showConfirmDialog(null,"Da li ste sigurni da zelite da obrisete tip sobe?", 
								"Soba " + String.valueOf(s.getNaziv()) + " - Potvrda brisanja", JOptionPane.YES_NO_OPTION);
						if(izbor == JOptionPane.YES_OPTION) {
							factoryMng.getTipSobeMng().remove(s.getNaziv());
							refreshData();
						}
					}else {
						JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabrani tip sobe!", "Greska", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		
		

		
		
	}

	
	
	
	// potrebno osvezavanje podataka u tabeli bez gasenja prozora
	public void refreshData() {
		TipSobeModel sm = (TipSobeModel)this.table.getModel();
		sm.fireTableDataChanged();
	}
	
	// Pamcenje redosleda sortiranja za svaku kolonu posebno - primer
		@SuppressWarnings("serial")
		private Map<Integer, Integer> sortOrder = new HashMap<Integer, Integer>() {{put(0, 1);put(1, 1);put(2, 1);put(3, 1);}};

// Manuelni sorter - potrebno za razumevanje rada podrazumevanog sortera tabele
	protected void sort(int index) {
		// index of table column
		this.factoryMng.getTipSobeMng().getTipoviSobe().sort(new Comparator<TipSobe>() {
			int retVal = 0;
			@Override
			public int compare(TipSobe o1, TipSobe o2) {
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
