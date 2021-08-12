package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {

    private Map<Integer, Accident> accidents = new HashMap<Integer, Accident>();
    private final AtomicInteger key = new AtomicInteger(0);

    private AccidentMem() {
        accidents.put(key.incrementAndGet(), new Accident(key.get(),
                "User1",
                "dtp",
                "Moscow",
                AccidentType.of(1, "Две машины")));
        accidents.put(key.incrementAndGet(), new Accident(key.get(),
                "User2",
                "speed",
                "Moscow",
                AccidentType.of(1, "Две машины")));
        accidents.put(key.incrementAndGet(), new Accident(key.get(),
                "User3",
                "speed",
                "SPB",
                AccidentType.of(1, "Две машины")));
        accidents.put(key.incrementAndGet(), new Accident(key.get(),
                "User4",
                "parking",
                "EKB",
                AccidentType.of(1, "Две машины")));
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
        AccidentType type = AccidentTypesMem.instOf().getById(accident.getType().getId());
        accident.setType(type);
        if (accidents.containsKey(accident.getId())){
            accidents.put(accident.getId(), accident);
        } else {
            accident.setId(key.incrementAndGet());
            accidents.put(accidents.size() + 1, accident);
        }
    }

    public Accident findById(int id){
        return accidents.get(id);
    }
}
