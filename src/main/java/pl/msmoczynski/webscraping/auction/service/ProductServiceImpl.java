package pl.msmoczynski.webscraping.auction.service;

import org.springframework.stereotype.Service;
import pl.msmoczynski.webscraping.auction.dto.Product;

import java.io.File;
import java.util.List;

@Service
public final class ProductServiceImpl implements ProductService {

    private final WebScrapingService webScrapingService;
    private final ReponseTypeService reponseTypeService;

    public ProductServiceImpl(WebScrapingService webScrapingService, ReponseTypeService reponseTypeService) {
        this.webScrapingService = webScrapingService;
        this.reponseTypeService = reponseTypeService;
    }

    @Override
    public File getFileProducts(String url, String responseType) {
        List<Product> products = webScrapingService.scraping(url);

        return reponseTypeService.getResponse(products, responseType);
    }

}
