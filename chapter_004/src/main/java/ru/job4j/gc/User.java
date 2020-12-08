package ru.job4j.gc;

public class User {
    int age;
    int weight;

    public User(int age, int weight) {
        this.age = age;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return String.format("User{age=%d,weight=%d}", age, weight);
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("Removed " + this.toString());
    }
}
