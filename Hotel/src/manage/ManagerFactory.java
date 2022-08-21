package manage;

import utils.AppSettings;

public class ManagerFactory {
	private AppSettings appSettings;
	private GostManager gostMng;
	private DodatnaUslugaManager uslugaMng;
	private ZaposlenManager zaposlenMng;
	private TipSobeManager tipSobeMng;
	private RezervacijaManager rezervacijaMng;
	
	public ManagerFactory(AppSettings appSettings) {
		this.appSettings = appSettings;
		this.gostMng = new GostManager(this.appSettings.getGostFilename());
		this.uslugaMng = new DodatnaUslugaManager(this.appSettings.getDodatneUslugeFilename());
		this.zaposlenMng = new ZaposlenManager(this.appSettings.getZaposlenFilename());
		this.tipSobeMng = new TipSobeManager(this.appSettings.getTipSobeFilename());
		this.rezervacijaMng = new RezervacijaManager(this.appSettings.getRezervacijaFilename(), tipSobeMng, uslugaMng);
	}
	
	public GostManager getGostMng() {
		return gostMng;
	}
	
	public DodatnaUslugaManager getUslugaMng() {
		return uslugaMng;
	}
	
	public ZaposlenManager getZaposlenManager() {
		return zaposlenMng;
	}
	
	public TipSobeManager getTipSobeMng() {
		// TODO Auto-generated method stub
		return tipSobeMng;
	}
	
	public RezervacijaManager getRezervacijaMng() {
		return rezervacijaMng;
	}
	
	public void loadData() {
		this.gostMng.loadData();
		this.uslugaMng.loadData();
		this.zaposlenMng.loadData();
		this.tipSobeMng.loadData();
		this.rezervacijaMng.loadData();
	}
	
	public void saveData() {
		this.gostMng.saveData();
		this.zaposlenMng.saveData();
		this.tipSobeMng.saveData();
		this.rezervacijaMng.saveData();
	}
}