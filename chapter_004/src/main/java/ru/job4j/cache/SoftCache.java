package ru.job4j.cache;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * Класс реализует абстрактный
 * кэш на Soft-ссылках.
 *
 * По имени файла он выдаёт его
 * содержимое.
 *
 * При добавлении файла в кэш, он
 * изменяет имеющийся результат.
 *
 * Все файлы хранятся в папке
 * resources
 *
 * @author Geraskin Egor
 * @version 1.0
 * @since 12.12.2020
 */
public class SoftCache implements Cache {
    /**
     * Задаваемые значения словаря
     * задаются по Soft-ссылке.
     * Таким образом, они будут
     * уничтожены сборщиком
     * мусора только в случае
     * нехватки памяти в куче.
     */
    private final Map<String, SoftReference<String>> map = new HashMap<>();

    /**
     * Метод загружает содержимое
     * файла из папки resources
     * @param fileName - имя файла
     * @return содержимое файла
     */
    private String load(String fileName) {
        StringJoiner joiner = new StringJoiner(System.lineSeparator());
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        Objects.requireNonNull(SoftCache.class.getClassLoader().getResourceAsStream(fileName))
                )
        )) {
            reader.lines().forEach(joiner::add);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return joiner.toString();
    }

    /**
     * Метод возвращает текст
     * файла по ключу,
     * где ключ - имя файла
     * из папки resources.
     *
     * Так как кэш абстрактный,
     * если в Map на данный
     * момент нет такого ключа,
     * то происходит загрузка
     * из файла.
     * @param key - ключ, имя файла
     * @return содержимое файла
     *         из папки resources
     */
    @Override
    public String get(String key) {
        if (!map.containsKey(key) || map.get(key).get() == null) {
            SoftReference<String> softValue = new SoftReference<>(load(key));
            map.put(key, softValue);
        }
        return map.get(key).get();
    }


    /**
     * Устанавливает новое значение
     * ключа.
     * @param key - ключ
     * @param value - новое значение
     */
    @Override
    public void put(String key, String value) {
        SoftReference<String> softValue = new SoftReference<>(value);
        map.put(key, softValue);
    }
}
