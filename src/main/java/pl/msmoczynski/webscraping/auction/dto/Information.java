package pl.msmoczynski.webscraping.auction.dto;

import org.jsoup.nodes.Element;

import java.util.Optional;

public interface Information {
    Optional<String> getTitle(Element productDOM);

    Optional<String> getPrice(Element productDOM);

    Optional<String> getImage(Element productDOM);

    Product getInformation(Element productDOM);

    void chooseView(Element dom);
}
