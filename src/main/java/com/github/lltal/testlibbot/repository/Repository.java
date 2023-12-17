package com.github.lltal.testlibbot.repository;

import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface Repository<K, V> {

    Optional<V> find(K key);

    void save(V value);
}
