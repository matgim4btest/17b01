package rs.edu.matgim.zadatak;

import java.sql.SQLException;

public class Program {

    public static void main(String[] args) throws SQLException {

        DB _db = new DB();
        _db.printKomitent();
        _db.printPositiveRacun();
        
    }
}
