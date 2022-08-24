package view;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import manage.ManagerFactory;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class RecepcionerFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					RecepcionerFrame frame = new RecepcionerFrame();
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
	public RecepcionerFrame(ManagerFactory factoryMng) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				factoryMng.saveData();
			}
		});
		setTitle("Hotel (Recepcioner)");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnRecepcionerNavigacija = new JMenu("Navigacija");
		menuBar.add(mnRecepcionerNavigacija);
		
		JMenuItem mntmRegistrujGosta = new JMenuItem("Registruj gosta");
		mntmRegistrujGosta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistracijaGosta rg = new RegistracijaGosta(null, factoryMng, null);
				rg.show();
			}
		});
		mnRecepcionerNavigacija.add(mntmRegistrujGosta);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}
