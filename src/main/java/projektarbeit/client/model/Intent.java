package projektarbeit.client.model;

import java.util.HashMap;

public class Intent {

    private String name = null;
    private HashMap<String, Slot> slots = new HashMap<>();

    public Intent(String name, HashMap<String, Slot> slots) {
        this.name = name;
        this.slots = slots;
    }

    public String getName() {
        return name;
    }

    public HashMap<String, Slot> getSlots() {
        return slots;
    }

    public Slot getSlot(String name) {
        return slots.get(name);
    }
}
