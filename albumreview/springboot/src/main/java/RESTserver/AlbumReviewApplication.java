package RESTserver;

import com.fasterxml.jackson.databind.Module;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import statepersistence.AlbumReviewPersistence;
import statepersistence.AlbumReviewPersistence.AlbumReviewParts;

import java.util.EnumSet;

/**
 * SpringBoot application.
 * */
@SpringBootApplication
public class AlbumReviewApplication {

  @Bean
  public Module objectMapperModule() {
    return AlbumReviewPersistence.createJacksonModule(EnumSet.of(AlbumReviewParts.ALBUM_LIST));
  }

  public static void main(String... args) {
    SpringApplication.run(AlbumReviewApplication.class, args);
  }
}
