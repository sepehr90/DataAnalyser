package models;

import javax.persistence.*;
import javax.validation.Constraint;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.RawSql;
import com.avaje.ebean.RawSqlBuilder;
import com.avaje.ebean.text.json.JsonContext;
import play.db.ebean.*;
import play.twirl.api.Html;
import scala.util.parsing.json.JSONArray;
import scala.util.parsing.json.JSONObject;

import java.util.*;

/**
 * Created by Sepehr on 9/12/2016.
 */
@Entity
public class SilkroadData extends Model {

    public String source;
    @Column(columnDefinition = "TEXT")
    public String topic;
    public String discussionnumber;
    public String username;
    public String publishdate;
    @Column(columnDefinition = "TEXT")
    public String content;
    public String language;


    public static Model.Finder<String, SilkroadData> find = new Model.Finder<String, SilkroadData>(
            String.class, SilkroadData.class
    );

    public static List<SilkroadData> getALLPosts() {
        /*RawSql rawSql = RawSqlBuilder.parse("SELECT COUNT(*) AS NumberOfPosts, username FROM silkroad.silkroad_data\n" +
                "group by username")
                .columnMapping("username", "username")
                .columnMapping("NumberOfPosts", "NumberOfPosts")
                .create();

        com.avaje.ebean.Query<SilkroadData> query = Ebean.find(SilkroadData.class);
        query.setRawSql(rawSql);
        List<SilkroadData> posts = query.findList();*/
        List<SilkroadData> posts = new ArrayList<SilkroadData>();
        //posts = Ebean.find(SilkroadData.class).findList();
        //posts = Ebean.find(SilkroadData.class).where().eq("username", "TrashBox").findList();
        posts = Ebean.find(SilkroadData.class).findList();
        return posts;
    }

    public static List<SilkroadData> getcontents() {
        List<SilkroadData> contents = new ArrayList<SilkroadData>();
        contents = Ebean.find(SilkroadData.class).select("content").where().eq("username", "TrashBox").findList();
        return contents;
    }

    public static List<String> topKWords(final String stream, final int k) {
        final class WordFreq implements Comparable<WordFreq> {

            String word;
            int freq;

            public WordFreq(final String w, final int c) {
                word = w;
                freq = c;
            }

            @Override
            public int compareTo(final WordFreq other) {
                return Integer.compare(this.freq, other.freq);
            }
        }
        final Map<String, Integer> frequencyMap = new HashMap<String, Integer>();
        final PriorityQueue<WordFreq> topKHeap = new PriorityQueue<WordFreq>(k);

        final String[] words = stream.toLowerCase().trim().split(" ");
        for (final String word : words) {
            int freq = 1;
            if (frequencyMap.containsKey(word)) {
                freq = frequencyMap.get(word) + 1;
            }

            // update the frequency map
            frequencyMap.put(word, freq);
        }

        // Build the topK heap
        for (final java.util.Map.Entry<String, Integer> entry : frequencyMap.entrySet()) {
            if (topKHeap.size() < k) {
                topKHeap.add(new WordFreq(entry.getKey(), entry.getValue()));
            } else if (entry.getValue() > topKHeap.peek().freq) {
                topKHeap.remove();
                topKHeap.add(new WordFreq(entry.getKey(), entry.getValue()));
            }
        }

        // extract the top K
        final String[] topK = new String[k];
        int i = 0;
        while (topKHeap.size() > 0) {
            topK[i++] = topKHeap.remove().word;
        }

        List myList = new ArrayList();
        Collections.addAll(myList, topK);
        return myList;
    }

    public static List<String> wordbag() {
        String[] stockArr = new String[getcontents().size()];
        for (int i = 0; i < getcontents().size(); i++) {
            stockArr[i] = getcontents().get(i).toString();
        }
        String listString = "";

        for (String s : stockArr) {
            listString += s + "\t";
        }

        return topKWords(listString, 10);
    }

    //passing data from Sql in Json format
    public static String JsonStyleData() {
        JsonContext json = Ebean.json();
        String jsonOutput = json.toJson(getALLPosts());
        return jsonOutput;
    }

    //passing jason format data in tml
    public static Html chartData() {
        Html chartData = new Html(JsonStyleData());
        return chartData;
    }

}
