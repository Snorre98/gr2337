package json;

import java.io.IOException;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;

import core.AlbumReview;

public class AlbumReviewDeserializer extends JsonDeserializer<AlbumReview>  {

  @Override
  public AlbumReview deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException, JacksonException {
    TreeNode treeNode = parser.getCodec().readTree(parser);
    return deserialize((JsonNode) treeNode);
  }

  public AlbumReview deserialize(JsonNode jsonNode) {
    if(jsonNode instanceof ObjectNode){
      ObjectNode objectNode = (ObjectNode) jsonNode;
      JsonNode nameNode = objectNode.get("name");
      String name = null;
      if (nameNode instanceof TextNode){
        name = ((TextNode) nameNode).asText();
      }
      JsonNode ratingNode = objectNode.get("rating");
      int rating = 0;
      if(ratingNode instanceof IntNode){
        rating = ((IntNode) ratingNode).asInt();
      }
      AlbumReview album = new AlbumReview(name, rating);
      return album;
    }
    return null;
  }
  
}
