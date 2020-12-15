package ru.job4j.tdd;

import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;

/**
 * Интерфейс для описания
 * кинотеатра
 *
 * @author Geraskin Egor
 * @version 1.0
 * @since 15.12.2020
 */
public interface Cinema {
    /**
     * Метод, который составляет
     * список сеансов,
     * удовлетворяющих предикату,
     * и затем возвращает его.
     *
     * @param filter - предикат
     * @return - список сеансов,
     *           удовлетворяющих
     *           предикату
     */
    List<Session> find(Predicate<Session> filter);

    /**
     * Метод, который эмулирует
     * покупку билета посетителем
     * кинотеатра, формирует
     * объект типа {@code Ticket}
     * и возвращает его
     *
     * @param account - посетитель кинотеатра
     * @param row - ряд в кинозале
     * @param column - место в кинозале
     * @param date - дата посещения кинотеатра
     * @return объект типа {@code Ticket},
     *         который описывает купленный
     *         посетителем кинотеатра
     *         билет на сеанс
     */
    Ticket buy(Account account, int row, int column, Calendar date);

    /**
     * Метод добавляет новый
     * сеанс в расписание
     * кинотеатра.
     *
     * @param session - добавляемый сеанс
     */
    void add(Session session);
}
