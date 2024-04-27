package com.artvu.batch.artvu.application;

import com.artvu.batch.artvu.domain.entity.*;
import com.artvu.batch.artvu.infrastructure.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ArtvuArtService {

    private final ArtListRepository artListRepository;
    private final ArtDetailRepository artDetailRepository;
    private final ArtFacDetailRepository artFacDetailRepository;
    private final ArtImgRepository imgRepository;
    private final ArtTimeRepository timeRepository;
    private final ArtAreaRepository areaRepository;


    public List<ArtList> findAllArtList(){
        return artListRepository.findAll();
    }

    public List<ArtDetail> findAllArtDetail() {
        return artDetailRepository.findAll();
    }

    public List<ArtFacDetail> findAllArtFacDetail(){
        return artFacDetailRepository.findAll();
    }


    public List<String> findAllArtId() {
        List<ArtList> list = findAllArtList();
        List<String> artIdList = new ArrayList<>();
        for (ArtList artList : list) {
            artIdList.add(artList.getArtId());
        }
        return artIdList;
    }

    public List<String> findArtDetailAllArtId() {
        List<ArtDetail> list = findAllArtDetail();
        List<String> artIdList = new ArrayList<>();
        for (ArtDetail artDetail : list) {
            artIdList.add(artDetail.getArtId());
        }
        return artIdList;
    }

    public List<String> findArtFacilityAllFacId() {
        List<ArtFacDetail> list = findAllArtFacDetail();
        List<String> artIdList = new ArrayList<>();
        for (ArtFacDetail artDetail : list) {
            artIdList.add(artDetail.getArtFacId());
        }
        return artIdList;
    }

    public void facSave(ArtFacDetail build) {
        artFacDetailRepository.saveAndFlush(build);
    }

    public void detailSave(ArtDetail build){
        artDetailRepository.saveAndFlush(build);
    }

    public void listSave(ArtList build) {
        artListRepository.saveAndFlush(build);
    }

    public void imgSave(ArtImg build) {
        imgRepository.saveAndFlush(build);
    }

    public void timeSave(ArtTime build) {
        timeRepository.saveAndFlush(build);
    }

    public ArtArea findAreaByNm(String areaNm) {
        return areaRepository.findByAreaNm(areaNm);
    }

    public ArtFacDetail findByArtFacId(String facId) {
        return artFacDetailRepository.findByArtFacId(facId);
    }

}
