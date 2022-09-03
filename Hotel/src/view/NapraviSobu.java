package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import entity.Soba;
import entity.TipSobe;
import entity.Soba.StatusSobe;
import manage.ManagerFactory;
import net.miginfocom.swing.MigLayout;
import viewTable.TabelaSoba;

public class NapraviSobu extends JFrame {
	
	private Soba sobaZaEdit;
	
	public NapraviSobu(JFrame parent, ManagerFactory factoryMng, Soba sobaZaEdit) {
		
		this.sobaZaEdit = sobaZaEdit;
		if (sobaZaEdit != null) {
			setTitle("Izmena sobe");
		}
		else {
			setTitle("Dodavanje sobe");
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		
		MigLayout ml = new MigLayout("wrap, fill", "[][]", "[][][][]");
		setLayout(ml);
		
		JLabel lbId = new JLabel("id");
		add(lbId);
		
		JTextField tfId = new JTextField(20);
		add(tfId);
		
		JLabel lbTipSobe = new JLabel("Tip sobe");
		add(lbTipSobe);
		
		List<TipSobe> tipoviSobe = factoryMng.getTipSobeMng().getTipoviSobe();
		List<Soba> sobe = factoryMng.getSobaMng().getSobe();
		String[] s1 = new String[tipoviSobe.size()];
		for (int i = 0; i < tipoviSobe.size(); i++) {
			s1[i] = tipoviSobe.get(i).getNaziv();
		}
		JComboBox comboBoxTipSobe = new JComboBox(s1);
		add(comboBoxTipSobe);
		
		JLabel lbError = new JLabel(" ");
		add(lbError, "span 2");
		lbError.setForeground(Color.RED);

		
		JButton btnOk = new JButton("OK");
		btnOk.setSize(getPreferredSize());
		add(btnOk);
		
		JButton btnCancel = new JButton("Cancel");
		add(btnCancel);
		
		if (sobaZaEdit != null) {
			tfId.setText(String.valueOf(sobaZaEdit.getId()));
			comboBoxTipSobe.setSelectedItem(sobaZaEdit.getTipSobe().getNaziv());
		}
		
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Boolean isValid = true;
				int id = Integer.parseInt(tfId.getText().trim());
				String tipSobestr = comboBoxTipSobe.getSelectedItem().toString();
				TipSobe tipSobe = factoryMng.getTipSobeMng().NameToObject(tipSobestr);
				for(Soba s : sobe) {
					if (s.getId() == id) {
						isValid = false;
						break;
					}
				}
				if (isValid == false) {
					lbError.setText("ID koji ste uneli je vec u upotrebi.");
				}
				else {
					if(sobaZaEdit == null) {
						factoryMng.getSobaMng().getSobe().add(new Soba(id, StatusSobe.SLOBODNA, tipSobe, ""));
					}
					else {
						factoryMng.getSobaMng().edit(sobaZaEdit.getId(), id, StatusSobe.SLOBODNA, tipSobe);

					}
					((TabelaSoba) parent) .refreshData();
					dispose();
				}
				
			}
		});
		pack();
	}
}