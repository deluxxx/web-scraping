package pl.msmoczynski.webscraping.auction.enumfile;

import org.springframework.http.MediaType;

public enum ResponseType {
    XML("XML", MediaType.APPLICATION_XML);

    public static final String RESPONSE_TYPE_NOT_SUPPORTED_ERR_MSG = "Aplikacja nie obsługuje formatu: %s.";

    private String code;
    private String mediaType;

    ResponseType(String code, MediaType mediaType) {
        this.code = code;
        this.mediaType = mediaType.toString();
    }

    private String getCode() {
        return this.code;
    }

    public static ResponseType chooseResponseType(String code) {
        if (XML.getCode().equalsIgnoreCase(code)) {
            return XML;
        } else {
            throw new IllegalArgumentException(String.format(RESPONSE_TYPE_NOT_SUPPORTED_ERR_MSG, code));
        }
    }

    public String getMediaType() {
        return mediaType;
    }
}
