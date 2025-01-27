import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Scanner;

public class InsecureDeserializationDemo {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.println("Enter the path to the serialized payload file:");
    String filePath = scanner.nextLine();

    try (FileInputStream fileIn = new FileInputStream(filePath);
        ObjectInputStream objIn = new ObjectInputStream(fileIn)) {

      System.out.println("Attempting to deserialize object from: " + filePath);

      // Deserialize the object (this is where payloads trigger their behavior)
      Object obj = objIn.readObject();

      System.out.println("Deserialization completed. Object: " + obj);

    } catch (IOException e) {
      System.err.println("I/O error occurred: " + e.getMessage());
    } catch (ClassNotFoundException e) {
      System.err.println("Class not found: " + e.getMessage());
    } catch (Exception e) {
      System.err.println("An unexpected error occurred: " + e.getMessage());
    } finally {
      scanner.close();
    }
  }
}
