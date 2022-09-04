package view;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entity.Gost;
import manage.ManagerFactory;

import javax.swing.JToolBar;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import net.miginfocom.swing.MigLayout;
import viewTable.GostTabelaRezervacija;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GostFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					GostFrame frame = new GostFrame();
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
	public GostFrame(ManagerFactory factoryMng, Gost ulogovaniGost) {
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
		
		JMenu mnGostMenu = new JMenu("Navigacija");
		menuBar.add(mnGostMenu);
		
		JMenuItem mntmRezervisi = new JMenuItem("Rezerviši apartman...");
		mnGostMenu.add(mntmRezervisi);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("fill", "[][][]", "[][][]"));
		
		JLabel lblNewLabel = new JLabel("<html>Dobrodošli u hotel aplikaciju. Molimo vas izaberite jednu od stavki iz taba za navigaciju.</html>");
		contentPane.add(lblNewLabel, "cell 1 0,alignx center");
		
		JButton btnNapraviRezervaciju = new JButton("Napravi rezervaciju");
		btnNapraviRezervaciju.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NapraviRezervaciju nr = new NapraviRezervaciju(factoryMng, ulogovaniGost);
				nr.setVisible(true);
			}
		});
		contentPane.add(btnNapraviRezervaciju, "cell 1 3");
		
		JButton btnMojeRezervacije = new JButton("Moje rezervacije");
		btnMojeRezervacije.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				GostTabelaRezervacija gtr = new GostTabelaRezervacija(factoryMng, ulogovaniGost.getEmail());
				gtr.setVisible(true);
				
			}
		});
		contentPane.add(btnMojeRezervacije);
		
		
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
