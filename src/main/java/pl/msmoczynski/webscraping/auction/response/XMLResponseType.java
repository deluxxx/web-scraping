package pl.msmoczynski.webscraping.auction.response;

import org.springframework.stereotype.Component;
import pl.msmoczynski.webscraping.auction.dto.Product;
import pl.msmoczynski.webscraping.auction.dto.ProductsXML;
import pl.msmoczynski.webscraping.auction.enumfile.ResponseType;
import pl.msmoczynski.webscraping.auction.mapper.ProductToProductXMLMapper;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@Component
public class XMLResponseType implements ResponseTypeStrategy {

    @Override
    public File generate(List<Product> products) {
        try {
            JAXBContext context = JAXBContext.newInstance(ProductsXML.class);
            ProductsXML productXMLS = new ProductsXML();
            List<ProductsXML.ProductXML> productXMLList = productXMLS.getProductXMLList();

            for (Product product : products) {
                Optional<ProductsXML.ProductXML> productXML = ProductToProductXMLMapper.mapToProductXML(product);
                if(productXML.isPresent()) {
                    productXMLList.add(productXML.get());
                }
            }

            productXMLS.setProductXMLList(productXMLList);

            return getXmlFromProduct(productXMLS, context);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public ResponseType getType() {
        return ResponseType.XML;
    }

    private static File getXmlFromProduct(ProductsXML products, JAXBContext context) {
        try {
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            Path tempFile = Files.createTempFile("temp", ".xml");
            File file = new File(tempFile.toString());

            m.marshal(products, file);

            return file;
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
