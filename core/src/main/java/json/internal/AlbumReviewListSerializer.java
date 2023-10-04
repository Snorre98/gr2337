package json.internal;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import core.AlbumReview;
import core.AlbumReviewList;

public class AlbumReviewListSerializer extends JsonSerializer<AlbumReviewList> {
  @Override
  public void serialize(AlbumReviewList albumList,
                        JsonGenerator jGen,
                        SerializerProvider serializerProvider) throws IOException {
    jGen.writeStartObject();
    jGen.writeArrayFieldStart("albums");
    for (AlbumReview album : albumList.getAlbumReviews()) {
      jGen.writeObject(album);
    }
    jGen.writeEndArray();
    jGen.writeEndObject();
  }
}
