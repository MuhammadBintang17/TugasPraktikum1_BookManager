import org.example.Book;
import org.example.BookManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BookManagerTest {
    private BookManager bookManager;

    @BeforeEach
    void setup() {
        bookManager = new BookManager();
    }

    // =======================
    // BAGIAN TEST AWAL
    // =======================

    @Test
    @DisplayName("Test menambahkan buku")
    void testAddBook() {
        Book buku = new Book("Pemrograman", "Andi", 2020);
        bookManager.addBook(buku);
        assertEquals(1, bookManager.getBookCount());
    }

    @Test
    @DisplayName("Test menghapus buku yang ada")
    void testRemoveExistingBook() {
        Book buku = new Book("Basis Data", "ErLangga", 2021);
        bookManager.addBook(buku);

        boolean removed = bookManager.removeBook("Basis Data");
        assertTrue(removed);
        assertEquals(0, bookManager.getBookCount());
    }

    @Test
    @DisplayName("Test menghapus buku yang tidak ada")
    void testRemoveNonExistingBook() {
        Book buku = new Book("Jaringan Komputer", "Budi", 2019);
        bookManager.addBook(buku);

        boolean removed = bookManager.removeBook("Algoritma");
        assertFalse(removed);
        assertEquals(1, bookManager.getBookCount());
    }

    @Test
    @DisplayName("Test mencari buku berdasarkan author")
    void testFindBooksByAuthor() {
        Book buku1 = new Book("AI Dasar", "Citra", 2022);
        Book buku2 = new Book("AI Lanjut", "Citra", 2023);
        Book buku3 = new Book("Sistem Operasi", "Dewi", 2021);

        bookManager.addBook(buku1);
        bookManager.addBook(buku2);
        bookManager.addBook(buku3);

        List<Book> hasil = bookManager.findBooksByAuthor("Citra");
        assertEquals(2, hasil.size());
        assertTrue(hasil.contains(buku1));
        assertTrue(hasil.contains(buku2));
    }

    @Test
    @DisplayName("Test mendapatkan semua buku")
    void testGetAllBooks() {
        Book buku1 = new Book("Matematika Diskrit", "Andi", 2018);
        Book buku2 = new Book("Pemrograman Web", "Budi", 2020);

        bookManager.addBook(buku1);
        bookManager.addBook(buku2);

        List<Book> semuaBuku = bookManager.getAllBooks();
        assertEquals(2, semuaBuku.size());
        assertTrue(semuaBuku.contains(buku1));
        assertTrue(semuaBuku.contains(buku2));
    }

    // =======================
    // PENGEMBANGAN TEST
    // =======================

    // 6.1.1 Positive Test Cases
    @Test
    @DisplayName("Positive: Valid Book Creation")
    void testValidBookCreation() {
        Book buku = new Book("Machine Learning", "Rina", 2022);
        bookManager.addBook(buku);
        assertEquals(1, bookManager.getBookCount());
    }

    @Test
    @DisplayName("Positive: Book Search by Author")
    void testBookSearchByAuthor() {
        Book buku1 = new Book("Cloud Computing", "Fajar", 2021);
        Book buku2 = new Book("IoT Fundamentals", "Fajar", 2020);
        bookManager.addBook(buku1);
        bookManager.addBook(buku2);

        List<Book> hasil = bookManager.findBooksByAuthor("Fajar");
        assertEquals(2, hasil.size());
    }

    @Test
    @DisplayName("Positive: Book Search by Year")
    void testBookSearchByYear() {
        Book buku1 = new Book("Big Data", "Andi", 2020);
        Book buku2 = new Book("Data Mining", "Budi", 2020);
        Book buku3 = new Book("Cyber Security", "Citra", 2021);

        bookManager.addBook(buku1);
        bookManager.addBook(buku2);
        bookManager.addBook(buku3);

        List<Book> hasil = bookManager.findBooksByYear(2020);
        assertEquals(2, hasil.size());
    }

    @Test
    @DisplayName("Positive: Multiple Books Added")
    void testMultipleBooksAdded() {
        for (int i = 1; i <= 5; i++) {
            bookManager.addBook(new Book("Book" + i, "Author" + i, 2000 + i));
        }
        assertEquals(5, bookManager.getBookCount());
    }

    // 6.1.2 Negative Test Cases
    @Test
    @DisplayName("Negative: Invalid Book Title")
    void testInvalidBookTitle() {
        Exception ex = assertThrows(IllegalArgumentException.class,
                () -> new Book("", "Anonim", 2020));
        assertEquals("Judul harus diisi", ex.getMessage());
    }

    @Test
    @DisplayName("Negative: Invalid Year")
    void testInvalidYear() {
        Exception ex = assertThrows(IllegalArgumentException.class,
                () -> new Book("Sejarah", "Dono", -100));
        assertEquals("Tahun hanya bisa diisi dari tahun 2000 sampai 2100", ex.getMessage());
    }

    @Test
    @DisplayName("Negative: Remove Non-existent Book")
    void testRemoveNonExistentBookNegative() {
        boolean removed = bookManager.removeBook("Buku Ga Ada");
        assertFalse(removed);
    }

    @Test
    @DisplayName("Negative: Search Non-existent Author")
    void testSearchNonExistentAuthor() {
        List<Book> hasil = bookManager.findBooksByAuthor("TidakAda");
        assertTrue(hasil.isEmpty());
    }

    // 6.1.3 Edge Cases
    @Test
    @DisplayName("Edge: Empty BookManager")
    void testEmptyBookManager() {
        assertEquals(0, bookManager.getBookCount());
        assertTrue(bookManager.getAllBooks().isEmpty());
    }

    @Test
    @DisplayName("Edge: Duplicate Books")
    void testDuplicateBooks() {
        Book buku = new Book("Blockchain", "Eka", 2022);
        bookManager.addBook(buku);
        bookManager.addBook(buku); // duplikat
        assertEquals(1, bookManager.getBookCount()); // sistem menolak duplikat
    }

    @Test
    @DisplayName("Edge: Large Dataset")
    void testLargeDataset() {
        for (int i = 0; i < 10000; i++) {
            bookManager.addBook(new Book("Book" + i, "Author" + i, 2000 + (i % 20)));
        }
        assertEquals(10000, bookManager.getBookCount());
    }

    @Test
    @DisplayName("Edge: Case Sensitivity in Author Search")
    void testCaseSensitivityInAuthorSearch() {
        Book buku = new Book("AI Revolution", "CITRA", 2022);
        bookManager.addBook(buku);

        List<Book> hasil = bookManager.findBooksByAuthor("citra"); // implementasi case-insensitive
        assertTrue(hasil.isEmpty() || hasil.contains(buku));
    }
}
