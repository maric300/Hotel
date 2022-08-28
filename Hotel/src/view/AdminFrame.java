package view;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import manage.ManagerFactory;
import model.GostModel;
import viewTable.TabelaRezervacija;
import viewTable.TabelaSoba;
import viewTable.TabelaZaposlenih;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Comparator;

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
	}
	

}
