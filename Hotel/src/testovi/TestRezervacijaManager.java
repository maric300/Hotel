package testovi;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import entity.DodatnaUsluga;
import entity.Rezervacija;
import entity.Rezervacija.Status;
import entity.TipSobe;
import manage.DodatnaUslugaManager;
import manage.RezervacijaManager;
import manage.TipSobeManager;

public class TestRezervacijaManager {
	
	private static TipSobeManager pomocniMng1 = new TipSobeManager("");
	private static DodatnaUslugaManager pomocniMng2 = new DodatnaUslugaManager("");
	private static RezervacijaManager tsMng = new RezervacijaManager("", pomocniMng1, pomocniMng2);

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		TipSobe pomocniTs = new TipSobe(1, "jednokrevetna", 1000);
		List<DodatnaUsluga> pomocnaLista = new ArrayList<DodatnaUsluga>();
		pomocnaLista.add(new DodatnaUsluga("jednokrevetna", 1000));
		tsMng.getRezervacije().add(new Rezervacija(100, Status.NA_CEKANJU, "mile@gmail.com", pomocniTs, pomocnaLista, "10.10.2022.", "15.10.2022.", 3, 12000));
		System.out.println("Zapoceto testiranje rezervacija");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("Zavrseno testiranje");
	}

	@Test
	public void test() {
		Rezervacija ts = tsMng.IdToObject(100);
		assertTrue(ts != null);
		assertFalse(ts == null);
		
	}
	
	@Test
	public void testListaGostijevihRezervacija() {
		List<Rezervacija> tsList = tsMng.getRezervacijeGosta("mile@gmail.com");
		assertFalse(tsList.isEmpty());
	}

}
