package ru.tshadrin.newjob.nonton.service.impl;

import ru.tshadrin.newjob.nonton.domain.Product;
import ru.tshadrin.newjob.nonton.service.AbstractStorage;
import ru.tshadrin.newjob.nonton.service.DataService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public class ProductHashing extends AbstractStorage<Product> implements DataService<Product> {
    private final Logger logger = Logger.getLogger(ProductHashing.class.getSimpleName());

    public ProductHashing() {
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
        if (validate(product)) return false;
        if (storage.containsKey(product.getId())) {
            logger.warning("Продукт с id " + product.getId() + " уже существует");
            return false;
        }
        storage.put(product.getId(), product);
        return true;
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
        if (validate(product)) return false;
        if (storage.remove(product.getId()) == null) {
            logger.warning("Продукт с id " + product.getId() +
                    " не найден, поэтому не может быть удален");
            return false;
        }
        return true;
    }

    /**
     * Данный метод получает имя (name) продукта
     *
     * @param id - уникальный номер продукта
     * @return name продукта у которого идентификатор равен (=) id,
     * если продукта нет, возвращает пустую строку ""
     */
    @Override
    public String getName(String id) {
        if (id == null || id.isEmpty()) {
            logger.warning("Значение id не валидно");
            return "";
        }
        Product result = storage.get(id);
        if (result == null) {
            logger.info("Продукт с id " + id + " не найден");
            return "";
        }
        return result.getName();
    }

    /**
     * Данный метод получает список идентификаторов продуктов
     * по указанному его имени
     *
     * @param name - наименование продукта
     * @return массив (список) идентификаторов (id),
     * у которых наименование равно (=) name
     * Если таких нет, возвращается пустой массив (список)
     */
    @Override
    public List<String> findByName(String name) {
        if (name == null || name.isEmpty()) {
            logger.warning("Имя продукта не валидно");
            return Collections.emptyList();
        }
        List<String> result = new ArrayList<>();
        for (Product product : storage.values()) {
            if ((product.getName()).equals(name)) {
                result.add(product.getId());
            }
        }
        // если список пустой, то уничтожаем ссылку на объект возвращая Collections.emptyList(),
        // чтобы освободить кучу
        return (result.isEmpty()) ? Collections.emptyList() : result;
    }

    /**
     * Данный метод валидирует данные входящей сущности "Product"
     * @param product - продукт
     * @return true - если данные не валидны, а false - если да.
     */
    private boolean validate(Product product) {
        if ((product.getName() == null || product.getName().isEmpty())
                || (product.getId() == null || product.getId().isEmpty())) {
            logger.warning("Входные данные не валидны");
            return true;
        }
        return false;
    }
}
