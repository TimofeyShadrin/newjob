package ru.tshadrin.newjob.nonton.service.impl;

import ru.tshadrin.newjob.nonton.domain.Product;
import ru.tshadrin.newjob.nonton.service.AbstractStorage;
import ru.tshadrin.newjob.nonton.service.DataService;

import java.util.List;

public class ProductService extends AbstractStorage<Product> implements DataService<Product> {
    public ProductService() {
        super();
    }

    /**
     * Данный метод добавляет новый продукт
     *
     * @param product - новый продукт
     * @return true - если продукта с таким id еще не было
     * false - если был такой id, вставка отменяется
     */
    @Override
    public boolean addProduct(Product product) {
        return false;
    }

    /**
     * Данный метод удаляет продукт
     *
     * @param product - удаляемый продукт
     * @return true - если продукт с таким id был
     * false - если id не было, (удаления не происходит)
     */
    @Override
    public boolean deleteProduct(Product product) {
        return false;
    }

    /**
     * Данный метод получает имя (name) продукта
     * @param id - уникальный номер продукта
     * @return name продукта у которого идентификатор равен (=) id,
     * если продукта нет, возвращает пустую строку ""
     */
    @Override
    public String getName(String id) {
        return null;
    }

    /**
      * @param name - наименование продукта
     * @return массив (список) идентификаторов (id),
     * у которых наименование равно (=) name
     * Если таких нет, возвращается пустой массив (список)
     */
    @Override
    public List<String> findByName(String name) {
        return null;
    }
}
