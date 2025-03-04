package com.vietnamroller.ranking;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Year;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class EmployeeCheck {
    public static void main(String[] args) throws IOException {
        String month = "T12";
        String employeeList = "/Users/mac-Z28NHOAN/Documents/CHOPPER/test/VRT/" + month + "/ids.csv";
        String dataFile = "/Users/mac-Z28NHOAN/Documents/CHOPPER/test/VRT/" + month + "/" + month + ".csv";
        String processDataFile = "/Users/mac-Z28NHOAN/Documents/CHOPPER/test/VRT/" + month + "/" + month + "_processed.csv";
        var data = readCsv(dataFile);
        var ids = readCsv(employeeList);
        var employeeIdMap = new HashMap<String, String>();
        for (var entry : ids) {
            employeeIdMap.put(entry[0], entry[1]);
        }
        for (var entry: data) {
            try {
                if (employeeIdMap.containsKey(entry[0])) {
                    entry[0] = employeeIdMap.get(entry[0]);
                } else {
                    employeeIdMap.put(entry[0], "404");
                }
                entry[1] = entry[1].replace("/2024", "");
                entry[2] = entry[2].split(" ")[1];
                entry[2] = groupTo15MinSlot(entry[2]);
            } catch (Exception e) {
                System.out.println(data.indexOf(entry));
                return;
            }
//            print(entry);
        }

        writeListToCSV(data, processDataFile);
        var map = groupByFirstColumn(data);
        var matrixMap = new HashMap<String, String[][]>();
        for (var entry : map.entrySet()) {
            matrixMap.put(entry.getKey(), fillMatrix(entry.getValue(), Integer.parseInt(month.substring(1))));
        }

        writeToCSV(matrixMap, Integer.parseInt(month.substring(1)), 2024,  "/Users/mac-Z28NHOAN/Documents/CHOPPER/test/VRT/" + month);
//
//

        for (var key: employeeIdMap.keySet()) {
            if (employeeIdMap.get(key).equals("404")) {
                System.out.println(key + " NOT FOUND");
            }
        }


    }



    public static void writeListToCSV(List<String[]> data, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            // Loop through each array in the list (each array is a row in the CSV)
            for (String[] row : data) {
                // Join the array elements into a CSV format (comma-separated values)
                String csvRow = String.join(",", row);
                // Write the CSV row to the file, followed by a newline
                writer.append(csvRow).append("\n");
            }
            System.out.println("CSV file was created successfully: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeToCSV(Map<String, String[][]> employeeMatrices, int month, int year, String path) {
        // Format the month with two digits for file names
        String monthStr = String.format("%02d", month);

        // Time slots array (96 time slots for 15-minute intervals)
        // Removing time slots from 1:00 AM to 5:30 AM
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime startTime = LocalTime.of(0, 0);
        int index = 0;

        String[] timeSlots = {
                "06:00 - 06:15", "06:15 - 06:30", "06:30 - 06:45", "06:45 - 07:00",
                "07:00 - 07:15", "07:15 - 07:30", "07:30 - 07:45", "07:45 - 08:00",
                "08:00 - 08:15", "08:15 - 08:30", "08:30 - 08:45", "08:45 - 09:00",
                "09:00 - 09:15", "09:15 - 09:30", "09:30 - 09:45", "09:45 - 10:00",
                "10:00 - 10:15", "10:15 - 10:30", "10:30 - 10:45", "10:45 - 11:00",
                "11:00 - 11:15", "11:15 - 11:30", "11:30 - 11:45", "11:45 - 12:00",
                "12:00 - 12:15", "12:15 - 12:30", "12:30 - 12:45", "12:45 - 13:00",
                "13:00 - 13:15", "13:15 - 13:30", "13:30 - 13:45", "13:45 - 14:00",
                "14:00 - 14:15", "14:15 - 14:30", "14:30 - 14:45", "14:45 - 15:00",
                "15:00 - 15:15", "15:15 - 15:30", "15:30 - 15:45", "15:45 - 16:00",
                "16:00 - 16:15", "16:15 - 16:30", "16:30 - 16:45", "16:45 - 17:00",
                "17:00 - 17:15", "17:15 - 17:30", "17:30 - 17:45", "17:45 - 18:00",
                "18:00 - 18:15", "18:15 - 18:30", "18:30 - 18:45", "18:45 - 19:00",
                "19:00 - 19:15", "19:15 - 19:30", "19:30 - 19:45", "19:45 - 20:00",
                "20:00 - 20:15", "20:15 - 20:30", "20:30 - 20:45", "20:45 - 21:00",
                "21:00 - 21:15", "21:15 - 21:30", "21:30 - 21:45", "21:45 - 22:00",
                "22:00 - 22:15", "22:15 - 22:30", "22:30 - 22:45", "22:45 - 23:00",
                "23:00 - 23:15", "23:15 - 23:30", "23:30 - 23:45", "23:45 - 00:00"
        };

        // Get the number of days in the given month
        int daysInMonth = YearMonth.of(year, month).lengthOfMonth();

        // Loop through each employee in the map
        for (Map.Entry<String, String[][]> entry : employeeMatrices.entrySet()) {
            String employeeName = entry.getKey();
            String[][] matrix = entry.getValue();

            // Create the CSV file name with the specified path
            String fileName = path + "/" + employeeName + "_" + monthStr + "_" + year + ".csv";

            try (FileWriter writer = new FileWriter(fileName)) {
                // Write the employee name as a header
                writer.append(employeeName).append("\n");

                // Write the days of the month as column headers
                writer.append("Time Slot");
                for (int day = 1; day <= daysInMonth; day++) {
                    writer.append(",").append(String.format("%02d", day));
                }
                writer.append("\n");

                // Write the matrix (time slots and X's) while skipping the removed slots
                for (int i = 0; i < timeSlots.length; i++) {
                    if (timeSlots[i] != null) {
                        // Write the time slot in the first column
                        writer.append(timeSlots[i]);

                        // Write the corresponding day slots for the employee
                        for (int j = 0; j < daysInMonth; j++) {
                            writer.append(",").append(matrix[i][j] == null ? " " : matrix[i][j]);  // Handle null values
                        }
                        writer.append("\n");
                    }
                }

                System.out.println("CSV file created: " + fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String[][] fillMatrix(List<String[]> data, int month) {
        // Get the current year dynamically
        int year = java.time.Year.now().getValue();

        // Get the number of days in the given month
        YearMonth yearMonth = YearMonth.of(year, month);
        int daysInMonth = yearMonth.lengthOfMonth(); // Dynamically get the number of days in the month

        // Time slots array (excluding midnight to 6:00 AM)
        String[] timeSlots = {
                "06:00 - 06:15", "06:15 - 06:30", "06:30 - 06:45", "06:45 - 07:00",
                "07:00 - 07:15", "07:15 - 07:30", "07:30 - 07:45", "07:45 - 08:00",
                "08:00 - 08:15", "08:15 - 08:30", "08:30 - 08:45", "08:45 - 09:00",
                "09:00 - 09:15", "09:15 - 09:30", "09:30 - 09:45", "09:45 - 10:00",
                "10:00 - 10:15", "10:15 - 10:30", "10:30 - 10:45", "10:45 - 11:00",
                "11:00 - 11:15", "11:15 - 11:30", "11:30 - 11:45", "11:45 - 12:00",
                "12:00 - 12:15", "12:15 - 12:30", "12:30 - 12:45", "12:45 - 13:00",
                "13:00 - 13:15", "13:15 - 13:30", "13:30 - 13:45", "13:45 - 14:00",
                "14:00 - 14:15", "14:15 - 14:30", "14:30 - 14:45", "14:45 - 15:00",
                "15:00 - 15:15", "15:15 - 15:30", "15:30 - 15:45", "15:45 - 16:00",
                "16:00 - 16:15", "16:15 - 16:30", "16:30 - 16:45", "16:45 - 17:00",
                "17:00 - 17:15", "17:15 - 17:30", "17:30 - 17:45", "17:45 - 18:00",
                "18:00 - 18:15", "18:15 - 18:30", "18:30 - 18:45", "18:45 - 19:00",
                "19:00 - 19:15", "19:15 - 19:30", "19:30 - 19:45", "19:45 - 20:00",
                "20:00 - 20:15", "20:15 - 20:30", "20:30 - 20:45", "20:45 - 21:00",
                "21:00 - 21:15", "21:15 - 21:30", "21:30 - 21:45", "21:45 - 22:00",
                "22:00 - 22:15", "22:15 - 22:30", "22:30 - 22:45", "22:45 - 23:00",
                "23:00 - 23:15", "23:15 - 23:30", "23:30 - 23:45", "23:45 - 00:00"
        };

        // Initialize the matrix (rows: time slots, columns: days of the month)
        String[][] matrix = new String[timeSlots.length][daysInMonth]; // Adjust matrix size based on remaining time slots

        // Initialize matrix with empty strings or null values
        for (int i = 0; i < timeSlots.length; i++) {
            Arrays.fill(matrix[i], " ");
        }

        // Loop through each entry in the list
        for (String[] entry : data) {
            String dateStr = entry[1];  // The date (format: d/M like "1/9" or "30/9")
            String timeSlotStr = entry[2];  // The time slot (e.g., "23:00 - 23:15")

            // Extract the day part from the date (which is the column in the matrix)
            String[] dateParts = dateStr.split("/");
            int day = Integer.parseInt(dateParts[0]);  // Extract the day as integer
            int column = day - 1;  // Zero-based index for columns

            // Find the row (time slot) by comparing the strings directly
            int row = -1;
            for (int i = 0; i < timeSlots.length; i++) {
                if (timeSlots[i].equals(timeSlotStr)) {
                    row = i;
                    break;
                }
            }

            // If valid row and column, mark the matrix with "X"
            if (row != -1 && column >= 0 && column < daysInMonth) {
                matrix[row][column] = "X";
            }
        }

        return matrix;
    }
    public static String transformDate(String dateStr) {
        // Define the input formatter (to parse the "d/M/yyyy" format)
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        // Define the output formatter (to format the date as "dd-MM")
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd-MM");

        // Parse the input string to LocalDate
        LocalDate date = LocalDate.parse(dateStr, inputFormatter);

        // Format the LocalDate into the desired output format
        return date.format(outputFormatter);
    }

    public static Map<String, List<String[]>> groupByFirstColumn(List<String[]> data) {
        // Initialize the map to store the grouped data
        Map<String, List<String[]>> groupedMap = new HashMap<>();

        // Loop through each String[] in the input list
        for (String[] entry : data) {
            // The first element of each entry is the key
            String key = entry[0];

            // Add the entry to the corresponding key in the map
            groupedMap.putIfAbsent(key, new ArrayList<>());  // If the key doesn't exist, create a new list
            groupedMap.get(key).add(entry);  // Add the current entry to the list for the key
        }

        return groupedMap;
    }

    public static String groupTo15MinSlot(String timeStr) {
        // Define formatters to handle both "HH:mm" and "H:mm" formats
        DateTimeFormatter formatterWithLeadingZero = DateTimeFormatter.ofPattern("HH:mm");
        DateTimeFormatter formatterWithoutLeadingZero = DateTimeFormatter.ofPattern("H:mm");
        LocalTime time = null;

        // Try parsing with both formats to account for leading zeros and no leading zeros
        try {
            time = LocalTime.parse(timeStr, formatterWithLeadingZero);
        } catch (DateTimeParseException e) {
            // If it fails, try parsing with no leading zero in hours
            time = LocalTime.parse(timeStr, formatterWithoutLeadingZero);
        }

        // Subtract one hour from the parsed time
        time = time.minusHours(1);

        // Find the minute part of the time and round down to the nearest 15-minute interval
        int minutes = time.getMinute();
        int startMinutes = (minutes / 15) * 15; // Start of the time slot

        // Create the start time for the slot
        LocalTime startTime = time.withMinute(startMinutes).withSecond(0).withNano(0);
        // Create the end time, which is 15 minutes after the start time
        LocalTime endTime = startTime.plusMinutes(15);

        // Format the start and end times as strings
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("HH:mm");
        String startStr = startTime.format(outputFormatter);
        String endStr = endTime.format(outputFormatter);

        // Return the time slot in the format "HH:mm - HH:mm"
        return startStr + " - " + endStr;
    }



    private static void print(String[] entry) {
        // Print or process the data
        for (String value : entry) {
            System.out.print(value + " ");
        }
        System.out.println();
    }

    private static List<String[]> readCsv(String file) throws IOException {
        String line = "";
        String delimiter = ",";  // You can change the delimiter if it's not a comma.
        List<String[]> result = new ArrayList();

        BufferedReader br = new BufferedReader(new FileReader(file));
        while ((line = br.readLine()) != null) {
            result.add(line.split(delimiter));
        }
        return result;
    }
}
