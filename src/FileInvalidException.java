public class FileInvalidException extends Exception {

    // default constructor
    public FileInvalidException() {
        super("Error: Input file cannot be parsed due to missing information (i.e.month = {}, title = {}, etc.)");
    }

    // custom error message constructor
    public FileInvalidException(String e) {
        super(e);
    }
}
