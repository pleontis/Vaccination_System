import java.time.LocalDate;
import java.time.LocalTime;
/**
 * Class for creating Appointment Objects
 */
public class Appointment {

    private int id;
    private LocalDate day;
    private LocalTime time;
    private Citizen citizen;
    private VaccinationCenter vCenter;
    private Doctor doctor;

    /**
     * Constructor
     * @param id
     * @param day
     * @param time
     * @param citizen
     * @param vCenter
     * @param doctor
     */
    public Appointment(int id, LocalDate day,LocalTime time, Citizen citizen, VaccinationCenter vCenter, Doctor doctor) {
        this.id=id;
        this.day=day;
        this.time=time;
        this.citizen=citizen;
        this.vCenter=vCenter;
        this.doctor=doctor;
    }
    //Getters and Setters for each member variable
    public int getId() {
        return id;
    }
    public LocalDate getDay() {
        return day;
    }
    public LocalTime getTime() {
        return time;
    }
    public Citizen getCitizen() {
        return citizen;
    }
    public VaccinationCenter getvCenter() {
        return vCenter;
    }
    public Doctor getDoctor() {
        return doctor;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setDay(LocalDate day) {
        this.day = day;
    }
    public void setTime(LocalTime time) {
        this.time = time;
    }
    public void setCitizen(Citizen citizen) {
        this.citizen = citizen;
    }
    public void setvCenter(VaccinationCenter vCenter) {
        this.vCenter = vCenter;
    }
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public void printAppointment(){
        System.out.println("1.Appointment's Id: "+this.getId()+
                          "\n2.Citizen: "+this.citizen.getFullName()+
                          "\n3.Vaccination Center: "+this.vCenter.getTitle()+
                          "\n4.Doctor: "+this.doctor.getFullName()+
                          "\n5.Date: "+this.getDay().toString()+" "+getTime().toString());
    }
}