package statepersistence.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import domainlogic.AlbumList;
import domainlogic.AlbumListModel;
import statepersistence.AlbumReviewPersistence;

import java.io.IOException;
import java.util.Set;


//TODO: Finish this !!
public class AlbumListModelSerializer extends JsonSerializer<AlbumListModel> {

  private final Set<AlbumReviewPersistence.AlbumReviewParts> parts;

  public AlbumListModelSerializer(Set<AlbumReviewPersistence.AlbumReviewParts> parts) {
    this.parts = parts;
  }

  public void serialize(AlbumListModel albumListModel, JsonGenerator jsonGen, SerializerProvider serizerProvider) throws IOException {
    jsonGen.writeStartObject();
    if (parts.contains(AlbumReviewPersistence.AlbumReviewParts.ALBUM) || parts.contains(AlbumReviewPersistence.AlbumReviewParts.REVIEW)) {
      jsonGen.writeArrayFieldStart("albums");
      //for (AlbumList album : albumListModel) {
      //
      //}
    }
  }

}
