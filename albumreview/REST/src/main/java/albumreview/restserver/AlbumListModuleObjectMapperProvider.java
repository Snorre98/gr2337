package albumreview.restserver;

import com.fasterxml.jackson.databind.ObjectMapper;
import domainlogic.AlbumListModel;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.ext.ContextResolver;
import jakarta.ws.rs.ext.Provider;
import statepersistence.AlbumReviewPersistence;
import java.util.EnumSet;

@Provider
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AlbumListModuleObjectMapperProvider implements ContextResolver<ObjectMapper> {

  private final ObjectMapper objectMapper;

  public AlbumListModuleObjectMapperProvider() {
    objectMapper = AlbumReviewPersistence.createObjectMapper(EnumSet.of(AlbumReviewPersistence.AlbumReviewParts.ALBUM_LIST));
  }

  @Override
  public ObjectMapper getContext(final Class<?> type) {
    return objectMapper;
  }
}
