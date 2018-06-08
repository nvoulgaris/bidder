package com.bluebanana.bidder;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@RunWith(JUnitPlatform.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {BidderApplication.class})
public class BidderApplicationShould {

	@Test
	public void loadTheContext() {
	}
}
