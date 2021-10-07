package com.example.login;

import android.os.AsyncTask;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class ExchangeParser extends AsyncTask<String, Void, List<Exchange>> {

    @Override
    protected List<Exchange> doInBackground(String... strings) {
        try {
            List<Exchange> lista=new ArrayList<>();
            URL url=new URL(strings[0]);
            HttpURLConnection http=(HttpURLConnection)url.openConnection();

            InputStream inputStream=http.getInputStream();

            DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
            DocumentBuilder builder=factory.newDocumentBuilder();
            Document document=builder.parse(inputStream);

            if(document!=null){
                NodeList rates=document.getElementsByTagName("currency");
                for(int i=0;i<rates.getLength();i++){
                    Node node=rates.item(i);

                   // String curs;
                    if(node!=null && node.getNodeType()==Node.ELEMENT_NODE) {
                        Element element = (Element) node;
//                        Exchange rate=new Exchange(
//                                element.getAttribute("currency"),element.getAttribute("sell").Double.parseDouble(element.getTextContent())
//                        );
                       String curs = element.getAttribute("name");

                       float sell=Float.parseFloat(element.getElementsByTagName("value").item(0).getTextContent());
                       float buy=Float.parseFloat(element.getElementsByTagName("value").item(1).getTextContent());
                        DecimalFormat df = new DecimalFormat("#.##");
                        df.setRoundingMode(RoundingMode.DOWN);
                        double s=Math.round(sell * 1000)/ 1000.0;
                        double b=Math.round(buy * 1000)/ 1000.0;


                        //Math.round(value * Math.pow(10, scale)) / Math.pow(10, scale);
                        Exchange rata=new Exchange(curs,s,b);
                        //rata.setCurs(curs);
                        lista.add(rata);
                    }
                    //float sell=Float.parseFloat(node.getFirstChild().getNodeValue());
                    //float buy=Float.parseFloat(node.getLastChild().getNodeValue());
//
                    //rata.setCumparare(buy);
                   // rata.setVanzare(sell);

                    }
                }
                return lista;


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }


        return null;
    }
}
