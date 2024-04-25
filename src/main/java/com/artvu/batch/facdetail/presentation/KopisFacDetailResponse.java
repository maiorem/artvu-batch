package com.artvu.batch.facdetail.presentation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class KopisFacDetailResponse {

    private Dbs dbs;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public static class Dbs {

        private Db db;

        @Getter
        @NoArgsConstructor
        @AllArgsConstructor
        @ToString
        public static class Db {

            private String mt10id; //공연시설ID

            private String fcltynm;//공연시설명

            private int opende;//개관연도

            private String fcltychartr;//시설특성

            private int seatscale;//좌석수

            private int mt13cnt;//공연장 수

            private String telno;//전화번호

            private String relateurl;//홈페이지

            private String adres;//주소

            private Float la;//위도

            private Float lo;//경도

            private String restaurant;//레스토랑유무

            private String cafe;//카페유무

            private String store;//편의점유무

            private String nolibang;//놀이방 유무

            private String suyu;//수유실 유무

            private String parkbarrier;//장애인 주차시설

            private String restbarrier;//장애인 화장실

            private String runwbarrier;//장애인 경사로

            private String elevbarrier;//장애인 엘레베이터

            private String parkinglot;//주차시설

        }
    }
}
