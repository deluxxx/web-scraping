package pl.msmoczynski.webscraping.auction.portal.ceneo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import pl.msmoczynski.webscraping.auction.dto.Information;
import pl.msmoczynski.webscraping.auction.dto.Product;
import pl.msmoczynski.webscraping.auction.enumfile.Portal;
import pl.msmoczynski.webscraping.auction.enumfile.View;
import pl.msmoczynski.webscraping.auction.portal.ScannerStrategy;
import pl.msmoczynski.webscraping.auction.util.FileUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CeneoScannerStrategy implements ScannerStrategy, Information {

    private static final String NEXT_PAGE = ";0020-30-0-0-";
    private static final String SUFIX_NEXT_PAGE = ".htm";

    private static final String DOM_CLASS_LIST_VIEW = "enable-view enable-list-view active";
    private static final String DOM_CLASS_PRODUCT_LIST_VIEW = "cat-prod-row";
    private static final String DOM_CLASS_TITLE_LIST_VIEW = "cat-prod-row-name";
    private static final String DOM_CLASS_PRICE_LIST_VIEW = "price";
    private static final String DOM_CLASS_IMAGE_LIST_VIEW = "cat-prod-row-foto";

    private static final String DOM_CLASS_BOX_VIEW = "enable-view enable-box-view active";
    private static final String DOM_CLASS_PRODUCT_BOX_VIEW = "category-item-box";
    private static final String DOM_CLASS_TITLE_BOX_VIEW = "category-item-box-name";
    private static final String DOM_CLASS_PRICE_BOX_VIEW = "price";
    private static final String DOM_CLASS_IMAGE_BOX_VIEW = "category-item-box-picture";

    private static final Logger logger = LoggerFactory.getLogger(CeneoScannerStrategy.class);

    private View view;

    private String titleClass;
    private String priceClass;
    private String imageClass;
    private String prodClass;

    @Override
    public List<Product> scraping(String url) {
        List<Product> results = new ArrayList<>();
        url = url.substring(0, url.length() - SUFIX_NEXT_PAGE.length());
        try {
            int page = 0;
            Document website = Jsoup.connect(url + NEXT_PAGE + page + SUFIX_NEXT_PAGE).get();

            chooseView(website);
            fillVariablesForView();

            while (true) {
                Elements elementsByClass = website.getElementsByClass(prodClass);
                if (elementsByClass.isEmpty()) {
                    break;
                }

                elementsByClass.forEach(element -> results.add(getInformation(element)));

                ++page;
                website = Jsoup.connect(url + NEXT_PAGE + page + SUFIX_NEXT_PAGE).get();
            }
            logger.info("Było " + page + " stron.");
            logger.info("Znalaziono " + results.size() + " produktów.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return results;
    }

    @Override
    public Product getInformation(Element dom) {
        Optional title = getTitle(dom);
        Optional price = getPrice(dom);
        Optional image = getImage(dom);

        if (title.isPresent() || price.isPresent() || image.isPresent()) {
            return new Product(title.get().toString(), image.get().toString(), price.get().toString());
        }
        return null;
    }

    @Override
    public Portal getType() {
        return Portal.CENEO;
    }

    @Override
    public Optional<String> getTitle(Element productDOM) {
        Elements titles = productDOM.getElementsByClass(this.titleClass);
        if (!titles.isEmpty()) {
            return Optional.ofNullable(titles.first().text());
        }
        return Optional.empty();
    }

    @Override
    public Optional<String> getPrice(Element productDOM) {
        Elements prices = productDOM.getElementsByClass(this.priceClass);
        if (!prices.isEmpty()) {
            return Optional.ofNullable(prices.first().getElementsByTag("span").get(0).text());
        }

        return Optional.empty();
    }


    @Override
    public Optional<String> getImage(Element productDOM) {
        Elements imageDiv = productDOM.getElementsByClass(this.imageClass);

        if (!imageDiv.isEmpty()) {
            Element img = imageDiv.first().select("img").first();
            String srcToImage = img.absUrl("src");
            return Optional.ofNullable(FileUtils.getBase64FromUrl(srcToImage));
        }
        return Optional.empty();
    }

    @Override
    public void chooseView(Element dom) {
        Elements activeBoxView = dom.getElementsByClass(DOM_CLASS_BOX_VIEW);
        Elements activeListView = dom.getElementsByClass(DOM_CLASS_LIST_VIEW);

        if (activeBoxView.first() != null) {
            this.view = View.BOX;
        } else if (activeListView != null) {
            this.view = View.LIST;
        } else {
            throw new IllegalArgumentException("Aplikacja nie jest w stanie odczytać widoku użytego na stronie.");
        }

    }

    private void fillVariablesForView() {
        if (this.view == View.BOX) {
            this.titleClass = DOM_CLASS_TITLE_BOX_VIEW;
            this.priceClass = DOM_CLASS_PRICE_BOX_VIEW;
            this.imageClass = DOM_CLASS_IMAGE_BOX_VIEW;
            this.prodClass = DOM_CLASS_PRODUCT_BOX_VIEW;
        } else if (this.view == View.LIST) {
            this.titleClass = DOM_CLASS_TITLE_LIST_VIEW;
            this.priceClass = DOM_CLASS_PRICE_LIST_VIEW;
            this.imageClass = DOM_CLASS_IMAGE_LIST_VIEW;
            this.prodClass = DOM_CLASS_PRODUCT_LIST_VIEW;
        }
    }
}
