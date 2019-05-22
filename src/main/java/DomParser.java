import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class DomParser {
    private SAXReader reader;
    private Document document;

    public DomParser(String filePath) throws DocumentException {
        reader= new SAXReader();
        this.document=reader.read(filePath);
        System.out.println(document);
    }

    public void addCustomer(String id,String firstname,String lastname,String phone,String street,String city,String country) throws IOException {

        List<Node> nodes = document.selectNodes("//library/users/customers");
        // nodes will have all the child nodes under your Xpath.
        for (Node node : nodes) {
            //get the required node and add your new node to specific node.
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

    }

    public void updateCustomer(String cusID,String id,String firstname,String lastname,String phone,String street,String city,String country) throws IOException {
//        Node node = document.selectSingleNode("//library/users/customers/customer[@customer_id = '"+id+"']" );
//
//        Element customer = (Element) node;
//        Element

        List<Node> nodes = document.selectNodes("//library/users/customers/customer[@customer_id = '"+cusID+"']" );
        // nodes will have all the child nodes under your Xpath.
        for (Node node : nodes) {
            //get the required node and add your new node to specific node.
            if(node instanceof Element) {
                Element e = (Element) node;
                e.element("firstname").setText(firstname);
                e.element("lastname").setText(lastname);
                e.element("phone").setText(phone);
                e.element("address").element("street").setText(street);
                e.element("address").element("city").setText(city);
                e.element("address").element("country").setText(country);
            }
        }
        print();
    }

    public void deleteCustomer(String id) throws IOException {
        Node node= document.selectSingleNode("//library/users/customers/customer[@customer_id = '"+id+"']");
        node.detach();
        print();
    }

    public void getNodes(String nodePath){

        List<Node> nodes = document.selectNodes(nodePath);
        for(Node n: nodes){
            n.asXML();
        }
        System.out.println("\n");
    }

    public void print() throws IOException {
        OutputFormat format = OutputFormat.createPrettyPrint();
        XMLWriter writer;
        writer = new XMLWriter( System.out, format );
        writer.write( document );
    }

}
