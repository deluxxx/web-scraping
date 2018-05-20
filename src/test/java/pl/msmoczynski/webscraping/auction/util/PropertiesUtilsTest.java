package pl.msmoczynski.webscraping.auction.util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PropertiesUtils.class)
@TestPropertySource(properties = {
        "ceneo.urls=https://www.ceneo.pl,www.ceneo.pl,http://www.ceneo.pl",
})
public class PropertiesUtilsTest {

    @Autowired
    private PropertiesUtils propertiesUtils;

    @Value("#{'${ceneo.urls}'.split(',')}")
    private List<String> ceneoURLs;

    @Test
    public void getCeneoURLsTest(){
        List<String> ceneoURLs = propertiesUtils.getCeneoURLs();
        Assert.assertEquals(3, ceneoURLs.size());
    }

}