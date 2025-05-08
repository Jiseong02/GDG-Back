package com.gdg.gdgback.Diary;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class DiaryTestRepository implements DiaryRepository {
    DiaryDocument testDocument;

    DiaryTestRepository() {
        testDocument = DiaryDocument.builder()
                .id("test")
                .counselId("test")
                .userId("test")
                .score(0)
                .date(LocalDateTime.now())
                .imageUrl(null)
                .expected(null)
                .category(null)
                .title("test")
                .content("test")
                .build();
    }

    @Override
    public List<DiaryDocument> findAllByUserId(String id) {
        if (!id.equals("test")) throw new NullPointerException();
        return List.of(testDocument);
    }

    @Override
    public <S extends DiaryDocument> S insert(S entity) {
        return null;
    }

    @Override
    public <S extends DiaryDocument> List<S> insert(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public <S extends DiaryDocument> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends DiaryDocument> List<S> findAll(Example<S> example) {
        return List.of();
    }

    @Override
    public <S extends DiaryDocument> List<S> findAll(Example<S> example, Sort sort) {
        return List.of();
    }

    @Override
    public <S extends DiaryDocument> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends DiaryDocument> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends DiaryDocument> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends DiaryDocument, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends DiaryDocument> S save(S entity) {
        entity.id = "test";
        return entity;
    }

    @Override
    public <S extends DiaryDocument> List<S> saveAll(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public Optional<DiaryDocument> findById(String s) {
        if (!s.equals(testDocument.id)) throw new NullPointerException();
        return Optional.of(testDocument);
    }

    @Override
    public boolean existsById(String s) {
        return s.equals(testDocument.id);
    }

    @Override
    public List<DiaryDocument> findAll() {
        return List.of();
    }

    @Override
    public List<DiaryDocument> findAllById(Iterable<String> strings) {
        return List.of();
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(String s) {
        if (!s.equals(testDocument.id)) throw new NullPointerException();
    }

    @Override
    public void delete(DiaryDocument entity) {
        if (!entity.equals(testDocument)) throw new NullPointerException();
    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {

    }

    @Override
    public void deleteAll(Iterable<? extends DiaryDocument> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<DiaryDocument> findAll(Sort sort) {
        return List.of();
    }

    @Override
    public Page<DiaryDocument> findAll(Pageable pageable) {
        return null;
    }
}
