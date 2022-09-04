package main;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import manage.ManagerFactory;
import utils.AppSettings;

import view.MainFrame;

public class Hotel {
	public static void main(String[] args) {
		AppSettings appSettings = new AppSettings("data/gosti.txt", "data/zaposleni.txt", "data/dodatneUsluge.txt", "data/tipSobe.txt", "data/rezervacija.txt", "data/sobe.txt", "data/sredjivanjeSoba.txt", "data/cenovnikTipSobe.txt", "data/cenovnikDodatneUsluge.txt");

		ManagerFactory controlers = new ManagerFactory(appSettings);
		controlers.loadData();

		
		MainFrame mains = new MainFrame(controlers);
		
	}
}