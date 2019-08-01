package com.example.poiutis.xml;

import com.example.poiutis.model.InvoiceOrder;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.ArrayList;
import java.util.List;

/**
 * 说明：
 *  DOM解析器把XML文档转化为一个包含其内容的树，并可以对树进行遍历。用DOM解析模型的优点是编程容易，
 *  开发人员只需要调用建树的指令，然后利用navigation APIs访问所需的树节点来完成任务。可以很容易的
 *  添加和修改树中的元素。然而由于使用DOM解析器的时候需要处理整个XML文档，所以对性能和内存的要求比较
 *  高，尤其是遇到很大的XML文件的时候。由于它的遍历能力，DOM解析器常用于XML文档需要频繁的改变的服务中。
 * */

/**
 * @ClassName  用DOM方式读取xml文件
 * @Description TODO
 * @Author wushaopei
 * @Date 2019/8/1 10:57
 * @Version 1.0
 */
public class ReadxmlByDom {

    private static List<InvoiceOrder> invoiceOrderss = null;
    private static List<String> contents = null;

    public List<InvoiceOrder> getDoXml(String fileName) throws Exception {
        // 定义工厂API 使应用程序能够从XML文档获取生成DOM对象树的解析器
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        // 获取此类的实例之后，将可以从各种输入源解析XML
        DocumentBuilder builder = factory.newDocumentBuilder();
        // builder.parse(this.getClass().getResourceAsStream("/" + fileName));
        // Document接口表示整个HTML或XML文档，从概念上讲，它是文档树的根，并提供对文档数据的基本访问
        Document document = builder.parse(this.getClass().getResourceAsStream(
                "/" + fileName));
        // 获取根节点
        Element root = document.getDocumentElement();
        System.out.println(root.getNodeName());

        invoiceOrderss = new ArrayList<InvoiceOrder>();

        //读取database节点NodeList接口提供对节点的有序集合的抽象
        NodeList nodeList = root.getElementsByTagName("InvoiceOrder");
        for (int i = 0; i < nodeList.getLength(); i++) {

            InvoiceOrder invoiceOrder = new InvoiceOrder();

            // 获取一个节点
            Node node = nodeList.item(i);
            // 获取该节点所有属性
            NamedNodeMap attributes = node.getAttributes();
            for (int j = 0; j < attributes.getLength(); j++) {
                Node attribute = attributes.item(j);
                System.out.println(attribute.getNodeName() + ":"
                        + attribute.getNodeValue());
                invoiceOrder.setId(Integer.parseInt(attribute.getNodeValue()));
            }

            //获取所有子节点数据
            NodeList childNodes = node.getChildNodes();
            System.out.println(childNodes.getLength());
           contents = new ArrayList<>();

            for(int j=1;j<childNodes.getLength();j+=2){
                Node item = childNodes.item(j);
                String content = item.getFirstChild().getTextContent();
                contents.add(content);
//                System.out.println(content);

            }

            if(contents.size()>=8){

                invoiceOrder.setInvoiceOrder(contents.get(0));
                invoiceOrder.setCompanyName(contents.get(1));
                invoiceOrder.setTaxNumber(contents.get(2));
                invoiceOrder.setAccountBank(contents.get(3));
                invoiceOrder.setCompanyAddress(contents.get(4));
                invoiceOrder.setBankNumber(contents.get(5));
                invoiceOrder.setCompanyTelephone(contents.get(6));
                invoiceOrder.setAccountName(contents.get(7));
                invoiceOrderss.add(invoiceOrder);
            }
        }
        return invoiceOrderss;

    }
}
