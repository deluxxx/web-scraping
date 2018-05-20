package pl.msmoczynski.webscraping.auction.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import pl.msmoczynski.webscraping.auction.dto.Product;

import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceImplTest {

    private static final String URL = "url";
    private static final String RESPONSE_TYPE = "responseType";

    @Mock
    private WebScrapingService webScrapingService;
    @Mock
    private ReponseTypeService reponseTypeService;
    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    public void testGetFileProducts() {
        List<Product> products = Mockito.mock(List.class);
        when(webScrapingService.scraping(URL)).thenReturn(products);

        productService.getFileProducts(URL, RESPONSE_TYPE);

        verify(webScrapingService, times(1)).scraping(URL);
        verify(reponseTypeService, times(1)).getResponse(products, RESPONSE_TYPE);
    }

}