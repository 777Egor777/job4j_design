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
     * Задаваемые ключи словаря
     * задаются по Soft-ссылке.
     * Таким образом, они будут
     * уничтожены сборщиком
     * мусора только в случае
     * нехватки памяти в куче.
     */
    private final Map<SoftReference<String>, String> map = new HashMap<>();

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
     * Получает путь по имени
     * файла из папки resources
     * и возвращает его
     * @param fileName - имя файла
     * @return путь файла
     */
    private String getPath(String fileName) {
        return Objects.requireNonNull(SoftCache.class.getClassLoader().getResource(fileName)).getPath();
    }

    /**
     * Метод сохраняет строку
     * в файл
     * @param filename - имя файла
     * @param content - сохраняемый текст
     */
    private void save(String filename, String content) {
        String path = getPath(filename);
        try (PrintStream ps = new PrintStream(path)) {
            ps.print(content);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
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
        SoftReference<String> softKey = new SoftReference<>(key);
        if (!map.containsKey(softKey)) {
            map.put(softKey, load(key));
        }
        return map.get(softKey);
    }


    /**
     * Устанавливает новое значение
     * ключа. Так же обновляет
     * содержимое файла, чтобы
     * не было несостыковок.
     *
     * Если такого файла
     * не существует, он будет создан
     * @param key - ключ
     * @param value - новое значение
     */
    @Override
    public void put(String key, String value) {
        SoftReference<String> softKey = new SoftReference<>(key);
        map.put(softKey, value);
        save(key, value);
    }
}
