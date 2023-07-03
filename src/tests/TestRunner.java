package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import java.util.logging.Logger;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

class TestRunner {

	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(AutoGumaTests.class, VPParametrizedTests.class);
		
		Logger l = Logger.getLogger(TestRunner.class.toString());
		
		// opis svih gresaka
		for (Failure f: result.getFailures()) {
			l.warning(f.toString());
		}
		
		// vreme i broj izvrsenih testova
		l.info("Vreme izvrsavanja: " + result.getRunTime());
		l.info("Broj izvrsenih testova: " + result.getRunCount());
		
		// koliko testova je uspesno, neuspesno, preskoceno i dinamicki preskoceno
		l.info("Uspesno izvrsenih testova: " + (result.getRunCount()-result.getIgnoreCount()-result.getFailureCount()-result.getAssumptionFailureCount()));
		l.info("Neuspesno izvrsenih testova: " + result.getFailureCount());
		l.info("Broj preskocenih testova: " + result.getIgnoreCount());
		l.info("Dinamicki preskoceno testova: " + result.getAssumptionFailureCount());
		
		if (result.wasSuccessful())
			l.info("Svi testovi su bili uspesni!");
		else
			l.warning("Postoje greske u testovima!");
		
	}

}
