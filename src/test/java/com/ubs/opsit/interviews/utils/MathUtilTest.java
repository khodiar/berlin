package com.ubs.opsit.interviews.utils;

import org.junit.Assert;
import org.junit.Test;

public class MathUtilTest {

	@Test
	public void validateIntegerTest() {
		Assert.assertEquals(true, MathUtil.validateInteger("123").isPresent());
		Assert.assertEquals(123, (int) MathUtil.validateInteger("123").get());

		Assert.assertEquals(true, MathUtil.validateInteger("98765").isPresent());
		Assert.assertEquals(98765, (int) MathUtil.validateInteger("98765").get());

		Assert.assertEquals(false, MathUtil.validateInteger("asd").isPresent());
	}
}
