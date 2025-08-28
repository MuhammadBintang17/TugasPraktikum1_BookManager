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

    // Test menghapus buku yang tidak ada
    @Test
    @DisplayName("Test menghapus buku yang tidak ada")
    void testRemoveNonExistingBook() {
        Book buku = new Book("Jaringan Komputer", "Budi", 2019);
        bookManager.addBook(buku);

        boolean removed = bookManager.removeBook("Algoritma"); // tidak ada dalam list
        assertFalse(removed); // harus false
        assertEquals(1, bookManager.getBookCount()); // jumlah buku tetap 1
    }

    // Test mencari buku berdasarkan penulis
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

    // Test mendapatkan semua buku
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
}
