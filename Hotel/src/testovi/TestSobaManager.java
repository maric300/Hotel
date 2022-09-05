package testovi;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import entity.Soba;
import entity.TipSobe;
import entity.Soba.StatusSobe;
import manage.SobaManager;
import manage.TipSobeManager;

public class TestSobaManager {
	
	private static SobaManager tsMng = new SobaManager("", null);

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		TipSobe pomocniTs = new TipSobe(2, "dvokrevetna", 2000);
		tsMng.getSobe().add(new Soba(2, StatusSobe.SLOBODNA, pomocniTs, ""));
		tsMng.getSobe().add(new Soba(2, StatusSobe.SPREMANJE, pomocniTs, "ivan@gmail.com"));
		
		System.out.println("zapoceto testiranje");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("Zavrseno testiranje");
	}

	@Test
	public void test() {
		Soba ts = tsMng.IdToObject(2);
		assertTrue(ts != null);
	}
	
	@Test
	public void testVratiSpremacicu() {
		List<Soba> sobe = tsMng.VratiSobeZaSpremanje("ivan@gmail.com");
		assertTrue(sobe != null);
		assertTrue(sobe.get(0).getId() == 2);
	}

}
