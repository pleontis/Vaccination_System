import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Random;
import java.util.Scanner;
/**
 * Class with main method for printing menu to user
 */
public class Console {
    //Use Scanner class for reading from System.in user's options
    static Scanner scanner = new Scanner(System.in);
    static Service service;
    static Random ran= new Random();
    public static void main(String[] args) {
        //Create a new System and initialize it with data
        service = new Service("National Vaccination Program", "gov.gr", 7);
        initializeService();
        int uOption=0;
        
        //Keep printing menu to user until Exit option is selected
        while(uOption!=9){
            printMenu();
            System.out.println("-------------------------------------------------");
            System.out.print("Please enter your option: ");
            if(scanner.hasNext()){
                uOption=scanner.nextInt();
            }
            switch (uOption) {
                case 0:
                    continue;
                case 1:
                    addCitizen();
                    break;
                case 2:
                    addVacCenter();
                    break;
                case 3:
                    addDoctor();
                    break;
                case 4:
                    addAppointment();
                    break;
                case 5:
                    searchAppointByKey();
                    break;
                case 6:
                    service.printCitizens();
                    break;
                case 7:
                    service.printVacCenters(); 
                    break;
                case 8:
                    service.printDoctors();
                    break;
                case 9:
                    System.out.println("Thanks for using "+service.getName()+" service");
                    break;
                default:
                    System.out.println("User option: "+ uOption+ "ignored.");
                break;
            }
        }
    }
    /**
     * Type Data for creating a new Citizen
     */
    public static void addCitizen(){
        System.out.println("Enter Citizen's Amka: ");
        int amka=scanner.nextInt();

        System.out.println("Enter Citizen's Full Name: ");
        String fullName=scanner.next();

        System.out.println("Enter Citizen's City: ");
        String city=scanner.next();

        service.addCitizen(new Citizen(amka, fullName, city));
        System.out.println("Citizen was registrated");
    }
    /**
     * Type Data for creating a new Vaccination Center
     */
    public static void addVacCenter(){
        System.out.println("Enter Center's Id (5 digits): ");
        int kek=scanner.nextInt();

        System.out.println("Enter Center's Title: ");
        String title=scanner.next();

        System.out.println("Enter Center's City: ");
        String city=scanner.next();

        service.addCenter(new VaccinationCenter(kek, title, city));
        System.out.println("Vaccination Center was registrated");
    }
    /**
     * Type Data for creating a new Doctor
     */
    public static void addDoctor(){
        System.out.println("Enter Doctor's Id: ");
        int id=scanner.nextInt();

        System.out.println("Enter Doctor's Full Name: ");
        String fullName=scanner.next();

        System.out.println("Enter Center's id for registering Doctor: ");
        int kek=scanner.nextInt();

        VaccinationCenter center=service.searchCenter(kek);
        if(center==null){
            System.out.println("There is no Vac. Center for registration found");
        }
        else{
            center.addDoctor(new Doctor(id, fullName));
            System.out.println("Doctor was registrated");
        }
    }
    /**
     * Creating a new Appointment. 
     * Enter Citizen's id and find him into CitizenList. 
     * Print all available Vaccination Centers and pick one. 
     * Schedule a Radevu for a specific Center and add Radevu at each object's List
     */
    public static void addAppointment(){
        System.out.println("Enter Citizen's Amka: ");
        int amka=scanner.nextInt();
        Citizen citizen=service.searchCitizen(amka);
        if(citizen==null){
            System.out.println("There is no Citizen for the new Appointment found");
        }
        else{
            for(int i=0;i<service.getListOfVaccinationCenters().size();i++){
                System.out.println((i+1)+". "+service.getListOfVaccinationCenters().get(i).getTitle()+", Kek: "+service.getListOfVaccinationCenters().get(i).getKek());
            }
            System.out.print("Please type Kek for selection of Vaccination Center: ");
            int kek=scanner.nextInt();
            VaccinationCenter vCenter=service.searchCenter(kek);
            if(vCenter==null){
                System.out.println("There is no Vac. Center with this Kek for a new Appointment");
            }
            else{
                Appointment appointment= vCenter.makeAppointment(ran.nextInt(),citizen,vCenter);
                if(appointment==null){
                    System.out.println("Could not be able to create a new Appoinment");  
                }
                else{
                    vCenter.addAppointment(appointment);
                    citizen.setAppointment(appointment);
                    appointment.printAppointment();
                }
            }
        }
    }
    /**
     * Case 5 of menu's. User types with which key he wants to search and print all Appointments
     */
    public static void searchAppointByKey(){
        System.out.println("Search an appointment by: ");
        System.out.println("                          1.Citizen's Amka");
        System.out.println("                          2.Center's Id"); 
        System.out.println("                          3.Doctor's Id"); 
        int option=scanner.nextInt();
        
        switch (option) {
            case 1:
                System.out.println("Enter Citizen's Amka: ");
                int amka=scanner.nextInt();
                if(!service.searchByAmka(amka)){
                    System.out.println("Not any citizen with this Amka, or any Appointment found");
                }
                break;
            case 2:
                System.out.println("Enter Center's Id (5 digits): ");
                int kek=scanner.nextInt();
                service.searchByCenterId(kek);
                break;
            case 3:
                System.out.println("Enter Doctor's Id: ");
                int id=scanner.nextInt();
                service.searchByDoctor(id);
                break;
            default:
                break;
        }
    }
    public static void printMenu() {
		System.out.println("                 "+service.getName());
		System.out.println("===============================================================");
		System.out.println("1 Enter a new Citizen..........................................");
		System.out.println("2.Enter a new Vaccination Center...............................");
		System.out.println("3.Enter a new Doctor...........................................");
		System.out.println("4.New Appointment..............................................");
		System.out.println("5.Search and Print Appointment.................................");
		System.out.println("6.Print all Insured Citizen....................................");
        System.out.println("7.Print all Vaccination Centers................................");
		System.out.println("8.Print all Doctors from each Vaccination Center...............");
        System.out.println("9.Exit menu....................................................");
		System.out.println("===============================================================");
	}
    /**
     * Initialize System with data given
     */
    public static void initializeService() {
        service.addCitizen(new Citizen(11111111, "Asfalismenos 1", "XANIA"));
        service.addCitizen(new Citizen(22222222, "Asfalismenos 2", "XANIA"));
        service.addCitizen(new Citizen(33333333, "Asfalismenos 3", "XANIA"));
        service.addCitizen(new Citizen(44444444, "Asfalismenos 4", "XANIA"));
        service.addCitizen(new Citizen(55555555, "Asfalismenos 5", "XANIA"));
        service.addCitizen(new Citizen(66666666, "Asfalismenos 6", "RETHYMNO"));
        service.addCitizen(new Citizen(77777777, "Asfalismenos 7", "RETHYMNO"));
        service.addCitizen(new Citizen(88888888, "Asfalismenos 8", "RETHYMNO"));
        service.addCitizen(new Citizen(99999999, "Asfalismenos 9", "RETHYMNO"));

        service.addCenter(new VaccinationCenter(22222, "CH-22", "XANIA"));
        service.addCenter(new VaccinationCenter(33333, "RTH-33", "RETHYMNO"));

        VaccinationCenter centerCH=service.searchCenter(22222);
        VaccinationCenter centerRTH=service.searchCenter(33333);

        centerCH.addDoctor(new Doctor(111111, "Doctor 1"));
        centerCH.addDoctor(new Doctor(222222, "Doctor 2"));
        centerCH.addDoctor(new Doctor(333333, "Doctor 3"));

        centerRTH.addDoctor(new Doctor(444444, "Doctor 4"));
        centerRTH.addDoctor(new Doctor(555555, "Doctor 5"));
        
        Citizen citizen1=service.searchCitizen(11111111);
        Citizen citizen2=service.searchCitizen(22222222);
        Citizen citizen3=service.searchCitizen(33333333);
        Citizen citizen4=service.searchCitizen(44444444);
        Citizen citizen5=service.searchCitizen(55555555);
        
        Citizen citizen6=service.searchCitizen(66666666);
        Citizen citizen7=service.searchCitizen(77777777);
        Citizen citizen8=service.searchCitizen(88888888);
        Citizen citizen9=service.searchCitizen(99999999);
        
        Doctor doctor1=centerCH.searchDoctor(111111);
        Doctor doctor2=centerCH.searchDoctor(222222);
        Doctor doctor3=centerCH.searchDoctor(333333);
        Doctor doctor4=centerRTH.searchDoctor(444444);
        Doctor doctor5=centerRTH.searchDoctor(555555);
        
        centerCH.addAppointment(new Appointment(20, LocalDate.now().plusDays(1), LocalTime.of(9, 0, 0), citizen1, centerCH, doctor1));
        centerCH.addAppointment(new Appointment(21, LocalDate.now().plusDays(1), LocalTime.of(10, 30, 0), citizen2, centerCH, doctor1));
        centerCH.addAppointment(new Appointment(22, LocalDate.now().plusDays(1), LocalTime.of(10, 30, 0), citizen3, centerCH, doctor2));
        centerCH.addAppointment(new Appointment(23, LocalDate.now().plusDays(1), LocalTime.of(10, 30, 0), citizen4, centerCH, doctor3));
        centerCH.addAppointment(new Appointment(24, LocalDate.now().plusDays(2), LocalTime.of(9, 30, 0), citizen5, centerCH, doctor1));

        centerRTH.addAppointment(new Appointment(30, LocalDate.now().plusDays(2), LocalTime.of(9, 30, 0), citizen6, centerRTH, doctor4));
        centerRTH.addAppointment(new Appointment(31, LocalDate.now().plusDays(2), LocalTime.of(9, 30, 0), citizen7, centerRTH, doctor5));
        centerRTH.addAppointment(new Appointment(32, LocalDate.now().plusDays(2), LocalTime.of(10, 0, 0), citizen8, centerRTH, doctor5));
        centerRTH.addAppointment(new Appointment(33, LocalDate.now().plusDays(3), LocalTime.of(9, 30, 0), citizen9, centerRTH, doctor4));
    }
}