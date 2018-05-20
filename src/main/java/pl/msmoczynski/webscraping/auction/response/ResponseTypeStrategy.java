package pl.msmoczynski.webscraping.auction.response;

import pl.msmoczynski.webscraping.auction.dto.Product;
import pl.msmoczynski.webscraping.auction.enumfile.ResponseType;

import java.io.File;
import java.util.List;

public interface ResponseTypeStrategy {
    File generate(List<Product> products);

    ResponseType getType();
}
