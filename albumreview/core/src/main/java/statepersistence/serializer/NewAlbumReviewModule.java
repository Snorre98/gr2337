package statepersistence.serializer;

import com.fasterxml.jackson.core.util.VersionUtil;
import com.fasterxml.jackson.databind.module.SimpleModule;
import domainlogic.Album;
import domainlogic.AlbumList;
import domainlogic.Review;
import java.util.Set;
import statepersistence.AlbumReviewPersistence;


/**
 * Module for persistance in AlbumReview.
 */
public class NewAlbumReviewModule extends SimpleModule {
  private static final String NAME = "CustomIntervalModule";
  private static final VersionUtil VERSION_UTIL = new VersionUtil() {};

  /**
   * AlbumReviewModule serializer.
   */
  public NewAlbumReviewModule() {
    super(NAME, VERSION_UTIL.version());
    addSerializer(Review.class, new ReviewSerializer());
    addSerializer(Album.class, new AlbumSerializer());
    addSerializer(AlbumList.class, new AlbumListSerializer());

    addDeserializer(Review.class, new ReviewDeserializer());
    addDeserializer(Album.class, new AlbumDeserializer());
    addDeserializer(AlbumList.class, new AlbumListDeserializer());
  }

  /**
   * Used in {@link AlbumReviewPersistence}.
   * */
  public NewAlbumReviewModule(Set<AlbumReviewPersistence.AlbumReviewParts> parts) {
    super(NAME, VERSION_UTIL.version());
    addSerializer(Review.class, new ReviewSerializer());
    addSerializer(Album.class, new AlbumSerializer());
    addSerializer(AlbumList.class, new AlbumListSerializer());

    addDeserializer(Review.class, new ReviewDeserializer());
    addDeserializer(Album.class, new AlbumDeserializer());
    addDeserializer(AlbumList.class, new AlbumListDeserializer());

  }

}

