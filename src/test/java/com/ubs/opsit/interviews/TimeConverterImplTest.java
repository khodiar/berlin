package com.ubs.opsit.interviews;

import org.junit.Assert;
import org.junit.Test;

public class TimeConverterImplTest {
	@Test
	public void convertTimeTest() {
		TimeConverter timeConverter = new TimeConverterImpl();
		String expectd = "";
		String actual = "";
		expectd = "O" + System.lineSeparator() + "RROO" + System.lineSeparator() + "RRRO"
				+ System.lineSeparator() + "YYROOOOOOOO" + System.lineSeparator() + "YYOO";
		actual = timeConverter.convertTime("13:17:01");
		Assert.assertEquals(expectd, actual);

		expectd = "Y" + System.lineSeparator() + "OOOO" + System.lineSeparator() + "OOOO"
				+ System.lineSeparator() + "OOOOOOOOOOO" + System.lineSeparator() + "OOOO";
		 actual = timeConverter.convertTime("00:00:00");
		Assert.assertEquals(expectd, actual);
		
		 expectd = "O" + System.lineSeparator() + "RRRR" + System.lineSeparator() + "RRRO"
				+ System.lineSeparator() + "YYRYYRYYRYY" + System.lineSeparator() + "YYYY";
		 actual = timeConverter.convertTime("23:59:59");
		System.out.println(actual);
		Assert.assertEquals(expectd, actual);
		
		
		 expectd = "Y" + System.lineSeparator() + "RRRR" + System.lineSeparator() + "RRRR"
				+ System.lineSeparator() + "OOOOOOOOOOO" + System.lineSeparator() + "OOOO";
		 actual = timeConverter.convertTime("24:00:00");
		System.out.println(actual);
		Assert.assertEquals(expectd, actual);

	}
}
