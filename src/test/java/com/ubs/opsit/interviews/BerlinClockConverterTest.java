package com.ubs.opsit.interviews;

import org.junit.Assert;
import org.junit.Test;

public class BerlinClockConverterTest {
	BerlinClockConverter timeConverter = new BerlinClockConverter();

	@Test
	public void convertTimeTest() {
		String expectd = "";
		String actual = "";
		expectd = "O" + System.lineSeparator() + "RROO" + System.lineSeparator() + "RRRO" + System.lineSeparator()
				+ "YYROOOOOOOO" + System.lineSeparator() + "YYOO";
		actual = timeConverter.convertTime("13:17:01");
		Assert.assertEquals(expectd, timeConverter.convertTime("13:17:01"));

		expectd = "Y" + System.lineSeparator() + "OOOO" + System.lineSeparator() + "OOOO" + System.lineSeparator()
				+ "OOOOOOOOOOO" + System.lineSeparator() + "OOOO";
		actual = timeConverter.convertTime("00:00:00");
		Assert.assertEquals(expectd, actual);

		expectd = "O" + System.lineSeparator() + "RRRR" + System.lineSeparator() + "RRRO" + System.lineSeparator()
				+ "YYRYYRYYRYY" + System.lineSeparator() + "YYYY";
		actual = timeConverter.convertTime("23:59:59");
		Assert.assertEquals(expectd, actual);

		expectd = "Y" + System.lineSeparator() + "RRRR" + System.lineSeparator() + "RRRR" + System.lineSeparator()
				+ "OOOOOOOOOOO" + System.lineSeparator() + "OOOO";
		actual = timeConverter.convertTime("24:00:00");
		Assert.assertEquals(expectd, actual);

	}

	@Test
	public void validateTimeTest() {
		Assert.assertEquals(true, timeConverter.validateTime("00:00:00"));
		Assert.assertEquals(true, timeConverter.validateTime("24:00:00"));
		Assert.assertEquals(true, timeConverter.validateTime("23:01:00"));
		Assert.assertEquals(true, timeConverter.validateTime("23:59:00"));
		Assert.assertEquals(true, timeConverter.validateTime("23:59:59"));

		Assert.assertEquals(false, timeConverter.validateTime("23:59:60"));
		Assert.assertEquals(false, timeConverter.validateTime("23:60:59"));
		Assert.assertEquals(false, timeConverter.validateTime("24:00:01"));
		Assert.assertEquals(false, timeConverter.validateTime("-1:012:1231"));
		Assert.assertEquals(false, timeConverter.validateTime("arwaw"));
	}

	@Test
	public void getSecondsStringTest() {
		Assert.assertEquals("Y", timeConverter.getSecondsString("10").get());
		Assert.assertEquals("Y", timeConverter.getSecondsString("0").get());
		Assert.assertEquals("Y", timeConverter.getSecondsString("188").get());

		Assert.assertEquals("O", timeConverter.getSecondsString("1").get());
		Assert.assertEquals("O", timeConverter.getSecondsString("111").get());

		Assert.assertEquals(false, timeConverter.getSecondsString("x").isPresent());
		Assert.assertEquals(false, timeConverter.getSecondsString("").isPresent());
	}

	@Test
	public void getMinutesStringTest() {
		Assert.assertEquals("YYOOOOOOOOO" + System.lineSeparator() + "YYYY",
				timeConverter.getMinutesString("14").get());
		Assert.assertEquals("YYROOOOOOOO" + System.lineSeparator() + "OOOO",
				timeConverter.getMinutesString("15").get());
		Assert.assertEquals("YYRYYROOOOO" + System.lineSeparator() + "OOOO",
				timeConverter.getMinutesString("30").get());
		Assert.assertEquals("YYRYYRYOOOO" + System.lineSeparator() + "OOOO",
				timeConverter.getMinutesString("35").get());
		Assert.assertEquals("YYRYYRYOOOO" + System.lineSeparator() + "YYOO",
				timeConverter.getMinutesString("37").get());
		Assert.assertEquals("YYRYYRYYROO" + System.lineSeparator() + "YYYO",
				timeConverter.getMinutesString("48").get());
		Assert.assertEquals("YYRYYRYYRYY" + System.lineSeparator() + "YYYO",
				timeConverter.getMinutesString("58").get());
		Assert.assertEquals("OOOOOOOOOOO" + System.lineSeparator() + "YOOO", timeConverter.getMinutesString("1").get());
		Assert.assertEquals(false, timeConverter.getMinutesString("-1").isPresent());

	}

	@Test
	public void replaceThirdWithRedTest() {
		Assert.assertEquals("YYRYYRYYRYY", timeConverter.replaceThirdCharWithRedIfY("YYYYYYYYYYY"));
		Assert.assertEquals("YYOOOOOOOOO", timeConverter.replaceThirdCharWithRedIfY("YYOOOOOOOOO"));
	}

	@Test
	public void getHoursStringTest() {
		Assert.assertEquals("OOOO" + System.lineSeparator() + "ROOO", timeConverter.getHoursString("1").get());
		Assert.assertEquals("OOOO" + System.lineSeparator() + "OOOO", timeConverter.getHoursString("0").get());
		Assert.assertEquals("RRRO" + System.lineSeparator() + "OOOO", timeConverter.getHoursString("15").get());
		Assert.assertEquals("RRRO" + System.lineSeparator() + "RRRO", timeConverter.getHoursString("18").get());
		Assert.assertEquals("RRRR" + System.lineSeparator() + "RRRO", timeConverter.getHoursString("23").get());
		Assert.assertEquals("RRRR" + System.lineSeparator() + "RRRR", timeConverter.getHoursString("24").get());
		Assert.assertEquals(false, timeConverter.getHoursString("25").isPresent());
		Assert.assertEquals(false, timeConverter.getHoursString("-1").isPresent());
	}

	@Test
	public void getOandRforHourTest() {
		Assert.assertEquals("RRRRROOO", timeConverter.getOandRforHour(5, 8, "R").get());
		Assert.assertEquals("OOOOOOOO", timeConverter.getOandRforHour(0, 8, "R").get());
		Assert.assertEquals("RRRRRRRR", timeConverter.getOandRforHour(8, 8, "R").get());
		Assert.assertEquals(false, timeConverter.getOandRforHour(-1, 8, "R").isPresent());
		Assert.assertEquals(false, timeConverter.getOandRforHour(8, 7, "R").isPresent());
	}
}
