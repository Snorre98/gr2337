package statepersistence.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import domainlogic.Album;
import domainlogic.Review;
import java.io.IOException;

/**
 * Serializes AlbumReviewList object.
 */
public class AlbumSerializer extends JsonSerializer<Album> {
  @Override
  public void serialize(Album album, JsonGenerator jgn, SerializerProvider serializerProvider)
      throws IOException {
    jgn.writeStartObject();
    jgn.writeStringField("name", album.getName());
    jgn.writeStringField("artist", album.getArtist());
    jgn.writeArrayFieldStart("reviews");
    for (Review review : album.getReviews()) {
      jgn.writeObject(review);
    }
    jgn.writeEndArray();
    jgn.writeEndObject();
  }
}
