import its.backbone.sdd.SddReceiver;
import java.io.IOException;
import its.backbone.sdd.*;
import its.SQL.*;
import java.util.*;

class SddArchiver extends SddReceiver{

  public SddArchiver() throws IOException {
    super( "sdd.its.washington.edu", 8412 );
    start();
  }

  public void schemaReceived( Schema schema, String serialNum ) {
    System.out.println( schema.toSql() );
  }
  
  public void extractedDataReceived(Hashtable ht, String serialNum) {
    print((ContentsData)ht.get("AVL_DATA"));
  }

  void print(ContentsData cd) {
    StringBuffer sb = new StringBuffer();
    sb.append(cd.getColumnNames()).append("\r\n");
    cd.resetRowIndex();
    while (cd.next()) {
      sb.append(cd.getRowString(true,true));
      sb.append("\r\n");
    }		
    System.out.print(sb.toString());
    System.out.println();		// HACK - TEMPORARY!!
  }
}
