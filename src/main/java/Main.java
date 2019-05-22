import org.dom4j.DocumentException;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws DocumentException, IOException {
        String filePath="src/main/resources/library1.xml";
        DomParser dom= new DomParser(filePath);
        System.out.println("Hello in Library");
        Scanner sc= new Scanner(System.in);
        int a=0;
        while(a!=6){
            printOptions();
            a=sc.nextInt();
            switch (a){
                case 1:

                    dom.addOption(sc);
                    //dom.addCustomer("A123098","Bolek","Olek","123456789","Lawendowa 12","Gdansk","Polska");
                    break;
                case 2:
                    dom.deleteOption(sc);
                    break;
                case 3:
                    dom.getCustomers();
                    break;
                case 4:
                    dom.modifyOption(sc);
                    break;
                case 5:
                    dom.sort("/library/users/customers/customer");
                    break;
            }

        }
    }

    public static void printOptions(){
        System.out.println("What do you want to do ?");
        System.out.println("1. Add customer to library");
        System.out.println("2. Delete customer from library");
        System.out.println("3. Show all customers");
        System.out.println("4. Modify customer data");
        System.out.println("5. Sort");
        System.out.println("6. Exit");
    }
}
