package com.example.poiutis.xml;

import com.example.poiutis.model.InvoiceOrder;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *  说明：
 *  JDOM仅使用具体类而不使用接口。API大量使用了Collections类；
 *  JDOM自身不包含解析器。它通常使用SAX2解析器来解析和验证输入XML文档（尽管它还可以将以前构造的DOM表示作为输入）。它包含一些转换器以将JDOM表示输出成SAX2事件流、DOM模型或XML文本文档。JDOM是在Apache许可证变体下发布的开放源码。
 * */

/**
 * @ClassName 用JDOM方式读取xml文件
 * @Description TODO
 * @Author wushaopei
 * @Date 2019/8/1 15:14
 * @Version 1.0
 */
public class ReadXMLByJDom {

    private List<InvoiceOrder> invoiceOrders = null;
    private InvoiceOrder invoiceOrder = null;

    public List<InvoiceOrder> getInvoiceOrders(String fileName) {
        SAXBuilder saxBuilder = new SAXBuilder();
        try {
            Document document = saxBuilder.build(new FileInputStream(fileName));
            //获取根节点bookstore
            Element rootElement = document.getRootElement();
            //获取根节点的子节点，返回子节点的数组
            List<Element> bookList = rootElement.getChildren();
            invoiceOrders = new ArrayList<InvoiceOrder>();
            for (Element bookElement : bookList) {
                invoiceOrder = new InvoiceOrder();
                //获取bookElement的属性
                List<Attribute> bookAttributes = bookElement.getAttributes();
                for (Attribute attribute : bookAttributes) {
                    if (attribute.getName().equals("id")) {
                        String id = attribute.getValue(); //System.out.println(id);
                        invoiceOrder.setId(Integer.parseInt(id));
                    }
                }
                //获取bookElement的子节点
                List<Element> children = bookElement.getChildren();

                for (Element child : children) {
                    if (child.getName().equals("invoiceOrder")) {
                        String invoiceOrderid = child.getValue();
                        invoiceOrder.setInvoiceOrder(invoiceOrderid);
//                        System.out.println("发票单号"+"---"+invoiceOrderid);
                    } else if (child.getName().equals("companyName")) {
                        String companyName = child.getValue();
                        invoiceOrder.setCompanyName(companyName);
//                        System.out.println("公司名"+"---"+content);
                    } else if (child.getName().equals("taxNumber")) {
                        String taxNumber = child.getValue();
                        invoiceOrder.setTaxNumber(taxNumber);
//                        System.out.println("金额"+"---"+content);
                    } else if (child.getName().equals("accountBank")) {
                        String accountBank = child.getValue();
                        invoiceOrder.setAccountBank(accountBank);
//                        System.out.println("开户行"+"---"+content);
                    } else if (child.getName().equals("companyAddress")) {
                        String companyAddress = child.getValue();
                        invoiceOrder.setCompanyAddress(companyAddress);
//                        System.out.println("公司地址"+"---"+content);
                    } else if (child.getName().equals("bankNumber")) {
                        String bankNumber = child.getValue();
                        invoiceOrder.setBankNumber(bankNumber);
//                        System.out.println("账号"+"---"+bankNumber);
                    } else if (child.getName().equals("companyTelephone")) {
                        String companyTelephone = child.getValue();
                        invoiceOrder.setCompanyTelephone(companyTelephone);
//                        System.out.println("公司电话"+"---"+companyTelephone);
                    } else if (child.getName().equals("accountName")) {
                        String accountName = child.getValue();
                        invoiceOrder.setAccountName(accountName);
//                        System.out.println("账户类型"+"---"+accountName);
                    }

                }

                invoiceOrders.add(invoiceOrder);
                invoiceOrder = null;

            }

        } catch (FileNotFoundException e) {

            e.printStackTrace();
        } catch (JDOMException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }

        return invoiceOrders;

    }


}