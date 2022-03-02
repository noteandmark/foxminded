package ua.com.foxminded.spring.integerdivision;

import java.util.List;
import java.util.StringJoiner;

public class DivisionResult {

	private List<Integer> resultDigit;
	private List<Integer> resultQuotientMulDivisor;
	private List<Integer> resultDigitOfQuotient;
	private int dividend;
	private int divisor;
	private int maxLength;

	public DivisionResult(List<Integer> resultDigit, List<Integer> resultQuotientMulDivisor,
			List<Integer> resultDigitOfQuotient, int dividend, int divisor, int maxLength) {
		super();
		this.dividend = dividend;
		this.divisor = divisor;
		this.maxLength = maxLength;
		this.resultDigit = resultDigit;
		this.resultQuotientMulDivisor = resultQuotientMulDivisor;
		this.resultDigitOfQuotient = resultDigitOfQuotient;
	}

	public DivisionResult() {
	}

	public int getMaxLength() {
		return maxLength;
	}

	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}

	public int getDividend() {
		return dividend;
	}

	public void setDividend(int dividend) {
		this.dividend = dividend;
	}

	public int getDivisor() {
		return divisor;
	}

	public void setDivisor(int divisor) {
		this.divisor = divisor;
	}

	public List<Integer> getResultDigit() {
		return resultDigit;
	}

	public void setResultDigit(List<Integer> resultDigit) {
		this.resultDigit = resultDigit;
	}

	public List<Integer> getResultQuotientMulDivisor() {
		return resultQuotientMulDivisor;
	}

	public void setResultQuotientMulDivisor(List<Integer> resultQuotientMulDivisor) {
		this.resultQuotientMulDivisor = resultQuotientMulDivisor;
	}

	public List<Integer> getResultDigitOfQuotient() {
		return resultDigitOfQuotient;
	}

	public void setResultDigitOfQuotient(List<Integer> resultDigitOfQuotient) {
		this.resultDigitOfQuotient = resultDigitOfQuotient;
	}

	@Override
	public String toString() {
		return "DataResult [resultDigit=" + resultDigit + ", resultQuotientMulDivisor=" + resultQuotientMulDivisor
				+ ", resultDigitOfQuotient=" + resultDigitOfQuotient + ", dividend=" + dividend + ", divisor=" + divisor
				+ ", maxLength=" + maxLength + "]";
	}

}
