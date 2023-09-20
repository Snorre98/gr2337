package core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileHandler {

    public static void writeToFile(AlbumReviewList ar) {
        try {
            clearFile();
            PrintWriter pr = new PrintWriter("core/src/main/resources/persistant-data.txt");
            for (int i = 0; i < ar.getAlbumReviews().size(); i++) {
                AlbumReview review = ar.getAlbumReview(i);
                StringBuilder reviewString = new StringBuilder();
                reviewString.append(review.getName() + "%%%" + review.getRating());
                pr.println(reviewString);
            }
            pr.close();
        } catch (Exception e) {
            System.err.println("Saving Error");
        }
    }

    public static void loadFile(AlbumReviewList ar){
        try {
            File f = new File("core/src/main/resources/persistant-data.txt");
            Scanner scanner = new Scanner(f);
            while(scanner.hasNextLine()){
                String[] s = scanner.nextLine().split("%%%");
                String album = s[0];
                int review = Integer.parseInt((s[1]));
                ar.addAlbumReview(album, review);
            
            }
            scanner.close();
        }catch (FileNotFoundException e) {
      System.out.println("Something has gone wrong in loading");
      e.printStackTrace();
        }
    }

    public static void clearFile() {
        try {
            PrintWriter pr = new PrintWriter("core/src/main/resources/persistant-data.txt");
            pr.write("");
            pr.flush();
            pr.close();
            
        } catch (Exception e) {
            System.err.println("Could not clear file");
        }
    }
}
