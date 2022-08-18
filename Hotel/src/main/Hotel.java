package main;

import manage.ManagerFactory;
import utils.AppSettings;

import view.MainFrame;

public class Hotel {
	public static void main(String[] args) {
		AppSettings appSettings = new AppSettings("data/gosti.txt", "data/zaposleni.txt", "dodatneUsluge.txt");
		
		ManagerFactory controlers = new ManagerFactory(appSettings);
		controlers.loadData();
		
		MainFrame mains = new MainFrame(controlers);
		
	}
}