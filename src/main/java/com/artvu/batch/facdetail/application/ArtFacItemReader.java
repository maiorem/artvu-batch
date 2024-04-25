package com.artvu.batch.facdetail.application;

import com.artvu.batch.artdetail.application.ArtDetailService;
import com.artvu.batch.common.constants.Constants;
import com.artvu.batch.facdetail.presentation.KopisFacDetailResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.codec.xml.Jaxb2XmlDecoder;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class ArtFacItemReader implements ItemReader<List<KopisFacDetailResponse>> {

    private final ArtDetailService artService;

    @Value("${spring.open-api.secretKey}")
    private String secretKey;

    int count = 0;

    @Override
    public List<KopisFacDetailResponse> read() throws Exception {

        List<String> artFacIdList = artService.artFacIdList();
        List<KopisFacDetailResponse> detailList = new ArrayList<>();
        Gson gson = new Gson();


        ExchangeStrategies exchangeStrategies = ExchangeStrategies.builder()
                .codecs(clientCodecConfigurer -> clientCodecConfigurer.defaultCodecs()
                        .jaxb2Decoder(new Jaxb2XmlDecoder()))
                .build();

        WebClient webClient = WebClient.builder()
                .baseUrl(Constants.KOPIS_BASE_URL)
                .exchangeStrategies(exchangeStrategies)
                .build();

        for (String facId : artFacIdList) {
            Mono<String> response = webClient
                    .get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/prfplc")
                            .path("/"+facId)
                            .queryParam("service", secretKey)
                            .queryParam("newsql", "Y")
                            .build())
                    .accept(MediaType.APPLICATION_XML)
                    .retrieve()
                    .bodyToMono(String.class)
                    .log();
            String xmlResult = response.block();
            assert xmlResult != null;
            JSONObject jsonResult = XML.toJSONObject(xmlResult);
            KopisFacDetailResponse result = gson.fromJson(jsonResult.toString(), TypeToken.getParameterized(KopisFacDetailResponse.class).getType());
            detailList.add(result);
        }

        count++;
        log.info("art FacDetail READER ============================================== ");

        return count > 1 ? null : detailList;
    }
}
