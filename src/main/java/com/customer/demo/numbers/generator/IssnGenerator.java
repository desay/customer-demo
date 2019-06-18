package com.customer.demo.numbers.generator;

import com.customer.demo.numbers.EightDigits;
import com.customer.demo.numbers.Loggable;

import javax.inject.Inject;
import java.util.Random;
import java.util.logging.Logger;

@EightDigits
public class IssnGenerator implements NumberGenerator{

    @Inject
    private Logger logger;

    @Loggable
    @Override
    public String generateNumber() {
        String issn =  "8-" + Math.abs(new Random().nextInt());
        logger.info("Generated ISBN : " + issn);
        return issn;
    }
}
