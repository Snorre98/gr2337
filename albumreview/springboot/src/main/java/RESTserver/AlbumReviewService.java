package RESTserver;

import domainlogic.AlbumListModel;
import org.springframework.stereotype.Service;
import statepersistence.AlbumReviewPersistence;
import domainlogic.Album;
import domainlogic.AlbumList;

@Service
public class AlbumReviewService {
  private AlbumListModel albumListModel;
  private AlbumReviewPersistence albumReviewPersistence;

  public AlbumReviewService(AlbumListModel albumListModel) {
    this.albumListModel = albumListModel;
    this.albumReviewPersistence = new AlbumReviewPersistence();
    albumReviewPersistence.setSaveFile("SpringBoot-server-AlbumReview.json");
  }

  //spublic AlbumReviewService() { this(createDefaultAlbumListModel); }


}
