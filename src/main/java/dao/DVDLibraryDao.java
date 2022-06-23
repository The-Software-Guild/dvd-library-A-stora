package dao;


import dto.DVD;

import java.util.List;

public interface DVDLibraryDao {

    DVD addDVD(String studentId, DVD student)throws DVDLibraryDaoException ;


    List<DVD> getAllDVDs()throws DVDLibraryDaoException ;


    DVD getDVD(String studentId)throws DVDLibraryDaoException ;


    DVD removeDVD(String studentId)throws DVDLibraryDaoException ;
}
