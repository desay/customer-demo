package com.customer.demo.numbers.generator;

import com.customer.demo.numbers.Loggable;
import com.customer.demo.numbers.ThirteenDigits;

import javax.inject.Inject;
import java.util.Random;
import java.util.logging.Logger;

@ThirteenDigits
public class IsbnGenerator implements NumberGenerator{
    @Inject
    private Logger logger;


    @Loggable
    @Override
    public String generateNumber() {
        String isbn = "13-84356-" + Math.abs(new Random().nextInt());
        logger.info("Generated ISBN : " + isbn);
        return isbn;
    }
}
