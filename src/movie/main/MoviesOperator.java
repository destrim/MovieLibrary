package movie.main;

import movie.management.MoviesLibrary;
import utils.ReadInputData;

public class MoviesOperator {
    public static void main(String[] args) {
        MoviesLibrary moviesLibrary = new MoviesLibrary();
        StringBuilder messageToPrint = new StringBuilder()
                .append("\nYour movie.representation.Movie Database\n\n")
                .append("What do you want to do?\n")
                .append("\t1. Add new movie to your list.\n")
                .append("\t2. Delete movie from your list.\n")
                .append("\t3. Show your list.\n")
                .append("\t4. Sort your list.\n")
                .append("\t5. Save your list to JSON file.\n")
                .append("\t6. Import your list from JSON file.\n")
                .append("\t0. Quit program.\n")
                .append("\n");

        loop: while (true) {
            System.out.print(messageToPrint.toString());
            String whatToDo = ReadInputData.readWhatToDo();

            switch (whatToDo) {
                case "1":
                    moviesLibrary.searchForMovie();
                    break;
                case "2":
                    moviesLibrary.deleteMovie();
                    break;
                case "3":
                    moviesLibrary.showMovies();
                    break;
                case "4":
                    moviesLibrary.sortMovies();
                    break;
                case "5":
                    moviesLibrary.saveMovies();
                    break;
                case "6":
                    moviesLibrary.importMovies();
                    break;
                case "0":
                    break loop;
                default:
                    System.out.println("Incorrect number.");
                    break;
            }
        }
    }
}
