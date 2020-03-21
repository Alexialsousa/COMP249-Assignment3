/*
Alexia Sousa
40132338
COMP 249 Assignment #3
March 30th 2020

This program acts as a basic bibliography generator. It takes input files with information on multiple
articles and creates three different file types for each input file. These types are IEE, NJ and ACM
format files. This is an efficient way to create and store literary sources.
 */


import java.io.*;
import java.util.Scanner;


public class BibliographyFactory {
    private static String[] OriginalFileNames = {"src/Latex1.bib", "src/Latex2.bib", "src/Latex3.bib", "src/Latex4.bib", "src/Latex5.bib", "src/Latex6.bib", "src/Latex7.bib", "src/Latex8.bib", "src/Latex9.bib", "src/Latex10.bib"};
    private static String[] IEEENames = {"IEEE1.json", "IEEE2.json", "IEEE3.json", "IEEE4.json", "IEEE5.json", "IEEE6.json", "IEEE7.json", "IEEE8.json", "IEEE9.json", "IEEE10.json"};
    private static String[] ACMNames = {"ACM1.json", "ACM2.json", "ACM3.json", "ACM4.json", "ACM5.json", "ACM6.json", "ACM7.json", "ACM8.json", "ACM9.json", "ACM10.json"};
    private static String[] NJNames = {"NJ1.json", "NJ2.json", "NJ3.json", "NJ4.json", "NJ5.json", "NJ6.json", "NJ7.json", "NJ8.json", "NJ9.json", "NJ10.json"};
    private static Scanner[] scanners = new Scanner[10];
    private static File[] IEEEfiles = new File[10];
    private static File[] NJfiles = new File[10];
    private static File[] ACMfiles = new File[10];
    private static PrintWriter[] IEEEWriters = new PrintWriter[10];
    private static PrintWriter[] ACMWriters = new PrintWriter[10];
    private static PrintWriter[] NJWriters = new PrintWriter[10];
    private static Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) {

        int open_index = 0;
        int write_index = 0;
        int tries = 1;
        boolean deleted = false;
        boolean found = false;

        System.out.println("***********************************");
        System.out.println("Welcome to the BibliographyFactory! My name is Alexia Sousa");
        System.out.println("***********************************");
        System.out.println("\n");

        // Opening 10 original files
        try {
            for (int i = 0; i < OriginalFileNames.length; i++) {
                open_index = i;
                scanners[i] = new Scanner(new FileInputStream(OriginalFileNames[i]));
            }

        } catch (FileNotFoundException e) {
            System.out.println("Could not open input file " + OriginalFileNames[open_index] + " for reading. \nPlease check if file exists! Program will terminate after closing any opened files.");
            // closing any opened files
            for (int i = open_index - 1; i >= 0; i--) {
                scanners[i].close();
            }
            System.out.println("Closed all opened files! Goodbye");
            System.exit(0);
        }

        // Create IEEE Files
        try {
            for (int i = 0; i < OriginalFileNames.length; i++) {
                write_index = i;
                IEEEfiles[i] = new File((IEEENames[i]));
                IEEEWriters[i] = new PrintWriter(IEEEfiles[i]);
            }

        } catch (FileNotFoundException e) {
            System.out.println("Could not create file " + IEEENames[write_index] + ". Deleting all created files and exiting program.");
            // deleting all created files
            for (int i = write_index - 1; i >= 0; i--) {
                deleted = IEEEfiles[i].delete();

                // making sure file was deleted
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
                ACMfiles[i] = new File((ACMNames[i]));
                ACMWriters[i] = new PrintWriter(ACMfiles[i]);
            }

        } catch (FileNotFoundException e) {
            System.out.println("Could not create file " + ACMNames[write_index] + ". Deleting all created files and exiting program.");
            // deleting all created files
            for (int i = write_index - 1; i >= 0; i--) {
                deleted = ACMfiles[i].delete();

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
                NJfiles[i] = new File(NJNames[i]);
                NJWriters[i] = new PrintWriter(NJfiles[i]);
            }

        } catch (FileNotFoundException e) {
            System.out.println("Could not create file " + NJNames[write_index] + ". Deleting all created files and exiting program.");
            // deleting all created files
            for (int i = write_index - 1; i >= 0; i--) {
                deleted = NJfiles[i].delete();

                if (!deleted) {
                    System.out.println("An error occurred: Could not delete all created files! Goodbye");
                    System.exit(0);
                }
            }
            System.out.println("Deleted all created files! Goodbye");
            System.exit(0);
        }

        // calling the method to write and process all files
        processFilesForValidation();

        //asking user for file input
        while (!found) {
            try {
                System.out.println("Please enter the name of the file that you need to review: ");
                String file_input = keyboard.next();

                for (int l = 0; l < IEEENames.length; l++) {
                    if (IEEEfiles[l].getName().equals(file_input)) { // verifies name of files match
                        if (IEEEfiles[l].exists()) { // verifies that the file was not deleted
                            found = true;
                        }
                    } else if (ACMfiles[l].getName().equals(file_input)) {
                        if (ACMfiles[l].exists()) {
                            found = true;
                        }
                    } else if (NJfiles[l].getName().equals(file_input)) {
                        if (NJfiles[l].exists()) {
                            found = true;
                        }
                    }
                }
                // throws exception if not found
                if (!found) {
                    throw new FileNotFoundException("Could not open input file. File does not exist or possibly could not have been created!\nHowever, you have another chance to enter another file name.");
                } else {
                    BufferedReader reader = null;
                    reader = new BufferedReader(new FileReader(file_input));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.out.println(line);
                    }
                    reader.close();
                    System.out.println("\nGoodbye! Hope you have enjoyed using BibliographyFactory.");
                }
            } catch (FileNotFoundException e) {
                if (tries == 1) {
                    System.out.println(e.getMessage());
                }
                // if max tries is reached, program will exit
                if (tries == 2) {
                    System.out.println("Could not open input file again! Either file does not exist or could not be created. Sorry, I am not able to display your desired file. Program will exit!");
                    System.exit(0);
                }
                tries++;
            } catch (IOException e) {
                System.out.println(e);
            }
        }

        keyboard.close();
    }

    public static void processFilesForValidation() {
        String line;
        String journal = "journal 1";
        String title = "title 1";
        String volume = "volume";
        String year = "0";
        String number = "0";
        String pages = "pages";
        String month = "month";
        String doi = "doi";
        String nextLine = "";
        StringBuilder IEEE_Author = new StringBuilder();
        String ACM_Author = null;
        StringBuilder NJ_Author = new StringBuilder();
        String[] authorStringArray = null;
        int j;
        int invalids = 0;

        for (int i = 0; i < OriginalFileNames.length; i++) {
            try {
                j = 0;
                // checks if at the end of file
                while (scanners[i].hasNextLine()) {
                    line = scanners[i].nextLine();

                    // checks if new article is staring
                    if (line.contains("@ARTICLE")) {
                        j++;
                        // setting all author fields to null
                        IEEE_Author.setLength(0);
                        ACM_Author = null;
                        NJ_Author.setLength(0);
                        nextLine = scanners[i].nextLine();

                        // checks if article is over
                        while (!(nextLine.equals("}"))) {
                            int index = nextLine.indexOf("=");

                            // verifying if line has a field
                            if (index != -1) {
                                String field = nextLine.substring(0, index);
                                String content = nextLine.substring(index + 2, nextLine.indexOf("}"));

                                //verifying if field is empty
                                if (content.isBlank()) {
                                    throw new FileInvalidException("ERROR\n=============================================\nProblem detected with input file: " + OriginalFileNames[i] + "\nFile is invalid: Field " + field + " is empty. Processing has stopped although other empty field might still be present!\n=============================================");
                                }

                                // assigning the contents to its field
                                switch (field) {
                                    case "author":
                                        authorStringArray = content.split(" and");

                                        //IEEE
                                        for (int k = 0; k < authorStringArray.length; k++) {
                                            if (k == authorStringArray.length - 1) {
                                                IEEE_Author.append(authorStringArray[k]);
                                            } else {
                                                IEEE_Author.append(authorStringArray[k]).append(", ");
                                            }
                                        }

                                        //NJ
                                        for (int k = 0; k < authorStringArray.length; k++) {
                                            if (k == authorStringArray.length - 1) {
                                                NJ_Author.append(authorStringArray[k]);
                                            } else {
                                                NJ_Author.append(authorStringArray[k]).append(" & ");
                                            }
                                        }

                                        //ACM
                                        ACM_Author = authorStringArray[0];
                                        break;
                                    case "journal":
                                        journal = content;
                                        break;
                                    case "title":
                                        title = content;
                                        break;
                                    case "year":
                                        year = content;
                                        break;
                                    case "number":
                                        number = content;
                                        break;
                                    case "pages":
                                        pages = content;
                                        break;
                                    case "month":
                                        month = content;
                                        break;
                                    case "volume":
                                        volume = content;
                                        break;
                                    case "doi":
                                        doi = content;
                                        break;
                                    default:
                                        break;
                                }
                            }
                            nextLine = scanners[i].nextLine();
                        } // article is finished

                        String IEEEOutput = IEEE_Author + ". \"" + title + " \", " + journal + ", vol. " + volume + ", no. " + number + ", p. " + pages + ", " + month + " " + year + ".";
                        IEEEWriters[i].printf(IEEEOutput);
                        IEEEWriters[i].printf("\n");
                        IEEEWriters[i].flush();
                        String ACMOutput = "[" + j + "] " + ACM_Author + " et al. " + year + ". " + title + ". " + journal + ". " + volume + ", " + number + " (" + year + ")," + pages + ". DOI:https://doi.org/" + doi + ".";
                        ACMWriters[i].printf(ACMOutput);
                        ACMWriters[i].printf("\n");
                        ACMWriters[i].flush();
                        String NJOutput = NJ_Author + ". " + title + ". " + journal + ". " + volume + ", " + pages + "(" + year + ").";
                        NJWriters[i].printf(NJOutput);
                        NJWriters[i].printf("\n");
                        NJWriters[i].flush();
                    }
                }
            } catch (FileInvalidException e) {
                invalids++; // counts how many invalid files there are
                System.out.println(e.getMessage());
                if (IEEEfiles[i].delete() && ACMfiles[i].delete() && NJfiles[i].delete()) {
                    System.out.println("All created files for invalid file deleted!\n");
                }

            }
        }
        // closing all writer & scanner files
        for (PrintWriter pw : IEEEWriters) {
            pw.close();
        }
        for (PrintWriter pw : ACMWriters) {
            pw.close();
        }
        for (PrintWriter pw : NJWriters) {
            pw.close();
        }
        for (Scanner scan : scanners) {
            scan.close();
        }
        System.out.println("A total of " + invalids + " files were invalid and could not be processed. All other files have been created.\n");
    }


}

