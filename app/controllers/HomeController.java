package controllers;



import models.BarChartData;
import play.mvc.*;

import play.twirl.api.Html;
import views.html.*;





/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    // public Result index() {
    //     return ok(index.render("Welcome to Data Analysing Forum!"));
    // }

    //Navigating to login page
    // public Result login() {
    //  return ok(index.render("Welcome to Login!"));
    //}
    //navigate to main page

    //Retrieve data from database with edean
    public Result index() {
        //List<SilkroadData> posts = SilkroadData.getALLPosts();
        //List<BarChartData> counts = BarChartData.getBarChartData();
        Html D3DataNPost = BarChartData.chartData();
        //Html D3DataDate = SilkroadData.chartData();
        //String[] wordbag = SilkroadData.wordbag();
        return ok(index.render(D3DataNPost));
    }

    public Result login() {
        return ok(login.render("Login"));
    }

    public Result frequencychart() {
        return ok(frequencychart.render("slider"));
    }

   // public Result frequencychart() {
   //     List<SilkroadData> wordbag = SilkroadData.getALLPosts();
   //     return ok(frequencychart.render("Fchart", wordbag));
        //rendertemplate("Fchart", wordbag);
   // }

    //public Result qcontents() {
    //    List<String> ALL_contents = SilkroadData.getALLPosts();
    //    return ok(qcontents.render(ALL_contents));
    //}
    /*public Result ReturnUserInfo(){

        return ok(index.render(numberofposts));
    }*/
    /*public Html login(){
        return (views.html.login.render());
    }*/


}
