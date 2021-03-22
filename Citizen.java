/**
 * Class for creating citizen Objects
 */
public class Citizen {
    private int amka;
    private String fullName;
    private String city;

    private Appointment appointment;
    
    /**
     * Constructor
     * @param amka
     * @param fullName
     * @param city
     */
    public Citizen(int amka, String fullName, String city) {
        this.amka = amka;
        this.fullName = fullName;
        this.city = city;
    }
    //Getters and Setters for each member variable
    public int getAmka() {
        return amka;
    }
    public String getCity() {
        return city;
    }
    public String getFullName() {
        return fullName;
    }
    public Appointment getAppointment() {
        return appointment;
    }
    public void setAmka(int amka) {
        this.amka = amka;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public void print(){
        System.out.println("Amka: "+getAmka()+", Full Name: "+getFullName()+", City: "+getCity());
    }
}
