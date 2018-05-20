package pl.msmoczynski.webscraping.auction.controller;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.msmoczynski.webscraping.auction.enumfile.ResponseType;
import pl.msmoczynski.webscraping.auction.service.ProductService;

import java.io.File;
import java.net.MalformedURLException;

@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/file")
    public ResponseEntity<Resource> getByResponseType(@PathVariable(name = "url") String url, @PathVariable(name = "responseType") String responseType) {

        File productsFile = productService.getFileProducts(url, responseType);
        ResponseType responseTypeEnum = ResponseType.chooseResponseType(responseType);

        try {
            Resource resource = new UrlResource(productsFile.toURI());

            return ResponseEntity.ok()
                    .contentType(MediaType.valueOf(responseTypeEnum.getMediaType()))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + productsFile.getName())
                    .body(resource);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @PostMapping(value = "/xml")
    public ResponseEntity<Resource> getXML(@RequestBody String url) {
        return getByResponseType(url, ResponseType.XML.name());
    }

}