# This module (restapi) handles the service layer of the application. The module gives access to several classes and transfer data to and from the service layer. The classes are:

- AlbumReviewApplication

- AlbumListApiController
- AlbumListService
  
- AlbumService
- AlbumApiController
  
- ReviewApiController
- ReviewService


## AlbumListApiApplication

Spring boot application class. Starts the server.

### Methods

main(String...) Main method for starting the application.

<!--- objectMapper() funksjonen?-->
## AlbumListApiController

Controller for handeling get and post, and consist of AlbumListService. Contain all the endpoints for the request. When a request with correct endpoint comes in, the controller runs the service to perform the action.

### Methods

The methods are divided into get and post. The tags are simplified and not completly equal to the tags in the code.

@Get

- getAlbumList() -> "/getAlbumList" Returns the AlbumList
  
- getAlbum(int) -> "/getAlbum/{index}" int: what index the album is located in the AlbumList. Returns Album.
  
- sortAlbumByName() -> "/sortAlbumsByName" Returns a sorted AlbumList by album name.

- sortAlbumByArtist() -> "/sortAlbumsByArtist" Returns a sorted AlbumList by artist.

@Post

- addAlbum(String, String) -> "/addAlbum/{artist}/{name}" String: Adds a new album with artist and album name to list and returns 200_OK.

## AlbumApiController

Controller for handeling get, post and remove requests, and consist of AlbumService. Also contain all the endpoints for the requests.

### Methods

@Get

<!-- TODO mangler getArtist() og getName og getAlbumReview og getReiewList, DISSE brukte UUID men da jeg skrev dette vet vi ikke om vi skl bruke det -->

@Post

- 


## AlbumListService, AlbumService and ReviewService

- Gives the AlbumListApiController server access to the mothods from core.
  
