package com.devopsbuddy;

import com.devopsbuddy.web.i18n.I18NService;
import com.sun.tools.javac.util.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DevopsbuddyApplicationTests {

	@Autowired
	private I18NService i18NService;

	@Test
	public void testMessageByLocaleService() {
		String expectedResult = "Bootstrap starter Template";
		String messageId = "index.main.callout";
		String actual = i18NService.getMessage(messageId);
		try {
			if(actual != expectedResult) {
				throw new Exception("The actual and expected Strings don't match");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}