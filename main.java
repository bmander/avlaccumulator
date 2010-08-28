import java.io.IOException;

class Main {

  public static void main(String args[]) {
    try {
      SddArchiver sddArchiver = new SddArchiver();
      System.out.println( sddArchiver );
    } catch( IOException e ) {
      System.out.println( e );
    }
  }

}
