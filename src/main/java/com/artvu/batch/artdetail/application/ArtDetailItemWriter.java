package com.artvu.batch.artdetail.application;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.database.JpaItemWriter;

import java.util.List;

@Slf4j
public class ArtDetailItemWriter<T> extends JpaItemWriter<List<T>> {

    private final JpaItemWriter<T> jpaItemWriter;

    public ArtDetailItemWriter(JpaItemWriter<T> jpaItemWriter) {
        this.jpaItemWriter = jpaItemWriter;
    }

    @Override
    public void write(Chunk<? extends List<T>> items) {
        log.info("art DETAIL WRITER ================================================");

        Chunk<T> collect = new Chunk<T>();
        for(List<T> list : items){
            collect.addAll(list);
        }
        jpaItemWriter.write(collect);
    }

    @Override
    public void afterPropertiesSet()  {

    }

}
