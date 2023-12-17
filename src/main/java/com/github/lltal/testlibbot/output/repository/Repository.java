package com.github.lltal.testlibbot.output.repository;

import org.springframework.stereotype.Component;

@Component
public interface Repository <K, V>{
    V save(K key, V value);

    V get(K key);
}
