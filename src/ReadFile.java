import java.io.*;
import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ReadFile {
    protected static String info;
    public void ReadCustomersFile() {

        String temp;
        String searchWord;
        String paymentDate ="";
        boolean isCustomer = false;
        try (BufferedReader bufIn = new BufferedReader(new FileReader("src/customers.txt"))){
            Scanner input = new Scanner(System.in);
            Scanner fileScan = new Scanner("src/customers.txt");
            fileScan.useDelimiter(",");
            while(true) {
                System.out.println("Skriv in ett namn eller personnummer: ");
            if(input.hasNextInt()) { //läser in ett personnummer och omvandlar till String
                searchWord = String.valueOf(input.nextInt());
                break;
            }
            else if (input.hasNext()) { //läser in ett namn
                searchWord = input.next();
                if (searchWord != null)
                    break;
            }
            }
            while ((temp = bufIn.readLine()) != null) { //kollar om kunden finns i filen
                if(temp.equalsIgnoreCase(searchWord)){
                    info = temp;
                    paymentDate = fileScan.nextLine();
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
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NoSuchElementException e1){
            System.out.println("Du gjorde en felaktig sökning");
            e1.printStackTrace();
        }
    }
}
