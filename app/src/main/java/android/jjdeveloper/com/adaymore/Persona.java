package android.jjdeveloper.com.adaymore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Persona {

    private String name, lastname;
    private ArrayList<Integer> loteria;

    public Persona() {
    }

    public Persona(String name, String lastname, ArrayList<Integer> loteria) {
        this.name = name;
        this.lastname = lastname;
        this.loteria = loteria;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("name", name);
        result.put("lastname", lastname);
        result.put("loteria", loteria);

        return result;
    }

}
