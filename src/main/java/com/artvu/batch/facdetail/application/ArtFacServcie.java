package com.artvu.batch.facdetail.application;

import com.artvu.batch.artdetail.domain.entity.KopisArtDetail;
import com.artvu.batch.facdetail.domain.entity.KopisFacDetail;
import com.artvu.batch.facdetail.infrastructure.repository.KopisArtFacDetailApiRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ArtFacServcie {

    private final KopisArtFacDetailApiRepository facDetailApiRepository;

    @Transactional
    public void saveDate(KopisFacDetail build) {
        facDetailApiRepository.saveAndFlush(build);
    }

    public List<String> artFacIdList() {
        List<KopisFacDetail> list = facDetailApiRepository.findAll();
        List<String> facIdList = new ArrayList<>();
        for (KopisFacDetail facDetail : list) {
            facIdList.add(facDetail.getArtFacId());
        }
        return facIdList;

    }
}
