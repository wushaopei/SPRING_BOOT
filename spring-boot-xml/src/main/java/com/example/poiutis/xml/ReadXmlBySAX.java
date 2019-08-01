package com.example.poiutis.xml;

import com.example.poiutis.model.InvoiceOrder;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.List;


/**
 * @ClassName  SAX方式解析XML
 * @Description TODO
 * @Author wushaopei
 * @Date 2019/8/1 14:45
 * @Version 1.0
 */
public class ReadXmlBySAX {

    private SAXParserFactory sParserFactory = null;
    private SAXParser parser = null;


    public List<InvoiceOrder> getInvoiceOrders(String fileName) throws Exception {
        SAXParserFactory sParserFactory = SAXParserFactory.newInstance();
        SAXParser parser = sParserFactory.newSAXParser();

        SAXParseHandler handler = new SAXParseHandler();
        parser.parse(fileName, handler);

        return handler.getInvoiceOrders();

    }
}