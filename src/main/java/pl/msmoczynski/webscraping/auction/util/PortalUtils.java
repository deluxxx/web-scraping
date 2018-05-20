package pl.msmoczynski.webscraping.auction.util;

import org.springframework.stereotype.Component;
import pl.msmoczynski.webscraping.auction.enumfile.Portal;

import javax.annotation.PostConstruct;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Component
public class PortalUtils {

    private final PropertiesUtils propertiesUtils;
    private final Map<Portal, List<String>> portalsMap = new EnumMap<>(Portal.class);

    public PortalUtils(PropertiesUtils propertiesUtils) {
        this.propertiesUtils = propertiesUtils;
    }

    @PostConstruct
    private void fillEnumPortalMap() {
        portalsMap.put(Portal.CENEO, propertiesUtils.getCeneoURLs());
    }

    public Portal choosePortal(String url) {
        if(url == null || url.trim().isEmpty()) {
            throw new NullPointerException("Podany url nie może być pusty");
        }

        for (Map.Entry property : portalsMap.entrySet()) {
            List<String> urlsForPortal = (List<String>) property.getValue();
            for (String urlProp : urlsForPortal) {
                if (url.contains(urlProp)) {
                    return (Portal) property.getKey();
                }
            }
        }

        throw new IllegalArgumentException(String.format("Nie odnaleziono portalu dla podanego url: %s", url));
    }
}
