import org.dom4j.DocumentException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws DocumentException, IOException {
        String filePath="library.xml";
        DomParser dom= new DomParser(filePath);
        dom.getNodes("/library");
        dom.addCustomer("A123098","Bolek","Olek","123456789","Lawendowa 12","Gdansk","Polska");
        dom.updateCustomer("A123098","A123098","Bwerolek","Olewerwk","123456789","Lawenwerdowa 12","Gdansk","Polska");
        dom.deleteCustomer("A123098");
    }
}
