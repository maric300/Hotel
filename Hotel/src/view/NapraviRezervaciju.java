package view;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.EventQueue;
import java.time.Year;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import entity.TipSobe;
import entity.DodatnaUsluga;
import entity.Gost;
import entity.Rezervacija;
import entity.Rezervacija.Status;
import manage.ManagerFactory;
import net.miginfocom.swing.MigLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
	public NapraviRezervaciju(ManagerFactory factoryMng, Gost ulogovaniGost) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Rezervacija");
		setBounds(100, 100, 404, 345);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("wrap, fill", "[][][][]", "[][][][][]"));
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
		
		for(int i = 1;i < 32;i++) {
			choiceDay.add(String.valueOf(i));
		}
		
		for(int i = 1;i < 13;i++) {
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
		
		for(int i = 1;i < 32;i++) {
			choiceDay1.add(String.valueOf(i));
		}
		
		for(int i = 1;i < 13;i++) {
			choiceMonth1.add(String.valueOf(i));
		}

		for(int i = Year.now().getValue();i < Year.now().getValue() + 2;i++) {
			choiceYear1.add(String.valueOf(i));
		}
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
				
				factoryMng.getRezervacijaMng().getRezervacije().add(new Rezervacija(status, ulogovaniGost.getEmail(), tipSobe, dodatnaUslugaList, checkInDateStr, checkOutdateStr));
				dispose();
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
