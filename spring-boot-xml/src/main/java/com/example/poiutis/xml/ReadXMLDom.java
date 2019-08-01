package com.example.poiutis.xml;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * @ClassName Dom加载解析获取xml文件内容，包括标签
 * @Description TODO
 * @Author wushaopei
 * @Date 2019/8/1 12:23
 * @Version 1.0
 */
public class ReadXMLDom {

    public void readXML(File file) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            // ❷Ⅱ获得DocumentBuilder
            DocumentBuilder builder = factory.newDocumentBuilder();
            // ❸Ⅲ--获得文档对象--
            Document doc = builder.parse(file);
            // ❹Ⅳ获得根元素
            Element element = doc.getDocumentElement();
            // ❺Ⅴ用方法遍历递归打印根元素下面所有的ElementNode(包括属性,TextNode非空的值),用空格分层次显示.
            listAllChildNodes(element, 0);// 参数0表示设定根节点层次为0,它的前面不打印空格.
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listAllChildNodes(Node node, int level) {
        // 只处理ElementNode类型的节点,感觉这种类型的节点(还有有效的文本节点)才是真正有用的数据,其他注释节点,空白节点等都用不上.
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            boolean hasTextChild = false;// 变量表示该节点的第一个子节点是否就是一个有有效内容的文本节点)
            // Ⅰ❶【打印 - 空格】空格的长度 - level(n级ElementNode有n个长度的空格在前面)
            String levelSpace = "";
            for (int i = 0; i < level; i++) {
                levelSpace += "    ";
            }
            // Ⅱ❷【打印 - 开始标签】先打印ElementNode的开始标签(有属性的话也要打印)
            System.out.print(levelSpace + "<" + node.getNodeName()
                    + (node.hasAttributes() ? " " : ">"));// 有属性的话节点的开始标签后面的尖括号">"就留待属性打印完再打印
            // Ⅲ❸【打印 - 属性】遍历打印节点的所有属性
            if (node.hasAttributes()) {
                NamedNodeMap nnmap = node.getAttributes();
                for (int i = 0; i < nnmap.getLength(); i++) {
                    System.out.print(nnmap.item(i).getNodeName()
                            + "=\""// 字符串里含双引号要用到转义字符\
                            + nnmap.item(i).getNodeValue() + "\""
                            + (i == (nnmap.getLength() - 1) ? "" : " "));// 不是最后一个属性的话属性之间要留空隙
                }
                System.out.print(">");// 开始标签里的属性全部打印完加上尖括号">"
            }
            // Ⅳ❹【打印 - 子节点】该ElementNode包含子节点时候的处理
            if (node.hasChildNodes()) {
                level++;// 有下一级子节点,层次加1,新的层次表示的是这个子节点的层次(递归调用时传给了它)
                // 获得所有的子节点列表
                NodeList nodelist = node.getChildNodes();
                // 循环遍历取到所有的子节点
                for (int i = 0; i < nodelist.getLength(); i++) {
                    // Ⅳ❹❶【有效文本子节点】子节点为TextNode类型,并且包含的文本内容有效
                    if (nodelist.item(i).getNodeType() == Node.TEXT_NODE
                            && (!nodelist.item(i).getTextContent()
                            .matches("\\s+"))) {// 用正则选取内容包含非空格的有效字符的文本节点
                        hasTextChild = true;// 该ElementNode的一级子节点是存在有效字符的文本节点
                        System.out.print(nodelist.item(i).getTextContent());// 在开始标签后面添加文本内容
                        // Ⅳ❹❷【ElementNode子节点】子节点是正常的ElementNode的处理
                    } else if (nodelist.item(i).getNodeType() == Node.ELEMENT_NODE) {
                        System.out.println();
                        // 递归调用方法 - 以遍历该节点下面所有的子节点
                        listAllChildNodes(nodelist.item(i), level);// level表示该节点处于第几个层次(相应空格)
                    }
                }
                level--;// 遍历完所有的子节点,层次变量随子节点的层数,依次递减,回归到该节点本身的层次
                // level++ 和 level--对于该节点的子节点影响的是子节点的初值
            }
            // Ⅴ❺【打印 - 结束标签】打印元素的结束标签.如果它的第一个一级子节点是有效文本的话,文本和结束标签添加到开始标签后面,
            // 层次什么的就作废用不上了,否则,才按层次打印结束标签.
            System.out.print(((hasTextChild) ? "" : "\n" + levelSpace) + "</"
                    + node.getNodeName() + ">");
        }
    }
}
