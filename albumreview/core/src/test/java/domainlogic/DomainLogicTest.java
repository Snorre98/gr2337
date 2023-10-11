package domainlogic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DomainLogicTest {
  private AlbumReviewList albumList;


  @BeforeEach
  public void setUp() {
    albumList = new AlbumReviewList();
  }

  @Test
  public void testAddAlbumReview() {
    AlbumReview album1 = new AlbumReview("Album1", 5);
    albumList.addAlbumReview(album1);
    assertEquals(1, albumList.getAlbumReviews().size());
    assertEquals("Album1", albumList.getAlbumReview(0).getName());
    assertEquals(5, albumList.getAlbumReview(0).getRating());
  }

  @Test
  public void testRemoveAlbumReview() {
    AlbumReview album1 = new AlbumReview("Album1", 5);
    AlbumReview album2 = new AlbumReview("Album2", 4);
    albumList.addAlbumReview(album1);
    albumList.addAlbumReview(album2);

    albumList.removeAlbumReview(0);
    assertEquals(1, albumList.getAlbumReviews().size());
    assertEquals("Album2", albumList.getAlbumReview(0).getName());
  }

  @Test
  public void testSortByName() {
    AlbumReview a1 = new AlbumReview("C", 5);
    AlbumReview a2 = new AlbumReview("A", 4);
    AlbumReview a3 = new AlbumReview("B", 3);

    albumList.addAlbumReview(a1);
    albumList.addAlbumReview(a2);
    albumList.addAlbumReview(a3);

    albumList.sortName();

    assertEquals("A", albumList.getAlbumReview(0).getName());
    assertEquals("B", albumList.getAlbumReview(1).getName());
    assertEquals("C", albumList.getAlbumReview(2).getName());
  }

  @Test
  public void testSortByRating() {
    AlbumReview a1 = new AlbumReview("C", 3);
    AlbumReview a2 = new AlbumReview("A", 4);
    AlbumReview a3 = new AlbumReview("B", 5);

    albumList.addAlbumReview(a1);
    albumList.addAlbumReview(a2);
    albumList.addAlbumReview(a3);

    albumList.sortRating();

    assertEquals(5, albumList.getAlbumReview(0).getRating());
    assertEquals(4, albumList.getAlbumReview(1).getRating());
    assertEquals(3, albumList.getAlbumReview(2).getRating());
  }
}
