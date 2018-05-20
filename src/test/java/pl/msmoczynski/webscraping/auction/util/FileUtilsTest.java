package pl.msmoczynski.webscraping.auction.util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

@RunWith(MockitoJUnitRunner.class)
public class FileUtilsTest {

    @Test
    public void getBase64FromUrlTest(){
        String url = "https://www.assecobs.pl/pl/";
        String base64FromUrl = FileUtils.getBase64FromUrl(url);

        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decode = decoder.decode(base64FromUrl);

        try {
            Assert.assertEquals(url, new String(decode, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

}