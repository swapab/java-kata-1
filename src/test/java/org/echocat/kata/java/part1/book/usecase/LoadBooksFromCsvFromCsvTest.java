package org.echocat.kata.java.part1.book.usecase;

import org.echocat.kata.java.part1.book.domain.Book;
import org.echocat.kata.java.part1.util.CsvEntityLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class LoadBooksFromCsvFromCsvTest {

    private CsvEntityLoader mockCsvEntityLoader = mock(CsvEntityLoader.class);
    private CreateNewBookUseCase mockCreateNewBookUseCase = mock(CreateNewBookUseCase.class);

    private LoadBooksFromCsvFromCsv loadBooksFromCsvFromCsv;

    @BeforeEach
    void setUp() {
        loadBooksFromCsvFromCsv = new LoadBooksFromCsvFromCsv(mockCsvEntityLoader, mockCreateNewBookUseCase);
    }

    @Test
    void shouldCreate_N_BooksFromCsvWith_N_Records() {
        Book book = Book.builder().build();
        List<Book> books = List.of(book);
        Path resourceDirectory = Paths.get("target", "classes");
        resourceDirectory.toAbsolutePath();
        String expectedBookCsvFilePath = resourceDirectory.toAbsolutePath() + "/org/echocat/kata/java/part1/data/books.csv";
        given(mockCsvEntityLoader.loadBooks(expectedBookCsvFilePath)).willReturn(books);

        loadBooksFromCsvFromCsv.loadBooks();

        verify(mockCreateNewBookUseCase).saveBook(book);
    }
}

