package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {

    private Map<Integer, Accident> accidents = new HashMap<Integer, Accident>();
    private final AtomicInteger key = new AtomicInteger(0);

    private AccidentMem() {
        accidents.put(key.incrementAndGet(), new Accident(key.get(), "User1", "dtp", "Moscow"));
        accidents.put(key.incrementAndGet(), new Accident(key.get(), "User2", "speed", "Moscow"));
        accidents.put(key.incrementAndGet(), new Accident(key.get(), "User3", "speed", "SPB"));
        accidents.put(key.incrementAndGet(), new Accident(key.get(), "User4", "parking", "EKB"));
    }

    private static final class Holder {
        public static final AccidentMem INSTANCE = new AccidentMem();
    }

    public static AccidentMem instOf() {
        return Holder.INSTANCE;
    }

    public Map<Integer, Accident> getAccidents(){
        return accidents;
    }

    public void create(Accident accident){
        accident.setId(key.incrementAndGet());
        accidents.put(accidents.size() + 1, accident);
    }
}
