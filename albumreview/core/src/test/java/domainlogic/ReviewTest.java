package domainlogic;

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
}
