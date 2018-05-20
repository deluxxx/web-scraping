package pl.msmoczynski.webscraping.auction.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PropertiesUtils {

    @Value("#{'${ceneo.urls}'.split(',')}")
    private List<String> ceneoURLs;

    public List<String> getCeneoURLs() {
        return ceneoURLs;
    }


}
