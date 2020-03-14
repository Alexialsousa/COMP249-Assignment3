import java.io.*;
import java.util.Scanner;

public class BibliographyFactory {

    public static void main(String[] args) {

        String[] OriginalFileNames = {"src/Latex1.bib", "src/Latex2.bib", "src/Latex3.bib", "src/Latex4.bib"};
        String[] IEEENames = {"IEEE1.json", "IEEE2.json", "IEEE3.json", "IEEE4.json"};
        String[] ACMNames = {"ACM1.json", "ACM2.json", "ACM3.json", "ACM4.json"};
        String[] NJNames = {"NJ1.json", "NJ2.json", "NJ3.json", "NJ4.json"};
        Scanner[] scanners = new Scanner[4];
        File[] files = new File[4];
        PrintWriter[] IEEEWriters = new PrintWriter[4];
        PrintWriter[] ACMWriters = new PrintWriter[4];
        PrintWriter[] NJWriters = new PrintWriter[4];
        int open_index = 16;
        int write_index = 17;
        boolean deleted = false;


        System.out.println("Welcome to the BibliographyFactory!");

        // Opening 10 original files
        try {
            for (int i = 0; i < OriginalFileNames.length; i++) {
                open_index = i;
                scanners[i] = new Scanner(new FileInputStream(OriginalFileNames[i]));
            }

        } catch (FileNotFoundException e) {
            System.out.println("Could not open input file " + OriginalFileNames[open_index] + " for reading. \nPlease check if file exists! Program will terminate after closing any opened files.");
        }
        // closing any opened files
        finally {
            for (int i = open_index - 1; i >= 0; i--) {
                scanners[i].close();
                System.out.println("Closed all opened files! Goodbye");
            }
            System.exit(0);
        }

        // Create IEE Files
        try {
            for (int i = 0; i < OriginalFileNames.length; i++) {
                write_index = i;
                files[i] = new File((IEEENames[i]));
                IEEEWriters[i] = new PrintWriter(files[i]);
            }

        } catch (FileNotFoundException e) {
            System.out.println("Could not create file " + IEEENames[write_index] + ". Deleting all created files and exiting program.");
            // deleting all created files
            for (int i = write_index - 1; i >= 0; i--) {
                deleted = files[i].delete();

                if (!deleted) {
                    System.out.println("An error occurred: Could not delete all created files! Goodbye");
                    System.exit(0);
                }
            }
            System.out.println("Deleted all created files! Goodbye");
            System.exit(0);
        }

        // Create ACM Files
        try {
            for (int i = 0; i < OriginalFileNames.length; i++) {
                write_index = i;
                files[i] = new File((ACMNames[i]));
                ACMWriters[i] = new PrintWriter(files[i]);
            }

        } catch (FileNotFoundException e) {
            System.out.println("Could not create file " + ACMNames[write_index] + ". Deleting all created files and exiting program.");
            // deleting all created files
            for (int i = write_index - 1; i >= 0; i--) {
                deleted = files[i].delete();

                if (!deleted) {
                    System.out.println("An error occurred: Could not delete all created files! Goodbye");
                    System.exit(0);
                }
            }
            System.out.println("Deleted all created files! Goodbye");
            System.exit(0);
        }
        // Create NJ Files
        try {
            for (int i = 0; i < OriginalFileNames.length; i++) {
                write_index = i;
                files[i] = new File(NJNames[i]);
                NJWriters[i] = new PrintWriter(files[i]);
            }

        } catch (FileNotFoundException e) {
            System.out.println("Could not create file " + NJNames[write_index] + ". Deleting all created files and exiting program.");
            // deleting all created files
            for (int i = write_index - 1; i >= 0; i--) {
                deleted = files[i].delete();

                if (!deleted) {
                    System.out.println("An error occurred: Could not delete all created files! Goodbye");
                    System.exit(0);
                }
            }
            System.out.println("Deleted all created files! Goodbye");
            System.exit(0);
        }

        public static void processFilesForValidation () {
            // TODO are the files valid?
            // if valid then create records for their respectful 3 files
            // store them in each file
            // if invalid then throw FileInvalidException and output error message
            // print name of file that threw error and where the FIRST time it was invalid
            // DELETE INVALID FILE
            // continue with next file

        }

    }
}

