package com.destrim.service;

import com.destrim.exception.BadApikeyException;
import com.destrim.exception.MovieInOmdbNotFound;
import com.destrim.exception.OmdbConnectionProblem;
import com.destrim.model.MovieDTO;
import com.destrim.model.command.AddCommand;
import com.destrim.model.command.DeleteCommand;
import com.destrim.model.command.FileCommand;
import com.destrim.model.command.ShowCommand;

import java.io.IOException;
import java.util.List;

public class CommandService {

    private final IOService ioService;
    private final MoviesService moviesService;

    public CommandService() throws BadApikeyException {
        this.ioService = new IOService();
        this.moviesService = new MoviesService();
    }

    public void start() {
        boolean isWorking = true;

        while (isWorking) {
            ioService.printUserCommandGuide();

            String input = ioService.readInput();
            isWorking = handleCommand(input);
        }
    }

    private boolean handleCommand(String input) {
        String command = parseInput(input);

        switch (command) {
            case "add" -> handleAdd(input);
            case "show" -> handleShow(input);
            case "delete" -> handleDelete(input);
            case "import" -> handleImport(input);
            case "export" -> handleExport(input);
            case "quit" -> {
                return false;
            }
            default -> ioService.wrongCommandMessage();
        }
        return true;
    }

    private void handleAdd(String input) {
        AddCommand addCommand = AddCommand.fromInput(input);
        try {
            MovieDTO movieDTO = moviesService.fetchMovieData(addCommand.getTitle(), addCommand.getYear());
            boolean userAccept = ioService.askIfMovieToAddIsCorrect(movieDTO);

            if (userAccept) {
                moviesService.addMovie(movieDTO);
            }
        } catch (MovieInOmdbNotFound | OmdbConnectionProblem e) {
            ioService.printExceptionMessage(e);
        }
    }

    private void handleShow(String input) {
        ShowCommand showCommand = ShowCommand.fromInput(input);
        List<MovieDTO> moviesDTO;

        if (showCommand.getSortBy().isEmpty()) {
            moviesDTO = moviesService.getMovies();
        } else {
            moviesDTO = moviesService.getMoviesSortedBy(showCommand.getSortBy());
        }

        ioService.printMovies(moviesDTO);
    }

    private void handleDelete(String input) {
        DeleteCommand deleteCommand = DeleteCommand.fromInput(input);
        MovieDTO movieDTO = moviesService.getMovie(deleteCommand.getId());
        boolean userAccept = ioService.askIfMovieToDeleteIsCorrect(movieDTO);

        if (userAccept) {
            moviesService.deleteMovie(movieDTO.getId());
        }
    }

    private void handleImport(String input) {
        FileCommand fileCommand = FileCommand.fromInput(input);

        try {
            moviesService.importMoviesFromJSON(fileCommand.getFileName());
        } catch (IOException e) {
            ioService.printFileNotFoundMessage();
        }
    }

    private void handleExport(String input) {
        FileCommand fileCommand = FileCommand.fromInput(input);

        try {
            moviesService.exportMoviesToJSON(fileCommand.getFileName());
        } catch (IOException e) {
            ioService.printFileNotFoundMessage();
        }
    }

    private String parseInput(String input) {
        String[] commandSplit = input.split("-");
        String command = commandSplit[0];
        command = command.replace(" ", "");
        return command.toLowerCase();
    }
}
