package com.artvu.batch.artdetail.application;

import com.artvu.batch.artdetail.presentation.KopisArtDetailResponse;
import com.artvu.batch.artdetail.domain.entity.KopisArtDetail;
import com.artvu.batch.artlist.domain.entity.KopisArtIntroImgList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ArtDetailItemProcessor  implements ItemProcessor<List<KopisArtDetailResponse>, List<KopisArtDetail>> {
    @Override
    public List<KopisArtDetail> process(List<KopisArtDetailResponse> items) throws Exception {

        log.info("art DETAIL PROCESSOR ============================================== ");
        LocalDateTime regdt = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
        List<KopisArtDetail> detailList = new ArrayList<>();
        List<KopisArtIntroImgList> imgListList = new ArrayList<>();
        for (KopisArtDetailResponse response : items) {
            List<KopisArtDetailResponse.Dbs.Db.Styurls> urls = response.getDbs().getDb().getStyurls();

            for (KopisArtDetailResponse.Dbs.Db.Styurls url : urls) {
                imgListList.stream().map(img -> KopisArtIntroImgList.builder()
                        .artId(response.getDbs().getDb().getMt20id())
                        .introductImgUrl(url.getStyurl())
                        .build()
                );
            }

            detailList.stream().map(detail -> KopisArtDetail.builder()
                    .artId(response.getDbs().getDb().getMt20id())
                    .artFacId(response.getDbs().getDb().getMt10id())
                    .artNm(response.getDbs().getDb().getPrfnm())
                    .artStrDt(response.getDbs().getDb().getPrfpdfrom())
                    .artEndDt(response.getDbs().getDb().getPrfpdto())
                    .artActors(response.getDbs().getDb().getPrfcast())
                    .artStaff(response.getDbs().getDb().getPrfcrew())
                    .artFacNm(response.getDbs().getDb().getFcltynm())
                    .artRuntime(response.getDbs().getDb().getPrfruntime())
                    .artShowAge(response.getDbs().getDb().getPrfage())
                    .prodCompNm(response.getDbs().getDb().getEntrpsnmP())
                    .agencyNm(response.getDbs().getDb().getEntrpsnmA())
                    .sponsorNm(response.getDbs().getDb().getEntrpsnmH())
                    .organizerNm(response.getDbs().getDb().getEntrpsnmS())
                    .price(Integer.parseInt(response.getDbs().getDb().getPcseguidance()))
                    .posterImgUrl(response.getDbs().getDb().getPoster())
                    .summary(response.getDbs().getDb().getSty())
                    .genreNm(response.getDbs().getDb().getGenrenm())
                    .status(response.getDbs().getDb().getPrfstate())
                    .openrunYn(response.getDbs().getDb().getOpenrun())
                    .visitKorYn(response.getDbs().getDb().getVisit())
                    .childYn(response.getDbs().getDb().getChild())
                    .daehakroYn(response.getDbs().getDb().getDaehakro())
                    .festivalYn(response.getDbs().getDb().getFestival())
                    .musicalLicenseYn(response.getDbs().getDb().getMusicallicense())
                    .musicalCreateYn(response.getDbs().getDb().getMusicalcreate())
                    .lastModDt(response.getDbs().getDb().getUpdatedate())
                    .artTime(response.getDbs().getDb().getDtguidance())
                    .regDt(regdt)
                    .introImgListList(imgListList)
                    .build()
            );

        }

        return detailList;

    }
}
