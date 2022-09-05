package testovi;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import entity.TipSobe;
import manage.TipSobeManager;

public class TestTipSobeManager {
	
	private static TipSobeManager tsMng = new TipSobeManager("");

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		tsMng.getTipoviSobe().add(new TipSobe(1, "jednokrevetna", 1100));
		tsMng.getTipoviSobe().add(new TipSobe(4, "cetvorokrevetna", 5200));
		System.out.println("Zapoceto testiranje");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("Zavrseno testiranje");
	}

	@Test
	public void test() {
		TipSobe ts = tsMng.NameToObject("jednokrevetna");
		assertTrue(ts != null);
		assertFalse(ts == null);
		
		ts = tsMng.NameToObject("dvokrevetna sa pomocnim lezajem");
		assertTrue(ts == null);
	}

}