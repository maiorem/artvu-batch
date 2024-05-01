package com.artvu.batch.artdetail.application;

import com.artvu.batch.artdetail.presentation.KopisArtDetailResponse;
import com.artvu.batch.artlist.application.ArtListService;
import com.artvu.batch.common.constants.Constants;
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
public class ArtDetailItemReader implements ItemReader<List<KopisArtDetailResponse>> {

    private final ArtListService artService;

    @Value("${spring.open-api.secretKey}")
    private String secretKey;

    int count = 0;

    @Override
    public List<KopisArtDetailResponse> read() throws Exception {

        List<String> kopisArtIdList = artService.artIdList();
        List<KopisArtDetailResponse> detailList = new ArrayList<>();
        Gson gson = new Gson();


        ExchangeStrategies exchangeStrategies = ExchangeStrategies.builder()
                .codecs(clientCodecConfigurer -> clientCodecConfigurer.defaultCodecs()
                        .jaxb2Decoder(new Jaxb2XmlDecoder()))
                .build();

        WebClient webClient = WebClient.builder()
                .baseUrl(Constants.KOPIS_BASE_URL)
                .exchangeStrategies(exchangeStrategies)
                .build();

        for (String artId : kopisArtIdList) {
            Mono<String> response = webClient
                    .get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/pblprfr")
                            .path("/"+artId)
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
            KopisArtDetailResponse result = null;
            if (jsonResult.get("dbs") != null) {
                result = gson.fromJson(jsonResult.toString(), TypeToken.getParameterized(KopisArtDetailResponse.class).getType());
            }
            detailList.add(result);
        }

        count++;
        log.info("art DETAIL READER ============================================== ");

        return count > 1 ? null : detailList;
    }
}
