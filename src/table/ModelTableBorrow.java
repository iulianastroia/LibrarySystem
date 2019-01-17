package table;

/**
 * @author Iuliana
 */
public class ModelTableBorrow {
    private String borrowUsername, bookId, today, borrowtime;

    public ModelTableBorrow(String borrowUsername, String bookId, String today, String borrowtime) {
        this.borrowUsername = borrowUsername;
        this.bookId = bookId;
        this.today = today;
        this.borrowtime = borrowtime;
    }


    public String getBorrowUsername() {
        return borrowUsername;
    }

    public void setBorrowUsername(String borrowUsername) {
        this.borrowUsername = borrowUsername;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getToday() {
        return today;
    }

    public void setToday(String today) {
        this.today = today;
    }

    public String getBorrowtime() {
        return borrowtime;
    }

    public void setBorrowtime(String borrowtime) {
        this.borrowtime = borrowtime;
    }


}
