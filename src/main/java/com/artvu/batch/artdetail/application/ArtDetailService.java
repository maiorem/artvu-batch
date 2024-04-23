package com.artvu.batch.artdetail.application;

import com.artvu.batch.artlist.domain.entity.KopisArtList;
import com.artvu.batch.artlist.infrastructure.repository.KopisArtListApiRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ArtDetailService {

    private final KopisArtListApiRepository listApiRepository;

    public List<String> artIdList() {

        List<KopisArtList> artList = listApiRepository.findAll();
        List<String> kopisArtIdList = new ArrayList<>();
        for (KopisArtList kopisArt : artList) {
            kopisArtIdList.add(kopisArt.getArtId());
        }
        return kopisArtIdList;
    }
}
