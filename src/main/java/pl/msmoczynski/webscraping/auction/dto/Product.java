package pl.msmoczynski.webscraping.auction.dto;

public class Product {

    private String title;
    private String image;
    private String price;

    public Product(String title, String image, String price) {
        this.title = title;
        this.image = image;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public String getPrice() {
        return price;
    }

}
