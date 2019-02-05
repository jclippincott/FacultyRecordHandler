public class Publication implements Comparable<Publication>{

    private String authors;
    private String title;
    private String conferenceOrJournalName;
    private String conferenceLocation;
    private String publicationDate;

    public Publication(String authors, String title, String conferenceOrJournalName, String conferenceLocation, String publicationDate) {
        this.authors = authors;
        this.title = title;
        this.conferenceOrJournalName = conferenceOrJournalName;
        this.conferenceLocation = conferenceLocation;
        this.publicationDate = publicationDate;
    }

    public String getAuthors() {
        return authors;
    }

    public String getTitle() {
        return title;
    }

    public String getConferenceOrJournalName() {
        return conferenceOrJournalName;
    }

    public String getConferenceLocation() {
        return conferenceLocation;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setConferenceOrJournalName(String conferenceOrJournalName) {
        this.conferenceOrJournalName = conferenceOrJournalName;
    }

    public void setConferenceLocation(String conferenceLocation) {
        this.conferenceLocation = conferenceLocation;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public int compareTo(Publication anotherPublication) {
        return publicationDate.compareTo(anotherPublication.getPublicationDate());
    }
}