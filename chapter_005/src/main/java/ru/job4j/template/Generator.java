package ru.job4j.template;

import java.util.Map;

/**
 * Интерфейс описывает генератор
 * строк по шаблону.
 *
 * Входной шаблон содержит
 * ключи вида ${name},
 * которые мы должны
 * получать из словаря.
 *
 * @author Geraskin Egor
 * @version 1.0
 * @since 16.12.2020
 */
public interface Generator {
    /**
     * Основной метод.
     * Если в шаблоне есть ключи,
     * которых нет в словаре,
     * или наоборот в словаре
     * избыточное количество
     * ключей, метод кидает
     * исключение
     * IllegalArgumentsException
     *
     * @param template - шаблон
     * @param args - словарь аргументов
     * @return отформатированную строку
     * @throws IllegalArgumentException - если в словаре избыточное
     *                                    количество ключей, либо
     *                                    в шаблоне есть ключи, которых
     *                                    нет в словаре
     */
    String produce(String template, Map<String, String> args) throws IllegalArgumentException;
}
