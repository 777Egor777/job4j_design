package ru.job4j.collection;

import java.util.*;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public int getChildren() {
        return children;
    }

    public Calendar getBirthday() {
        return birthday;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }

    public static void main(String[] args) {
        Calendar calendar = new GregorianCalendar(1995, 6, 27);
        User firstUser = new User("Egor", 0, calendar);
        User secondUser = new User("Egor", 0, calendar);
        Map<User, Object> map = new HashMap<>();
        map.put(firstUser, new Object());
        map.put(secondUser, new Object());
        System.out.println(map);
    }
}
