package pl.msmoczynski.webscraping.auction.service;

import org.springframework.stereotype.Service;
import pl.msmoczynski.webscraping.auction.dto.Product;
import pl.msmoczynski.webscraping.auction.response.ResponseTypeStrategy;
import pl.msmoczynski.webscraping.auction.response.ResponseTypeStrategyFactory;

import java.io.File;
import java.util.List;

@Service
public final class ReponseTypeServiceImpl implements ReponseTypeService {

    private final ResponseTypeStrategyFactory responseTypeStrategyFactory;

    public ReponseTypeServiceImpl(ResponseTypeStrategyFactory responseTypeStrategyFactory) {
        this.responseTypeStrategyFactory = responseTypeStrategyFactory;
    }

    @Override
    public File getResponse(List<Product> products, String responseType) {
        ResponseTypeStrategy responseTypeStrategy = responseTypeStrategyFactory.create(responseType);

        return responseTypeStrategy.generate(products);
    }

}
