package testovi;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import entity.CenovnikTipSobe;
import manage.CenovnikTipSobeManager;

public class TestCenovnikTipSobeManager {
	private static CenovnikTipSobeManager testObjMng = new CenovnikTipSobeManager("");

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("Cenovnik tip sobe menadzer test start");
		HashMap<LocalDate, Integer> mapa1 = new HashMap<LocalDate, Integer>();
		mapa1.put(LocalDate.parse("01.09.2022.", DateTimeFormatter.ofPattern("dd.MM.uuuu.")), 1200);
		HashMap<LocalDate, Integer> mapa2 = new HashMap<LocalDate, Integer>();
		mapa2.put(LocalDate.parse("21.08.2022.", DateTimeFormatter.ofPattern("dd.MM.uuuu.")), 2500);
		testObjMng.getCenovnici().add(new CenovnikTipSobe("jednokrevetna", mapa1 ));
		testObjMng.getCenovnici().add(new CenovnikTipSobe("dvokrevetna", mapa2 ));
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("Testiranje zavrseno");
	}

	@Test
	public void testPronadjiCenovnik() {
		CenovnikTipSobe testObj = testObjMng.NameToObject("jednokrevetna");
		assertTrue(testObj != null);
		
		testObj = testObjMng.NameToObject("dvokrevetna");
		assertTrue(testObj != null);
		
		testObj = testObjMng.NameToObject("trokrevetna");
		assertTrue(testObj == null);
	}

}
