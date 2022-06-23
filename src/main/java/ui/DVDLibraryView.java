package ui;

import dto.DVD;

import java.util.List;

public class DVDLibraryView {
    private UserIO io;

    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. List DVDs");
        io.print("2. Add new DVD");
        io.print("3. Remove existing DVD");
        io.print("4. Display a DVD");
        io.print("5. Edit a DVD");
        io.print("6. Exit");

        return io.readInt("Please select from the above choices.", 1, 6);

    }

    public DVD getNewDVDInfo() {
        String title = io.readString("Please enter the DVD title");
        String date = io.readString("Please enter the year of release");
        String rating = io.readString("Please enter the DVD rating");
        String directorsName = io.readString("Please enter the directors name");
        String studio = io.readString("Please enter the studio");
        String userRating = io.readString("Please enter your own rating");
        DVD newDVD = new DVD(title);
        newDVD.setRating(rating);
        newDVD.setDate(date);
        newDVD.setDirectorsName(directorsName);
        newDVD.setStudio(studio);
        newDVD.setUserRating(userRating);
        return newDVD;
    }

    public void displayCreateDVDBanner() {
        io.print("=== Create DVD ===");
    }

    public void displayCreateSuccessBanner() {
        io.readString("DVD successfully created. Please hit enter to continue");
    }

    public void displayEditSuccessBanner() {
        io.readString("DVD successfully edited. Please hit enter to continue");
    }

    public void displayDVDList(List<DVD> DVDList) {
        for (DVD currentDVD : DVDList) {
            String DVDInfo = String.format("%s : %s ",
                    currentDVD.getTitle(),
                    currentDVD.getDate());
            io.print(DVDInfo);
        }
        io.readString("Please hit enter to continue");
    }

    public void displayDisplayAllBanner() {
        io.print("=== Display All DVDs ===");
    }

    public void displayDisplayStudentBanner() {
        io.print("=== Display DVD ===");
    }

    public String getDVDChoice() {
        return io.readString("Please enter the DVD title.");
    }

    public void displayDVD(DVD dvd) {
        if (dvd != null) {
            io.print(dvd.getTitle());
            io.print(dvd.getDate());
            io.print(dvd.getRating());
            io.print(dvd.getDirectorsName());
            io.print(dvd.getStudio());
            io.print(dvd.getUserRating());
            io.print("");
        } else {
            invalidDVDMessage();
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayRemoveDVDBanner() {
        io.print("=== Remove DVD ===");
    }

    public void displayRemoveResult(DVD DVDRecord) {
        if (DVDRecord != null) {
            io.print("DVD successfully removed.");
        } else {
            invalidDVDMessage();
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }

    public DVDLibraryView(UserIO io) {
        this.io = io;
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }

    public int EditMenuSelection() { //prints menu and returns choice for which attribute to edit
        io.print("Pick an attribute to edit:");
        io.print("1. Release date");
        io.print("2. Rating");
        io.print("3. Directors name");
        io.print("4. Studio");
        io.print("5. User rating");
        io.print("6. Exit");

        return io.readInt("Please select from the above choices.", 1, 6);
    }

    public void invalidDVDMessage() {
        io.print("No such DVD.");
    }

    public DVD edit(DVD dvd) {
        if (dvd == null) {
            invalidDVDMessage();
        } else {
            int edit = EditMenuSelection();
            switch (edit) {
                case 1:
                    dvd.setDate(io.readString("Please enter the updated date:"));
                    break;
                case 2:
                    dvd.setRating(io.readString("Please enter the updated rating:"));
                    break;
                case 3:
                    dvd.setDirectorsName(io.readString("Please enter the updated director:"));
                    break;
                case 4:
                    dvd.setStudio(io.readString("Please enter the updated studio:"));
                    break;
                case 5:
                    dvd.setDate(io.readString("Please enter the updated user rating:"));
                    break;
                case 6:
                    break;
                default:
                    displayUnknownCommandBanner();
            }

        }
        return dvd;
    }
}
