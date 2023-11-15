package statepersistence.serializer;

import com.fasterxml.jackson.core.util.VersionUtil;
import com.fasterxml.jackson.databind.module.SimpleModule;
import domainlogic.Album;
import domainlogic.AlbumList;
import domainlogic.AlbumListModel;
import domainlogic.Review;
import java.util.Set;
import statepersistence.AlbumReviewPersistence;
import statepersistence.AlbumReviewPersistence.AlbumReviewParts;


/**
 * Module for persistance in AlbumReview.
 */
public class AlbumReviewModule extends SimpleModule {
  private static final String NAME = "CustomIntervalModule";
  private static final VersionUtil VERSION_UTIL = new VersionUtil() {};

  /**
   * AlbumReviewModule serializer.
   */
  public AlbumReviewModule() {
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
  public AlbumReviewModule(Set<AlbumReviewPersistence.AlbumReviewParts> parts) {
    super(NAME, VERSION_UTIL.version());
    addSerializer(Review.class, new ReviewSerializer());
    addSerializer(Album.class, new AlbumSerializer());
    addSerializer(AlbumList.class, new AlbumListSerializer());


    addSerializer(AlbumListModel.class, new AlbumListModelSerializer());
    addDeserializer(AlbumListModel.class, new AlbumListModelDeserializer());


    addDeserializer(Review.class, new ReviewDeserializer());
    addDeserializer(Album.class, new AlbumDeserializer());
    addDeserializer(AlbumList.class, new AlbumListDeserializer());



  }

}

