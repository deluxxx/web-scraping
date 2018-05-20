package pl.msmoczynski.webscraping.auction.response;

import org.springframework.stereotype.Component;
import pl.msmoczynski.webscraping.auction.enumfile.ResponseType;

import java.util.List;

import static pl.msmoczynski.webscraping.auction.enumfile.ResponseType.RESPONSE_TYPE_NOT_SUPPORTED_ERR_MSG;

@Component
public class ResponseTypeStrategyFactoryImpl implements ResponseTypeStrategyFactory {
    private List<ResponseTypeStrategy> responseTypeStrategies;

    public ResponseTypeStrategyFactoryImpl(List<ResponseTypeStrategy> responseTypeStrategies) {
        this.responseTypeStrategies = responseTypeStrategies;
    }


    @Override
    public ResponseTypeStrategy create(String responseTypeIn) {
        ResponseType responseType = ResponseType.chooseResponseType(responseTypeIn);

        return responseTypeStrategies.stream().
                filter(responseTypeStrategy -> responseType.equals(responseTypeStrategy.getType()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format(RESPONSE_TYPE_NOT_SUPPORTED_ERR_MSG, responseTypeIn)));
    }
}
