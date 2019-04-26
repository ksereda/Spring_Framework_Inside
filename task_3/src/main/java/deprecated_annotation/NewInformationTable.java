package deprecated_annotation;

public class NewInformationTable extends InformationTable implements Quoter {

    @Override
    public void sayQuote() {
        System.out.println("New Information Table");
    }

}
