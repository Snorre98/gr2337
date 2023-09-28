package json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import core.AlbumReview;

public class AlbumReviewSerializer extends JsonSerializer<AlbumReview> {
  @Override
  public void serialize(AlbumReview album,
                        JsonGenerator jGen,
                        SerializerProvider serializerProvider) throws IOException {
    jGen.writeStartObject();
    jGen.writeStringField("name", album.getName());
    jGen.writeNumberField("rating", album.getRating());
    jGen.writeEndObject();
  }
}
