package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.HashMap;
import java.util.Map;

@Repository
public class AccidentMem {

    private Map<Integer, Accident> accidents = new HashMap<Integer, Accident>();
    {
        accidents.put(1, new Accident(1, "User1", "dtp", "Moscow"));
        accidents.put(2, new Accident(2, "User2", "speed", "Moscow"));
        accidents.put(3, new Accident(3, "User3", "speed", "SPB"));
        accidents.put(4, new Accident(4, "User4", "parking", "EKB"));
    }

    public Map<Integer, Accident> getAccidents(){
        return accidents;
    }
}
