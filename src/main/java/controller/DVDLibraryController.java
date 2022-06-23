package controller;

import dao.DVDLibraryDao;
import dao.DVDLibraryDaoException;
import dto.DVD;
import ui.DVDLibraryView;
import ui.UserIO;
import ui.UserIOConsoleImpl;

import java.util.List;

public class DVDLibraryController {
    //private UserIO io = new UserIOConsoleImpl();
    private DVDLibraryView view;
    private DVDLibraryDao dao;

    public void run() {
        boolean keepLooping = true;
        int menuSelection = 0;
        try {
            while (keepLooping) {
                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        listDVD();
                        break;
                    case 2:
                        addDVD();
                        break;
                    case 3:
                        removeDVD();
                        break;
                    case 4:
                        displayDVD();
                        break;
                    case 5:
                        editDVD();
                        break;
                    case 6:
                        keepLooping = false;
                        break;
                    default:
                        unknownCommand();
                }
            }
            exitMessage();
        } catch (DVDLibraryDaoException e){
            view.displayErrorMessage(e.getMessage());
        }

    }

    private int getMenuSelection(){
        return view.printMenuAndGetSelection();
    }
    private void editDVD() throws DVDLibraryDaoException {
        String dvdTitle = view.getDVDChoice();
        DVD dvd = dao.getDVD(dvdTitle);
        view.edit(dvd);
        if(dvd!=null){
            dao.addDVD(dvd.getTitle(), dvd);
            view.displayEditSuccessBanner();
        }
    }

    private void displayDVD() throws DVDLibraryDaoException {
        view.displayDisplayStudentBanner();
        String dvdTitle = view.getDVDChoice();
        DVD dvd = dao.getDVD(dvdTitle);
        view.displayDVD(dvd);
    }

    private void removeDVD() throws DVDLibraryDaoException {
        view.displayRemoveDVDBanner();
        String dvdtitle = view.getDVDChoice();
        DVD removedDVD = dao.removeDVD(dvdtitle);
        view.displayRemoveResult(removedDVD);
    }

    private void addDVD() throws DVDLibraryDaoException {
        view.displayCreateDVDBanner();
        DVD newDVD = view.getNewDVDInfo();
        dao.addDVD(newDVD.getTitle(), newDVD);
        view.displayCreateSuccessBanner();
    }

    private void listDVD() throws DVDLibraryDaoException {
        view.displayDisplayAllBanner();
        List<DVD> studentList = dao.getAllDVDs();
        view.displayDVDList(studentList);
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }
    public DVDLibraryController(DVDLibraryDao dao, DVDLibraryView view) {
        this.dao = dao;
        this.view = view;
    }
}
