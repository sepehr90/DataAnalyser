package models;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.text.json.JsonContext;
import play.data.format.Formats;
import play.db.ebean.Model;
import play.twirl.api.Html;

import javax.persistence.Entity;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sepehr on 9/21/2016.
 */
@Entity
public class DateOfPosts extends Model {
    public int numberofposts;
    public String dateofposts;

    public static Model.Finder<String, DateOfPosts> find = new Model.Finder<String, DateOfPosts>(
            String.class, DateOfPosts.class
    );

    public static List<DateOfPosts> getTotallNumberofPostsInDate() {
        List<DateOfPosts> dates = new ArrayList<DateOfPosts>();
        //posts = Ebean.find(SilkroadData.class).findList();
        //posts = Ebean.find(SilkroadData.class).where().eq("username", "TrashBox").findList();
        dates = Ebean.find(DateOfPosts.class).findList();
        return dates;
    }

    //passing data from Sql in Json format
    public static String JsonStyleData() {
        JsonContext json = Ebean.json();
        String jsonOutput = json.toJson(getTotallNumberofPostsInDate());
        return jsonOutput;
    }

    //passing jason format data in tml
    public static Html chartDataInTime() {
        Html chartData = new Html(JsonStyleData());
        return chartData;
    }
}
