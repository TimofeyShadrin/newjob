package ru.tshadrin.newjob.nonton.service.impl;

import ru.tshadrin.newjob.nonton.domain.Product;
import ru.tshadrin.newjob.nonton.service.AbstractStorage;
import ru.tshadrin.newjob.nonton.service.DataService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public class ClassicService extends AbstractStorage<Product> implements DataService<Product> {
    private final Logger logger = Logger.getLogger(ClassicService.class.getSimpleName());

    public ClassicService() {
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
        if ((product.getName() == null || product.getName().isEmpty())
                || (product.getId() == null || product.getId().isEmpty())) {
            logger.warning("Входные данные не валидны");
            return false;
        }
        for (Product item : repository) {
            if ((item.getId()).equals(product.getId())) {
                logger.warning("Продукт с id " + product.getId() + " уже существует");
                return false;
            }
        }
        return repository.add(product);
    }

    /**
     * Данный метод удаляет продукт
     *
     * @param product - удаляемый продукт
     * @return true - если продукт с таким id был
     * false - если id не было, (удаления не происходит)
     */
    @Override
    public boolean deleteProduct(Product product) { //Уточнить (String id)?
        if ((product.getName() == null || product.getName().isEmpty())
                || (product.getId() == null || product.getId().isEmpty())) {
            logger.warning("Входные данные не валидны");
            return false;
        }
        for (Product item : repository) {
            if ((item.getId()).equals(product.getId())) {
                //&& (item.getName()).equals(product.getName())?
                return repository.remove(item);
            }
        }
        logger.warning("Продукт с id " + product.getId() +
                " не найден, поэтому не может быть удален");
        return false;
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
        for (Product item : repository) {
            if ((item.getId()).equals(id)) {
                return item.getName();
            }
        }
        logger.info("Продукт с id " + id + " не найден");
        return "";
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
        for (Product item : repository) {
            if ((item.getName().equals(name))) {
                result.add(item.getId());
            }
        }
        // если список пустой, то уничтожаем ссылку на объект возвращая Collections.emptyList(),
        // чтобы освободить кучу
        return (result.isEmpty()) ? Collections.emptyList() : result;
    }
}
