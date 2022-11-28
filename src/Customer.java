import java.util.ArrayList;
import java.util.List;

public class Customer extends ReadFile { //används ej
    protected String SocialSecurityNumber;
    protected String name;
    protected int date;
    protected boolean isCustomer;

    public boolean isCustomer() {
        return isCustomer;
    }

    public void setCustomer(boolean customer) {
        isCustomer = customer;
    }

    protected List<String> visits = new ArrayList<>();

    public List<String> getVisits() {
        return visits;
    }

    public void setVisits(String s) {
         this.visits.add(s);
    }

    public String getSocialSecurityNumber() {
        return SocialSecurityNumber;
    }

    public void setSocialSecurityNumber(String SSNumber) {
        this.SocialSecurityNumber = SSNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }
}
