package com.artvu.batch.artlist.application;

import com.artvu.batch.artlist.domain.entity.KopisArtIntroImgList;
import com.artvu.batch.artlist.domain.entity.KopisArtList;
import com.artvu.batch.artlist.infrastructure.repository.KopisArtIntroImgListRepository;
import com.artvu.batch.artlist.infrastructure.repository.KopisArtListApiRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ArtListService {

    private final KopisArtListApiRepository listApiRepository;
    private final KopisArtIntroImgListRepository imgListRepository;

    public List<String> artIdList() {

        List<KopisArtList> artList = listApiRepository.findAll();
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
}
