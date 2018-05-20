package pl.msmoczynski.webscraping.auction.portal;

import pl.msmoczynski.webscraping.auction.dto.Product;
import pl.msmoczynski.webscraping.auction.enumfile.Portal;

import java.util.List;

public interface ScannerStrategy {

    List<Product> scraping(String url);

    Portal getType();
}
