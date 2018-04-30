import java.io.ByteArrayOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.jar.Attributes;
import java.util.jar.Manifest;
import sun.net.www.content.text.PlainTextInputStream;

public class Main {
  public static void main(String... args) throws IOException {
    System.out.println("\n\n=== File " + Main.class.getResource("MANIFEST.MF") + " ===\n");
    readAndWriteManifest(Main.class.getResourceAsStream("MANIFEST.MF"));
    System.out.println("\n\n=== File " + Main.class.getResource("MANIFEST2.MF") + " ===\n");
    readAndWriteManifest(Main.class.getResourceAsStream("MANIFEST2.MF"));

    System.out.println(
      "MANIFEST.MF equals MANIFEST2.MF?: " +
      new Manifest(Main.class.getResourceAsStream("MANIFEST.MF")).getMainAttributes()
        .equals(new Manifest(Main.class.getResourceAsStream("MANIFEST2.MF")).getMainAttributes())
    );
  }

  private static void readAndWriteManifest(InputStream in) throws IOException {
    System.out.println("MANIFEST attributes as Java sees them:");
    final Manifest manifest = new Manifest(in);
    final Attributes mainAtts = manifest.getMainAttributes();
    mainAtts.keySet().forEach(key -> {
      System.out.println(key + ": " + mainAtts.get(key));
    });
    System.out.println("\njava.util.jar.Manifest.write() writes the MANIFEST.MF as follows:");
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    manifest.write(baos);
    System.out.println(baos.toString());
  }
}
