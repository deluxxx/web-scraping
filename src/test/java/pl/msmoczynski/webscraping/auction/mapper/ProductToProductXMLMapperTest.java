package pl.msmoczynski.webscraping.auction.mapper;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import pl.msmoczynski.webscraping.auction.dto.Product;
import pl.msmoczynski.webscraping.auction.dto.ProductsXML;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class ProductToProductXMLMapperTest {

    @Test
    public void nullMapToProductXMLTest() {

        Optional<ProductsXML.ProductXML> productXML = ProductToProductXMLMapper.mapToProductXML(null);
        Assert.assertFalse(productXML.isPresent());
    }

    @Test
    public void MapToProductXMLTest() {
        Product product = new Product("Asseco BS", "testBase64", "1000");

        Optional<ProductsXML.ProductXML> productXMLObjectOptional = ProductToProductXMLMapper.mapToProductXML(product);
        Assert.assertTrue(productXMLObjectOptional.isPresent());

        ProductsXML.ProductXML productXML = productXMLObjectOptional.get();
        Assert.assertEquals(product.getTitle(), productXML.getTitle());
        Assert.assertEquals(product.getImage(), productXML.getImage());
        Assert.assertEquals(product.getPrice(), productXML.getPrice());
    }

}