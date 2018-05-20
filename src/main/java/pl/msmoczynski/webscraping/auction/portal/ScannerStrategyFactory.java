package pl.msmoczynski.webscraping.auction.portal;

public interface ScannerStrategyFactory {

    ScannerStrategy create(String url);

}
