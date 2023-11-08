package statepersistance.serielizer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domainlogic.Album;
import domainlogic.AlbumList;
import domainlogic.Review;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import statepersistence.serializer.AlbumReviewModule;

/**
 * Tests serialization.
 */
public class AlbumReviewModuleTest {
  private static ObjectMapper mapper;

  @BeforeAll
  public static void setUp() {
    mapper = new ObjectMapper();
    mapper.registerModule(new AlbumReviewModule());
  }

  @Test
  public void testSerializers() {
    AlbumList albums = new AlbumList();
    Album album = new Album("testArtist", "testAlbumName");
    Review review = new Review("testUser", 1);
    album.addReview(review);
    albums.addAlbum(album);
    try {
      assertEquals(
          "{\"albums\":[{\"name\":\"testAlbumName\",\"artist\":\"testArtist\",\"reviews\":[{\"userName\":\"testUser\",\"rating\":1}]}]}",
          mapper.writeValueAsString(albums));
    } catch (JsonProcessingException e) {
      fail();
    }
  }
}

