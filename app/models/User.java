package models;

import javax.persistence.*;
import com.avaje.ebean.Ebean;
import com.avaje.ebean.text.json.JsonContext;
import play.data.validation.Constraints;
import play.db.ebean.Model;
import play.twirl.api.Html;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

    @Entity
    public class User extends Model {

        @Id @GeneratedValue
        public int id;
        @Constraints.Required
        public String firstname;
        @Constraints.Required
        public String lastname;
        @Constraints.Required @Column(columnDefinition = "TEXT")
        public String adress;
        public String city;
        public String state;
        public String zip;
        public String title;
        public String company;
        public String phonenumber;
        @Column(unique = true)
        public String email;
        public String website;
        public String password;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getFirstname() {
            return firstname;
        }

        public void setFirstname(String firstname) {
            this.firstname = firstname;
        }

        public String getLastname() {
            return lastname;
        }

        public void setLastname(String lastname) {
            this.lastname = lastname;
        }

        public String getAdress() {
            return adress;
        }

        public void setAdress(String adress) {
            this.adress = adress;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getZip() {
            return zip;
        }

        public void setZip(String zip) {
            this.zip = zip;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getPhonenumber() {
            return phonenumber;
        }

        public void setPhonenumber(String phonenumber) {
            this.phonenumber = phonenumber;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getWebsite() {
            return website;
        }

        public void setWebsite(String website) {
            this.website = website;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        /*Play has been designed to generate getter/setter automatically, to ensure compatibility with libraries that
        expect them to be available at runtime (ORM, Databinder, JSON Binder, etc).
        If Play detects any user-written getter/setter in the Model, it will not generate
        getter/setter in order to avoid any conflict.*/

        /*public User(String email, String name, String password) {
            this.email = email;
            this.name = name;
            this.password = password;
        }*/

        public static Finder<String,User> find = new Finder<String,User>(
                String.class, User.class
        );

        public String validate() {
            if(User.authenticate(email, password) == null) {
                return "Invalid user or password";
            }
            return null;
        }

        public static User authenticate(String email, String password) {

            System.out.println(email + " " + password); // Check if form data is passed.

            return find.where()
                    .eq("email", email)
                    .eq(password, password)
                    .findUnique();

        }
    }
