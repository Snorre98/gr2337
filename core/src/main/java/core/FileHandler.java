package core;

import java.io.PrintWriter;

public class FileHandler {

    AlbumReviewList ar;

    public FileHandler(AlbumReviewList ar) {
        this.ar = ar;
    }

    public void writeToFile() {
        try {
            PrintWriter pr = new PrintWriter("albumReviews.txt");
            for (int i = 0; i < ar.getAlbumReviews().size(); i++) {
                pr.println(ar.getAlbumReview(i));
            }
            pr.close();
        } catch (Exception e) {
            System.err.println("Saving Error");
        }
    }
}
