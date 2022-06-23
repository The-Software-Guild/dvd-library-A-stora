package dao;

import dto.DVD;

import java.io.*;
import java.util.*;

public class DVDLibraryDaoFileImpl implements DVDLibraryDao{
    private Map<String, DVD> DVDs = new HashMap<>();

    public static final String ROSTER_FILE = "library.txt";
    public static final String DELIMITER = "::";

    public DVD addDVD(String title, DVD dvd) throws DVDLibraryDaoException {
        loadRoster();
        DVD newDVD = DVDs.put(title, dvd);
        writeRoster();
        return newDVD;
    }

    @Override
    public List<DVD> getAllDVDs() throws DVDLibraryDaoException {
        loadRoster();
        return new ArrayList(DVDs.values());
    }

    @Override
    public DVD getDVD(String dvdTitle) throws DVDLibraryDaoException {
        loadRoster();
        return DVDs.get(dvdTitle);
    }

    @Override
    public DVD removeDVD(String dvdTitle) throws DVDLibraryDaoException {
        loadRoster();
        DVD removeDVD = DVDs.remove(dvdTitle);
        writeRoster();
        return removeDVD;
    }

    private DVD unmarshallDVD(String DVDAsText){

        String[] DVDTokens = DVDAsText.split(DELIMITER);

        // Given the pattern above, the student Id is in index 0 of the array.
        String dvdTitle = DVDTokens[0];

        // Which we can then use to create a new Student object to satisfy
        // the requirements of the Student constructor.
        DVD DVDFromFile = new DVD(dvdTitle);

        // However, there are 3 remaining tokens that need to be set into the
        // new student object. Do this manually by using the appropriate setters.

        // Index 1 - FirstName
        DVDFromFile.setDate(DVDTokens[1]);

        // Index 2 - LastName
        DVDFromFile.setRating(DVDTokens[2]);

        // Index 3 - Cohort
        DVDFromFile.setDirectorsName(DVDTokens[3]);

        DVDFromFile.setStudio(DVDTokens[4]);

        DVDFromFile.setUserRating(DVDTokens[5]);

        // We have now created a student! Return it!
        return DVDFromFile;
    }

    private void loadRoster() throws DVDLibraryDaoException {
        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(ROSTER_FILE)));
        } catch (FileNotFoundException e) {
            throw new DVDLibraryDaoException(
                    "-_- Could not load roster data into memory.", e);
        }
        // currentLine holds the most recent line read from the file
        String currentLine;
        // currentStudent holds the most recent student unmarshalled
        DVD currentDVD;
        // Go through ROSTER_FILE line by line, decoding each line into a
        // Student object by calling the unmarshallStudent method.
        // Process while we have more lines in the file
        while (scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();
            // unmarshall the line into a Student
            currentDVD = unmarshallDVD(currentLine);

            // We are going to use the student id as the map key for our student object.
            // Put currentStudent into the map using student id as the key
            DVDs.put(currentDVD.getTitle(), currentDVD);
        }
        // close scanner
        scanner.close();
    }

    private String marshallDVD(DVD aDVD){

        String DVDAsText = aDVD.getTitle() + DELIMITER;

        DVDAsText += aDVD.getDate() + DELIMITER;

        DVDAsText += aDVD.getRating() + DELIMITER;

        DVDAsText += aDVD.getDirectorsName() + DELIMITER;

        DVDAsText += aDVD.getStudio() + DELIMITER;

        DVDAsText += aDVD.getUserRating();

        return DVDAsText;
    }

    private void writeRoster() throws DVDLibraryDaoException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(ROSTER_FILE));
        } catch (IOException e) {
            throw new DVDLibraryDaoException(
                    "Could not save student data.", e);
        }

        String DVDAsText;
        List<DVD> DVDList = this.getAllDVDs();
        for (DVD currentDVD : DVDList) {
            // turn a Student into a String
            DVDAsText = marshallDVD(currentDVD);
            // write the Student object to the file
            out.println(DVDAsText);
            // force PrintWriter to write line to the file
            out.flush();
        }
        // Clean up
        out.close();
    }

}
