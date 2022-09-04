package manage;

import utils.AppSettings;

public class ManagerFactory {
	private AppSettings appSettings;
	private GostManager gostMng;
	private DodatnaUslugaManager uslugaMng;
	private ZaposlenManager zaposlenMng;
	private TipSobeManager tipSobeMng;
	private RezervacijaManager rezervacijaMng;
	private SobaManager sobaMng;
	private SredjivanjeSobaManager sredjivanjeMng;
	private CenovnikTipSobeManager cenovnikTipSobeMng;
	
	public ManagerFactory(AppSettings appSettings) {
		this.appSettings = appSettings;
		this.gostMng = new GostManager(this.appSettings.getGostFilename());
		this.uslugaMng = new DodatnaUslugaManager(this.appSettings.getDodatneUslugeFilename());
		this.zaposlenMng = new ZaposlenManager(this.appSettings.getZaposlenFilename());
		this.tipSobeMng = new TipSobeManager(this.appSettings.getTipSobeFilename());
		this.rezervacijaMng = new RezervacijaManager(this.appSettings.getRezervacijaFilename(), tipSobeMng, uslugaMng);
		this.sobaMng = new SobaManager(this.appSettings.getSobaFilename(), tipSobeMng);
		this.sredjivanjeMng = new SredjivanjeSobaManager(this.appSettings.getSredjivanjeSobaFilename());
		this.cenovnikTipSobeMng = new CenovnikTipSobeManager(this.appSettings.getCenovnikTipSobeFilename());
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
	
	public SobaManager getSobaMng() {
		return sobaMng;
	}
	public SredjivanjeSobaManager getSredjivanjeMng() {
		return sredjivanjeMng;
	}
	
	public CenovnikTipSobeManager getCenovnikTipSobeMng() {
		return cenovnikTipSobeMng;
	}
	
	public void loadData() {
		this.gostMng.loadData();
		this.uslugaMng.loadData();
		this.zaposlenMng.loadData();
		this.tipSobeMng.loadData();
		this.rezervacijaMng.loadData();
		this.sobaMng.loadData();
		this.sredjivanjeMng.loadData();
		this.cenovnikTipSobeMng.loadData();
	}
	
	public void saveData() {
		this.gostMng.saveData();
		this.zaposlenMng.saveData();
		this.tipSobeMng.saveData();
		this.rezervacijaMng.saveData();
		this.sobaMng.saveData();
		this.sredjivanjeMng.saveData();
		this.cenovnikTipSobeMng.saveData();
	}
}