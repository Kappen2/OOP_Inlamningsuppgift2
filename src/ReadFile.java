import java.io.*;
import java.time.LocalDate;
import java.util.Scanner;

public class ReadFile {
    protected static String info;
    public void ReadCustomersFile() {
        Scanner input = new Scanner(System.in);
        Scanner fileScan = new Scanner("src/customers.txt");
        fileScan.useDelimiter(",");
        String temp;
        String searchWord;
        String paymentDate ="";
        boolean isCustomer = false;
        try (BufferedReader bufIn = new BufferedReader(new FileReader("src/customers.txt"))){
            while(true) {
                System.out.println("Skriv in ett namn eller personnummer: ");
            if(input.hasNextInt()) {
                searchWord = String.valueOf(input.nextInt());
                break;
            }
            else if (input.hasNext()) {
                searchWord = input.useDelimiter("\\s*,\\s*").nextLine();
                if (searchWord != null)
                    break;
            }
            }
            while ((temp = bufIn.readLine()) != null) {

                if(temp.equalsIgnoreCase(searchWord)){
                    info = temp;
                    paymentDate = bufIn.readLine();
                    isCustomer = true;
                    break;
                }
            }
            if (isCustomer) {
                LocalDate payDate = LocalDate.parse(paymentDate);
                LocalDate currentDate = LocalDate.now();
                if(payDate.plusDays(365).isAfter(currentDate)) {
                    WriteFile visitors = new WriteFile();
                    visitors.createVisitorFile();
                    System.out.println("Kunden är en medlem\n"+temp+ "\n"+paymentDate);
                }
                else System.out.println("Personen du sökte har inte betalat sin medlemsavgift");
            }
            else System.out.println("Personen du sökte finns inte i databasen");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
