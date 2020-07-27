package utils;

import management.movie.representation.Movie;

import java.util.Scanner;

public class ReadInputData {
    public static String readWhatToDo() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Choose number from the list: ");
        return scanner.next();
    }

    public static char readYesOrNo() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("(y/n): ");
        return scanner.next().charAt(0);
    }

    public static String readFileName() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("File Name: ");
        return scanner.next();
    }

    public static Movie readMovieAndYear() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("movie.management.movie.representation.Movie title: ");
        String title = scanner.nextLine();

        System.out.print("movie.management.movie.representation.Movie year: ");
        String year = scanner.nextLine();

        return new Movie(
                title,
                year,
                "",
                "",
                ""
        );
    }
}
