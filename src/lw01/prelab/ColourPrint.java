package lw01.prelab;

public class ColourPrint extends PrintJob {

    public ColourPrint(String id, int pages) {
        super(id, pages);
    }

    @Override 
    public int calculateCharge() {
        if (getPages() <= 10) {
            return getPages() * 1500 + 2000;
        } else {
            return 10 * 1500 + (getPages() - 10) * 1000 + 2000;
        }
    }

    @Override
    public String label() {
        return "Colour";
    }
}
