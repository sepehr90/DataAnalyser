package models;

import javax.persistence.*;
import com.avaje.ebean.Ebean;
import com.avaje.ebean.text.json.JsonContext;
import play.db.ebean.Model;
import play.twirl.api.Html;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

    @Entity
    public class User extends Model {

        @Id
        public String email;
        public String name;
        public String password;

        public User(String email, String name, String password) {
            this.email = email;
            this.name = name;
            this.password = password;
        }

        public static Finder<String,User> find = new Finder<String,User>(
                String.class, User.class
        );
    }
