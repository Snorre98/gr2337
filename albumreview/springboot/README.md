# This module (restapi) handles the service layer of the application. The module gives access to several classes and transfer data to and from the service layer. The classes are

Some of the services a controllers are not used. They were created before passing HTTP requests in ui controllers. 
Some of them where also written to support future functionality we were not able to finish. 
- AlbumReviewApplication

- AlbumListApiController
- AlbumListService
  
- AlbumService
- AlbumApiController

- ReviewApiController
- ReviewService

## AlbumListApiApplication

Spring boot application class. Starts the server.

### Methods in AlbumListApiApplication

main(String...) Main method for starting the application.

## AlbumListApiController

Controller for handeling get and post, and consist of AlbumListService. Contain all the endpoints for the request. When a request with correct endpoint comes in, the controller runs the service to perform the action.

### Methods in AlbumListApiController

The methods are divided into get and post. These tags are simplified and not completly equal to the tags in the code.

@Get

- getAlbumList() -> "/getAlbumList" Returns the AlbumList
  
- getAlbum(int) -> "/getAlbum/{index}" int: what index the album is located in the AlbumList. Returns Album.
  
- sortAlbumByName() -> "/sortAlbumsByName" Returns a sorted AlbumList by album name.

- sortAlbumByArtist() -> "/sortAlbumsByArtist" Returns a sorted AlbumList by artist.

@Post

- addAlbum(String, String) -> "/addAlbum/{artist}/{name}" String: Adds a new album with artist and album name to list and returns 200_OK.

## AlbumApiController

Controller for handeling get, post and remove requests, and consist of AlbumService. Also contain all the endpoints for the requests.

### Methods in AlbumApiController
Most of these are not used in the current app. But the preform GET and POST requests as well as a DELETE request. 


## ReviewApiController

Controller for handeling get requests, and consist of ReviewService. Also contain all the endpoints for the requests.

### Methods in ReviewApiController

@Get

- getUsername(int) -> "/getUsername/{albumId}/{index}" int: Returns a String of the username by sending in the index of the desired Album.

- getRating(int) -> "/getRating/{albumId}/{index}" int: Returns a Integer of the username by sending in the index of the desired Album.

## AlbumListService, AlbumService and ReviewService

  Gives the AlbumListApiController server access to the mothods from core.
  