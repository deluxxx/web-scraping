package pl.msmoczynski.webscraping.auction.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pl.msmoczynski.webscraping.auction.portal.ScannerStrategy;
import pl.msmoczynski.webscraping.auction.portal.ScannerStrategyFactory;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WebScrapingServiceImplTest {

    public static final String URL = "url";
    @Mock
    private ScannerStrategy scannerStrategy;
    @Mock
    private ScannerStrategyFactory scannerStrategyFactory;
    @InjectMocks
    private WebScrapingServiceImpl webScrapingService;

    @Test
    public void testScraping() {
        when(scannerStrategyFactory.create(URL)).thenReturn(scannerStrategy);

        webScrapingService.scraping(URL);

        verify(scannerStrategyFactory, times(1)).create(URL);
        verify(scannerStrategy, times(1)).scraping(URL);
    }

}