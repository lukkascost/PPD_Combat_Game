package Protocol;

public class Fields {
    private String value;
    private int collor;


    public Fields(String value, int collor) {
        this.value = value;
        this.collor = collor;
    }

    @Override
    public String toString() {
        return collor+ this.value;
    }
}
