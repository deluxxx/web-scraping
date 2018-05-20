package pl.msmoczynski.webscraping.auction.service;

import java.io.File;

public interface ProductService {

    File getFileProducts(String url, String responseType);

}
