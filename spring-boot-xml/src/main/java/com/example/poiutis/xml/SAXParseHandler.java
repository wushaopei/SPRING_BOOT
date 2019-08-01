package com.example.poiutis.xml;


import com.example.poiutis.model.InvoiceOrder;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;
/**
 * 说明：
 * SAX解析器采用了基于事件的模型，它在解析XML文档的时候可以触发一系列的事件，当发现给定的tag的时候，
 * 它可以激活一个回调方法，告诉该方法制定的标签已经找到。SAX对内存的要求通常会比较低，因为它让开发人
 * 员自己来决定所要处理的tag.特别是当开发人员只需要处理文档中所包含的部分数据时，SAX这种扩展能力得到
 * 了更好的体现。但用SAX解析器的时候编码工作会比较困难，而且很难同时访问同一个文档中的多处不同数据。
 * */

/**
 * @ClassName 用SAX解析xml文件时需要的handler
 * @Description TODO
 * @Author wushaopei
 * @Date 2019/8/1 14:36
 * @Version 1.0
 */
public class SAXParseHandler extends DefaultHandler{

    private List<InvoiceOrder> list;         //存放解析到的invoiceOrder数组
    private InvoiceOrder invoiceOrder;               //存放当前解析的invoiceOrder

    private String content = null;   //存放当前节点值

    private Integer count = 0;
    /**
     * 开始解析xml文档时调用此方法
     */
    @Override
    public void startDocument() throws SAXException {

        super.startDocument();
        System.out.println("开始解析xml文件");
        list = new ArrayList<InvoiceOrder>();
    }

    /**
     * 文档解析完成后调用此方法
     */
    @Override
    public void endDocument() throws SAXException {

        super.endDocument();
        System.out.println("xml文件解析完毕");

    }

    /**
     * 开始解析节点时调用此方法
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        super.startElement(uri, localName, qName, attributes);

        //当节点名为invoiceOrder时,获取invoiceOrder的属性id
        if(qName.equals("InvoiceOrder")){
            invoiceOrder = new InvoiceOrder();
            String id = attributes.getValue("id");//System.out.println("id值为"+id);
            invoiceOrder.setId(Integer.parseInt(id));

            //xml解析张数
            count ++ ;
            System.out.println( count + "--- 次");
        }

    }


    /**
     *节点解析完毕时调用此方法
     *
     *@param qName 节点名
     */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        super.endElement(uri, localName, qName);
        if(qName.equals("invoiceOrder")){
            invoiceOrder.setInvoiceOrder(content);
            System.out.println("发票单号"+"---"+content);
        }else if(qName.equals("companyName")){
            invoiceOrder.setCompanyName(content);
              System.out.println("公司名"+"---"+content);
        }else if(qName.equals("taxNumber")){
            invoiceOrder.setTaxNumber(content);
              System.out.println("金额"+"---"+content);
        }else if(qName.equals("accountBank")){
            invoiceOrder.setAccountBank(content);
              System.out.println("开户行"+"---"+content);
        }else if(qName.equals("companyAddress")){
            invoiceOrder.setCompanyAddress(content);
              System.out.println("公司地址"+"---"+content);
        }else if(qName.equals("bankNumber")){
            invoiceOrder.setBankNumber(content);
              System.out.println("账号"+"---"+content);
        }else if(qName.equals("companyTelephone")){
            invoiceOrder.setCompanyTelephone(content);
              System.out.println("公司电话"+"---"+content);
        }else if(qName.equals("accountName")){
            invoiceOrder.setAccountName(content);
              System.out.println("账户类型"+"---"+content);
        }else if(qName.equals("invoiceOrder")){         //当结束当前invoiceOrder解析时,将该invoiceOrder添加到数组后置为空，方便下一次invoiceOrder赋值
            list.add(invoiceOrder);
            invoiceOrder = null;
        }


    }



    /**
     * 此方法用来获取节点的值
     */
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {

        super.characters(ch, start, length);

        content = new String(ch, start, length);
        //收集不为空白的节点值
//      if(!content.trim().equals("")){
//          System.out.println("节点值为："+content);
//      }

    }

    public List<InvoiceOrder> getInvoiceOrders(){

        return list;
    }

}
