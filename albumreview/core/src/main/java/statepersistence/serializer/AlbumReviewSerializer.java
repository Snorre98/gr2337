package statepersistence.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import domainlogic.AlbumReview;
import java.io.IOException;

/**
 * AlbumReview serializer.
 */
public class AlbumReviewSerializer extends JsonSerializer<AlbumReview> {
  @Override
  public void serialize(AlbumReview album, JsonGenerator jgn, SerializerProvider serializerProvider)
      throws IOException {
    jgn.writeStartObject();
    jgn.writeStringField("name", album.getName());
    jgn.writeNumberField("rating", album.getRating());
    jgn.writeEndObject();
  }
}
