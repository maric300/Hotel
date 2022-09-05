package testovi;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import entity.Gost;
import manage.GostManager;

public class TestGostManager {
	
	private static GostManager tsMng = new GostManager("");

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		tsMng.getGosti().add(new Gost("Vukasin", "Ostojic", "Musko", "02.12.2001.", "Bobana Rajovica 5", "064122482", "vule@gmail.com", "123123"));
		tsMng.getGosti().add(new Gost("Milorad", "Kuburic", "Musko", "06.2.2000.", "Milutina Radojlovica bb", "069282163", "mile@gmail.com", "050505"));
		tsMng.getGosti().add(new Gost("Mileva", "Ursulic", "Zensko", "08,07.1998.", "Cedomira Gvozdenovica 2", "061232178", "ursa@gmail.com", "101010"));
		System.out.println("Testiranje gostiju zapoceto");
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("Testiranje gostiju zavrseno");
	}

	@Test
	public void PronadjiGostaTest() {
		Gost ts = tsMng.NameToObject("vule@gmail.com");
		assertTrue(ts != null);
		
		ts = tsMng.NameToObject("mile@gmail.com");
		assertFalse(ts == null);
		
		ts = tsMng.NameToObject("mile@gmailcom");
		assertTrue(ts == null);
	}
	
	@Test
	public void ObrisiGostaTest() {
		Gost ts = tsMng.NameToObject("ursa@gmail.com");
		assertTrue(ts != null);
		
		tsMng.remove("ursa@gmail.com");
		assertTrue(tsMng.NameToObject("ursa@gmail.com") == null);
	}

}
