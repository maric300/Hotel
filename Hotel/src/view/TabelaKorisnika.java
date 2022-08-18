package view;
import java.awt.BorderLayout;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import entity.Gost;
import manage.GostManager;
import manage.ManagerFactory;
import model.GostModel;

public class TabelaKorisnika extends JFrame {

	private JPanel contentPane;
	
	private GostManager gostMng;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TabelaKorisnika frame = new TabelaKorisnika();
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
	public TabelaKorisnika(ManagerFactory factoryMng) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		GostModel mdl = new GostModel(factoryMng.getGostMng());
		JTable j;
		j = new JTable(mdl);
	
        JScrollPane sp = new JScrollPane(j);
        contentPane.add(sp);
        // Frame Size
        contentPane.setSize(500, 200);
        // Frame Visible = true
        contentPane.setVisible(true);
	}
	

	
}
