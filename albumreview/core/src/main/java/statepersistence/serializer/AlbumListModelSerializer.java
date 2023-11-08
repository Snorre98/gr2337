package statepersistence.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import domainlogic.Album;
import domainlogic.AlbumList;
import domainlogic.AlbumListModel;
import statepersistence.AlbumReviewPersistence;

import java.io.IOException;
import java.util.Set;


//TODO: Finish this !!
//This is more or less a copy of AlbumListSerializer

public class AlbumListModelSerializer extends JsonSerializer<AlbumListModel> {

  //private final Set<AlbumReviewPersistence.AlbumReviewParts> parts;

  /*public AlbumListModelSerializer(Set<AlbumReviewPersistence.AlbumReviewParts> parts) {
    this.parts = parts;
  }*/

  public void serialize(AlbumListModel albumListModel, JsonGenerator jsonGen, SerializerProvider serizerProvider) throws IOException {
    jsonGen.writeStartObject();
    jsonGen.writeArrayFieldStart("albums");
    for (Album album : albumListModel.getAlbums()) {
      jsonGen.writeObject(album);
    }
    jsonGen.writeEndArray();
    jsonGen.writeEndObject();
  }
  /*
    jsonGen.writeStartObject();
    if (parts.contains(AlbumReviewPersistence.AlbumReviewParts.ALBUM) || parts.contains(AlbumReviewPersistence.AlbumReviewParts.REVIEW)) {
      jsonGen.writeArrayFieldStart("albums");
      //for (AlbumList album : albumListModel) {
      //
      //}
   }*/
}
