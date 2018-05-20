package pl.msmoczynski.webscraping.auction.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductsXML {

    @XmlElement(name = "product")
    List<ProductXML> productXMLList;

    public List<ProductXML> getProductXMLList() {
        if(productXMLList == null){
            return new ArrayList<>();
        }
        return productXMLList;
    }

    public void setProductXMLList(List<ProductXML> productXMLList) {
        this.productXMLList = productXMLList;
    }

    @XmlRootElement
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class ProductXML{
        private String title;
        private String image;
        private String price;

        public void setTitle(String title) {
            this.title = title;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getImage() {
            return image;
        }

        public String getTitle() {
            return title;
        }

        public String getPrice() {
            return price;
        }
    }
}
