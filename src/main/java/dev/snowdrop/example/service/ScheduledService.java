package dev.snowdrop.example.service;

import dev.snowdrop.example.util.Quotes;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.logging.Logger;

@Service
public class ScheduledService {
    Logger log = Logger.getLogger("ScheduledService");
    @Scheduled(fixedDelay = 120000)
    public void schedule() {
        Random random = new Random();
        int index = random.nextInt(Quotes.ALL_QUOTES.length);
        log.info(Quotes.ALL_QUOTES[index]);
    }
}
