package statepersistence.serializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import domainlogic.AlbumReview;
import domainlogic.Review;
import java.io.IOException;

/**
 * Deserializes Review objects.
 */
public class ReviewDeserializer extends JsonDeserializer<Review> {

  @Override
  public Review deserialize(JsonParser parser, DeserializationContext ctxt)
      throws IOException, JacksonException {
    TreeNode treeNode = parser.getCodec().readTree(parser);
    return deserialize((JsonNode) treeNode);
  }

  /**
   * Deserializes Review jsonNode.
   * 
   * @param jsonNode
   * @return
   * @throws IOException
   * @throws JacksonException
   */
  public Review deserialize(JsonNode jsonNode) throws IOException, JacksonException {
    if (jsonNode instanceof ObjectNode) {
      ObjectNode objectNode = (ObjectNode) jsonNode;
      JsonNode userNode = objectNode.get("user");
      String user = null;
      if (userNode instanceof TextNode) {
        user = ((TextNode) userNode).asText();
      }
      JsonNode ratingNode = objectNode.get("rating");
      int rating = 0;
      if (ratingNode instanceof IntNode) {
        rating = ((IntNode) ratingNode).asInt();
      }
      Review review = new Review(user, rating);
      return review;
    }
    return null;
  }

}
