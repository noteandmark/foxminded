package ua.com.foxminded.spring.integerdivision;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class FormatterTest {

	private Formatter formatter = new Formatter();
	private DivisionResult dataresult;

	@Test
	void classicFormat_whenDivide_thenShouldMakeString() {

		dataresult = new DivisionResult();
		Division result = mock(Division.class);

		int dividend = 78945;
		int divisor = 4;
		int maxLength = 5;
		List<Integer> resultDigit = new ArrayList<>();
		List<Integer> resultQuotientMulDivisor = new ArrayList<>();
		List<Integer> resultDigitOfQuotient = new ArrayList<>();
		resultDigit.addAll(Arrays.asList(1, 9, 7, 3, 6));
		resultQuotientMulDivisor.addAll(Arrays.asList(4, 36, 28, 12, 24));
		resultDigitOfQuotient.addAll(Arrays.asList(38, 29, 14, 25, 1));

		setDTO(resultDigit, resultQuotientMulDivisor, resultDigitOfQuotient, dividend, divisor, maxLength);

		StringBuilder stringResult = new StringBuilder();
		stringResult.append("_78945|4").append("\n").append(" 4    |-----").append("\n").append(" -    |19736")
				.append("\n").append("_38").append("\n").append(" 36").append("\n").append(" --").append("\n")
				.append(" _29").append("\n").append("  28").append("\n").append("  --").append("\n").append("  _14")
				.append("\n").append("   12").append("\n").append("   --").append("\n").append("   _25").append("\n")
				.append("    24").append("\n").append("    --").append("\n").append("     1");

		String expected = new String(stringResult.toString());
		when(result.getResultOfDivision(78945, 4)).thenReturn(dataresult);
		assertEquals(expected, formatter.classicFormat(dataresult));
	}

	@Test
	void classicFormat_whenAnotherDivide_thenShouldMakeString() {

		dataresult = new DivisionResult();
		Division result = mock(Division.class);

		int dividend = 512;
		int divisor = 8;
		int maxLength = 3;
		List<Integer> resultDigit = new ArrayList<>();
		List<Integer> resultQuotientMulDivisor = new ArrayList<>();
		List<Integer> resultDigitOfQuotient = new ArrayList<>();
		resultDigit.addAll(Arrays.asList(6, 4));
		resultQuotientMulDivisor.addAll(Arrays.asList(48, 32));
		resultDigitOfQuotient.addAll(Arrays.asList(32, 0));

		setDTO(resultDigit, resultQuotientMulDivisor, resultDigitOfQuotient, dividend, divisor, maxLength);

		StringBuilder stringResult = new StringBuilder();
		stringResult.append("_512|8").append("\n").append(" 48 |--").append("\n").append(" -- |64").append("\n")
				.append("_32").append("\n").append(" 32").append("\n").append(" --").append("\n").append("  0");

		String expected = new String(stringResult.toString());
		when(result.getResultOfDivision(512, 8)).thenReturn(dataresult);
		assertEquals(expected, formatter.classicFormat(dataresult));
	}

	private DivisionResult setDTO(List<Integer> resultDigit, List<Integer> resultQuotientMulDivisor,
			List<Integer> resultDigitOfQuotient, int dividend, int divisor, int maxLength) {

		dataresult.setResultDigit(resultDigit);
		dataresult.setResultQuotientMulDivisor(resultQuotientMulDivisor);
		dataresult.setResultDigitOfQuotient(resultDigitOfQuotient);
		dataresult.setDividend(dividend);
		dataresult.setDivisor(divisor);
		dataresult.setMaxLength(maxLength);

		return dataresult;
	}

}
