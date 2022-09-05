package charts;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.swing.JDialog;
import javax.swing.JFrame;

import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler.LegendPosition;
import org.knowm.xchart.style.markers.SeriesMarkers;

import manage.ManagerFactory;
import entity.CenovnikTipSobe;
import entity.Rezervacija;
import entity.TipSobe;

public class ChartTipoviSoba extends JDialog {
	private ManagerFactory factoryMng;
	
	public ChartTipoviSoba(ManagerFactory factoryMng) {
		this.factoryMng = factoryMng;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Chart");
		pack();
	    
	    XYChart chart = getChart();
	    XChartPanel<XYChart> panel = new XChartPanel<XYChart>(getChart());
	    this.add(panel);
	    
	    // Show it
	    setVisible(true);
	    pack();
	    setLocationRelativeTo(null);
	}

  private XYChart getChart() {
	  List<java.util.Date> xData = new ArrayList<java.util.Date>();
	  List<Integer> yData = new ArrayList<Integer>();
	  List<Integer> yDataUkupno = new ArrayList<Integer>();
	  
	  for(int i = 0; i < 12; i++) {
			yDataUkupno.add(0);
		}
	  
	  
    XYChart chart = new XYChartBuilder().width(800).height(600).title("LineChart03").xAxisTitle("X").yAxisTitle("Y").build();
    
    HashMap<String, int[]> izvestaj = new HashMap<String, int[]>();
    for (TipSobe tipSobe : factoryMng.getTipSobeMng().getTipoviSobe()) {
		LocalDate datum1 = LocalDate.now().minusMonths(12);
		LocalDate datum2 = LocalDate.now().minusMonths(11);
		xData.clear();
		yData.clear();
		
	    chart.getStyler().setDatePattern("dd-MMM");
	    chart.getStyler().setDecimalPattern("#0");
	    chart.getStyler().setLocale(Locale.UK); 
		
		for(int i = 0; i < 12; i++) {
			int ukupnaCena = 0;
			for (Rezervacija rezervacija : factoryMng.getRezervacijaMng().getRezervacije()) {
				
				if (rezervacija.getTipSobe().equals(tipSobe)) {
					LocalDate ldRezIn = LocalDate.parse(rezervacija.getCheckInDateStr(), DateTimeFormatter.ofPattern("dd.MM.uuuu."));
					LocalDate ldRezOut = LocalDate.parse(rezervacija.getCheckOutDateStr(), DateTimeFormatter.ofPattern("dd.MM.uuuu."));
					ukupnaCena += factoryMng.getRezervacijaMng().CenaIzOpsegaRezervacija(datum1, datum2, ldRezIn, ldRezOut, rezervacija, factoryMng);
					System.out.println(tipSobe.getNaziv() + "---" + ukupnaCena + "---" + ldRezIn.toString() + "--- datum iz opsega ->" + datum1.toString()); 
					
				}
				
				
			}
			java.util.Date date = Date.from(datum2.atStartOfDay(ZoneId.systemDefault()).toInstant());
			xData.add(date);
			yData.add(ukupnaCena);
			yDataUkupno.set(i, yDataUkupno.get(i) + ukupnaCena);
			datum1 = datum1.plusMonths(1);
			datum2 = datum2.plusMonths(1);
			
		}
		System.out.println(tipSobe.getNaziv() ); 
//		chart.addSeries(tipSobe.getNaziv(), xData, yData);
		XYSeries series = chart.addSeries(tipSobe.getNaziv(), xData, yData);
    }
    System.out.println(xData.size());
    System.out.println(yDataUkupno.size() ); 
    XYSeries series = chart.addSeries("Ukupno", xData, yDataUkupno);

    
    return chart;
  }
}