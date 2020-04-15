package unl.cse.sorting;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class SortingTests {
    int locationCount = 500;
    Location[] locations = new Location[locationCount];
	
    /**
    * Load the data from a CSV
    */
    @BeforeEach
    void loadFromFile() {

        List<Location>temp = new ArrayList<>();
        try {
            File file = new File("data/geoData.csv");
            Scanner scanner = new Scanner(file);
            //throw away first line
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                //create airport objects
                String[] arr = line.split(",");

                Integer z = Integer.parseInt(arr[0]);
                String zip = String.format("%05d", z);
                double lat = 0d;
                double lon = 0d;
                String city = arr[3] == null ? "" : arr[3];
                String state = arr[4] == null ? "" : arr[4];
                try {
                    lat = Double.parseDouble(arr[1]);
                    lon = Double.parseDouble(arr[2]);
                } catch (NumberFormatException nfe) {
                    //silence exceptions
                }
                Location location = new Location(zip, lat, lon, city, state);
                temp.add(location);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Collections.shuffle(temp);
        for (int i = 0; i < locationCount; i++) {
            locations[i] = temp.get(i);
        }
    }

    /**
    * Test insertion sort
    */
    @Test
    void insertionSortTest() {
        Location[] sortedExpectation = Arrays.copyOf(locations, locations.length);
        Arrays.sort(sortedExpectation);

        assertArrayEquals(sortedExpectation, SortingAlgorithms.insertionSort(sortedExpectation), "Insertion sort did not match the expected sort order for the given array");
    }

    /**
    * Test quick sort
    */
    @Test
    void quickSortTest() {
        Location[] sortedExpectation = Arrays.copyOf(locations, locations.length);
        Arrays.sort(sortedExpectation);

        assertArrayEquals(sortedExpectation, SortingAlgorithms.quickSort(sortedExpectation), "Quick sort did not match the expected sort order for the given array");
    }

}
