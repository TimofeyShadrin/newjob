package ru.tshadrin.newjob.nonton.service;

import ru.tshadrin.newjob.nonton.domain.Product;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractStorage<T extends Product> {
    protected final Map<String, Product> repository;
    protected AbstractStorage() {
        this.repository = new HashMap<>();
    }
}
