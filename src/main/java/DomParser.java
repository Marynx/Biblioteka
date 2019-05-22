import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.*;
import java.util.*;

public class DomParser {
    private SAXReader reader;
    private Document document;

    public DomParser(String filePath) throws DocumentException {
        reader= new SAXReader();
        this.document=reader.read(filePath);
    }

    public void addOption(Scanner sc) throws IOException {
        System.out.println("Customer ID(needs to start with \"A\"-for men or \"B\"-for women and needs to have 6 digits):");
        sc.nextLine();
        String id=sc.nextLine();
        System.out.println("Firstname: ");
        String firstname=sc.nextLine();
        System.out.println("Lastname: ");
        String lastname=sc.nextLine();
        System.out.println("Phone(9 digits): ");
        String phone=sc.nextLine();
        System.out.println("Street: ");
        String street=sc.nextLine();
        System.out.println("City: ");
        String city=sc.nextLine();
        System.out.println("Country: ");
        String country=sc.nextLine();
        addCustomer(id,firstname,lastname,phone,street,city,country);
    }

    public void addCustomer(String id,String firstname,String lastname,String phone,String street,String city,String country) throws IOException {

        List<Node> nodes = document.selectNodes("//library/users/customers");
        for (Node node : nodes) {
            if(node instanceof Element) {
                Element e = (Element) node;
                Element customer=e.addElement("customer");
                customer.addAttribute("customer_id",id);
                Element cFirstname= customer.addElement("firstname");
                cFirstname.setText(firstname);
                Element cLastname=customer.addElement("lastname");
                cLastname.setText(lastname);
                Element cPhone=customer.addElement("phone");
                cPhone.setText(phone);
                Element address=customer.addElement("address");
                Element cStreet=address.addElement("street");
                cStreet.setText(street);
                Element cCity=address.addElement("city");
                cCity.setText(city);
                Element cCountry=address.addElement("country");
                cCountry.setText(country);

            }
        }
        print();
        //save

    }
    public void deleteOption(Scanner sc) throws IOException {
        System.out.println("Which customer do you want to delete?");
        List<Node>customers=getCustomers();
        System.out.println("0. Return");
        int a=sc.nextInt();
        if(a!=0 && a<=customers.size()){
            System.out.println(customers.get(a-1).valueOf("@customer_id"));
            deleteCustomer(customers.get(a-1).valueOf("@customer_id"));
        }

    }

    public void deleteCustomer(String id) throws IOException {
        Node node= document.selectSingleNode("//library/users/customers/customer[@customer_id = '"+id+"']");
        node.detach();
        print();
        //save
    }

    public void modifyOption(Scanner sc) throws IOException {
        System.out.println("Which customer do you want to modify?");
        List<Node> customers= getCustomers();
        int a=sc.nextInt();
        if(a!=0&& a<=customers.size()){
            String customerID=customers.get(a-1).valueOf("@customer_id");
            Node customer=document.selectSingleNode("//library/users/customers/customer[@customer_id = '"+customerID+"']");
            Element updatedCustomer= (Element) customer;
            System.out.println("What do you want to update?");
            List<String> elements=getContent(updatedCustomer);
            int b=sc.nextInt();
            if(b!=0&& b<=elements.size()){
                if(b<3) {
                    System.out.println("Old value: " + updatedCustomer.element(elements.get(b-1)).getText());
                    System.out.println("Enter new value: ");
                    sc.nextLine();
                    String updatedValue=sc.nextLine();
                    updatedCustomer.element(elements.get(b - 1)).setText(updatedValue);
                }else {
                    System.out.println("Old value: " + updatedCustomer.element("address").element(elements.get(b-1)).getText());
                    System.out.println("Enter new value: ");
                    sc.nextLine();
                    String updatedValue=sc.nextLine();
                    updatedCustomer.element("address").element(elements.get(b - 1)).setText(updatedValue);
                }

            }
        }
        print();
    }

    public List<Node> getCustomers(){
        List<Node> nodes = document.selectNodes("/library/users/customers/customer");
        int a=1;
        for( Node node: nodes){
            System.out.println(a+". ID of customer: "+node.valueOf("@customer_id")
                    + "  Firstname: " + node.selectSingleNode("firstname").getText()
                    + "  Lastname: " + node.selectSingleNode("lastname").getText()
                    +"  Phone: "+node.selectSingleNode("phone").getText()
                    +"  Street: "+node.selectSingleNode("address/street").getText()
                    +"  City: "+ node.selectSingleNode("address/city").getText()
                    +"  Country: "+node.selectSingleNode("address/country").getText());
            a++;
        }
        return nodes;
    }

    public void sort(String path) throws IOException {
        List<Node> nodes = this.document.selectNodes(path);
        Collections.sort(nodes, Comparator.comparing(Node::getStringValue));
        int a=1;
        for (Node node : nodes) {
            System.out.println(a+". ID of customer: "+node.valueOf("@customer_id")
                    + "  Firstname: " + node.selectSingleNode("firstname").getText()
                    + "  Lastname: " + node.selectSingleNode("lastname").getText()
                    +"  Phone: "+node.selectSingleNode("phone").getText()
                    +"  Street: "+node.selectSingleNode("address/street").getText()
                    +"  City: "+ node.selectSingleNode("address/city").getText()
                    +"  Country: "+node.selectSingleNode("address/country").getText());
            a++;
        }
        print();
    }

    public void print() throws IOException {
        OutputFormat format = OutputFormat.createPrettyPrint();
        Writer w=new OutputStreamWriter(new FileOutputStream(this.document.getName()));
        XMLWriter writer;
        writer = new XMLWriter( w, format );
        writer.write( document );
        writer.close();
    }

    public List<String> getContent(Element element) {
        StringBuilder builder = new StringBuilder();
        List<String> nodes=new ArrayList<String>();
        int a=1;
        for (Iterator<Element> i = element.elementIterator(); i.hasNext();) {
            Element e = i.next();
            if(e.isTextOnly()) {
                nodes.add(e.getName());
                builder.append(a + ". " + e.getName() + "\n");
                a++;
            }else{
//                builder.append(a+" ."+ e.elements().get(0).toString());
                for(Iterator<Element> j = e.elementIterator(); j.hasNext();){
                    Element child=j.next();
                    nodes.add(child.getName());
                    builder.append(a + ". " + child.getName() + "\n");
                    a++;
                }

            }
        }
        System.out.println(builder.toString());
        return nodes;
    }

}
