import its.backbone.sdd.SddReceiver;
import java.io.IOException;
import its.backbone.sdd.*;
import its.SQL.*;
import java.util.*;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

class SddArchiver extends SddReceiver{

  Connection conn;

  public SddArchiver( Connection conn ) throws IOException {
    super( "sdd.its.washington.edu", 8412 );
    this.conn = conn;
    start();
  }

  public void schemaReceived( Schema schema, String serialNum ) {
    System.out.println( schema.toSql() );
  }
  
  public void extractedDataReceived(Hashtable ht, String serialNum) {
    ContentsData locations = (ContentsData)ht.get("AVL_DATA");
    try{
      insert(locations);
    } catch( SQLException e ) {
      System.out.println( "Failed to archive record" );
      System.out.println( e );
    }
  }

  void insert(ContentsData cd) throws SQLException{
    StringBuffer sb = new StringBuffer();
    sb.append(cd.getColumnNames()).append("\r\n");
    cd.resetRowIndex();
    while (cd.next()) {
      String insertString = "INSERT INTO avl_data VALUES ("+cd.getRowString(true,true)+")";

      Statement stmt = conn.createStatement();
      int rs = stmt.executeUpdate(insertString);

      sb.append(insertString);
      sb.append("\r\n");
    }		
    System.out.print(sb.toString());
    System.out.println();		// HACK - TEMPORARY!!
  }
}
