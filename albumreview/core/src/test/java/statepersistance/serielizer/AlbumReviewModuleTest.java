package statepersistance.serielizer;

import static org.junit.jupiter.api.Assertions.fail;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domainlogic.AlbumReview;
import domainlogic.AlbumReviewList;
import statepersistence.serializer.AlbumReviewModule;

public class AlbumReviewModuleTest {
  private static ObjectMapper mapper;

  @BeforeAll
  public static void setUp() {
    mapper = new ObjectMapper();
    mapper.registerModule(new AlbumReviewModule());
  }

  @Test
  public void testSerializers(){
    AlbumReviewList list = new AlbumReviewList();
    AlbumReview review1 = new AlbumReview("Bov Jobi", 6);
    AlbumReview review2 = new AlbumReview("BAD", 10);
    list.addAlbumReview(review1);
    list.addAlbumReview(review2);
    try{
      assertEquals("{\"albums\":[{\"name\":\"Bov Jobi\",\"rating\":6},{\"name\":\"BAD\",\"rating\":10}]}",
      mapper.writeValueAsString(list));
    }
    catch(JsonProcessingException e){
      fail();
    }
  }
}
