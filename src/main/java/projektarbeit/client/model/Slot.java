package projektarbeit.client.model;

public class Slot {

    private String name = null;
    private String value = null;

    public Slot(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
