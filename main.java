import java.io.IOException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

class Main {

  public static void main(String args[]) {
      
    Connection c = null;
	  
    try {
      // The second and third arguments are the username and password,
      // respectively. They should be whatever is necessary to connect
      // to the database.
      c = DriverManager.getConnection("jdbc:postgresql://localhost:5433/brandon");
    } catch (SQLException se) {
      System.out.println("Couldn't connect: print out a stack trace and exit.");
      se.printStackTrace();
      System.exit(1);
    }

    try {
      SddArchiver sddArchiver = new SddArchiver(c);
      System.out.println( sddArchiver );
    } catch( IOException e ) {
      System.out.println( e );
    }
  }

}
