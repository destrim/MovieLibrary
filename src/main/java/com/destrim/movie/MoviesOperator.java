package com.destrim.movie;

import com.destrim.utils.ReadInputData;

public class MoviesOperator {
    public static void start() {
        MoviesService moviesService = new MoviesService();
        StringBuilder messageToPrint = new StringBuilder()
                .append("\nYour Movie Database\n\n")
                .append("What do you want to do?\n")
                .append("\t1. Add new movie to your list.\n")
                .append("\t2. Delete movie from your list.\n")
                .append("\t3. Show your list.\n")
                .append("\t4. Sort your list.\n")
                .append("\t5. Save your list to JSON file.\n")
                .append("\t6. Import your list from JSON file.\n")
                .append("\t7. Save movies to database.\n")
                .append("\t0. Quit program.\n")
                .append("\n");

        loop:
        while (true) {
            System.out.print(messageToPrint.toString());
            String whatToDo = ReadInputData.readWhatToDo();

            switch (whatToDo) {
                case "1":
                    moviesService.searchForMovie();
                    break;
                case "2":
                    moviesService.deleteMovie();
                    break;
                case "3":
                    moviesService.showMovies();
                    break;
                case "4":
                    moviesService.sortMovies();
                    break;
                case "5":
                    moviesService.exportMoviesToJSON();
                    break;
                case "6":
                    moviesService.importMoviesfromJSON();
                    break;
                case "7":
                    moviesService.saveMovies();
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
