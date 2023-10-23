package statepersistence.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import domainlogic.Review;
import java.io.IOException;

/**
 * AlbumReview serializer.
 */
public class ReviewSerializer extends JsonSerializer<Review> {
  @Override
  public void serialize(Review review, JsonGenerator jgn, SerializerProvider serializerProvider)
      throws IOException {
    jgn.writeStartObject();
    jgn.writeStringField("user", review.getUserName());
    jgn.writeNumberField("rating", review.getRating());
    jgn.writeEndObject();
  }
}
