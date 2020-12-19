package ru.job4j.lsp.food;

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
public final class ControlQuality {
    private final FoodHolder trash = new Trash();
    private final FoodHolder shop = new Shop();
    private final FoodHolder warehouse = new Warehouse();
    private final List<FoodHolder> holders = new ArrayList<>();
    private static final double TO_SHOP_WITH_DISCOUNT_THRESHOLD = 0.75;
    private static final double TO_SHOP_THRESHOLD = 0.25;
    private static final double DIFF = 0.0000001;
    private static final Discounter DISCOUNTER = new Discounter();

    {
        holders.add(trash);
        holders.add(shop);
        holders.add(shop);
        holders.add(warehouse);
    }

    private static final BiFunction<Long, Long, Double> RATIO =
            (first, second) -> first.doubleValue() / second.doubleValue();

    private static final BiFunction<Long, Long, Boolean> ADD_TO_TRASH =
            (liveMillis, allMillis) -> liveMillis - allMillis >= 0;

    private static final BiFunction<Long, Long, Boolean> ADD_TO_SHOP_WITH_DISCOUNT =
            (liveMillis, allMillis) ->
                    RATIO.apply(liveMillis, allMillis) > TO_SHOP_WITH_DISCOUNT_THRESHOLD + DIFF;

    private static final BiFunction<Long, Long, Boolean> ADD_TO_SHOP =
            (liveMillis, allMillis) ->
                    RATIO.apply(liveMillis, allMillis) > TO_SHOP_THRESHOLD + DIFF;

    private static final BiFunction<Long, Long, Boolean> ADD_TO_WAREHOUSE =
            (liveMillis, allMillis) -> true;

    private static final List<BiFunction<Long, Long, Boolean>> ADD_ACTIONS = new ArrayList<>();

    static {
        ADD_ACTIONS.add(ADD_TO_TRASH);
        ADD_ACTIONS.add(ADD_TO_SHOP_WITH_DISCOUNT);
        ADD_ACTIONS.add(ADD_TO_SHOP);
        ADD_ACTIONS.add(ADD_TO_WAREHOUSE);
    }

    private static final BiFunction<Long, Long, Integer> ADD_TYPE_INT_BY_MILLIS =
            (liveMillis, allMillis) -> {
                int result = -1;
                for (int i = 0; i < ADD_ACTIONS.size(); ++i) {
                    if (ADD_ACTIONS.get(i).apply(liveMillis, allMillis)) {
                        result = i;
                        break;
                    }
                }
                return result;
            };

    private static final Function<Food, Long> GET_LIVE_MILLIS =
            food -> Calendar.getInstance().getTimeInMillis() - food.getCreate().getTimeInMillis();

    private static final Function<Food, Long> GET_ALL_MILLIS =
            food -> food.getExpire().getTimeInMillis() - food.getCreate().getTimeInMillis();

    private static final Function<Food, Integer> ADD_TYPE_INT =
            food -> ADD_TYPE_INT_BY_MILLIS.apply(
                    GET_LIVE_MILLIS.apply(food),
                    GET_ALL_MILLIS.apply(food)
            );

    private final Consumer<Food> add =
            food -> {
                int type = ADD_TYPE_INT.apply(food);
                holders.get(type).add(food);
                if (type == 1) {
                    DISCOUNTER.apply(food);
                }
            };

    public final void add(Food food) {
        add.accept(food);
    }

    public final FoodHolder getTrash() {
        return trash;
    }

    public final FoodHolder getShop() {
        return shop;
    }

    public final FoodHolder getWarehouse() {
        return warehouse;
    }
}
