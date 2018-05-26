package com.ubs.opsit.interviews.utils;

import java.util.Optional;

public class MathUtil {
	public static Optional<Integer> validateInteger(final String val) {
		Optional<Integer> ret = Optional.empty();
		try {
			ret = Optional.of(Integer.parseInt(val));
		} catch (Exception e) {

		}
		return ret;
	}
}
