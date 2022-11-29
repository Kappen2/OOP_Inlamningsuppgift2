import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ReadFile {
    protected static String info;
    public void ReadCustomersFile() {

        String temp;
        String searchWord;
        String paymentDate = "";
        boolean isCustomer = false;
        try {
            Scanner input = new Scanner(System.in);
            Scanner fileScan = new Scanner(new File("src/customers.txt"));
            while(true) {
                System.out.println("Skriv in ett namn eller personnummer: ");
                if(input.hasNextInt()) { //läser in ett personnummer och omvandlar till String
                    searchWord = String.valueOf(input.nextInt());
                    break;
                }
                else if (input.hasNextLine()) { //läser in ett namn
                    searchWord = input.nextLine().trim();
                    if (searchWord != null)
                        break;
                }
            }
            while ((temp = fileScan.nextLine()) != null) { //kollar om kunden finns i filen
                System.out.println("temp "+temp+"X");
                String personnumer = temp.substring(0, 10).trim();
                System.out.println("personnummer "+ personnumer+"X");
                String namn = temp.substring(11).trim();
                System.out.println("namn "+ namn+"X");
                System.out.println("searchWord "+ searchWord+"X");
                paymentDate = fileScan.nextLine();
                if(personnumer.equalsIgnoreCase(searchWord) || namn.equalsIgnoreCase(searchWord)){
                    System.out.println("i if");
                    info = temp;

                    isCustomer = true;
                    break;
                }
            }
            if (isCustomer) { // kollar om kunden har betalat
                LocalDate payDate = LocalDate.parse(paymentDate);
                LocalDate currentDate = LocalDate.now();
                if(payDate.plusDays(365).isAfter(currentDate)) { //skriver till en fil om kunden har betalat
                    WriteFile visitors = new WriteFile();
                    visitors.createVisitorFile();
                    System.out.println("Kunden är en medlem\n"+temp+ "\n"+paymentDate);
                }
                else System.out.println("Personen du sökte har inte betalat sin medlemsavgift");
            }
            else System.out.println("Personen du sökte finns inte i databasen");
        }  catch (NoSuchElementException e1){
            System.out.println("Du gjorde en felaktig sökning");
            e1.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
