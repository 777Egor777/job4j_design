package ru.job4j.generics;

import java.util.Iterator;
import java.util.List;

public class Generics {
    static class Animal {
    }

    static class Predator extends Animal {
    }

    static class Tiger extends Predator {
    }

    public static void main(String[] args) {
        List<Object> first = List.of(new Animal(), new Predator(), new Tiger());
        new Generics().printObject(first);

        List<Predator> second = List.of(
                 new Tiger(), new Predator(), new Tiger()
        );
        new Generics().printBoundedWildCard(second);

        List<Animal> third = List.of(
                new Animal(), new Tiger(), new Predator(), new Animal(), new Tiger()
        );
        new Generics().printLowerBoundedWildCard(third);
    }

    public void printObject(List<?> list) {
        for (Iterator<?> it = list.iterator(); it.hasNext();) {
            Object next = it.next();
            System.out.println("Текущий элемент: " + next);
        }
    }

    public void printBoundedWildCard(List<? extends Predator> list) {
        for (Iterator<? extends Predator> it = list.iterator(); it.hasNext();) {
            Object next = it.next();
            System.out.println("Текущий элемент: " + next);
        }
    }

    public void printLowerBoundedWildCard(List<? super Predator> list) {
        for (Iterator<? super Predator> it = list.iterator(); it.hasNext();) {
            Object next = it.next();
            System.out.println("Текущий элемент: " + next);
        }
    }
}