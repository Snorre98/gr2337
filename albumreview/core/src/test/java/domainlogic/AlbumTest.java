package domainlogic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * Testing AlbumList and sorting classes.
 */
public class AlbumTest {
  private Album album;

  @BeforeEach
  public void setUp() {
    album = new Album("artist", "name");
  }

  @Test
  public void testAddReview() {
    Review review = new Review("username", 5);
    album.addReview(review);

    assertEquals(1, album.getReviews().size());
    assertEquals("username", album.getReview(0).getUserName());
    assertEquals(5, album.getReview(0).getRating());

  }

  @Test
  public void testRemoveReview() {
    Review review1 = new Review("username1", 1);
    Review review2 = new Review("username2", 2);
    album.addReview(review1);
    album.addReview(review2);

    //Test when the username match
    album.removeReview(0, "username1");
    assertEquals(1, album.getReviews().size());
    assertEquals("username2", album.getReview(0).getUserName());
    assertEquals(2, album.getReview(0).getRating());

    // Test when the username does not match
    assertThrows(IllegalStateException.class, () -> album.removeReview(0, "User1"));
    
  }

  @Test
  public void testTostring() {
    assertEquals("Album: name, Artist: artist", album.toString());
  }
}
