package charts;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import javax.swing.JFrame;

import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.XChartPanel;

import entity.Rezervacija;
import entity.SredjivanjeSoba;
import entity.Rezervacija.Status;
import manage.ManagerFactory;

public class ChartStatusRezervacija extends JFrame{
	
	private ManagerFactory factoryMng;
	
	public ChartStatusRezervacija(ManagerFactory factoryMng) {
		this.factoryMng = factoryMng;
		setTitle("Statusi rezervacije u prethodnih 30 dana");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		PieChart chart = getChart();
		XChartPanel panel = new XChartPanel(chart);
		this.add(panel);
		pack();
		
		
		
	}

  public PieChart getChart() {
	  
	  HashMap<String, Integer> mapa = new HashMap<String, Integer>();
	  
	  PieChart chart = new PieChartBuilder().width(800).height(600).title(getClass().getSimpleName()).build();
	  
	  for(Rezervacija rezervacija : factoryMng.getRezervacijaMng().getRezervacije()) {
		  LocalDate datumCmp1 = LocalDate.now().minusMonths(1);
		  LocalDate datumCmp2 = LocalDate.now();
		  LocalDate ldIn = LocalDate.parse(rezervacija.getCheckInDateStr(), DateTimeFormatter.ofPattern("dd.MM.uuuu."));
		  LocalDate ldOut = LocalDate.parse(rezervacija.getCheckOutDateStr(), DateTimeFormatter.ofPattern("dd.MM.uuuu."));
		  

		  if(ldIn.isAfter(datumCmp1) || ldOut.isAfter(datumCmp1) || ldIn.isBefore(datumCmp2) || ldOut.isBefore(datumCmp2)) {
			  if (mapa.containsKey(rezervacija.getStatus().name())) {
				  int noviBroj = mapa.get(rezervacija.getStatus().name()) + 1;
				  mapa.replace(rezervacija.getStatus().name(), noviBroj);
			  }
			  else {
				  mapa.put(rezervacija.getStatus().name(), 1);
			  }
		  }
		  
	  }
	  for(String key : mapa.keySet()) {
		  chart.addSeries(key, mapa.get(key));
	  }
	  
	  
	  
	  
	  
	  
    return chart;
    
  }
}