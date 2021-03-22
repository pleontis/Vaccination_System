import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Vector;

public class Doctor {
    
    private int id;
    private String fullName;

    private Vector<Appointment> listOfAppointments;
    private int numOfAppointments;

    public Doctor(int id, String fullName) {
        this.id=id;
        this.fullName=fullName;

        listOfAppointments=new Vector<Appointment>();
        this.numOfAppointments=0;
    }
    
    public int getId() {
        return id;
    }
    public String getFullName() {
        return fullName;
    }
    public Vector<Appointment> getListOfAppointments() {
        return listOfAppointments;
    }
    public int getNumOfAppointments() {
        return numOfAppointments;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public void addAppointment(Appointment appointment){
        listOfAppointments.add(appointment);
        this.numOfAppointments++;
    }
    public void setNumOfAppointments(int numOfAppointments) {
        this.numOfAppointments = numOfAppointments;
    }
    public void printAppointments(){
        for(int i=0;i<listOfAppointments.size();i++){
            listOfAppointments.get(i).printAppointment();
        }
    }
    public void print(){
        System.out.println("Id: "+getId()+", Full Name: "+getFullName());
    }
    public boolean isAvailable(LocalDate date, LocalTime time){
        for(int i=0;i<getListOfAppointments().size();i++){
            Appointment appointment=getListOfAppointments().get(i);
            if(appointment.getDay().isEqual(date)){
                if(appointment.getTime().equals(time)){
                    System.out.println("There is already an appointment at this specific time slot");
                    return false;
                }
            }
        }
        return true;
    }
}
