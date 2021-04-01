package rs.edu.matgim.zadatak;

import java.sql.*;

public class DB {

    String connectionString = "jdbc:sqlite:src\\main\\java\\Banka.db";

    public void printKomitent() {
        try ( Connection conn = DriverManager.getConnection(connectionString);  Statement s = conn.createStatement()) {

            ResultSet rs = s.executeQuery("SELECT * FROM Komitent");
            while (rs.next()) {
                int IdKom = rs.getInt("IdKom");
                String Naziv = rs.getString("Naziv");
                String Adresa = rs.getString("Adresa");

                System.out.println(String.format("%d\t%s\t%s", IdKom, Naziv, Adresa));
            }

        } catch (SQLException ex) {
            System.out.println("Greska prilikom povezivanja na bazu");
            System.out.println(ex);
        }
    }
    public void printPositiveRacun() throws SQLException
    {
        try ( Connection conn = DriverManager.getConnection(connectionString);  Statement s = conn.createStatement()) {
            ResultSet rs = s.executeQuery("SELECT * FROM Racun WHERE Stanje > 0");
            while (rs.next()) {
                int IdRac = rs.getInt("IdRac");
                String Status = rs.getString("Status");
                int brstavki = rs.getInt("BrojStavki");
                int DozvMinus = rs.getInt("DozvMinus");
                int Stanje = rs.getInt("Stanje");
                System.out.println(String.format("%d\t%s\t%d\t%d\t%d", IdRac, Status, brstavki, DozvMinus, Stanje));
            }
        }
    }
    public void zadatak(int idFil, int idRac) throws SQLException{
        try ( Connection connection = DriverManager.getConnection(connectionString);  Statement s = connection.createStatement()) {
       
        String upit3="SELECT IdRac,Stanje FROM Racun WHERE IdKom=? AND Stanje>0";
        PreparedStatement ps2=connection.prepareStatement(upit3);
        ResultSet rs=ps2.executeQuery();
        int sum = 0;
        while(rs.next())
        {
        sum+=rs.getInt("Stanje");
        
        }
        String upit5="Updtate Racun SET Stanje = 0,Status = U  WHERE IdFil=? AND Stanje>0";
        PreparedStatement ps3=connection.prepareStatement(upit5);
        ResultSet rs1=ps2.executeQuery();
        
        String upit4="UPDATE Racun SET Stanje=Stanje+? WHERE IdRac=?";
        PreparedStatement st2=connection.prepareStatement(upit4);
        st2.setInt(1,sum);
        st2.executeUpdate();
        connection.commit();
        connection.setAutoCommit(true);
    }catch(SQLException e) {System.out.println("greska");}
        System.out.println("Uspesno");
        
    }

}
