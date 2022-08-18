package manage;

import utils.AppSettings;

public class ManagerFactory {
	private AppSettings appSettings;
	private GostManager gostMng;
	private DodatnaUslugaManager uslugaMng;
	private ZaposlenManager zaposlenMng;
	
	public ManagerFactory(AppSettings appSettings) {
		this.appSettings = appSettings;
		this.gostMng = new GostManager(this.appSettings.getGostFilename());
		this.uslugaMng = new DodatnaUslugaManager(this.appSettings.getDodatneUslugeFilename());
		this.zaposlenMng = new ZaposlenManager(this.appSettings.getZaposlenFilename());
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
	
	public void loadData() {
		this.gostMng.loadData();
		this.uslugaMng.loadData();
		this.zaposlenMng.loadData();
	}
	
	public void saveData() {
		this.gostMng.saveData();
		this.zaposlenMng.saveData();
	}
}