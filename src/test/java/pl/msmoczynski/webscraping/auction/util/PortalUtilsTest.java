package pl.msmoczynski.webscraping.auction.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PortalUtilsTest {

    private static final String UNSUPPORTED_URL = "unsupportedUrl";
    private static final String EMPTY_URL = "";
    private static final String NULL_URL = null;

    @InjectMocks
    private PortalUtils portalUtils;

    @Test(expected = NullPointerException.class)
    public void testChoosePortalThrowsExeptionOnEmptyUrl() {
        portalUtils.choosePortal(EMPTY_URL);
    }

    @Test(expected = NullPointerException.class)
    public void testChoosePortalThrowsExeptionOnNullUrl() {
        portalUtils.choosePortal(NULL_URL);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testChoosePortalThrowExceptionOnUnsupportedUrl() {
        portalUtils.choosePortal(UNSUPPORTED_URL);
    }
}