package com.destrim.service;

import com.destrim.model.Movie;

import java.util.List;
import java.util.Scanner;

public class IOService {

    private final String userCommandGuide = new StringBuilder()
            .append("\nAvailable commands:\n")
            .append("\tshow = Show your movie database.\n")
            .append("\tadd -t \"title\" -y year = Add new movie with title and released year to your list.\n")
            .append("\tdelete -i index = Delete movie with index from your list.\n")
            .append("\tsort -p t/y/r = Sort your list by title t, released year y or IMDB rating r.\n")
            .append("\texport -f filename = Save your movies to JSON file.\n")
            .append("\timport -f filename = Import your list from JSON file.\n")
            .append("\tquit = Quit program.")
            .toString();

    public boolean askIfMovieToAddIsCorrect(Movie movie) {
        System.out.println("\nAre you sure you want to add movie:");
        printMovie(movie);
        System.out.println("\nto your database?\n");

        return askIfUserAccepts();
    }

    public boolean askIfMovieToDeleteIsCorrect(Movie movie) {
        System.out.println("\nAre you sure you want to delete movie:");
        printMovie(movie);
        System.out.println("\nfrom your database?\n");

        return askIfUserAccepts();
    }

    public void printMovies(List<Movie> movies) {
        System.out.println("\n~~~~~~~~~~~~~~~~~~~ Your Movie Database ~~~~~~~~~~~~~~~~~~~");
        for (Movie movie : movies) {
            printMovieWithId(movie);
        }
    }

    public void printUserCommandGuide() {
        System.out.println(userCommandGuide);
    }

    public void printFileNotFoundMessage() {
        System.out.println("File do not exists.");
    }

    public void wrongCommandMessage() {
        System.out.println("Incorrect command.");
    }

    public void printExceptionMessage(Exception e) {
        System.out.println(e.getMessage());
    }

    public String readInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("\nWrite your command: ");
        return scanner.nextLine();
    }

    private void printMovieWithId(Movie movie) {
        System.out.print("\nMovie ID: ");
        System.out.print(movie.getId());
        printMovie(movie);
    }

    private void printMovie(Movie movie) {
        StringBuilder messageToPrint = new StringBuilder()
                .append("\n\tMovie title: ")
                .append(movie.getTitle())
                .append("\n\tMovie released: ")
                .append(movie.getReleased())
                .append("\n\tMovie genre: ")
                .append(movie.getGenre())
                .append("\n\tMovie plot: ")
                .append(movie.getPlot())
                .append("\n\tMovie IMDB Rating: ")
                .append(movie.getImdbRating());
        System.out.println(messageToPrint.toString());
    }

    private boolean askIfUserAccepts() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("(y/n): ");
        char choice = Character.toLowerCase(scanner.next().charAt(0));
        return choice == 'y';
    }
}
