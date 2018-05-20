package pl.msmoczynski.webscraping.auction.service;

import pl.msmoczynski.webscraping.auction.dto.Product;

import java.io.File;
import java.util.List;

public interface ReponseTypeService {

    File getResponse(List<Product> products, String responseType);

}
