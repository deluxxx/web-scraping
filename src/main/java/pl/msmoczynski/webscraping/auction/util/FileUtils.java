package pl.msmoczynski.webscraping.auction.util;

import java.util.Base64;

public final class FileUtils {

    public static String getBase64FromUrl(String url) {
        Base64.Encoder urlEncoder = Base64.getUrlEncoder();
        String result = urlEncoder.encodeToString(url.getBytes());

        return result;
    }

}
