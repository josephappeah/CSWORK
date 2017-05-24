import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jsoup.Connection;
/**
 * Created by orion on 5/21/17.
 */
public class AnalyzerTest {
    @Test
    public void exampleTest() throws Exception{
        Analyzer analyzer = new Analyzer();
        int val = analyzer.analyzeString("wise men bleed after a crime");
        int val2 = analyzer.analyzeString("Abolish crime so men do not bleed, bleed, bleed");
        int val3 = analyzer.analyzeString("Wise people rule fairly and are supreme leaders. " +
                "Hopefully wise people will rule");

        assertEquals(-1, val);
        assertEquals(-5, val2);
        assertEquals(4, val3);

        Document doc = JSoupScraper.getWebsiteContent("http://www.greattreks.com/");
        Elements elements = JSoupScraper.selectElementsFromDocument(doc, "h1, h2, strong");
        //code coverage 
        Connection con = JSoupScraper.connect("http://www.greattreks.com/");
        assertNotNull(con);
        Document gDoc1 = JSoupScraper.getDocumentFromConnection(con);
        assertNotNull(gDoc1);
        
        
        Document doc_1 = JSoupScraper.getWebsiteContent("http://www.greattreks.com/");
        Elements elements_1 = JSoupScraper.selectElementsFromDocument(doc_1, "h1, h2, strong");
        
        Document doc_2 = JSoupScraper.getWebsiteContent("https://seanpgrimes.com");
        Elements elements_2 = JSoupScraper.selectElementsFromDocument(doc_2, "h1, h2, strong");
        
        
        Document doc_3 = JSoupScraper.getWebsiteContent("http://www.businessinsider.com/");
        Elements elements_3 = JSoupScraper.selectElementsFromDocument(doc_3, "h1, h2, strong");
        
        Document doc_4 = JSoupScraper.getWebsiteContent("https://www.yahoo.com/");
        Elements elements_4 = JSoupScraper.selectElementsFromDocument(doc_4, "h1, h2, strong");
        
       
        Document doc_5 = JSoupScraper.getWebsiteContent("http://www.espn.com/");
        Elements elements_5 = JSoupScraper.selectElementsFromDocument(doc_5, "h1, h2, strong");
        
        ArrayList<Integer> list = new ArrayList<Integer>();
      
        int strong_sentiment = 0;
        for(Element e : elements){
        	 strong_sentiment += analyzer.analyzeString(e.text());
        }
       // list.add(strong_sentiment);
           
        Map<String, Integer> map = new HashMap<String, Integer>();
        int strong_sentiment_1 = 0;
        for(Element e : elements_1){
        	strong_sentiment_1 += analyzer.analyzeString(e.text());
        }
        map.put("ttp://www.greattreks.com/",strong_sentiment_1);
        list.add(strong_sentiment_1);
        
        int strong_sentiment_2 = 0;
        for(Element e : elements_2){
        	strong_sentiment_2 += analyzer.analyzeString(e.text());
        }
        map.put( "https://seanpgrimes.com",strong_sentiment_2);
        list.add(strong_sentiment_2);
        
        int strong_sentiment_3 = 0;
        for(Element e : elements_3){
        	strong_sentiment_3 += analyzer.analyzeString(e.text());
        }
        map.put("http://www.businessinsider.com/", strong_sentiment_3);
        list.add(strong_sentiment_3);

        int strong_sentiment_4 = 0;
        for(Element e : elements_4){
        	strong_sentiment_4 += analyzer.analyzeString(e.text());
        }
        map.put("https://www.yahoo.com/", strong_sentiment_3);
        list.add(strong_sentiment_4);
        
        int strong_sentiment_5 = 0;
        for(Element e : elements_5){
        	strong_sentiment_5 += analyzer.analyzeString(e.text());
        }
        map.put("http://www.espn.com/", strong_sentiment_3);
        list.add(strong_sentiment_5);

        assertEquals(3, strong_sentiment);
        assertEquals("Neutral", analyzer.getSentiment(strong_sentiment));
        assertNotEquals("Positive", analyzer.getSentiment(strong_sentiment));
        assertNotEquals("Negative", analyzer.getSentiment(strong_sentiment));
        
        Map<String, Integer> sortedMap = sortByValue(map);
        printMap(sortedMap);
        
    }
    
    private static Map<String, Integer> sortByValue(Map<String, Integer> unsortMap) 
    {

        // 1. Convert Map to List of Map
        List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(unsortMap.entrySet());

        // 2. Sort list with Collections.sort(), provide a custom Comparator
        //    Try switch the o1 o2 position for a different order
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() 
        {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) 
            {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        // 3. Loop the sorted list and put it into a new insertion order Map LinkedHashMap
        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }

    public static <K, V> void printMap(Map<K, V> map) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            System.out.println(entry.getKey() +": "+ entry.getValue());
        }
    }
}