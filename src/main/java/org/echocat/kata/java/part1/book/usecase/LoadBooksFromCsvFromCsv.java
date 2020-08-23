package org.echocat.kata.java.part1.book.usecase;

import org.echocat.kata.java.part1.util.CsvEntityLoader;

import java.util.Objects;

public class LoadBooksFromCsvFromCsv implements LoadBooksFromCsvUseCase {
    private final CsvEntityLoader csvEntityLoader;
    private final CreateNewBookUseCase createNewBookUseCase;

    public LoadBooksFromCsvFromCsv(CsvEntityLoader csvEntityLoader,
                                   CreateNewBookUseCase createNewBookUseCase) {
        this.csvEntityLoader = csvEntityLoader;
        this.createNewBookUseCase = createNewBookUseCase;
    }

    @Override
    public void loadBooks() {
        ClassLoader classLoader = getClass().getClassLoader();
        csvEntityLoader.loadBooks(
                Objects.requireNonNull(classLoader.getResource("org/echocat/kata/java/part1/data/books.csv")).getFile())
                       .forEach(createNewBookUseCase::saveBook);
    }
}
