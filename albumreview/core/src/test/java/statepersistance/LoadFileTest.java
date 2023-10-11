package statepersistance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;
import domainlogic.AlbumReview;
import domainlogic.AlbumReviewList;
import statepersistence.LoadFromFile;

public class LoadFileTest {
  static Path saveFilePath = Paths.get("src/test/resources/fileTester.json");

  @Test
  public void testLoadFile() throws IOException{
  AlbumReviewList list = new AlbumReviewList();
  AlbumReview review = new AlbumReview("banana", 2);
  list.addAlbumReview(review);
  System.out.println(list.getAlbumReview(0).toString());

    assertEquals(
      list.getAlbumReview(0).toString(), 
      LoadFromFile.loadFromFile(saveFilePath).getAlbumReview(0).toString()
    );
  }
//LoadFromFile.loadFromFile(saveFilePath)
}
