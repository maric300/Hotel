package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import entity.Gost;
import entity.Zaposlen;
import entity.Zaposlen.Posao;
import manage.GostManager;
import manage.ManagerFactory;
import manage.ZaposlenManager;
import net.miginfocom.swing.MigLayout;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 8456560429229699542L;

	private ManagerFactory managers;

	public MainFrame(ManagerFactory managers) {
		this.managers = managers;

		loginDialog();

	}

	private void loginDialog() {
		JDialog d = new JDialog();
		d.setTitle("Prijava");
		d.setLocationRelativeTo(null);
		d.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		d.setResizable(false);
		initLoginGUI(d);
		d.pack();
		d.setVisible(true);
	}

	private void initLoginGUI(JDialog dialog) {
		/*
		 * Malo detaljnije podesavanje MigLayout-a: Drugi parametar (string) sadrzi 2
		 * prazne uglaste zagrade jer imamo 2 kolone (ovde nista nismo podesili) Treci
		 * parametar ima onoliko uglastih zagrada koliko imamo redova (u nasem slucaju
		 * 4) Unutar zagrada mozemo detaljnije podesavati kolone i redove, dok vrednosti
		 * izmedju njih predstavljaju razmake u pikselima. Ovde smo postavili razmak od
		 * 20px izmedju 1. i 2. i izmedju 3. i 4. reda.
		 */
		MigLayout layout = new MigLayout("wrap", "[][]", "[]20[][]10[]10[]");
		dialog.setLayout(layout);

		JTextField tfUsername = new JTextField(20);
		JPasswordField pfPassword = new JPasswordField(20);
		JLabel lbError = new JLabel(" ");
		lbError.setForeground(Color.red);
		JButton btnOk = new JButton("OK");
		JButton btnCancel = new JButton("Cancel");

		// Ako postavimo dugme 'btnOK' kao defaul button, onda ce svaki pritisak tastera
		// Enter na tastaturi
		// Izazvati klik na njega
		dialog.getRootPane().setDefaultButton(btnOk);

		dialog.add(new JLabel("Dobrodošli. Molimo da se prijavite."), "span 2");
		dialog.add(new JLabel("Korisničko ime"));
		dialog.add(tfUsername);
		dialog.add(new JLabel("Šifra"));
		dialog.add(pfPassword);
		dialog.add(lbError, "span 2");
		dialog.add(btnOk, "split 2");
		dialog.add(btnCancel);

		// Klik na Login dugme
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String username = tfUsername.getText().trim();
				String password = new String(pfPassword.getPassword()).trim();
				System.out.println(username+" "+password);
				if (username.equals("")) {
					lbError.setText("Korisnicko ime ne sme biti prazno!");
				}
				else if (password.equals("")) {
					lbError.setText("Lozinka ne sme biti prazna!");
				}
				else {
					if (username.equals("admin") && password.equals("admin")) {
						AdminFrame af = new AdminFrame(managers);
						af.setVisible(true);
						dialog.setVisible(false);
						dialog.dispose();
					}
					GostManager gostMng = managers.getGostMng();
					ZaposlenManager zaposlenMng = managers.getZaposlenManager();
					List<Gost> gosti = gostMng.getGosti();
					List<Zaposlen> zaposleni = zaposlenMng.getZaposleni();
					
					for (int i = 0;i < gosti.size(); i++) {
						Gost gost = gosti.get(i);
						
						if (gost.getEmail().equals(username) && gost.getBrPasosa().equals(password)) {
							dialog.setVisible(false);
							dialog.dispose();
							GostFrame gf = new GostFrame(managers, gost);
							gf.setVisible(true);
						}
					}
					
					for (int i = 0;i < zaposleni.size(); i++) {
						Zaposlen zaposlen = zaposleni.get(i);
						
						if (zaposlen.getEmail().equals(username) && zaposlen.getBrPasosa().equals(password)) {
							dispose();
							if (zaposlen.getPosao().equals(Posao.valueOf("RECEPCIONER"))) {
								dialog.setVisible(false);
								dialog.dispose();
								RecepcionerFrame rf = new RecepcionerFrame(managers, zaposlen);
								rf.setVisible(true);
							}
							else if (zaposlen.getPosao().equals(Posao.valueOf("SOBARICA"))) {
								dialog.setVisible(false);
								dialog.dispose();
								SobaricaFrame sf = new SobaricaFrame(managers, zaposlen);
								sf.setVisible(true);
							}
						}
					}
					
					lbError.setText("Netačno korisničko ime ili lozinka");
					
					
//					File file = new File("data/osobe.txt");
//					
//					Scanner scanner = null;
//					try {
//						scanner = new Scanner(file);
//					} catch (FileNotFoundException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
//					while(scanner.hasNextLine()) {
//						String[] nodes = scanner.nextLine().split(";");
//						if (username.equals(nodes[7]) && password.equals(nodes[8])) {
//							dispose();
//							if (nodes[0].equals("gost")) {
//								GostFrame gf = new GostFrame();
//								gf.show();
//							}
//							
//							else if (nodes[0].equals("admin")) {
//								AdminFrame af = new AdminFrame();
//								af.show();
//							}
//							
//							else if (nodes[0].equals("recepcioner")) {
//								RecepcionerFrame rf = new RecepcionerFrame();
//								rf.show();
//							}
//						}
//						lbError.setText("Netacna lozinka ili korisnicko ime.");
//					}
					
					
					
				}
			}
		});
		// Cancel dugme samo sakriva trenutni prozor
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dialog.setVisible(false);
				dialog.dispose();
			}
		});

	}
}

	