package com.artvu.batch.artdetail.application;

import com.artvu.batch.artdetail.presentation.KopisArtDetailResponse;
import com.artvu.batch.artdetail.domain.entity.KopisArtDetail;
import com.artvu.batch.artlist.application.ArtListService;
import com.artvu.batch.artlist.domain.entity.KopisArtIntroImgList;
import com.artvu.batch.artlist.infrastructure.repository.KopisArtIntroImgListRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class ArtDetailItemProcessor  implements ItemProcessor<List<KopisArtDetailResponse>, List<KopisArtDetail>> {

    @Autowired
    private ArtListService artListService;

    @Autowired
    private ArtDetailService detailService;

    @Override
    public List<KopisArtDetail> process(List<KopisArtDetailResponse> items) throws Exception {

        log.info("art DETAIL PROCESSOR ============================================== ");

        List<String> artIdList = detailService.artIdList();

        List<KopisArtDetail> detailList = new ArrayList<>();

        for (KopisArtDetailResponse response : items) {
            if (response != null) {
                if (!artIdList.contains(response.getDbs().getDb().getMt20id())) {

                    String posterUrl = ImageProcessor.downloader(response.getDbs().getDb().getPoster(), ImageType.POSTER);

                    KopisArtDetail build = KopisArtDetail.builder()
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
                            .price(response.getDbs().getDb().getPcseguidance())
                            .posterImgUrl(posterUrl)
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
                            .build();

                    log.info("detail info : {}", build.toString());
                    detailService.saveData(build);

                    // 소개 이미지 저장 ( 단건 or 다건 )
                    Object ob = response.getDbs().getDb().getStyurls().getStyurl();
                    String introImages = String.valueOf(ob);
                    introImages = introImages.replaceAll("\\[", "").replaceAll("\\]", "");
                    String[] introArray = introImages.split(",");
                    for (String image : introArray) {
                        String intro = ImageProcessor.downloader(image, ImageType.INTRO);
                        KopisArtIntroImgList img = KopisArtIntroImgList.builder()
                                .artDetail(build)
                                .introductImgUrl(intro)
                                .build();
                        artListService.ImgSave(img);
                    }
                    detailList.add(build);

                }

            }
        }
        return detailList;

    }
}
