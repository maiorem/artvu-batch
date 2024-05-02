package com.artvu.batch.artvu.application;

import com.artvu.batch.artdetail.application.ArtDetailService;
import com.artvu.batch.artdetail.domain.entity.KopisArtDetail;
import com.artvu.batch.artlist.application.ArtListService;
import com.artvu.batch.artlist.domain.entity.KopisArtIntroImgList;
import com.artvu.batch.artlist.domain.entity.KopisArtList;
import com.artvu.batch.artvu.domain.entity.*;
import com.artvu.batch.bestprice.application.CrawlDataService;
import com.artvu.batch.bestprice.domain.entity.KopisCrawlArtCont;
import com.artvu.batch.facdetail.application.ArtFacServcie;
import com.artvu.batch.facdetail.domain.entity.KopisFacDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class DbTransferProcessor implements ItemProcessor<KopisArtList, ArtList> {

    @Autowired
    private ArtListService artListService;

    @Autowired
    private ArtDetailService artDetailService;

    @Autowired
    private ArtFacServcie facServcie;

    @Autowired
    private ArtvuArtService artvuArtService;

    @Autowired
    private CrawlDataService crawlDataService;

    @Override
    public ArtList process(KopisArtList item) throws Exception {
        KopisArtDetail detail = artDetailService.findByArtId(item.getArtId());
        List<KopisArtIntroImgList> imgList = artListService.findAllByArtDetail(detail);
        KopisFacDetail facility = facServcie.findByArtFacId(detail.getArtFacId());
        Optional<KopisCrawlArtCont> crawlDataOptinal = crawlDataService.findByArtNm(item.getArtNm());

        List<String> allArtListArtId = artvuArtService.findAllArtId();
        List<String> artDetailAllArtId = artvuArtService.findArtDetailAllArtId();
        List<String> artFacilityAllFacId = artvuArtService.findArtFacilityAllFacId();

        int orgPrice = 0;
        if ( !detail.getPrice().equals("전석무료") && !detail.getPrice().isBlank() && !detail.getPrice().isEmpty() && detail.getPrice() != null) {
            String firstPrice = detail.getPrice().split(" ")[1].replace(",","").replace("원", "");
            if (isNumberic(firstPrice)) {
                orgPrice = Integer.parseInt(firstPrice);
            }
        }


        int minPrice = orgPrice;
        LocalDateTime minPriceRegDt = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
        if (crawlDataOptinal.isPresent()) {
            minPrice = crawlDataOptinal.get().getArtMinPrice();
            minPriceRegDt = crawlDataOptinal.get().getRegDt();
        }


        ArtFacDetail facDetail = null;
        ArtList artList = null;
        ArtDetail artDetail = null;
        if(!artFacilityAllFacId.contains(facility.getArtFacId())) {
            facDetail = ArtFacDetail.builder()
                    .artFacId(facility.getArtFacId())
                    .artFacNm(facility.getArtFacNm())
                    .cityNm(facility.getArtFacAddr().split(" ")[0])
                    .countyNm(facility.getArtFacAddr().split(" ")[1])
                    .hallCnt(facility.getHallCnt())
                    .artFacQuality(facility.getArtFacQuality())
                    .openYy(facility.getOpenYy())
                    .seatCnt(facility.getSeatCnt())
                    .telNo(facility.getTelNo())
                    .siteUrl(facility.getSiteUrl())
                    .artFacAddr(facility.getArtFacAddr())
                    .latNo(facility.getLatNo())
                    .lonNo(facility.getLonNo())
                    .restrauntYn(facility.getRestrauntYn())
                    .cafeYn(facility.getCafeYn())
                    .store24Yn(facility.getStore24Yn())
                    .playroomYn(facility.getPlayroomYn())
                    .babyCareYn(facility.getBabyCareYn())
                    .disPersonParkingYn(facility.getDisPersonParkingYn())
                    .disPersonRestrmYn(facility.getDisPersonRestrmYn())
                    .disPersonRunwayYn(facility.getDisPersonRunwayYn())
                    .disPersonElevatorYn(facility.getDisPersonElevatorYn())
                    .parkingYn(facility.getParkingYn())
                    .build();
            artvuArtService.facSave(facDetail);
        } else {
            facDetail = artvuArtService.findByArtFacId(facility.getArtFacId());
        }

        if(!artDetailAllArtId.contains(detail.getArtId())) {
            artDetail = ArtDetail.builder()
                    .artId(item.getArtId())
                    .artStaff(detail.getArtStaff())
                    .artActor(detail.getArtActors())
                    .artRuntime(detail.getArtRuntime())
                    .prodCompNm(detail.getProdCompNm())
                    .agencyNm(detail.getAgencyNm())
                    .sponsorNm(detail.getSponsorNm())
                    .organizerNm(detail.getOrganizerNm())
                    .summary(detail.getSummary())
                    .intermisMi(0)
                    .openrunYn(detail.getOpenrunYn())
                    .visitKorYn(detail.getVisitKorYn())
                    .childYn(detail.getChildYn())
                    .daehakroYn(detail.getDaehakroYn())
                    .festivalYn(detail.getFestivalYn())
                    .musicalLicenseYn(detail.getMusicalLicenseYn())
                    .musicalCreateYn(detail.getMusicalCreateYn())
                    .lastModDt(detail.getLastModDt())
                    .build();
            artvuArtService.detailSave(artDetail);
        }

        if(!allArtListArtId.contains(item.getArtId())) {
            artList = ArtList.builder()
                    .artId(item.getArtId())
                    .artNm(item.getArtNm())
                    .artFacId(facDetail)
                    .orgPrice(orgPrice)
                    .minPrice(minPrice)
                    .minPriceRegDt(minPriceRegDt)
                    .status(item.getStatus())
                    .artShowAge(detail.getArtShowAge())
                    .areaCode(artvuArtService.findAreaByNm(facility.getArtFacAddr().split(" ")[0]) == null ? artvuArtService.findAreaByNm("강원도") : artvuArtService.findAreaByNm(facility.getArtFacAddr().split(" ")[0])  )
                    .build();
            artvuArtService.listSave(artList);

            artvuArtService.imgSave(ArtImg.builder()
                    .artList(artList)
                    .clsCode(ClsCode.KOPIS)
                    .imgUrl(detail.getPosterImgUrl())
                    .build()
            );

            for (KopisArtIntroImgList kopisImg : imgList) {
                artvuArtService.imgSave(ArtImg.builder()
                        .artList(artList)
                        .clsCode(ClsCode.INTRO)
                        .imgUrl(kopisImg.getIntroductImgUrl())
                        .build());
            }

            artvuArtService.timeSave(
                    ArtTime.builder()
                            .artlist(artList)
                            .artStrDt(detail.getArtStrDt())
                            .artEndDt(detail.getArtEndDt())
                            .artTime(detail.getArtTime())
                            .regDt(LocalDate.now(ZoneId.of("Asia/Seoul")))
                            .build()
            );

        } else {
            LocalDate today = LocalDate.now(ZoneId.of("Asia/Seoul"));
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy.MM.dd");

            if (today.isAfter(LocalDate.parse(item.getArtStrDt(), dateFormat) )) {
                Optional<ArtList> art = artvuArtService.findByArtId(item.getArtId());
                art.ifPresent(artItem -> artItem.setStatus("공연중"));
            }

            if (today.isAfter(LocalDate.parse(item.getArtEndDt(), dateFormat) )) {
                Optional<ArtList> art = artvuArtService.findByArtId(item.getArtId());
                art.ifPresent(artItem -> artItem.setStatus("공연종료"));
            }
        }




        return artList;
    }


    private boolean isNumberic(String str) {
        return str.chars().allMatch(Character::isDigit);
    }
}
