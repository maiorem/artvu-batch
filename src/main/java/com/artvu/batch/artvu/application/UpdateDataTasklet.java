package com.artvu.batch.artvu.application;

import com.artvu.batch.artvu.domain.entity.ArtList;
import com.artvu.batch.artvu.domain.entity.ArtTime;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class UpdateDataTasklet implements Tasklet {

    private final ArtvuArtService artService;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

        LocalDate today = LocalDate.now(ZoneId.of("Asia/Seoul"));
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        List<ArtList> allArtList = artService.findAllArtList();
        for (ArtList artList : allArtList) {
            Optional<ArtTime> time = artService.findByArtlist(artList);
            if (time.isPresent()) {
                if (today.isAfter(LocalDate.parse(time.get().getArtStrDt(), dateFormat))) {
                    artList.setStatus("공연중");
                }
                if (today.isAfter(LocalDate.parse(time.get().getArtEndDt(), dateFormat))) {
                    artList.setStatus("공연종료");
                }
            }
        }

        return null;
    }
}
