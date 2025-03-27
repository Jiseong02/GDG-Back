package com.gdg.gdgback.Message;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class MessageTestRepository implements MessageRepository {
    MessageDocument testDocument;

    MessageTestRepository() {
        testDocument = MessageDocument.builder()
                .id("test")
                .counselId("test")
                .role("test")
                .content("test")
                .date(LocalDateTime.now())
                .build();
    }

    @Override
    public List<MessageDocument> findAllByCounselId(String id) {
        if(!id.equals(testDocument.counselId)) throw new NullPointerException();
        return List.of(testDocument);
    }

    @Override
    public <S extends MessageDocument> S insert(S entity) {
        return null;
    }

    @Override
    public <S extends MessageDocument> List<S> insert(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public <S extends MessageDocument> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends MessageDocument> List<S> findAll(Example<S> example) {
        return List.of();
    }

    @Override
    public <S extends MessageDocument> List<S> findAll(Example<S> example, Sort sort) {
        return List.of();
    }

    @Override
    public <S extends MessageDocument> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends MessageDocument> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends MessageDocument> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends MessageDocument, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends MessageDocument> S save(S entity) {
        return entity;
    }

    @Override
    public <S extends MessageDocument> List<S> saveAll(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public Optional<MessageDocument> findById(String s) {
        if(!s.equals(testDocument.id)) throw new NullPointerException();
        return Optional.of(testDocument);
    }

    @Override
    public boolean existsById(String s) {
        return s.equals(testDocument.id);
    }

    @Override
    public List<MessageDocument> findAll() {
        return List.of(testDocument);
    }

    @Override
    public List<MessageDocument> findAllById(Iterable<String> strings) {
        return List.of();
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(String s) {
        if(!s.equals(testDocument.id)) throw new NullPointerException();
    }

    @Override
    public void delete(MessageDocument entity) {
        if(!entity.equals(testDocument)) throw new NullPointerException();
    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {

    }

    @Override
    public void deleteAll(Iterable<? extends MessageDocument> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<MessageDocument> findAll(Sort sort) {
        return List.of();
    }

    @Override
    public Page<MessageDocument> findAll(Pageable pageable) {
        return null;
    }
}
