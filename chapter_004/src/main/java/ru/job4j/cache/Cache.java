package ru.job4j.cache;

/**
 * Интерфейс для структуры
 * данных типа "Кэш"
 *
 * @author Geraskin Egor
 * @version 1.0
 * @since 12.12.2020
 */
public interface Cache {
    /**
     * Получение значения по ключу
     * @param key - ключ
     * @return значение по ключу
     */
    String get(String key);

    /**
     * Измение значения по ключу
     * @param key - ключу
     * @param value - новое значение
     *              по ключу
     */
    void put(String key, String value);
}
