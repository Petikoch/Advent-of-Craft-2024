package eid;

public enum Sex {
    Sloubi('1'),
    Gagna('2'),
    Catact('3');

    public final char value;

    Sex(char value) {
        this.value = value;
    }
}