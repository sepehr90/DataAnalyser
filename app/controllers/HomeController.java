package controllers;



import com.avaje.ebean.Ebean;
import models.BarChartData;
import models.User;
import play.mvc.*;
import play.data.*;
import play.twirl.api.Html;
import views.html.*;
import javax.persistence.OptimisticLockException;
import static play.data.Form.form;


/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    private Html D3DataNPost;

    public HomeController() {
        D3DataNPost = BarChartData.chartData();
    }

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public static class Login {
        public String email;
        public String password;
    }

    //Retrieve data from database with ebean
    public Result index() {
        //List<SilkroadData> posts = SilkroadData.getALLPosts();
        //List<BarChartData> counts = BarChartData.getBarChartData();
        //Html D3DataNPost = BarChartData.chartData();
        //Html D3DataDate = SilkroadData.chartData();
        //String[] wordbag = SilkroadData.wordbag();
        return ok(index.render(D3DataNPost));
    }

    public Result login() {
        return ok(login.render(form(Login.class)));
    }
    public Result signup() { return ok(signup.render("SignUp")); }
    public Result contact() { return ok(contact.render("Contact")); }
    public Result aboutme() { return ok(aboutme.render("Me")); }
    public Result frequencychart() {
        return ok(frequencychart.render("slider"));
    }



    public Result authenticate() {
        Form<Login> loginForm = form(Login.class).bindFromRequest();
        if (loginForm.hasErrors()) {
            return badRequest(login.render(loginForm));
        } else {
            session().clear();
            session("email", loginForm.get().email);
            return redirect(
                    routes.HomeController.index()
            );
        }
    }

    public static Result logout() {
        session().clear();
        flash("success", "You've been logged out");
        return redirect(
                routes.HomeController.login()
        );
    }

    /*public Result adduser() {
        Form <User> = Form.form(User.class).bindFromRequest().get();
        try {
            Ebean.save(newUser);
            } catch (OptimisticLockException e) {
            e.printStackTrace();
            return internalServerError("Car registration failed");
        }

        return ok();
    }*/



}
