package com.gdg.gdgback.User;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class UserTestRepository implements UserRepository {
    private UserDocument testDocument;

    UserTestRepository() {
        this.testDocument = UserDocument.builder()
                .id("test")
                .name("test")
                .date(LocalDateTime.now())
                .build();
    }

    @Override
    public <S extends UserDocument> S insert(S entity) {
        return null;
    }

    @Override
    public <S extends UserDocument> List<S> insert(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public <S extends UserDocument> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends UserDocument> List<S> findAll(Example<S> example) {
        return List.of();
    }

    @Override
    public <S extends UserDocument> List<S> findAll(Example<S> example, Sort sort) {
        return List.of();
    }

    @Override
    public <S extends UserDocument> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends UserDocument> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends UserDocument> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends UserDocument, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends UserDocument> S save(S entity) {
        return entity;
    }

    @Override
    public <S extends UserDocument> List<S> saveAll(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public Optional<UserDocument> findById(String s) {
        if (s.equals(testDocument.id)) return Optional.of(testDocument);
        return Optional.empty();
    }

    @Override
    public boolean existsById(String s) {
        return s.equals(testDocument.id);
    }

    @Override
    public List<UserDocument> findAll() {
        return List.of();
    }

    @Override
    public List<UserDocument> findAllById(Iterable<String> strings) {
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
    public void delete(UserDocument entity) {
        if (!entity.equals(testDocument)) throw new NullPointerException();
    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {

    }

    @Override
    public void deleteAll(Iterable<? extends UserDocument> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<UserDocument> findAll(Sort sort) {
        return List.of(testDocument);
    }

    @Override
    public Page<UserDocument> findAll(Pageable pageable) {
        return null;
    }
}
