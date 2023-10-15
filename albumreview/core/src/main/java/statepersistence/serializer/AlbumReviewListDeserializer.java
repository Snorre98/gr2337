package statepersistence.serializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import domainlogic.AlbumReview;
import domainlogic.AlbumReviewList;
import java.io.IOException;

/**
 * AlbumReviewList deserializer.
 */
public class AlbumReviewListDeserializer extends JsonDeserializer<AlbumReviewList> {

  private AlbumReviewDeserializer albumReviewDeserializer = new AlbumReviewDeserializer();

  @Override
  public AlbumReviewList deserialize(JsonParser parser, DeserializationContext ctxt)
      throws IOException, JacksonException {
    TreeNode treeNode = parser.getCodec().readTree(parser);
    if (treeNode instanceof ObjectNode) {
      ObjectNode objectNode = (ObjectNode) treeNode;
      JsonNode albumsNode = objectNode.get("albums");
      AlbumReviewList albums = new AlbumReviewList();
      if (albumsNode instanceof ArrayNode) {
        for (JsonNode elementNode : ((ArrayNode) albumsNode)) {
          AlbumReview album = albumReviewDeserializer.deserialize(elementNode);
          if (album != null) {
            albums.addAlbumReview(album);
          }
        }
      }
      return albums;
    }
    return null;
  }
}
