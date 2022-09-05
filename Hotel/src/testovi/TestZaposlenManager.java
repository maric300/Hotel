package testovi;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import entity.Zaposlen;
import entity.Zaposlen.Posao;
import manage.ZaposlenManager;

public class TestZaposlenManager {
	
	private static ZaposlenManager tsMng = new ZaposlenManager("");

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		tsMng.getZaposleni().add(new Zaposlen(Posao.RECEPCIONER, "Igor", "Miric", "Musko", "01.03.2000.", "Bulevar Oslobodjenja 52", "06112345", "igor@gmail.com", "2222222", 3, 2));
		System.out.println("Zapoceto testiranje");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("Zavrseno testiranje");
	}

	@Test
	public void test() {
		
		Zaposlen ts = tsMng.NameToObject("igor@gmail.com");
		assertTrue(ts != null);
		
	}
	
	@Test
	public void testObrisiZaposlenog() {
		tsMng.remove("igor@gmail.com");
		Zaposlen ts = tsMng.NameToObject("igor@gmail.com");
		assertFalse(ts != null);
		
	}

}
