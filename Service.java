import java.util.Vector;

/**
 * Service
 */
public class Service {
    public static final int MAX_CITIZENS=100;
    public static final int MAX_CENTERS=10;

    private String name;
    private String url;
    private int duration;

    private Vector<Citizen> listOfCitizens;
    private Vector<VaccinationCenter> listOfVaccinationCenters;

    public Service(String name,String url,int duration) {
        this.name=name;
        this.url=url;
        this.duration=duration;

        listOfCitizens=new Vector<Citizen>();
        listOfVaccinationCenters=new Vector<VaccinationCenter>();
    }
    public String getName() {
        return name;
    }
    public String getUrl() {
        return url;
    }
    public int getDuration() {
        return duration;
    }
    public Vector<Citizen> getListOfCitizens() {
        return listOfCitizens;
    }
    public Vector<VaccinationCenter> getListOfVaccinationCenters() {
        return listOfVaccinationCenters;
    }

    public void addCitizen(Citizen citizen){
        if(listOfCitizens.size()<MAX_CITIZENS){
            listOfCitizens.add(citizen);
            return;
        }
        System.out.println("Sorry there is not any space for a new Citizen registration");
    }
    public void addCenter(VaccinationCenter center){
        if(listOfVaccinationCenters.size()<MAX_CENTERS){
            for(int i=0;i<listOfVaccinationCenters.size();i++){
                if(center.getCity().equals(listOfVaccinationCenters.get(i).getCity())){
                    System.out.println("There is already a Vaccination Center at this town");
                    return;
                }
            }
            listOfVaccinationCenters.add(center);
            return;
        }
        System.out.println("Sorry there is not any space for a new Center registration");
    }
    public VaccinationCenter searchCenter(int kek){
        for(int i=0;i<listOfVaccinationCenters.size();i++){
            if (kek==listOfVaccinationCenters.get(i).getKek()) {
                return listOfVaccinationCenters.get(i);
            }
        }
        return null;
    }
    public Citizen searchCitizen(int amka){
        for(int i=0;i<listOfCitizens.size();i++){
            if (amka==listOfCitizens.get(i).getAmka()) {
                return listOfCitizens.get(i);
            }
        }
        return null;
    }
    public boolean searchByAmka(int amka){
        boolean found=false;
        for(int i=0;i<listOfVaccinationCenters.size();i++){
            VaccinationCenter vCenter= listOfVaccinationCenters.get(i);
            for(int j=0;j<vCenter.getListOfAppointments().size();j++){
                if(amka==vCenter.getListOfAppointments().get(j).getCitizen().getAmka()){
                    vCenter.getListOfAppointments().get(j).printAppointment();
                    found=true;
                }
            }
        }
        return found;
    }
	public void searchByCenterId(int kek) {
        VaccinationCenter vCenter=searchCenter(kek);
        if (vCenter==null){
            System.out.println("There is no Vac Center with this kek found");
            return;
        }
        vCenter.printAppointments();
	}
    public void searchByDoctor(int id){
        for(int i=0;i<listOfVaccinationCenters.size();i++){
            VaccinationCenter vCenter= listOfVaccinationCenters.get(i);
            for(int j=0;j<vCenter.getListOfDoctors().size();j++){
                if(vCenter.getListOfDoctors().get(j).getId()==id){
                    vCenter.getListOfDoctors().get(j).printAppointments();
                    return;
                }
            }
        }
        System.out.println("There is not any Appoinments to print");
    }
    public void printCitizens(){
        for(int i=0;i<listOfCitizens.size();i++){
            listOfCitizens.get(i).print();
        }
    }
    public void printVacCenters() {
        for(int i=0;i<listOfVaccinationCenters.size();i++){
            listOfVaccinationCenters.get(i).print();
        }
    }
    public void printDoctors(){
        for(int i=0;i<listOfVaccinationCenters.size();i++){
            VaccinationCenter vCenter=listOfVaccinationCenters.get(i);
            for(int j=0;j<vCenter.getListOfDoctors().size();j++){
                vCenter.getListOfDoctors().get(j).print();
            }
        }
    }
}