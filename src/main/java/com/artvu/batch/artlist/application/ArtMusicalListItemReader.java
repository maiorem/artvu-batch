package com.artvu.batch.artlist.application;

import com.artvu.batch.artlist.presentation.KopisArtListResponse;
import com.artvu.batch.common.constants.Constants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
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

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Slf4j
@RequiredArgsConstructor
public class ArtMusicalListItemReader implements ItemReader<KopisArtListResponse> {


    @Value("${spring.open-api.secretKey}")
    private String secretKey;

    int count = 0;
    int cpage = 1;

    @Override
    public KopisArtListResponse read() throws JsonProcessingException {

        String current_page = String.valueOf(cpage++);
        String ROWS = "200";

        LocalDateTime performStrDt = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
        LocalDateTime performEndDt = performStrDt.plusMonths(1);
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyyMMdd");

        String strDt = performStrDt.format(dateFormat);
        String endDt = performEndDt.format(dateFormat);

        Gson gson = new Gson();

        ExchangeStrategies exchangeStrategies = ExchangeStrategies.builder()
                .codecs(clientCodecConfigurer -> clientCodecConfigurer.defaultCodecs()
                        .jaxb2Decoder(new Jaxb2XmlDecoder()))
                .build();

        WebClient webClient = WebClient.builder()
                .baseUrl(Constants.KOPIS_BASE_URL)
                .exchangeStrategies(exchangeStrategies)
                .build();

        Mono<String> response = webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/pblprfr")
                        .queryParam("service", secretKey)
                        .queryParam("stdate", strDt)
                        .queryParam("eddate", endDt)
                        .queryParam("cpage", current_page)
                        .queryParam("rows", ROWS)
                        .queryParam("shcate", "GGGA")
                        .queryParam("prstate", "02")
                        .queryParam("newsql", "Y")
                        .build())
                .accept(MediaType.APPLICATION_XML)
                .retrieve()
                .bodyToMono(String.class)
                .log();

        String xmlResult = response.block();
        JSONObject jsonResult = XML.toJSONObject(xmlResult);
        KopisArtListResponse result = gson.fromJson(jsonResult.toString(), KopisArtListResponse.class);

        count++;
        log.info("art musical LIST READER ============================================== ");
        log.info("art musical list info ::"+result.toString());

        return count > 1 ? null : result;
    }

}
