package tests;

import static org.junit.Assert.*;

import org.junit.Assume;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.migrationsupport.rules.adapter.ExpectedExceptionAdapter;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;
import org.junit.rules.TestName;
import org.junit.rules.TestRule;
import org.junit.rules.Timeout;

import gume.AutoGuma;

public class AutoGumaTest {

	public AutoGuma AG;
	
	@Rule
	public final TestRule timeout = Timeout.seconds(5);
	
	@Before
	public void ProveraOperativnogSistema() {
		Assume.assumeTrue(System.getProperty("os.name").contains("Windows"));
	}
	
	@Rule
	public final ErrorCollector ec = new ErrorCollector();
	
	@Before
	public void init() {
		AG = new AutoGuma("Michelin", true, 20, 200, 90);
	}
	
	@Rule
	public final TestName test = new TestName();


	@Test
	public void testGetZimska() {
		boolean ocekivaniRezultat = true;
		try {
			boolean stvarniRezultat = AG.getZimska();
			assertEquals(ocekivaniRezultat, stvarniRezultat);
		} catch (Throwable t) {
			// TODO Auto-generated catch block
			ec.addError(t);
		}
	}

	@Test
	public void testSetZimska() {
		boolean stvarniRezultat = AG.getZimska();
		boolean ocekivaniRezultat = false;
		
		try {
			AG.setZimska(false);
			assertEquals(ocekivaniRezultat, stvarniRezultat);
		} catch (Throwable t) {
			// TODO Auto-generated catch block
			ec.addError(t);
		}
	}

	@Test
	public void testGetMarkaModel() {
		String ocekivaniRezultat = "Michelin";
		try {
			String stvarniRezultat = AG.getMarkaModel();
			assertEquals(ocekivaniRezultat, stvarniRezultat);
		} catch (Throwable t) {
			// TODO Auto-generated catch block
			ec.addError(t);
		}
	}

	@Test
	public void testSetMarkaModel1() {
		String ocekivaniRezultat = "Pikolo";
		AG.setMarkaModel("Pikolo");
		assertEquals(ocekivaniRezultat, AG.getMarkaModel());
	}
	
	@Test(expected = RuntimeException.class)
	public void testSetMarkaModel2() {
		String ocekivaniRezultat = "AB";
		AG.setMarkaModel("AB");
		assertEquals(ocekivaniRezultat, AG.getMarkaModel());
	}
	
	@Test(expected = RuntimeException.class)
	public void testSetMarkaModel3() {
		String ocekivaniRezultat = null;
		AG.setMarkaModel(null);
		assertEquals(ocekivaniRezultat, AG.getMarkaModel());
	}

	@Test
	public void testGetPrecnik() {
		int ocekivaniRezultat = 20;
		try {
			assertEquals(ocekivaniRezultat, AG.getPrecnik());
		} catch (Throwable t) {
			// TODO Auto-generated catch block
			ec.addError(t);
		}
	}

	@Test
	public void testSetPrecnik1() {
		int ocekivaniRezultat = 15;
		AG.setPrecnik(15);
		assertEquals(ocekivaniRezultat, AG.getPrecnik());
	}
	
	@Test(expected = RuntimeException.class)
	public void testSetPrecnik2() {
		int ocekivaniRezultat = 20;
		AG.setPrecnik(10);
//		assertEquals(ocekivaniRezultat, AG.getPrecnik());
	}
	
	@Test(expected = RuntimeException.class)
	public void testSetPrecnik3() {
		int ocekivaniRezultat = 20;
		AG.setPrecnik(40);
//		assertEquals(ocekivaniRezultat, AG.getPrecnik());
	}
	
	
	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void testGetSirina() {
		int ocekivaniRezultat = 50;
		assertEquals(ocekivaniRezultat, AG.getSirina());
	}

	@Test
	public void testSetSirina() {
		int ocekivaniRezultat = 205;
		AG.setSirina(205);
		assertEquals(ocekivaniRezultat, AG.getSirina());
	}
	
	@Test
	public void testSetSirina2() {
		exception.expect(RuntimeException.class);
		exception.expectMessage("Sirina van opsega!");
		int ocekivaniRezultat = 200;
		AG.setSirina(130);
		assertEquals(ocekivaniRezultat, AG.getSirina());
	}
	
	@Test
	public void testSetSirina3() {
		exception.expect(RuntimeException.class);
		exception.expectMessage("Sirina van opsega!");
		int ocekivaniRezultat = 200;
		AG.setSirina(360);
		assertEquals(ocekivaniRezultat, AG.getSirina());
	}

	@Test
	public void testGetVisina() {
		int ocekivaniRezultat = 90;
		assertEquals(ocekivaniRezultat, AG.getSirina());
	}

	@Test
	public void testSetVisina() {
		try {
			AG.setVisina(20);
		} catch (Throwable t) {
			// TODO Auto-generated catch block
			Assume.assumeNoException(t);
		}
	}
	
	@Test
	public void testSetVisina2() {
		try {
			AG.setVisina(100);
		} catch (Throwable t) {
			// TODO Auto-generated catch block
			Assume.assumeNoException(t);
		}
	}
	
	@Test
	public void testSetVisina3() {
		int ocekivaniRezultat = 80;
		AG.setVisina(80);
		assertEquals(ocekivaniRezultat, AG.getVisina());
	}

	@Test
	public void testIzracunajCenu() {
		double ocekivaniRezultat = (AG.getPrecnik()*3+AG.getSirina()+AG.getVisina())*28.53;
		double stvarniRezultat = AG.izracunajCenu();
		assertEquals(ocekivaniRezultat, stvarniRezultat);
	}

	@Test
	public void testPovoljnaGuma() {
		AG.setPrecnik(20);
		AG.setVisina(26);
		AG.setSirina(136);
		assertTrue(AG.povoljnaGuma());
		
	}

	@Test
	public void testToString() {
		String ocekivaniRezutat = "AutoGuma [markaModel=" + AG.getMarkaModel() + ", precnik=" + AG.getPrecnik() + ", sirina=" + AG.getSirina() + ", visina="+ AG.getVisina() + "]";
		String stvarnirezultat = AG.toString();
		assertEquals(ocekivaniRezutat, stvarnirezultat);
	}

	@Test
	public void equalsTest1() {
		AutoGuma AG2 = new AutoGuma("Michelin", true, 18, 180, 40);
		assertTrue(AG.equals(AG2));
	}
	@Test
	public void equalsTest2() {
		VulkanizerskaRadnja VK = new VulkanizerskaRadnja();
		assertEquals(false, AG.equals(VK));
	}
	@Test
	public void equalsTest3() {
		AutoGuma AG2 = AG;
		assertTrue(AG.equals(AG2));
	}
	@Test
	public void equalsTest4() {
		AutoGuma AG2 = new AutoGuma(null, true, 18, 180, 40);
		assertFalse(AG.equals(AG2));
	}
	@Test
	public void equalsTest5() {
		AutoGuma AG2 = new AutoGuma(null, true, 18, 180, 40);
		AutoGuma AG3 = new AutoGuma(null, true, 18, 180, 40);
		assertTrue(AG2.equals(AG3));
	}
	@Test
	public void equalsTest6() {
		AutoGuma AG2 = new AutoGuma(null, true, 18, 180, 40);
		AutoGuma AG3 = new AutoGuma("Michelin", true, 18, 180, 40);
		assertFalse(AG2.equals(AG3));
	}
	@Test
	public void equalsTest7() {
		AutoGuma AG2 = new AutoGuma("Michelin", true, 19, 180, 40);
		assertFalse(AG.equals(AG2));
	}
	@Test
	public void equalsTest8() {
		AutoGuma AG2 = new AutoGuma("Michelin", true, 18, 185, 40);
		assertFalse(AG.equals(AG2));
	}
	@Test
	public void equalsTest9() {
		AutoGuma AG2 = new AutoGuma("Michelin", true, 18, 180, 35);
		assertFalse(AG.equals(AG2));
	}

}
