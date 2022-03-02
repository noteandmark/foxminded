package ua.com.foxminded.spring.integerdivision;

import java.util.ArrayList;
import java.util.List;

public class Division {

	public DivisionResult getResultOfDivision(int dividend, int divisor) {

		String stringFormatOfDividend = Integer.toString(dividend);
		int maxLength = stringFormatOfDividend.length();
		int numberDigits = maxLength;
		int quotient;
		int subQuotient;
		int quotientMulDivisor;
		int digitOfQuotient;

		List<Integer> resultDigit = new ArrayList<>();
		List<Integer> resultQuotientMulDivisor = new ArrayList<>();
		List<Integer> resultDigitOfQuotient = new ArrayList<>();

		char[] dividendNumbers = stringFormatOfDividend.toCharArray();

		int i = 0;
		int checkZero = 0;
		digitOfQuotient = Character.getNumericValue(dividendNumbers[0]);

		checkExceptions(dividend, divisor);

		while (numberDigits > 0) {
			if (!(digitOfQuotient < divisor)) {
				quotient = digitOfQuotient / divisor;
				quotientMulDivisor = quotient * divisor;
				subQuotient = digitOfQuotient % divisor;
				digitOfQuotient = digitOfQuotient - quotientMulDivisor;

				if ((i + 1 <= maxLength - 1) & (i + 1 < dividendNumbers.length)) {
					digitOfQuotient = subQuotient * 10 + Character.getNumericValue(dividendNumbers[i + 1]);
				}

				resultDigit.add(quotient);
				resultQuotientMulDivisor.add(quotientMulDivisor);
				resultDigitOfQuotient.add(digitOfQuotient);
			} else {
				if ((checkZero == 1) & (resultDigit.size() != 0)) {
					resultDigit.add(0);
					checkZero = 0;
				}
				if (i + 1 < dividendNumbers.length) {
					digitOfQuotient = digitOfQuotient * 10 + Character.getNumericValue(dividendNumbers[i + 1]);
				}
				checkZero = 1;
			}
			numberDigits--;
			i++;
		}

		return new DivisionResult(resultDigit, resultQuotientMulDivisor, resultDigitOfQuotient, dividend, divisor,
				maxLength);
	}

	private void checkExceptions(int dividend, int divisor) {

		if (divisor == 0) {
			throw new IllegalArgumentException("Can't be divided by zero!");
		}

		dividend = Math.abs(dividend);
		divisor = Math.abs(divisor);

		if (dividend < divisor) {
			throw new IllegalArgumentException("Dividend must be more than divisor. You have 0 at least");
		}
	}

}