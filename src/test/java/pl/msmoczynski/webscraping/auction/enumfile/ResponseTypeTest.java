package pl.msmoczynski.webscraping.auction.enumfile;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ResponseTypeTest {

    @Test
    public void chooseResponseTypeXMLTest() throws Exception {
        String code = "xml";
        ResponseType responseType = ResponseType.chooseResponseType(code);
        Assert.assertEquals(ResponseType.XML, responseType);
    }

    @Test(expected = IllegalArgumentException.class)
    public void chooseResponseTypeFailTest() throws Exception {
        String code = "AssecoBS";
        ResponseType responseType = ResponseType.chooseResponseType(code);
        Assert.assertEquals(ResponseType.XML, responseType);
    }

    @Test
    public void getMediaType() throws Exception {
        String code = "xml";
        ResponseType responseType = ResponseType.chooseResponseType(code);
        Assert.assertEquals("application/xml", responseType.getMediaType());
    }

}