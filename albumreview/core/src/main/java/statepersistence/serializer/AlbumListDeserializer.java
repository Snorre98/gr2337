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
import domainlogic.AlbumList;
import java.io.IOException;

/**
 * Deserializes AlbumList object.
 */
public class AlbumListDeserializer extends JsonDeserializer<AlbumList> {

  private AlbumDeserializer albumDeserializer = new AlbumDeserializer();

  @Override
  public AlbumList deserialize(JsonParser parser, DeserializationContext ctxt)
      throws IOException, JacksonException {
    TreeNode treeNode = parser.getCodec().readTree(parser);
    if (treeNode instanceof ObjectNode) {
      ObjectNode objectNode = (ObjectNode) treeNode;
      JsonNode albumsNode = objectNode.get("albums");
      AlbumList albumList = new AlbumList();
      if (albumsNode instanceof ArrayNode) {
        for (JsonNode elementNode : ((ArrayNode) albumsNode)) {
          Album album = albumDeserializer.deserialize(elementNode);
          if (album != null) {
            albumList.addAlbum(album);
          }
        }
      }
      return albumList;
    }
    return null;
  }
}
