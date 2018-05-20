package pl.msmoczynski.webscraping.auction.service;

import pl.msmoczynski.webscraping.auction.dto.Product;

import java.util.List;

public interface WebScrapingService {

    List<Product> scraping(String url);

}
