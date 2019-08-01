package com.example.poiutis.xml;

import com.example.poiutis.model.InvoiceOrder;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *  说明：
 *  DOM4J使用接口和抽象基本类方法。DOM4J大量使用了API中的Collections类，但是在许多情况下，
 *  它还提供一些替代方法以允许更好的性能或更直接的编码方法。直接好处是，虽然DOM4J付出了更复杂
 *  的API的代价，但是它提供了比JDOM大得多的灵活性。
 *  DOM4J性能最好，连Sun的JAXM也在用DOM4J.目前许多开源项目中大量采用DOM4J， 例如大名鼎鼎的Hibernate也用DOM4J来读取XML配置文件。
 *
 * */

/**
 * @ClassName 用DOM4J方法读取xml文件
 * @Description TODO
 * @Author wushaopei
 * @Date 2019/8/1 15:34
 * @Version 1.0
 */
public class ReadXMLByDom4j {


    private List<InvoiceOrder> invoiceOrders = null;
    private InvoiceOrder invoiceOrder = null;

    public List<InvoiceOrder> getInvoiceOrders(File file) {

        SAXReader reader = new SAXReader();
        try {
            Document document = reader.read(file);
            Element bookstore = document.getRootElement();
            Iterator storeit = bookstore.elementIterator();

            invoiceOrders = new ArrayList<InvoiceOrder>();
            while (storeit.hasNext()) {

                invoiceOrder = new InvoiceOrder();
                Element bookElement = (Element) storeit.next();
                //遍历bookElement的属性
                List<Attribute> attributes = bookElement.attributes();
                for (Attribute attribute : attributes) {
                    if (attribute.getName().equals("id")) {
                        String id = attribute.getValue();//System.out.println(id);
                        invoiceOrder.setId(Integer.parseInt(id));
                    }
                }

                Iterator bookit = bookElement.elementIterator();
                while (bookit.hasNext()) {
                    Element child = (Element) bookit.next();
//                    String nodeName = child.getName();

                    if(child.getName().equals("invoiceOrder")){
                        String invoiceOrderid = child.getStringValue();
                        invoiceOrder.setInvoiceOrder(invoiceOrderid);
//                        System.out.println("发票单号"+"---"+invoiceOrderid);
                    }else if(child.getName().equals("companyName")){
                        String companyName = child.getStringValue();
                        invoiceOrder.setCompanyName(companyName);
//                        System.out.println("公司名"+"---"+content);
                    }else if(child.getName().equals("taxNumber")){
                        String taxNumber = child.getStringValue();
                        invoiceOrder.setTaxNumber(taxNumber);
//                        System.out.println("金额"+"---"+content);
                    }else if(child.getName().equals("accountBank")){
                        String accountBank = child.getStringValue();
                        invoiceOrder.setAccountBank(accountBank);
//                        System.out.println("开户行"+"---"+content);
                    }else if(child.getName().equals("companyAddress")){
                        String companyAddress = child.getStringValue();
                        invoiceOrder.setCompanyAddress(companyAddress);
//                        System.out.println("公司地址"+"---"+content);
                    }else if(child.getName().equals("bankNumber")){
                        String bankNumber = child.getStringValue();
                        invoiceOrder.setBankNumber(bankNumber);
//                        System.out.println("账号"+"---"+bankNumber);
                    }else if(child.getName().equals("companyTelephone")){
                        String companyTelephone = child.getStringValue();
                        invoiceOrder.setCompanyTelephone(companyTelephone);
//                        System.out.println("公司电话"+"---"+companyTelephone);
                    }else if(child.getName().equals("accountName")){
                        String accountName = child.getStringValue();
                        invoiceOrder.setAccountName(accountName);
//                        System.out.println("账户类型"+"---"+accountName);
                    }
                }
                invoiceOrders.add(invoiceOrder);
                invoiceOrder = null;

            }
        } catch (DocumentException e) {

            e.printStackTrace();
        }


        return invoiceOrders;

    }
}