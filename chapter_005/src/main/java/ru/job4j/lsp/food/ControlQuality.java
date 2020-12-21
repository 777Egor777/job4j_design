package ru.job4j.lsp.food;

import ru.job4j.lsp.food.repeat.SimpleRepeatable;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Class that organize
 * separating food
 * objects to different
 * holders depending
 * on their expiration date.
 *
 * @author Geraskin Egor
 * @version 1.0
 * @since 19.12.2020
 */
public final class ControlQuality implements Resortable, SimpleRepeatable {
    private final Storage trash = new Trash();
    private final Storage shop = new Shop();
    private final Storage warehouse = new Warehouse();
    private final List<Storage> storages = new ArrayList<>();

    {
        storages.add(trash);
        storages.add(shop);
        storages.add(warehouse);
    }

    public final void add(Food food) {
        for (Storage storage : storages) {
            if (storage.accept(food)) {
                storage.add(food);
                break;
            }
        }
    }

    public final Storage getTrash() {
        return trash;
    }

    public final Storage getShop() {
        return shop;
    }

    public final Storage getWarehouse() {
        return warehouse;
    }

    @Override
    public final void resort() {
        List<Food> products = new ArrayList<>();
        storages.forEach(storage -> {
            products.addAll(storage.getAll());
            storage.clean();
        });
        products.forEach(this::add);
    }

    @Override
    public final void repeat() {
        resort();
    }
}
