package statepersistence.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import domainlogic.AlbumReview;
import domainlogic.AlbumReviewList;
import java.io.IOException;

/**
 * Serializes AlbumReviewList object.
 */
public class AlbumReviewListSerializer extends JsonSerializer<AlbumReviewList> {
  @Override
  public void serialize(AlbumReviewList albumList, JsonGenerator jgn,
      SerializerProvider serializerProvider) throws IOException {
    jgn.writeStartObject();
    jgn.writeArrayFieldStart("albums");
    for (AlbumReview album : albumList.getAlbumReviews()) {
      jgn.writeObject(album);
    }
    jgn.writeEndArray();
    jgn.writeEndObject();
  }
}
