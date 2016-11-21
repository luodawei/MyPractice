package com.david.practice.xmlsax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/21.
 * 解析监听回调类
 */
public class MySaxHandler extends DefaultHandler{
    //Hander的解析过程
    //先执行startElememt读取开始节点，调用character方法解析节点内的内容，调用endElement读取结束节点
    ArrayList<Person> persons;
    Person person;
    public String TagName;
    public MySaxHandler(ArrayList<Person> persons){
        this.persons = persons;
    }
    public ArrayList<Person> getData(){
        return persons;
    }
    //开始解析头标签
    @Override
    public void startElement(String uri,
                             String localName, //解析数据的根节点
                             String qName,//合格名称
                             Attributes attributes//属性的工具类
    ) throws SAXException {
        //解析到某个根节点的时候进行记录，用于在character中设置标记
        //用于解析里层内容
        if (localName!=null){
            //当首次读到person的头标签时，重新new一个对象
            if (localName.equals("person")){
                person = new Person();
            }
            this.TagName = localName;
            String id = attributes.getValue("id");//通过id这个key拿的id对应的值
            //强转
            if(id!=null){
                int intID =Integer.parseInt(id);
                person.id = intID;
            }
        }
        super.startElement(uri, localName, qName, attributes);
    }
    //解析里层节点的内容
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (TagName!=null){
            String data = new String(ch,start,length);
            if (TagName.equals("name")){
                person.name = data;
            }
            if (TagName.equals("age")){
                person.age = Short.parseShort(data);
            }
        }
        super.characters(ch, start, length);
    }
    //当读取到结束标签时的回调

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (localName.equals("person")){
            persons.add(person);
        }
        this.TagName = null;
        super.endElement(uri, localName, qName);
    }
}
