package testovi;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import entity.DodatnaUsluga;
import manage.DodatnaUslugaManager;

public class TestDodatnaUslugaManager {
	
	private static DodatnaUslugaManager tsMng = new DodatnaUslugaManager("");

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		tsMng.getDodatneUsluge().add(new DodatnaUsluga("rucak", 300));
		tsMng.getDodatneUsluge().add(new DodatnaUsluga("vecera", 200));
		System.out.println("Zapoceto testiranje");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("Zavrseno testiranje");
	}

	@Test
	public void test() {
		DodatnaUsluga ts = tsMng.NameToObject("rucak");
		assertTrue(ts != null);
		assertFalse(ts == null);
		
		ts = tsMng.NameToObject("dorucak");
		assertTrue(ts == null);
	}

}
