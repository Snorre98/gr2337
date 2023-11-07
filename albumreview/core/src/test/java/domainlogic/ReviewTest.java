package domainlogic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Tests for Review class.
 */
public class ReviewTest {
  Review review;

  @Test
  public void testReview() {
    assertThrows(IllegalArgumentException.class, () -> review = new Review("test", 11));

  }

  @Test
  public void testTostring() {
    review = new Review("test", 5);
    assertEquals("Username: test Rating: 5", review.toString());
  }
}
