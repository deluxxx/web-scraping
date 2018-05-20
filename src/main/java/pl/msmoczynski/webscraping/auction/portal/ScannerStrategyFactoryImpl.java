package pl.msmoczynski.webscraping.auction.portal;

import org.springframework.stereotype.Component;
import pl.msmoczynski.webscraping.auction.enumfile.Portal;
import pl.msmoczynski.webscraping.auction.util.PortalUtils;

import java.util.List;
import java.util.Optional;

@Component
public final class ScannerStrategyFactoryImpl implements ScannerStrategyFactory {

    private final static String URL_NOT_SUPPORTED_ERR_MSG = "Adres %s nie jest obsługiwany przez aplikację.";

    private final PortalUtils portalUtils;
    private final List<ScannerStrategy> scannerStrategies;

    public ScannerStrategyFactoryImpl(PortalUtils portalUtils, List<ScannerStrategy> scannerStrategies) {
        this.portalUtils = portalUtils;
        this.scannerStrategies = scannerStrategies;
    }

    @Override
    public ScannerStrategy create(String url) {
        Portal portal = portalUtils.choosePortal(url);

        return scannerStrategies.stream()
                .filter(scannerStrategy -> portal.equals(scannerStrategy.getType()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format(URL_NOT_SUPPORTED_ERR_MSG, url)));
    }

}
