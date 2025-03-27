package com.gdg.gdgback.Counsel;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class CounselTestRepository implements CounselRepository{
    private CounselDocument testDocument;

    CounselTestRepository() {
        this.testDocument = CounselDocument.builder()
                .id("test")
                .userId("test")
                .startTime(LocalDateTime.now())
                .endTime(LocalDateTime.now())
                .summation("test")
                .build();
    }

    @Override
    public List<CounselDocument> findAllByUserId(String id) {
        if(!id.equals(testDocument.userId)) throw new NullPointerException();
        return List.of(testDocument);
    }

    @Override
    public <S extends CounselDocument> S insert(S entity) {
        return null;
    }

    @Override
    public <S extends CounselDocument> List<S> insert(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public <S extends CounselDocument> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends CounselDocument> List<S> findAll(Example<S> example) {
        return List.of();
    }

    @Override
    public <S extends CounselDocument> List<S> findAll(Example<S> example, Sort sort) {
        return List.of();
    }

    @Override
    public <S extends CounselDocument> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends CounselDocument> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends CounselDocument> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends CounselDocument, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends CounselDocument> S save(S entity) {
        return entity;
    }

    @Override
    public <S extends CounselDocument> List<S> saveAll(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public Optional<CounselDocument> findById(String s) {
        if (s.equals(testDocument.id)) return Optional.of(testDocument);
        return Optional.empty();
    }

    @Override
    public boolean existsById(String s) {
        return s.equals(testDocument.id);
    }

    @Override
    public List<CounselDocument> findAll() {
        return List.of();
    }

    @Override
    public List<CounselDocument> findAllById(Iterable<String> strings) {
        return List.of();
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(String s) {
        if (!s.equals(testDocument.id)) throw new RuntimeException();
    }

    @Override
    public void delete(CounselDocument entity) {
    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {

    }

    @Override
    public void deleteAll(Iterable<? extends CounselDocument> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<CounselDocument> findAll(Sort sort) {
        return List.of();
    }

    @Override
    public Page<CounselDocument> findAll(Pageable pageable) {
        return null;
    }
}
