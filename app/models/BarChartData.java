package models;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.text.json.JsonContext;
import play.db.ebean.Model;
import play.twirl.api.Html;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sepehr on 11/24/2016.
 */
@Entity
public class BarChartData extends Model {
    public String username;
    public int numberofposts;
    public String topfiveposts;
    public String mostactivedate;
    @Column(columnDefinition = "TEXT")
    public String recentpost;

    public static Model.Finder<String,BarChartData> find = new Model.Finder<>(
            String.class, BarChartData.class
    );

    public static List<BarChartData> getBarChartData(){
        List<BarChartData> Data;
        //posts = Ebean.find(SilkroadData.class).findList();
        //posts = Ebean.find(SilkroadData.class).where().eq("username", "TrashBox").findList();
        //Data = Ebean.find(BarChartData.class).findList();
        Data = Ebean.find(BarChartData.class).select("username,numberofposts,topfiveposts").findList();
        return Data;
    }
    //passing data from Sql in Json format
    public static String JsonStyleData(){
        JsonContext json = Ebean.json();
        String jsonOutput = json.toJson(getBarChartData());
        return jsonOutput;
    }
    //passing jason format data in Html
    public static Html chartData(){
        Html chartData = new Html(JsonStyleData());
        return chartData;
    }
}
