package core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileHandler {

    public static void writeToFile(AlbumReviewList ar) {
        try {
            PrintWriter pr = new PrintWriter("albumReviews.txt");
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
            File f = new File("albumReviews.txt");
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

    public static void main(String args[]){
        AlbumReviewList ar = new AlbumReviewList();
        System.out.println(ar.albumReviews.size());
        loadFile(ar);
        System.out.println(ar.albumReviews.size());
        

    }
}
