import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;
import java.util.Vector;

/**
 * Class for creating VaccinationCenter Objects
 */
public class VaccinationCenter {
    public static final int MAX_DOCTORS=5;
    public static final int MAX_VACCINATIONS_PER_DOCTOR=4;
    static Scanner scanner = new Scanner(System.in);

    private int kek;
    private String title;
    private String city;
    
    private Vector<Doctor> listOfDoctors;
    private Vector<Appointment> listOfAppointments;

    /**
    * Constructor 
    * @param kek
    * @param title
    * @param city
    */
    public VaccinationCenter(int kek, String title, String city) {
        this.kek=kek;
        this.title=title;
        this.city=city;

        this.listOfDoctors=new Vector<Doctor>();
        this.listOfAppointments=new Vector<Appointment>();

    }
    //Getters and Setters for each member variable
    public int getKek() {
        return kek;
    }
    public String getCity() {
        return city;
    }
    public String getTitle() {
        return title;
    }
    public void setKek(int kek) {
        this.kek = kek;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Vector<Doctor> getListOfDoctors() {
        return listOfDoctors;
    }
    public Vector<Appointment> getListOfAppointments() {
        return listOfAppointments;
    }

    public void addDoctor(Doctor doctor){
        if(listOfDoctors.size()<MAX_DOCTORS){
            listOfDoctors.add(doctor);
            return;
        }
        System.out.println("Sorry there is not any space for a new Doctor registration");
    }
    public Doctor searchDoctor(int id){
        for(int i=0;i<listOfDoctors.size();i++){
            if (id==listOfDoctors.get(i).getId()) {
                return listOfDoctors.get(i);
            }
        }
        return null;
    }
    public void addAppointment(Appointment appointment){
        if(listOfAppointments.size()<listOfDoctors.size()*MAX_VACCINATIONS_PER_DOCTOR){
            listOfAppointments.add(appointment);
            appointment.getCitizen().setAppointment(appointment);
            appointment.getDoctor().addAppointment(appointment);
            return;
        }
        System.out.println("Sorry there is not any space for a new Appointment registration");
    }
    /**
     * Method for making a new Radevu. Get the Date and Time from user and locate a specific
     * slot into Center's Schedule. Find an available Doctor and make a Radevu.
     * @param id
     * @param citizen
     * @param vCenter
     * @return Appointment or null if could not schedule
     */
    public Appointment makeAppointment(int id,Citizen citizen,VaccinationCenter vCenter){
        Appointment appointment=null;
        
        System.out.println("Please pick a Day By number:\n1.Day1\n2.Day2\n3.Day3\n4.Day4\n5.Day5\n6.Day6\n7.Day7 ");
        int day=scanner.nextInt();
        System.out.println("Please a time slot By number:\n1.9:00-9:30\n2.9:30-10:00\n3.10:00-10:30\n4.10:30-11:00");
        int time=scanner.nextInt();

        LocalDate localDate=LocalDate.now();
        LocalTime localTime=null;
        
        switch (day){
            case 1:
                localDate = localDate.plusDays(1);
                break;
            case 2:
                localDate = localDate.plusDays(2);
                break;
            case 3:
                localDate=localDate.plusDays(3);
                break;
            case 4:
                localDate=localDate.plusDays(4);
                break;
            case 5:
                localDate=localDate.plusDays(5);
                break;
            case 6:
                localDate=localDate.plusDays(6);
                break;
            case 7:
                localDate=localDate.plusDays(7);
                break;
            default:
                break;
        }
        switch(time){
            case 1:
                localTime=LocalTime.of(9, 0, 0);
                break;
            case 2:
                localTime=LocalTime.of(9, 30, 0);
                break;
            case 3:
                localTime=LocalTime.of(10, 0, 0);
                break;
            case 4:
                localTime=LocalTime.of(10, 30, 0);
                break;
            default:
                break;
        }
        Doctor doctor=findAvailDoctor();
        if(doctor!=null){
            if(doctor.isAvailable(localDate, localTime)){
                appointment=new Appointment(id,localDate,localTime, citizen, vCenter, doctor);
                doctor.addAppointment(appointment);
                return appointment;
            }  
        }
        System.out.println("There is not any available Doctor found");
        return appointment;
    }

    public void printAppointments() {
        for(int i=0;i<listOfAppointments.size();i++){
            listOfAppointments.get(i).printAppointment();
        }
    }
    public void print(){
        System.out.println("Kek: "+getKek()+", Title: "+getTitle()+", City: "+getCity());
    }
    /**
     * Search into list of Doctors and find the one with less Radevus scheduled
     * @return Doctor or null if could not find
     */
    public Doctor findAvailDoctor(){
        Doctor tempDoctor=null;
        if(!listOfDoctors.isEmpty()){
            tempDoctor = listOfDoctors.get(0);
            for(int i=1;i<listOfDoctors.size();i++){
                if(listOfDoctors.get(i).getNumOfAppointments()<tempDoctor.getNumOfAppointments()){
                   tempDoctor=listOfDoctors.get(i);
                }
            }
        }
        return tempDoctor;
    }
}