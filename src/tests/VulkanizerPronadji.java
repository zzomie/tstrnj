package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

import org.junit.After;
import org.junit.Assume;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.TestRule;
import org.junit.rules.Timeout;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import gume.AutoGuma;
import gume.VulkanizerskaRadnja;

@RunWith(Parameterized.class)
public class VulkanizerPronadji {

	public AutoGuma AG;
	private VulkanizerskaRadnja VR;
	
	@BeforeClass
	public void ProveraOperativnogSistema() {
		Assume.assumeTrue(System.getProperty("os.name").contains("Windows"));
	}
	
	@Rule
	public final TestRule timeout = Timeout.seconds(5);
	
	public VulkanizerPronadji(AutoGuma AG) {
		this.AG = AG;
	}

	
	@Parameters
	public static Collection<Object[]> gume() {
		return Arrays.asList(new Object[][] {
			{new AutoGuma("Michelin", true, 18, 180, 40)},
			{new AutoGuma("Michelin", true, 18, 185, 45)},
			{new AutoGuma("Michelin", true, 18, 190, 40)},
			{new AutoGuma("Michelin", false, 19,170, 30)}
		});
	}
	
	@Before
	public void init() {
		VR = new VulkanizerskaRadnja();
	}
	
	@Test
	public void pronadjiGumuTest() {
		String marka = null;
		assertNull(VR.pronadjiGumu(marka));
	}
	
	// slucaj kad postoji
	@Test
	public void pronadjiGumuTest2() {
		assertFalse(VR.gume.contains(AG));
		VR.dodajGumu(AG);
		LinkedList<AutoGuma> gumice = new LinkedList<AutoGuma>();
		gumice.add(AG);
		assertEquals(gumice, VR.pronadjiGumu("Michelin"));
	}
	
	// slucaj kad ne postoji
	@Test
	public void pronadjiGumuTest3() {
		assertFalse(VR.gume.contains(AG));
		VR.dodajGumu(AG);
		LinkedList<AutoGuma> gumice = new LinkedList<AutoGuma>();
		gumice.add(AG);
		assertEquals(gumice, VR.pronadjiGumu("KADA"));
	}
	
	@After
	public void destroy() {
		VR = null;
	}

}
