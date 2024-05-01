package com.artvu.batch.artlist.application;

import com.artvu.batch.artdetail.domain.entity.KopisArtDetail;
import com.artvu.batch.artlist.domain.entity.KopisArtIntroImgList;
import com.artvu.batch.artlist.domain.entity.KopisArtList;
import com.artvu.batch.artlist.infrastructure.repository.KopisArtIntroImgListRepository;
import com.artvu.batch.artlist.infrastructure.repository.KopisArtListApiRepository;
import com.artvu.batch.artvu.domain.entity.ArtList;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArtListService {

    private final KopisArtListApiRepository listApiRepository;
    private final KopisArtIntroImgListRepository imgListRepository;

    public List<KopisArtList> findAllArtList() {
        return listApiRepository.findAll();
    }

    public List<String> artIdList() {

        List<KopisArtList> artList = findAllArtList();
        List<String> kopisArtIdList = new ArrayList<>();
        for (KopisArtList kopisArt : artList) {
            kopisArtIdList.add(kopisArt.getArtId());
        }
        return kopisArtIdList;
    }

    @Transactional
    public void ImgSave(KopisArtIntroImgList build) {
        imgListRepository.saveAndFlush(build);
    }

    public List<KopisArtIntroImgList> findAllByArtDetail(KopisArtDetail artDetail) {
        return imgListRepository.findAllByArtDetail(artDetail);
    }

}
