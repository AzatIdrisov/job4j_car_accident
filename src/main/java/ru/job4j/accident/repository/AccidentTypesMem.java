package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.AccidentType;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentTypesMem {
    private Map<Integer, AccidentType> accidentTypes = new HashMap<Integer, AccidentType>();
    private final AtomicInteger key = new AtomicInteger(0);

    private AccidentTypesMem() {
        accidentTypes.put(key.incrementAndGet(), AccidentType.of(1, "Две машины"));
        accidentTypes.put(key.incrementAndGet(), AccidentType.of(2, "Машина и человек"));
        accidentTypes.put(key.incrementAndGet(), AccidentType.of(3, "Машина и велосипед"));
    }

    private static final class Holder {
        public static final AccidentTypesMem INSTANCE = new AccidentTypesMem();
    }

    public static AccidentTypesMem instOf() {
        return AccidentTypesMem.Holder.INSTANCE;
    }

    public Collection<AccidentType> getAll() {
        return accidentTypes.values();
    }

    public AccidentType getById(int id) {
        return accidentTypes.get(id);
    }
}
