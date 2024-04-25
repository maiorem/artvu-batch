package com.artvu.batch.artdetail.application;

import com.artvu.batch.artdetail.domain.entity.KopisArtDetail;
import com.artvu.batch.artdetail.infrastructure.repository.KopisArtDetailApiRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ArtDetailService {

    private final KopisArtDetailApiRepository detailApiRepository;

    public List<String> artFacIdList() {
        List<KopisArtDetail> list = detailApiRepository.findAll();
        List<String> facIdList = new ArrayList<>();
        for (KopisArtDetail kopisArtDetail : list) {
            facIdList.add(kopisArtDetail.getArtFacId());
        }
        return facIdList;
    }
}
