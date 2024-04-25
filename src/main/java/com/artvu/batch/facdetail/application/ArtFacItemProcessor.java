package com.artvu.batch.facdetail.application;

import com.artvu.batch.facdetail.domain.entity.KopisFacDetail;
import com.artvu.batch.facdetail.presentation.KopisFacDetailResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ArtFacItemProcessor implements ItemProcessor<List<KopisFacDetailResponse>, List<KopisFacDetail>> {

    @Override
    public List<KopisFacDetail> process(List<KopisFacDetailResponse> items) throws Exception {

        log.info("art facility Detail PROCESSOR ============================================== ");
        LocalDateTime regdt = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
        List<KopisFacDetail> detailList = new ArrayList<>();
        for (KopisFacDetailResponse response : items) {

            detailList.stream().map(detail -> KopisFacDetail.builder()
                    .artFacId(response.getDbs().getDb().getMt10id())
                    .artFacNm(response.getDbs().getDb().getFcltynm())
                    .hallCnt(response.getDbs().getDb().getMt13cnt())
                    .artFacQuality(response.getDbs().getDb().getFcltychartr())
                    .openYy(String.valueOf(response.getDbs().getDb().getOpende()))
                    .seatCnt(response.getDbs().getDb().getSeatscale())
                    .telNo(response.getDbs().getDb().getTelno())
                    .siteUrl(response.getDbs().getDb().getRelateurl())
                    .artFacAddr(response.getDbs().getDb().getAdres())
                    .latNo(response.getDbs().getDb().getLa())
                    .lonNo(response.getDbs().getDb().getLo())
                    .restrauntYn(response.getDbs().getDb().getRestaurant())
                    .cafeYn(response.getDbs().getDb().getCafe())
                    .store24Yn(response.getDbs().getDb().getStore())
                    .playroomYn(response.getDbs().getDb().getNolibang())
                    .babyCareYn(response.getDbs().getDb().getSuyu())
                    .disPersonParkingYn(response.getDbs().getDb().getParkbarrier())
                    .disPersonRestrmYn(response.getDbs().getDb().getRestbarrier())
                    .disPersonRunwayYn(response.getDbs().getDb().getRunwbarrier())
                    .disPersonElevatorYn(response.getDbs().getDb().getElevbarrier())
                    .parkingYn(response.getDbs().getDb().getParkinglot())
                    .regDt(regdt)
                    .build()
            );

        }

        return detailList;
    }
}
