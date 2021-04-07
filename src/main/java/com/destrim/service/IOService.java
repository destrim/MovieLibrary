package com.destrim.service;

import com.destrim.model.MovieDTO;

import java.util.List;
import java.util.Scanner;

public class IOService {

    private final String userCommandGuide = new StringBuilder()
            .append("\nAvailable commands:\n")
            .append("\tshow -b t/y/r = Show your list sorted by title t, released year y or IMDB rating r. " +
                    "Argument is optional.\n")
            .append("\tadd -t \"title\" -y year = Add new movie with title and released year to your list.\n")
            .append("\tdelete -i index = Delete movie with index from your list.\n")
            .append("\texport -f filename = Save your movies to JSON file.\n")
            .append("\timport -f filename = Import your list from JSON file.\n")
            .append("\tquit = Quit program.\n")
            .toString();

    public boolean askIfMovieToAddIsCorrect(MovieDTO movieDTO) {
        printNewLine();
        System.out.println("Are you sure you want to add movie:");
        printMovie(movieDTO);
        printNewLine();
        System.out.println("to your database?");
        printNewLine();

        return askIfUserAccepts();
    }

    public boolean askIfMovieToDeleteIsCorrect(MovieDTO movieDTO) {
        printNewLine();
        System.out.println("Are you sure you want to delete movie:");
        printMovie(movieDTO);
        printNewLine();
        System.out.println("from your database?");
        printNewLine();

        return askIfUserAccepts();
    }

    public void printMovies(List<MovieDTO> moviesDTO) {
        printNewLine();
        System.out.println("~~~~~~~~~~~~~~~~~~~ Your Movie Database ~~~~~~~~~~~~~~~~~~~");
        for (MovieDTO movieDTO : moviesDTO) {
            printMovieWithId(movieDTO);
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

        System.out.println("Write your command: ");
        return scanner.nextLine();
    }

    private void printMovieWithId(MovieDTO movieDTO) {
        printNewLine();
        System.out.print("Movie ID: ");
        System.out.print(movieDTO.getId());
        printMovie(movieDTO);
    }

    private void printMovie(MovieDTO movieDTO) {
        StringBuilder messageToPrint = new StringBuilder()
                .append("\n\tMovie title: ")
                .append(movieDTO.getTitle())
                .append("\n\tMovie year: ")
                .append(movieDTO.getYear())
                .append("\n\tMovie genre: ")
                .append(movieDTO.getGenre())
                .append("\n\tMovie plot: ")
                .append(movieDTO.getPlot())
                .append("\n\tMovie IMDB Rating: ")
                .append(movieDTO.getImdbRating());
        System.out.println(messageToPrint.toString());
    }

    private boolean askIfUserAccepts() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("(y/n): ");
        char choice = Character.toLowerCase(scanner.next().charAt(0));
        return choice == 'y';
    }

    private void printNewLine() {
        System.out.println();
    }
}
