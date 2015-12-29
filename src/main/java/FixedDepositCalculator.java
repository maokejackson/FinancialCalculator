import java.math.BigDecimal;
import java.math.RoundingMode;

import org.apache.log4j.Logger;

public class FixedDepositCalculator {
	private static final Logger logger = Logger.getLogger(FixedDepositCalculator.class);

	/**
	 * Calculate fixed deposit with no compounding.
	 *
	 * @param p principle amount
	 * @param r interest rate per annum
	 * @param t number of years
	 * @return fixed deposit with no compounding.
	 */
	public static BigDecimal withoutCompound(BigDecimal p, BigDecimal r, BigDecimal t) {
		BigDecimal a = r.multiply(t).add(new BigDecimal(1));
		BigDecimal b = p.multiply(a);
		logger.debug("1 + r * t = " + a);
		logger.debug("p * (1 + r * t) = " + b);
		return b.setScale(2, RoundingMode.HALF_UP);
	}

	/**
	 * Calculate fixed deposit with periodic compound.
	 *
	 * @param p principle amount
	 * @param r interest rate per annum
	 * @param n number of times the interest is compounded per year
	 * @param t number of years
	 * @return fixed deposit with periodic compound.
	 */
	public static BigDecimal withPeriodicCompound(BigDecimal p, BigDecimal r, BigDecimal n, BigDecimal t) {
		final int scale = 18;
		BigDecimal a = r.divide(n, scale, RoundingMode.HALF_EVEN).add(new BigDecimal(1));
		BigDecimal b = n.multiply(t);
		BigDecimal c = new BigDecimal(Math.pow(a.doubleValue(), b.doubleValue())).setScale(scale, RoundingMode.HALF_UP);
		BigDecimal d = p.multiply(c);
		logger.debug("1 + r / n = " + a);
		logger.debug("n * t = " + b);
		logger.debug("(1 + r / n) ^ (n * t) = " + c);
		logger.debug("p * (1 + r / n) ^ (n * t) = " + d);
		return d.setScale(2, RoundingMode.HALF_UP);
	}
}
