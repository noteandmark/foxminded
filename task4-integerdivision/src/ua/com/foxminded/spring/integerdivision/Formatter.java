package ua.com.foxminded.spring.integerdivision;

import java.util.List;

public class Formatter {

	private StringBuilder stringResult;
	private int dividend;
	private int divisor;
	private int maxLength;
	private String spaces1, spaces2, spaces3;
	private String outLine1, outLine2, outLine3;
	private List<Integer> resultDigit;
	private String[] stringQuotientMulDivisor;
	private String[] stringDigitOfQuotient;

	public String classicFormat(DivisionResult dataresult) {

		resultDigit = dataresult.getResultDigit();
		maxLength = dataresult.getMaxLength();
		stringResult = new StringBuilder();
		dividend = dataresult.getDividend();
		divisor = dataresult.getDivisor();
		stringQuotientMulDivisor = getStringArray(dataresult.getResultQuotientMulDivisor());
		stringDigitOfQuotient = getStringArray(dataresult.getResultDigitOfQuotient());

		formatHead();
		formatBody();

		return stringResult.toString();
	}

	private void formatHead() {
		stringResult.append((String.format("_" + dividend + "|" + divisor))).append("\n");
	}

	private void formatSecondString(int spaceNumbers, int size1, int size2) {
		spaces1 = String.format("%" + spaceNumbers + "s", "");
		outLine1 += spaces1 + "|" + String.format("%" + size1 + "s", "").replace(' ', '-');
		outLine2 += String.format("%" + size2 + "s", "") + "|" + joinList(resultDigit);
		stringResult.append(outLine1).append("\n").append(outLine2).append("\n").append(outLine3).append("\n");
	}

	private void formatBody() {
		for (int j = 0; j <= resultDigit.size() - 1; j++) {

			spaces1 = " %" + (j + 1) + "s";
			spaces2 = String.format("%" + stringQuotientMulDivisor[j].length() + "s", "").replace(' ', '-');
			spaces3 = "%" + (j + 3) + "s";

			outLine1 = String.format(spaces1, stringQuotientMulDivisor[j]);
			outLine2 = String.format(spaces1, spaces2);
			outLine3 = String.format(spaces3, "_" + stringDigitOfQuotient[j]);

			if (j == 0) {
				int param1 = maxLength - stringQuotientMulDivisor[j].length();
				int param2 = resultDigit.size();
				int param3 = maxLength - spaces2.length();
				formatSecondString(param1, param2, param3);
			} else if (j == stringDigitOfQuotient.length - 1) {
				spaces3 = "%" + (j + 2) + "s";
				outLine3 = String.format(spaces3, stringDigitOfQuotient[j]);
				lastString();
			} else
				stringResult.append(outLine1).append("\n").append(outLine2).append("\n").append(outLine3).append("\n");
		}
	}

	private void lastString() {
		stringResult.append(outLine1).append("\n").append(outLine2).append("\n").append(outLine3);
	}

	private String joinList(List<Integer> list) {
		return list.toString().replaceAll("[,\\[\\].\\s+]", "");
	}

	private String[] getStringArray(List<Integer> arr) {
		String[] str;
		str = new String[arr.size()];
		for (int i = 0; i < arr.size(); i++) {
			str[i] = Integer.toString(arr.get(i));
		}
		return str;
	}

}
