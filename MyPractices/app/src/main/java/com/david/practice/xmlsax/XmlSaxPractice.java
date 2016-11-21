package com.david.practice.xmlsax;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.david.practice.R;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by Administrator on 2016/11/21.
 */
public class XmlSaxPractice extends Activity {
    ArrayList<Person> persons;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xmlsax_practice);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.xmlsax_practice);
        for(int i = 0;i<3;i++){
            Button button = new Button(this);
            button.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            button.setText("Button"+i);
            button.setId(300+i);
            button.setOnClickListener(getOnClickListener());
            linearLayout.addView(button);
        }
        persons = new ArrayList<Person>();
    }
    public View.OnClickListener getOnClickListener(){
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case 300:
                        saxDemo();
                        break;
                    case 301:
                        DomDemo();
                        break;
                    case 302:
                        break;
                }
            }
        };
        return onClickListener;
    }
    public void saxDemo(){
        try {
            //打开文件，转换成输入流
            InputStream inputStream = this.getAssets().open("person.xml");
            //创建一个Sax解析工程对象对象
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            //创建一个解析对象
            SAXParser saxParser = saxParserFactory.newSAXParser();
            //创建一个解析监听回调函数
            MySaxHandler mySaxHandler = new MySaxHandler(persons);
            //绑定监听，解析对应的流
            saxParser.parse(inputStream,mySaxHandler);
            persons = mySaxHandler.getData();
            for (int i = 0;i<persons.size();i++){
                Person person = persons.get(i);
                Log.i("person.id",""+person.id);
                Log.i("person.name",""+person.name);
                Log.i("person.age",""+person.age);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }
    public void DomDemo(){
        try {
            InputStream inputStream = this.getAssets().open("person.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            Document document = documentBuilder.parse(inputStream);
            Element element = document.getDocumentElement();
            //获取指定名称的根节点
            NodeList list = element.getElementsByTagName("person");
            for (int i = 0;i<list.getLength();i++){
                Element ele = (Element) list.item(i);
                Person person = new Person();
                person.setId(Integer.parseInt(ele.getAttribute("id")));
                NodeList sublist = ele.getChildNodes();
                for (int j = 0;j<sublist.getLength();j++){
                    Node node = sublist.item(j);
                    if (node.getNodeType() == Node.ELEMENT_NODE){
                        Element childNode = (Element) node;
                        if (childNode.getNodeName().equals("name")){
                            person.setName(childNode.getFirstChild().getNodeValue());//通过节点找到对应的值
                        }
                        if (childNode.getNodeName().equals("age")){
                            person.setAge(Short.parseShort(childNode.getFirstChild().getNodeValue()));
                        }
                    }
                }
                persons.add(person);
            }
            for (int i= 0;i<persons.size();i++){
                Person person = persons.get(i);
                Log.i("person.id",""+person.id);
                Log.i("person.name",""+person.name);
                Log.i("person.age",""+person.age);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }
}
