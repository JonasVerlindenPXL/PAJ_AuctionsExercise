package be.pxl.auctions.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EmailValidatorIsValidTest {

	@Test
	public void returnsTrueWhenValidEmail() {
		// TODO implement test
		assertTrue(EmailValidator.isValid("jonas.verlinden@live.be"));


	}

	@Test
	public void returnsFalseWhenAtSignMissing() {
		// TODO implement test
		assertFalse(EmailValidator.isValid("jonas.verlindenlive.be"));
	}

}
