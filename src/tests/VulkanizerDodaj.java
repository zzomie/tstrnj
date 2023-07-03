package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.After;
import org.junit.Assume;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TestRule;
import org.junit.rules.Timeout;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import gume.AutoGuma;
import gume.VulkanizerskaRadnja;

@RunWith(Parameterized.class)
public class VulkanizerDodaj {

	public AutoGuma AG;
	public VulkanizerskaRadnja VR;
	
	@Rule
	public final TestRule timeout = Timeout.seconds(5);
	
	@BeforeClass
	public void ProveraOperativnogSistema() {
		Assume.assumeTrue(System.getProperty("os.name").contains("Windows"));
	}
	
	public VulkanizerDodaj(AutoGuma AG) {
		this.AG = AG;
	}
	
	@Before
	public void init() {
		VR = new VulkanizerskaRadnja();
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
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Test(expected = NullPointerException.class)
	public void dodajGumuTest() {
		AG = null;
		VR.dodajGumu(AG);
	}

	@Test(expected = RuntimeException.class)
	public void dodajGumuTest2() {
		VR.dodajGumu(AG);
		VR.dodajGumu(AG);
	}

	@Test
	public void dodajGumuTest3() {
		assertFalse(VR.gume.contains(AG));
		VR.dodajGumu(AG);
		assertTrue(VR.gume.contains(AG));
	}
	
	@After
	public void destroy() {
		VR = null;
	}

}
