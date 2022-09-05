package charts;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import javax.swing.JFrame;

import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.XChartPanel;

import entity.SredjivanjeSoba;
import manage.ManagerFactory;

public class ChartSobarice extends JFrame{
	
	private ManagerFactory factoryMng;
	
	public ChartSobarice(ManagerFactory factoryMng) {
		this.factoryMng = factoryMng;
		setTitle("Sobarica chart");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		PieChart chart = getChart();
		XChartPanel panel = new XChartPanel(chart);
		this.add(panel);
		pack();
		
		
		
	}

  public PieChart getChart() {
	  
	  HashMap<String, Integer> mapa = new HashMap<String, Integer>();
	  
	  PieChart chart = new PieChartBuilder().width(800).height(600).title(getClass().getSimpleName()).build();
	  
	  for(SredjivanjeSoba sredjivanje : factoryMng.getSredjivanjeMng().getSredjivanjeSobaLista()) {
		  LocalDate datumCmp = LocalDate.now().minusMonths(1);
		  LocalDate datumCiscenja = LocalDate.parse(sredjivanje.getDatumCiscenjaStr(), DateTimeFormatter.ofPattern("dd.MM.yyyy."));
		  if (datumCmp.isBefore(datumCiscenja)) {
			  if (mapa.containsKey(sredjivanje.getImeSobarice())) {
//				  mapa.replace(sredjivanje.getImeSobarice(), mapa.get(sredjivanje.getImeSobarice() + sredjivanje.getBrOciscenjihSoba()));
				  int noviBroj = mapa.get(sredjivanje.getImeSobarice()) + sredjivanje.getBrOciscenjihSoba();
				  mapa.replace(sredjivanje.getImeSobarice(), noviBroj);
			  }
			  else {
				  mapa.put(sredjivanje.getImeSobarice(), sredjivanje.getBrOciscenjihSoba());


			  }
		  }
		  

		  
	  }
	  for(String key : mapa.keySet()) {
		  chart.addSeries(key, mapa.get(key));
	  }
	  
	  
	  
	  
	  
	  
    return chart;
    
  }
}