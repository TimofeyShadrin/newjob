package ru.tshadrin.newjob.nonton.service;

import ru.tshadrin.newjob.nonton.domain.Product;

import java.util.List;
/*
* Контракт
 */
public interface DataService<T extends Product> {
    boolean addProduct(T product);
    boolean deleteProduct(T product);
    String getName(String id);
    List<String> findByName(String name);
}
