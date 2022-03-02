package ua.com.foxminded.spring.integerdivision;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import org.junit.jupiter.api.Test;

class DivisionTest {

	private Division result = new Division();

	@Test
	void getResultOfDivision_whenDivisionByZero_thenShoudThrowIllegalArgumentException() {
		Throwable exception = assertThrows(IllegalArgumentException.class, () -> result.getResultOfDivision(5, 0));
		assertEquals("Can't be divided by zero!", exception.getMessage());
	}

	@Test
	void getResultOfDivision_whenDividendLessThanDivisor_thenShoudThrowIllegalArgumentException() {
		Throwable exception = assertThrows(IllegalArgumentException.class, () -> result.getResultOfDivision(123, 1045));
		assertEquals("Dividend must be more than divisor. You have 0 at least", exception.getMessage());
	}

	@Test
	void getResultOfDivision_whenDivide_thenShoudMakeDivision() {
		int dividend = 78945;
		int divisor = 4;
		List<Integer> resultDigit = new ArrayList<>();
		List<Integer> resultQuotientMulDivisor = new ArrayList<>();
		List<Integer> resultDigitOfQuotient = new ArrayList<>();
		int maxLength = 5;

		resultDigit.addAll(Arrays.asList(1, 9, 7, 3, 6));
		resultQuotientMulDivisor.addAll(Arrays.asList(4, 36, 28, 12, 24));
		resultDigitOfQuotient.addAll(Arrays.asList(38, 29, 14, 25, 1));

		DivisionResult expected = new DivisionResult();
		expected.setResultDigit(resultDigit);
		expected.setResultQuotientMulDivisor(resultQuotientMulDivisor);
		expected.setResultDigitOfQuotient(resultDigitOfQuotient);
		expected.setDividend(dividend);
		expected.setDivisor(divisor);
		expected.setMaxLength(maxLength);

		DivisionResult actual = result.getResultOfDivision(dividend, divisor);

		assertEquals(expected.toString(), actual.toString());
	}

}
