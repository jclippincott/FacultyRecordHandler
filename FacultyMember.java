import java.util.ArrayList;

public class FacultyMember implements Comparable<FacultyMember>{

    String name;
    String position;
    ArrayList<Publication> publications;

    public FacultyMember(String name, String position, ArrayList<Publication> publications) {
        this.name = name;
        this.position = position;
        this.publications = publications;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public ArrayList<Publication> getPublications() {
        return publications;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setPublications(ArrayList<Publication> publications) {
        this.publications = publications;
    }

    public int compareTo(FacultyMember anotherFacMem) {
        String lastname = name.split("")[(name.split("").length-1)];
        return lastname.compareTo(anotherFacMem.getName().split("")[(anotherFacMem.getName().split("").length-1)]);
    }

    @Override
    public String toString() {
        return name + " " + position + " " + publications;
    }
}
