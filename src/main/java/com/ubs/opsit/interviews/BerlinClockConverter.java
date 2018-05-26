package com.ubs.opsit.interviews;

import java.util.Optional;
import java.util.regex.Pattern;

import com.ubs.opsit.interviews.utils.MathUtil;

public class BerlinClockConverter implements TimeConverter {

	@Override
	public String convertTime(String aTime) {
		if (aTime == null || !validateTime(aTime)) {
			return "Time is not valid";
		}
		String time[] = aTime.split(":");
		Optional<String> seconds = getSecondsString(time[2]);
		Optional<String> minutes = getMinutesString(time[1]);
		Optional<String> hours = getHoursString(time[0]);
		return seconds.get() + System.lineSeparator() + hours.get() + System.lineSeparator() + minutes.get();
	}

	public boolean validateTime(final String time) {
		boolean valid = Pattern.compile("([01]?[0-9]|2[0-4]):[0-5][0-9]:[0-5][0-9]").matcher(time).matches();
		if (valid) {
			String timeArr[] = time.split(":");

			Optional<Integer> hour = MathUtil.validateInteger(timeArr[0]);
			Optional<Integer> minute = MathUtil.validateInteger(timeArr[1]);
			Optional<Integer> sec = MathUtil.validateInteger(timeArr[2]);

			if (hour.isPresent() && minute.isPresent() && sec.isPresent()) {
				if (hour.get() == 24 && (minute.get() > 0 || sec.get() > 0)) {
					valid = false;
				}
			}
		}
		return valid;
	}


	Optional<String> getSecondsString(String seconds) {
		Optional<Integer> secVal = MathUtil.validateInteger(seconds);
		if (secVal.isPresent()) {
			return Optional.of(secVal.get() % 2 == 0 ? "Y" : "O");
		}
		return Optional.empty();
	}

	Optional<String> getMinutesString(String minutes) {
		Optional<Integer> intMinute = MathUtil.validateInteger(minutes);
		if (intMinute.isPresent() && intMinute.get() > -1 && intMinute.get() < 60) {
			int firstRow = intMinute.get() / 5;
			int secondRow = intMinute.get() % 5;
			Optional<String> firstLine = getOandRforHour(firstRow, 11, "Y");
			if (firstLine.isPresent()) {
				firstLine = Optional.of(replaceThirdCharWithRedIfY(firstLine.get()));
				Optional<String> secondLine = getOandRforHour(secondRow, 4, "Y");
				if (secondLine.isPresent()) {
					return Optional.of(firstLine.get() + System.lineSeparator() + secondLine.get());
				}
			}
		}
		return Optional.empty();

	}

	public String replaceThirdCharWithRedIfY(String firstLine) {
		char firstLineArray[] = firstLine.toCharArray();
		if (firstLineArray[2] == 'Y') {
			firstLineArray[2] = 'R';
		}
		if (firstLineArray[5] == 'Y') {
			firstLineArray[5] = 'R';
		}
		if (firstLineArray[8] == 'Y') {
			firstLineArray[8] = 'R';
		}
		return new String(firstLineArray);
	}

	public Optional<String> getHoursString(String hours) {
		Optional<Integer> intHours = MathUtil.validateInteger(hours);
		if (intHours.isPresent() && intHours.get() > -1 && intHours.get() < 25) {
			int firstRow = intHours.get() / 5;
			int secondRow = intHours.get() % 5;
			Optional<String> firstRowStr = getOandRforHour(firstRow, 4, "R");
			Optional<String> secondRowStr = getOandRforHour(secondRow, 4, "R");
			if (firstRowStr.isPresent() && secondRowStr.isPresent()) {
				return Optional.of(firstRowStr.get() + System.lineSeparator() + secondRowStr.get());
			}
		}
		return Optional.empty();
	}

	Optional<String> getOandRforHour(int number, int length, String fColor) {
		if (number >= 0 && length >= number) {
			return Optional.of(new String(new char[number]).replace("\0", fColor)
					.concat(new String(new char[length - number]).replace("\0", "O")));
		}
		return Optional.empty();
	}
}
