package statepersistance;

import domainlogic.AlbumReview;
import domainlogic.AlbumReviewList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import statepersistence.LoadFromFile;
import statepersistence.WriteToFile;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WriteToFileTest{
  private Path saveFilePath;
  @BeforeEach
  public void setUp() throws URISyntaxException, IOException {
    URL resourceURL = getClass().getClassLoader().getResource("writeTest.json");
    System.out.println(resourceURL);
    if(resourceURL == null){
      throw new IllegalStateException("Cannot find mock JSON file. A fix might be to run 'mvn clean compile'");
    }
    saveFilePath = Paths.get(resourceURL.toURI());
    Files.writeString(saveFilePath, "[]");
  }

  @Test
  public void testWriteToFile() throws IOException{
    AlbumReviewList albums = new AlbumReviewList();
    AlbumReview review = new AlbumReview("fly", 6);
    albums.addAlbumReview(review);
    WriteToFile.writeToFile(albums, saveFilePath);

    assertEquals(
      albums.getAlbumReview(0).toString(),
      LoadFromFile.loadFromFile(saveFilePath, false).getAlbumReview(0).toString()
    );
  }
}