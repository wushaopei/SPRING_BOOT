package com.example.poiutis.xml;

import com.example.poiutis.model.InvoiceOrder;

import java.io.File;
import java.util.List;

/**
 * @ClassName XmlTest
 * @Description TODO
 * @Author wushaopei
 * @Date 2019/8/1 12:26
 * @Version 1.0
 */
public class XmlTest {

    private static List<InvoiceOrder> invoiceOrders = null;

    public static void main(String[] args) {

        //方法1： Dom方式读取xml表中所有数据及格式
//        File file = new File("J:\\POI_Utils_WorkSpace\\SpringBoot_POIUtils\\springboot_docxword\\src\\main\\resources\\invoiceOrder.xml");
//        (new ReadXMLDom()).readXML(file);

        //方法1： Dom方式读取xml表中所有数据并实现封装到javabean中
        try {
            List<InvoiceOrder> doXml = new ReadxmlByDom().getDoXml("invoiceOrder.xml");
            for (InvoiceOrder invoiceOrder : doXml) {
                System.out.println(invoiceOrder.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        //方法2： SAX方式解析XML
//        try {
//            invoiceOrders = new ReadXmlBySAX().getInvoiceOrders("src/main/resources/invoiceOrder.xml");
//            for(InvoiceOrder invoiceOrder: invoiceOrders){
//                System.out.println(invoiceOrder);
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        // 方法3： 用JDOM方式读取xml文件
//        String fileName = "src/main/resources/invoiceOrder.xml";
//        List<InvoiceOrder> invoiceOrders= new ReadXMLByJDom().getInvoiceOrders(fileName);
//        for(InvoiceOrder invoiceOrder : invoiceOrders){
//            System.out.println(invoiceOrder);
//        }

//        // TODO Auto-generated method stub
//        File file = new File("src/main/resources/invoiceOrder.xml");
//        List<InvoiceOrder> invoiceOrders = new ReadXMLByDom4j().getInvoiceOrders(file);
//        for (InvoiceOrder invoiceOrder : invoiceOrders) {
//            System.out.println(invoiceOrder.toString());
//        }


    }
}
