package pl.msmoczynski.webscraping.auction.service;

import org.springframework.stereotype.Service;
import pl.msmoczynski.webscraping.auction.dto.Product;
import pl.msmoczynski.webscraping.auction.portal.ScannerStrategy;
import pl.msmoczynski.webscraping.auction.portal.ScannerStrategyFactory;

import java.util.Collections;
import java.util.List;

@Service
public final class WebScrapingServiceImpl implements WebScrapingService {

    private final ScannerStrategyFactory scannerStrategyFactory;

    public WebScrapingServiceImpl(ScannerStrategyFactory scannerStrategyFactory) {
        this.scannerStrategyFactory = scannerStrategyFactory;
    }

    @Override
    public List<Product> scraping(String url) {
        ScannerStrategy scannerStrategy = scannerStrategyFactory.create(url);

        return scannerStrategy.scraping(url);
    }

}
