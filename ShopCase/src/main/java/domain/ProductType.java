package domain;

public enum ProductType {

    Pen("Pen"),
    Pad("Pad"),
    Paper("Paper"),
    Eraser("Eraser");

    private final String name;

    ProductType(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
