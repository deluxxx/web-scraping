package pl.msmoczynski.webscraping.auction.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pl.msmoczynski.webscraping.auction.dto.Product;
import pl.msmoczynski.webscraping.auction.response.ResponseTypeStrategy;
import pl.msmoczynski.webscraping.auction.response.ResponseTypeStrategyFactory;

import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ReponseTypeServiceImplTest {

    public static final String RESPONSE_TYPE = "responseType";
    @Mock
    private ResponseTypeStrategy responseTypeStrategy;
    @Mock
    private List<Product> products;
    @Mock
    private ResponseTypeStrategyFactory responseTypeStrategyFactory;
    @InjectMocks
    private ReponseTypeServiceImpl reponseTypeService;

    @Test
    public void testGetResponse() {
        when(responseTypeStrategyFactory.create(RESPONSE_TYPE)).thenReturn(responseTypeStrategy);

        reponseTypeService.getResponse(products, RESPONSE_TYPE);

        verify(responseTypeStrategyFactory, times(1)).create(RESPONSE_TYPE);
        verify(responseTypeStrategy, times(1)).generate(products);
    }

}