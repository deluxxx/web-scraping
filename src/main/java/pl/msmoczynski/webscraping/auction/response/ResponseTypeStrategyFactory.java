package pl.msmoczynski.webscraping.auction.response;

public interface ResponseTypeStrategyFactory {

    ResponseTypeStrategy create(String responseType);

}
