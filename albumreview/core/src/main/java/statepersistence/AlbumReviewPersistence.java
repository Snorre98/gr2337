package statepersistence;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import domainlogic.AlbumListModel;
import statepersistence.serializer.NewAlbumReviewModule;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.EnumSet;
import java.util.Set;
//import domainlogic.AlbumList;

public class AlbumReviewPersistence {
  public enum AlbumReviewParts {
    ALBUM_LIST, ALBUM, REVIEW
  }

  private ObjectMapper mapper;

  public AlbumReviewPersistence() {
    mapper = createObjectMapper();
  }

  //TODO: fix "new"
  public static SimpleModule createJacksonModule(Set<AlbumReviewParts> parts) {
    return new NewAlbumReviewModule(parts);
  }

 public static ObjectMapper createObjectMapper(Set<AlbumReviewParts> parts) {
    return new ObjectMapper().registerModule(createJacksonModule(parts));
  }
  public static ObjectMapper createObjectMapper() {
    return createObjectMapper((EnumSet.allOf(AlbumReviewParts.class)));
  }

  public AlbumListModel readAlbumListModel(Reader reader) throws IOException {
    return mapper.readValue(reader, AlbumListModel.class);
  }

  public void writeAlbumListModel(AlbumListModel albumListModel, Writer writer) throws IOException {
    mapper.writerWithDefaultPrettyPrinter().writeValue(writer, albumListModel);
  }

  private Path saveFilePath = null;

  public void setSaveFile(String saveFile) {
    this.saveFilePath = Paths.get(System.getProperty("user.home"), saveFile);
  }

  public Path getSaveFilePath() {
    return this.saveFilePath;
  }

  public AlbumListModel loadAlbumListModel() throws IOException, IllegalStateException {

    if (saveFilePath == null) {
      throw new IllegalStateException("Save file path is not set");
    }
    try (Reader reader = new FileReader(saveFilePath.toFile(), StandardCharsets.UTF_8)) {
      return readAlbumListModel(reader);
    }
  }
  public void saveAlbumListModel(AlbumListModel albumListModel) throws IOException, IllegalStateException {

    if (saveFilePath == null) {
      throw new IllegalStateException("Save file path is not set, yet");
    }
    try (Writer writer = new FileWriter(saveFilePath.toFile(), StandardCharsets.UTF_8)) {
      writeAlbumListModel(albumListModel, writer);
    }
  }

}
