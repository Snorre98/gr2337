package core;

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
        albumList.addAlbumReview("Album1", 5);
        assertEquals(1, albumList.getAlbumReviews().size());
        assertEquals("Album1", albumList.getAlbumReview(0).getName());
        assertEquals(5, albumList.getAlbumReview(0).getRating());
    }

    @Test
    public void testRemoveAlbumReview() {
        albumList.addAlbumReview("Album1", 5);
        albumList.addAlbumReview("Album2", 4);

        albumList.removeAlbumReview(0);
        assertEquals(1, albumList.getAlbumReviews().size());
        assertEquals("Album2", albumList.getAlbumReview(0).getName());
    }

    @Test
    public void testSortByName() {
        albumList.addAlbumReview("C", 5);
        albumList.addAlbumReview("A", 4);
        albumList.addAlbumReview("B", 3);

        albumList.sortName();

        assertEquals("A", albumList.getAlbumReview(0).getName());
        assertEquals("B", albumList.getAlbumReview(1).getName());
        assertEquals("C", albumList.getAlbumReview(2).getName());
    }

    @Test
    public void testSortByRating() {
        albumList.addAlbumReview("Album1", 3);
        albumList.addAlbumReview("Album2", 4);
        albumList.addAlbumReview("Album3", 5);

        albumList.sortRating();

        assertEquals(5, albumList.getAlbumReview(0).getRating());
        assertEquals(4, albumList.getAlbumReview(1).getRating());
        assertEquals(3, albumList.getAlbumReview(2).getRating());
    }
}
