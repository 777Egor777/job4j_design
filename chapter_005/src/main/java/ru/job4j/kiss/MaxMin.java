package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;

/**
 * Класс для поиска минимального
 * и максимального элемента в
 * списке по компаратору.
 *
 * Применяются принципы
 * KISS, DRY, YAGNI
 *
 * @author Geraskin Egor
 * @version 1.0
 * @since 15.12.2020
 */
public class MaxMin {
    /**
     * Метод ищет максимальное
     * по компаратору значение
     * в списке и возвращает
     * его.
     *
     * Чтобы следовать принципам
     * KISS, DRY и YAGNI,
     * используем уже написанный
     * код (метод min)
     *
     * @param values - список элементов
     * @param comparator - компаратор
     * @param <T> - тип generic
     * @return максимальный по компаратору
     *         элемент списка
     */
    public <T> T max(List<T> values, Comparator<T> comparator) {
        return min(values, comparator.reversed());
    }

    /**
     * Метод ищет минимальное
     * по компаратору значение
     * в списке и возвращает
     * его.
     *
     * @param values - список элементов
     * @param comparator - компаратор
     * @param <T> - тип generic
     * @return минимальный по компаратору
     *         элемент списка
     */
    public <T> T min(List<T> values, Comparator<T> comparator) {
        T result = values.get(0);
        for (int i = 1; i < values.size(); ++i) {
            if (comparator.compare(values.get(i), result) < 0) {
                result = values.get(i);
            }
        }
        return result;
    }
}
