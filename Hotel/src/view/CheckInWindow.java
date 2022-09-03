package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

import entity.DodatnaUsluga;
import entity.Rezervacija;
import entity.Soba;
import entity.Soba.StatusSobe;
import manage.ManagerFactory;
import net.miginfocom.swing.MigLayout;

public class CheckInWindow extends JFrame {
	
	private JLabel lbSobe;
	private JLabel lbDodatneUsluge;
	private JList lstSobe;
	private JList lstDodatneUsluge;
	private JButton btnCheckIn;
	private JButton btnCancel;
	private JLabel lbError;
	
	
	public CheckInWindow(ManagerFactory factoryMng, Rezervacija rezervacija) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Check-in");
		setLocationRelativeTo(null);
		
		
		MigLayout ml = new MigLayout("wrap, fill", "[][]", "[][][][]");
		setLayout(ml);
		
		lbSobe = new JLabel("Dodeli sobu");
		add(lbSobe);
		
		List<Soba> sobe = factoryMng.getSobaMng().getSobe();
		List<String> freeSobe = new ArrayList<String>();
		for (Soba s : sobe) {
			if (s.getTipSobe().equals(rezervacija.getTipSobe()) && s.getStatus().equals(StatusSobe.SLOBODNA)) {
				freeSobe.add(String.valueOf(s.getId()));
			}
		}
		
		String[] s3 = new String[freeSobe.size()];
		for (int i = 0; i < freeSobe.size(); i++) {
			s3[i] = freeSobe.get(i);
		}
		
		lstSobe = new JList(s3);
		add(lstSobe);
		
		lbDodatneUsluge = new JLabel("Dodatne usluge");
		add(lbDodatneUsluge);
		
		List<DodatnaUsluga> dodatneUsluge = factoryMng.getUslugaMng().getDodatneUsluge();
		List<DodatnaUsluga> dodatneUslugeRez = rezervacija.getDodatnaUslugaList();
		String[] s2 = new String[dodatneUsluge.size()];
		for (int i = 0; i < dodatneUsluge.size(); i++) {
			if (dodatneUslugeRez.contains(dodatneUsluge.get(i))) {
				continue;
			}
			else {
				s2[i] = dodatneUsluge.get(i).getNaziv();
			}
		}
		
		lstDodatneUsluge = new JList(s2);
		lstDodatneUsluge.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		add(lstDodatneUsluge);
		
		lbError = new JLabel(" ");
		add(lbError, "span 2");
		
		btnCheckIn = new JButton("Check-in");
		add(btnCheckIn);
		
		btnCheckIn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				int idSobe = Integer.parseInt(lstSobe.getSelectedValue().toString());
				Soba checkInSoba = factoryMng.getSobaMng().IdToObject(idSobe);
				checkInSoba.setStatus(StatusSobe.ZAUZETO);
				
			}
		});
		
		btnCancel = new JButton("Cancel");
		add(btnCancel);
		
		
		
	}
}