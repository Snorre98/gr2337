package json;

import com.fasterxml.jackson.core.util.VersionUtil;
import com.fasterxml.jackson.databind.module.SimpleModule;

import core.AlbumReview;
import core.AlbumReviewList;

public class AlbumReviewModule extends SimpleModule {
  private static final String NAME = "CustomIntervalModule";
  private static final VersionUtil VERSION_UTIL = new VersionUtil() {
  };

  public AlbumReviewModule() {
    super(NAME, VERSION_UTIL.version());
    addSerializer(AlbumReview.class, new AlbumReviewSerializer());
    addSerializer(AlbumReviewList.class, new AlbumReviewListSerializer());
    addDeserializer(AlbumReview.class, new AlbumReviewDeserializer());
    addDeserializer(AlbumReviewList.class, new AlbumReviewListDeserializer());
  }
}
