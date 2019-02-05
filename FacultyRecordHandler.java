import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class FacultyRecordHandler {
    static final String CURRENT_FOLDER_PATH = "C:\\Users\\Jesse\\IdeaProjects\\SoftwareEngineeringPrograms\\Project1\\src\\";

    private static ArrayList<FacultyMember> sortFacultyAndPublications (ArrayList<FacultyMember> facMembers){
        ArrayList<FacultyMember> sortedFacMembers = facMembers;
        Collections.sort(sortedFacMembers);
        facMembers = sortedFacMembers;

        for(FacultyMember facMember: facMembers){
            ArrayList<Publication> currentFacultyPublications = facMember.getPublications();
            Collections.sort(currentFacultyPublications);
            facMember.setPublications(currentFacultyPublications);
        }

        return facMembers;
    }

    private static void writeToOutputFile (String outFilePath, ArrayList<FacultyMember> facMembers) throws IOException{

        PrintWriter printToFile = new PrintWriter(new File(outFilePath));

        for(FacultyMember facMember: facMembers){
            printToFile.println("Name: "+facMember.getName());
            printToFile.println("Position: "+facMember.getPosition());

            printToFile.println("Publications: ");
            boolean firstPublication = true;
            for(Publication currentPub: facMember.getPublications()){
                if(!firstPublication){
                    printToFile.println("");
                }
                else{
                    firstPublication = false;
                }
                printToFile.println("\tAuthor(s): "+currentPub.getAuthors());
                printToFile.println("\tTitle: "+currentPub.getTitle());
                printToFile.println("\tConference/Journal Name: "+currentPub.getConferenceOrJournalName());
                printToFile.println("\tConference Location: "+currentPub.getConferenceLocation());
                printToFile.println("\tYear: "+currentPub.getPublicationDate());

            }
            printToFile.println("------------");
        }
        printToFile.close();
    }

    private static ArrayList<FacultyMember> takeUserInputFacultyMembers (Scanner userInput, int userWantsToInputRecords, ArrayList<FacultyMember> facMembers){
        while((userWantsToInputRecords > 1) || (userWantsToInputRecords < 0)){
            System.out.print("Invalid response. Please enter either 1 or 0: ");
            userWantsToInputRecords = Integer.parseInt(userInput.nextLine().trim());
        }
        while(userWantsToInputRecords == 1){
            System.out.println("Please enter the faculty member's information as prompted.");
            System.out.print("Name: ");
            String facultyName = userInput.nextLine();
            System.out.print("Position: ");
            String facultyPosition = userInput.nextLine();
            System.out.print("Does she/he have any publications? Enter 1 for yes or 0 for no: ");
            ArrayList<Publication> facultyPublications = new ArrayList<>();

            int facultyHasPublications = Integer.parseInt(userInput.nextLine().trim());
            while ((facultyHasPublications > 1) || (facultyHasPublications < 0)){
                System.out.print("Invalid response. Please enter either 1 or 0: ");
                facultyHasPublications = Integer.parseInt(userInput.nextLine().trim());

            }
            if(facultyHasPublications == 1){
                System.out.print("Please enter the name(s) of the author(s), or \"quit\" to exit: ");
                String authorName = userInput.nextLine().trim();
                while(!(authorName.equalsIgnoreCase("quit"))){
                    System.out.print("Enter the publication's title: ");
                    String publicationTitle = userInput.nextLine().trim();
                    System.out.print("Enter the conference or journal name: ");
                    String publicationConfOrJournalName = userInput.nextLine().trim();
                    System.out.print("Enter the conference location, or N/A if not applicable: ");
                    String conferenceLocation = userInput.nextLine().trim();
                    System.out.print("Enter the publication date: ");
                    String publicationDate = userInput.nextLine().trim();

                    Publication currentPublication = new Publication(authorName,publicationTitle,publicationConfOrJournalName,conferenceLocation,publicationDate);
                    facultyPublications.add(currentPublication);

                    System.out.print("Are there more publications to enter? Enter 1 for yes or 0 for no: ");
                    int morePublications = Integer.parseInt(userInput.nextLine().trim());
                    while((morePublications > 1) || (morePublications < 0)){
                        System.out.print("Invalid response. Please enter either 1 or 0: ");
                        morePublications = Integer.parseInt(userInput.nextLine().trim());
                    }
                    if(morePublications == 1){
                        System.out.print("Please enter the name(s) of the author(s): ");
                        authorName = userInput.nextLine();
                    }
                    else{
                        authorName = "quit";
                    }
                }
            }

            FacultyMember currentFacultyMember = new FacultyMember(facultyName,facultyPosition,facultyPublications);
            facMembers.add(currentFacultyMember);

            System.out.print("Would you like to enter another faculty member? Enter 1 for yes or 0 for no: ");
            userWantsToInputRecords = Integer.parseInt(userInput.nextLine().trim());
            while((userWantsToInputRecords > 1) || (userWantsToInputRecords < 0)){
                System.out.print("Invalid response. Please enter either 1 or 0: ");
                userWantsToInputRecords = Integer.parseInt(userInput.nextLine().trim());
            }
        }
        return facMembers;
    }

    public static void main (String[] args) throws IOException {

        ArrayList<FacultyMember> facMembers = new ArrayList<>();

        Scanner userInput = new Scanner(System.in);
        System.out.print("Does a file with faculty records already exist? Enter 1 for yes or 0 for no: ");
        int inputFileExists = Integer.parseInt(userInput.nextLine().trim());

        while((inputFileExists > 1) || (inputFileExists < 0)){
            System.out.print("Invalid response. Please enter either 1 or 0: ");
            inputFileExists = Integer.parseInt(userInput.nextLine().trim());
        }

        if(inputFileExists == 1){
            System.out.print("Please enter the name of the file: ");
            String inFileName = userInput.nextLine().trim();


            Scanner inputFile = new Scanner(new File(CURRENT_FOLDER_PATH + inFileName));

            while(inputFile.hasNext()){
                String facultyName = inputFile.nextLine().trim().replaceAll("(?i)(Name: )","");

                String facultyPosition = inputFile.nextLine().trim().replaceAll("(?i)(Position: )","");


                ArrayList<Publication> currentFacultyPublications = new ArrayList<Publication>();
                String throwaway = inputFile.nextLine();

                String currentLine = "";
                while(!currentLine.contains("----")){
                    currentLine = inputFile.nextLine();
                    String authorNames = currentLine.replaceAll("Authors: ","").trim();
                    currentLine = inputFile.nextLine();
                    String title = currentLine.replaceAll("Title: ","").trim();
                    currentLine = inputFile.nextLine();
                    String conferenceOrJournalName = currentLine.replaceAll("Conference/Journal Name: ","").trim();
                    currentLine = inputFile.nextLine();
                    String conferenceLocation = currentLine.replaceAll("Conference Location: ","").trim();
                    currentLine = inputFile.nextLine();
                    String publicationDate = currentLine.replaceAll("Year: ","").trim();

                    currentLine = inputFile.nextLine();

                    Publication currentPublication = new Publication(authorNames,title,conferenceOrJournalName,conferenceLocation,publicationDate);
                    currentFacultyPublications.add(currentPublication);
                }

                FacultyMember currentFacMember = new FacultyMember(facultyName,facultyPosition,currentFacultyPublications);
                facMembers.add(currentFacMember);
            }



            System.out.print("Would you like to add any new records? Enter 1 for yes or 0 for no: ");
            int userWantsToInputRecords = Integer.parseInt(userInput.nextLine().trim());
            facMembers = takeUserInputFacultyMembers(userInput,userWantsToInputRecords,facMembers);

            facMembers = sortFacultyAndPublications(facMembers);

            writeToOutputFile(CURRENT_FOLDER_PATH + inFileName,facMembers);

        }
        else if(inputFileExists == 0){
            facMembers = takeUserInputFacultyMembers(userInput,1,facMembers);

            facMembers = sortFacultyAndPublications(facMembers);

            System.out.println("Please enter the name of the file to output to: ");
            String outFileName = userInput.nextLine().trim();
            writeToOutputFile(CURRENT_FOLDER_PATH + outFileName,facMembers);
        }
    }
}
