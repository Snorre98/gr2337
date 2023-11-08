package statepersistence.serializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import domainlogic.Album;
import domainlogic.AlbumListModel;

import java.io.IOException;


//TODO: Finnish this
//This is more or less a copy of AlbumList deserializer for now
public class AlbumListModelDeserializer extends JsonDeserializer<AlbumListModel> {
  private AlbumDeserializer albumDeserializer = new AlbumDeserializer();

  @Override
  public AlbumListModel deserialize(JsonParser parser, DeserializationContext ctxt)
      throws IOException, JacksonException {
    TreeNode treeNode = parser.getCodec().readTree(parser);
    if (treeNode instanceof ObjectNode) {
      ObjectNode objectNode = (ObjectNode) treeNode;
      JsonNode albumsNode = objectNode.get("albums");
      AlbumListModel albumListModel = new AlbumListModel();
      if (albumsNode instanceof ArrayNode) {
        for (JsonNode elementNode : ((ArrayNode) albumsNode)) {
          Album album = albumDeserializer.deserialize(elementNode);
          if (album != null) {
            albumListModel.addAlbum(album);
          }
        }
      }
      return albumListModel;
    }
    return null;
  }
}
