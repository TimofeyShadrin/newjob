package ru.tshadrin.newjob.nonton.service;

import ru.tshadrin.newjob.nonton.domain.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public abstract class AbstractStorage<T extends Product> {
    // Для варианта со списком, но с алгоритмом поиска продуктов O(n)
    protected final List<Product> repository;

    // Варианта с индексированием ссылок на сущности "Product"
    // с помощью словаря из SDK с красно-черным деревом в его структуре,
    // для поиска продуктов с алгоритмом стремящемся к O(1)
    protected final TreeMap<String, Product> storage;

    // Можно рассмотреть и более частный случай сделав словарь с ключами в виде id
    // и значениями name, но это будут уже не универсальные методы, ибо сущность "Product"
    // в перспективе может заиметь другие поля, такие как цена, дата создания и т.п.
    // protected final TreeMap<String, String> unique;

    protected AbstractStorage() {
        this.repository = new ArrayList<>();
        this.storage = new TreeMap<>();
    }
}
