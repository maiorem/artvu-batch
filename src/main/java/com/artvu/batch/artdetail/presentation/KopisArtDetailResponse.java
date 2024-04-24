package com.artvu.batch.artdetail.presentation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class KopisArtDetailResponse {

    private Dbs dbs;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public class Dbs {

        private Db db;

        @Getter
        @NoArgsConstructor
        @AllArgsConstructor
        @ToString
        public static class Db {

            private String mt20id; //공연ID

            private String mt10id; //공연시설ID

            private String prfnm;//공연명

            private String prfpdfrom;//공연시작시간

            private String prfpdto;//공연종료시간

            private String fcltynm;//공연시설명

            private String prfcast;//공연출연진

            private String prfcrew;//공연제작진

            private String prfruntime;//런타임

            private String prfage; //공연관람연령

            private String entrpsnmP;//제작사

            private String entrpsnmA;//기획사

            private String entrpsnmH;//주최

            private String entrpsnmS;//주관

            private String pcseguidance; //가격

            private String poster;//포스터

            private String sty; //줄거리

            private String genrenm;//장르

            private String prfstate;//공연상태

            private String openrun; //오픈런여부

            private String visit;//내한

            private String child;//아동

            private String daehakro;//대학로

            private String festival;//축제

            private String musicallicense;//뮤지컬 라이센스

            private String musicalcreate;//뮤지컬 창작

            private String updatedate;//최종수정일

            private String dtguidance; //공연시각

            private Styurls styurls; //공연소개이미지목록

            @Getter
            @ToString
            @NoArgsConstructor
            @AllArgsConstructor
            public static class Styurls<T> {
                private T styurl;
            }

        }
    }
}
