package ru.job4j.map;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Analyze {

    public Info diff(List<User> previous, List<User> current) {
        Info info = new Info(0, 0, 0);
        Map<Integer, String> map = new HashMap<>();
        for (User user : previous) {
            map.put(user.id, user.name);
        }
        for (User user : current) {
            if (map.containsKey(user.id)) {
                if (!Objects.equals(user.name, map.get(user.id))) {
                    info.changed++;
                }
            } else {
                info.added++;
            }
        }
        map = new HashMap();
        for (User user : current) {
            map.put(user.id, user.name);
        }
        for (User user : previous) {
            if (!map.containsKey(user.id)) {
                info.deleted++;
            }
        }
        return info;
    }

    public static class User {
        int id;
        String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }
    public static class Info {
        int added;
        int changed;
        int deleted;

        public Info(int added, int changed, int deleted) {
            this.added = added;
            this.changed = changed;
            this.deleted = deleted;
        }

        @Override
        public boolean equals(Object obj) {
            boolean result = false;
            if (obj != null) {
                if (obj instanceof Info) {
                    Info another = (Info) obj;
                    result = added == another.added
                            && changed == another.changed
                            && deleted == another.deleted;
                }
            }
            return result;
        }

        @Override
        public int hashCode() {
            int result = Integer.hashCode(added);
            result *= 31;
            result += Integer.hashCode(changed);
            result *= 31;
            result += Integer.hashCode(deleted);
            return result;
        }
    }
}
