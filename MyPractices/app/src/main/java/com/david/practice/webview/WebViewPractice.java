package com.david.practice.webview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

import com.david.practice.R;

/**
 * Created by Administrator on 2016/11/21.
 */
public class WebViewPractice extends Activity {
    WebView webView;
    String data = "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\"\"http://www.w3.org/TR/html4/loose.dtd\">\n" +
            "<html>\n" +
            "<meta http-equiv=\"content-type\" content=\"text/html; charset=utf-8\">\n" +
            "<head>\n" +
            "<title>文档标题</title>\n" +
            "</head>\n" +
            "\n" +
            "<script>\n" +
            "<!-- 本地网页js脚本 带参方法>\n" +
            "function insert(string){\n" +
            "    document.getElementById(\"content\").innerHTML += string + \"<br/>\";//在content标签段落加入新行\n" +
            "}\n" +
            "\n" +
            "function showAndroidToast() \n" +
            "{\n" +
            "    var name = document.getElementById(\"username\").value;//获取输入框的内容\n" +
            "    window.demo.showToast(name);//将输入内容传给android提示  demo是绑定对象别名\n" +
            "}\n" +
            "</script>\n" +
            "\n" +
            "<body>\n" +
            "    <div>  \n" +
            "        <p>输入用户账号：</p>\n" +
            "        <input id=\"username\" style=\"height:30px;width:200px\"/> \n" +
            "    </div>\n" +
            "    <br>\n" +
            "    <div> \n" +
            "         <input type=\"submit\" value=\"提交\" style=\"height:51px;width:200px\" onclick=\"showAndroidToast()\"/>\n" +
            "    </div>\n" +
            "    <p id=\"content\"></p>\n" +
            "\n" +
            "</body>\n" +
            "\n" +
            "</html>";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview_practice);
        webView = (WebView) findViewById(R.id.webview_demo);
        Button loadData = (Button) findViewById(R.id.load_data);
        Button loadUrl = (Button) findViewById(R.id.load_url);
        Button loadUrlHttp = (Button) findViewById(R.id.load_url_http);
        loadData.setOnClickListener(getOnClickListener());
        loadUrl.setOnClickListener(getOnClickListener());
        loadUrlHttp.setOnClickListener(getOnClickListener());

    }
    public View.OnClickListener getOnClickListener(){
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(v.getId()){
                    case R.id.load_data:
                        webView.loadData(data,"text/html;charset=utf-8",null);
                        break;
                    case R.id.load_url:
                        webView.loadUrl("file:///android_asset/changecity.html");
                        break;
                    case R.id.load_url_http:
                        webView.loadUrl("http://www.baidu.com");
                        break;
                }
            }
        };
        return onClickListener;
    }
}
