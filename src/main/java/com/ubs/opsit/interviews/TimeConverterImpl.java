package com.ubs.opsit.interviews;

import java.util.regex.Pattern;

public class TimeConverterImpl implements TimeConverter {

	@Override
	public String convertTime(String aTime) {
		if (aTime == null || !validateTime(aTime)) {
			return "Time is not valid";
		}
		String time[] = aTime.split(":");
		if(Integer.parseInt(time[0])==24 && (Integer.parseInt(time[1]) > 0 || Integer.parseInt(time[2]) > 0)) {
			return "Time is not valid";
		}
		String seconds = getSecondsString(time[2]);
		String minutes = getMinutesString(time[1]);
		String hours = getHoursString(time[0]);
		return seconds + System.lineSeparator() + hours + System.lineSeparator() + minutes;
	}

	public boolean validateTime(final String time) {
		return Pattern.compile("([01]?[0-9]|2[0-4]):[0-5][0-9]:[0-5][0-9]").matcher(time).matches();
	}

	String getSecondsString(String seconds) {
		return Integer.parseInt(seconds) % 2 == 0 ? "Y" : "O";
	}

	String getMinutesString(String minutes) {
		int intMinute = Integer.parseInt(minutes);
		int firstRow = intMinute / 5;
		int secondRow = intMinute % 5;
		String firstLine = getOandRforHour(firstRow, 11, "Y");
		firstLine = replaceThirdWithRed(firstLine);
		String secondLine = getOandRforHour(secondRow, 4, "Y");

		return firstLine + System.lineSeparator() + secondLine;

	}

	private String replaceThirdWithRed(String firstLine) {
		char firstLineArray[] = firstLine.toCharArray();
		if (firstLineArray[2] == 'Y') {
			firstLineArray[2] = 'R';
		}  if (firstLineArray[5] == 'Y') {
			firstLineArray[5] = 'R';
		}  if (firstLineArray[8] == 'Y') {
			firstLineArray[8] = 'R';
		}
		return new String(firstLineArray);
	}

	String getHoursString(String hours) {
		int intHours = Integer.parseInt(hours);
		int firstRow = intHours / 5;
		int secondRow = intHours % 5;
		return getOandRforHour(firstRow, 4, "R") + System.lineSeparator() + getOandRforHour(secondRow, 4, "R");
	}

	String getOandRforHour(int number, int length, String fColor) {
		return new String(new char[number]).replace("\0", fColor)
				.concat(new String(new char[length - number]).replace("\0", "O"));
	}
}
