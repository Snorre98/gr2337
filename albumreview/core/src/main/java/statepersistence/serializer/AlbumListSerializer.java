package statepersistence.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import domainlogic.Album;
import domainlogic.AlbumList;
import java.io.IOException;

/**
 * Serializes AlbumReviewList object.
 */
public class AlbumListSerializer extends JsonSerializer<AlbumList> {
  @Override
  public void serialize(AlbumList albumList, JsonGenerator jgn,
      SerializerProvider serializerProvider) throws IOException {
    jgn.writeStartObject();
    jgn.writeArrayFieldStart("albums");
    for (Album album : albumList.getReviews()) {
      jgn.writeObject(album);
    }
    jgn.writeEndArray();
    jgn.writeEndObject();
  }
}
