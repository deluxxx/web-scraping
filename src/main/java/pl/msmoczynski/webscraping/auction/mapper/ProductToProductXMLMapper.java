package pl.msmoczynski.webscraping.auction.mapper;

import org.springframework.stereotype.Service;
import pl.msmoczynski.webscraping.auction.dto.Product;
import pl.msmoczynski.webscraping.auction.dto.ProductsXML;

import java.util.Optional;

public final class ProductToProductXMLMapper {

    public static Optional<ProductsXML.ProductXML> mapToProductXML(Product product) {
        if (product == null) {
            return Optional.empty();
        }

        ProductsXML.ProductXML result = new  ProductsXML.ProductXML();
        result.setImage(product.getImage());
        result.setPrice(product.getPrice());
        result.setTitle(product.getTitle());

        return Optional.ofNullable(result);
    }

}
