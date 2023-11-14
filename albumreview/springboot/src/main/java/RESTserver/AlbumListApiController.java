package restserver;

import domainlogic.Album;

import domainlogic.AlbumList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


/**
 * API controller for album list.
 * */
@RestController
@RequestMapping("/api/albumlist")
public class AlbumListApiController {


  @Autowired
  private AlbumListService albumListService;

  /**
   * empty constructor.
   * */
  public AlbumListApiController() {}

  public AlbumListApiController(AlbumListService albumListService) {
    this.albumListService = albumListService;
  }

  @GetMapping("/getAlbumList")
  public AlbumList getAlbumList() {
    return this.albumListService.getAlbumList();
  }

  @GetMapping("/getAlbum/{index}")
  public Album getAlbum(@PathVariable int index) {
    return this.albumListService.getAlbum(index);
  }

  @PostMapping(value = "/addAlbum/{artist}/{name}")
  //@ResponseStatus(code = HttpStatus.CREATED)
  public String addAlbum(@PathVariable String artist, @PathVariable String name) {
    return albumListService.addAlbum(artist, name);
  }

  @DeleteMapping(value = "/removeAlbum/{index}")
  @ResponseStatus(code = HttpStatus.OK)
  public String removeAlbum(@PathVariable int index) {
    return albumListService.removeAlbum(index);
  }

  @GetMapping("/sortAlbumsByName")
  public String sortAlbumByName() {
    return albumListService.sortAlbumsByName();
  }

  @GetMapping("/sortAlbumsByArtist")
  public String sortAlbumsByArtist() {
    return albumListService.sortAlbumsByArtist();
  }

}
