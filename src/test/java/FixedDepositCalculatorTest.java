import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

public class FixedDepositCalculatorTest {

	private void testWithoutCompound(String expected, String pStr, String rStr, String tStr) throws Exception {
		BigDecimal p = new BigDecimal(pStr);
		BigDecimal r = new BigDecimal(rStr);
		BigDecimal t = new BigDecimal(tStr);
		BigDecimal a = FixedDepositCalculator.withoutCompound(p, r, t);
		assertEquals(expected, a.toString());
	}

	@Test
	public void testWithoutCompound() throws Exception {
		testWithoutCompound("5038.75", "5000", "0.031", "0.25");
		testWithoutCompound("5155.00", "5000", "0.031", "1");
		testWithoutCompound("12746.21", "12345", "0.065", "0.5");
	}

	private void testWithPeriodicCompound(String expected, String pStr, String rStr, String nStr, String tStr) throws Exception {
		BigDecimal p = new BigDecimal(pStr);
		BigDecimal r = new BigDecimal(rStr);
		BigDecimal n = new BigDecimal(nStr);
		BigDecimal t = new BigDecimal(tStr);
		BigDecimal a = FixedDepositCalculator.withPeriodicCompound(p, r, n, t);
		assertEquals(expected, a.toString());
	}

	@Test
	public void testWithPeriodicCompound() throws Exception {
		testWithPeriodicCompound("5038.75", "5000", "0.031", "4", "0.25");
		testWithPeriodicCompound("16567.29", "15000", "0.05", "4", "2");
		testWithPeriodicCompound("5157.02", "5000", "0.031", "6", "1");
		testWithPeriodicCompound("3969.41", "3333", "0.035", "12", "5");
	}
}
