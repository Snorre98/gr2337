package domainlogic;

/**
 * Used to create data-model.
 */
public class AbstractAlbumList {

  private String abstractAlbumListName;

  AbstractAlbumList(String abstractAlbumListName) {
    setAlbumName(abstractAlbumListName);
  }

  public String getAbstractAlbumListName() {
    return abstractAlbumListName;
  }

  public void setAlbumName(String abstractAlbumListName) {
    this.abstractAlbumListName = abstractAlbumListName;
  }

  // eksempel:
  public Album createAbstractAlbum() {
    throw new UnsupportedOperationException("Abstract class cannot create album");
  }

}
