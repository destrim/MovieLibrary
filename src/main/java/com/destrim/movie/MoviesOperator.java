package com.destrim.movie;

import com.destrim.utils.ReadInputData;

public class MoviesOperator {
    public static void start() {
        MoviesService moviesService = new MoviesService();
        StringBuilder messageToPrint = new StringBuilder()
                .append("\nWhat do you want to do?\n")
                .append("\tadd -t title -y year = Add new movie with title and released year to your list.\n")
                .append("\tdelete -i index = Delete movie with index from your list.\n")
                .append("\tsort -p t/y/r = Sort your list by title t, released year y or IMDB rating r.\n")
                .append("\texport -f filename = Save your movies to JSON file.\n")
                .append("\timport -f filename = Import your list from JSON file.\n")
                .append("\tquit = Quit program.\n");

        while (true) {
            System.out.println("\n~~~~~~~~~~~~~~~~~~~ Your Movie Database ~~~~~~~~~~~~~~~~~~~");
            moviesService.showMovies();
            System.out.println(messageToPrint);

            String[] command = ReadInputData.readCommand().split("-");
            command[0] = command[0].replace(" ", "");

            if (command[0].equalsIgnoreCase("add")) {
                moviesService.addMovie(command);
            }
            else if (command[0].equalsIgnoreCase("delete")) {
                moviesService.deleteMovie(command);
            }
            else if (command[0].equalsIgnoreCase("import")) {
                moviesService.importMoviesFromJSON(command);
            }
            else if (command[0].equalsIgnoreCase("export")) {
                moviesService.exportMoviesToJSON(command);
            }
            else if (command[0].equalsIgnoreCase("quit")) {
                return;
            }
            else {
                System.out.println("Incorrect command.");
            }

//            String whatToDo = ReadInputData.readWhatToDo();
//            switch (whatToDo) {
//                case "1":
//                    moviesService.addMovie();
//                    break;
//                case "2":
//                    moviesService.deleteMovie();
//                    break;
//                case "4":
//                    moviesService.sortMovies();
//                    break;
//                case "5":
//                    moviesService.exportMoviesToJSON();
//                    break;
//                case "6":
//                    moviesService.importMoviesFromJSON();
//                    break;
//                case "0":
//                    return;
//                default:
//                    System.out.println("Incorrect number.");
//                    break;
//            }
        }
    }
}
