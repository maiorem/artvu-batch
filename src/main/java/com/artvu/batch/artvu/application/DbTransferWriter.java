package com.artvu.batch.artvu.application;

import com.artvu.batch.artvu.domain.entity.ArtList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.util.Assert;

@Slf4j
public class DbTransferWriter<T> extends JpaItemWriter<ArtList> {

    private JpaItemWriter<ArtList> jpaItemWriter;

    public DbTransferWriter(JpaItemWriter<ArtList> jpaItemWriter) {
        this.jpaItemWriter = jpaItemWriter;
    }

    @Override
    public void write(Chunk<? extends ArtList> items) {

        Chunk<ArtList> collect = new Chunk<>();
        for (ArtList item : items) {
            collect.add(item);
        }
        jpaItemWriter.write(collect);

    }


    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(jpaItemWriter, "For db transfer, An entity manager is required");
    }

}
