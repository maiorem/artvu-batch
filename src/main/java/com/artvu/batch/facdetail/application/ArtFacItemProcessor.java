package com.artvu.batch.facdetail.application;

import com.artvu.batch.facdetail.domain.entity.KopisFacDetail;
import com.artvu.batch.facdetail.presentation.KopisFacDetailResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ArtFacItemProcessor implements ItemProcessor<List<KopisFacDetailResponse>, List<KopisFacDetail>> {


    @Autowired
    private ArtFacServcie artFacServcie;

    @Override
    public List<KopisFacDetail> process(List<KopisFacDetailResponse> items) throws Exception {

        List<String> artFacIdList = artFacServcie.artFacIdList();

        log.info("art facility Detail PROCESSOR ============================================== ");
        List<KopisFacDetail> detailList = new ArrayList<>();

        for (KopisFacDetailResponse response : items) {
            if(!artFacIdList.contains(response.getDbs().getDb().getMt10id())) {
                KopisFacDetail build = KopisFacDetail.builder()
                        .artFacId(response.getDbs().getDb().getMt10id())
                        .artFacNm(response.getDbs().getDb().getFcltynm())
                        .hallCnt(Integer.parseInt(response.getDbs().getDb().getMt13cnt()))
                        .artFacQuality(response.getDbs().getDb().getFcltychartr())
                        .openYy(String.valueOf(response.getDbs().getDb().getOpende()))
                        .seatCnt(Integer.parseInt(response.getDbs().getDb().getSeatscale()))
                        .telNo(response.getDbs().getDb().getTelno())
                        .siteUrl(response.getDbs().getDb().getRelateurl())
                        .artFacAddr(response.getDbs().getDb().getAdres())
                        .latNo(Float.parseFloat(response.getDbs().getDb().getLa()))
                        .lonNo(Float.parseFloat(response.getDbs().getDb().getLo()))
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
                        .build();

                log.info("fac info : {}", build.toString());

                artFacServcie.saveDate(build);
                detailList.add(build);
            }

        }

        return detailList;
    }
}
