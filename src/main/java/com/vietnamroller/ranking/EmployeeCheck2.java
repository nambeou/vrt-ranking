package com.vietnamroller.ranking;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class EmployeeCheck2 {
    static List<String> rinks = Arrays.asList("THUẬN AN", "CỘNG HOÀ");
    static List<String> managers = Arrays.asList("Nguyễn Thị Hoà", "Võ Hoài Thương");
    static String department = "VRR";
    static String year = "25";
    static String month = "T2";
    static String NAME = "HỌ VÀ TÊN";
    static String DATE = "NGÀY";
    static String PREFIX = month + " - " + year;
    static String filename = "export.csv";
    static String COMPANY = "CÔNG TY PHÁT TRIỂN THỂ THAO VIỆT NAM ROLLER - CHI NHÁNH ";
    static String CHECK_IN_OUT = "BẢNG THEO DÕI CÔNG " + PREFIX;
    static String header = "NGÀY, CHECK-IN, CHECK-OUT, CÔNG, LỖI, GHI CHÚ QUẢN LÝ";
    static String TOTAL_HOUR = "TỔNG SỐ CÔNG: ";
    static String TOTAL_ERROR = "TỔNG SỐ LỖI: ";
    static String ALLOWED_EDROR = "SỐ LỖI DUYỆT BỞI QUẢN LÝ: ";
    static String ADDED_HOURS = "SỐ CÔNG THÊM DUYỆT BỞI QUẢN LÝ: ";
    static String HOUR_AND_ERROR = "CÔNG/LỖI";
    static String MANAGER_NOTE = "GHI CHÚ QUẢN LÝ";
    static String FINAL_HOURS = "TỔNG SỐ CÔNG CUỐI CÙNG: ";
    static String FINAL_HOURS_AND_ERROR = "TỔNG SỐ CÔNG/LỖI CUỐI CÙNG: ";
    static String FINAL_ERROR = "TỔNG SỐ LỖI CUỐI CÙNG: ";
    static String footer = "NHÂN VIÊN KÝ XÁC NHẬN:";
    static String logoPath = "src/main/resources/image/logo.png";
    static String EXTENTION = ".xlsm";
    public static void main(String[] args) throws IOException {

        for (String rink : rinks) {
            String path = "/Users/mac-Z28NHOAN/Documents/CHOPPER/test/VRT/" + rink + "/" + year + month + "/" + department;
            String file = path + "/" + filename;
            var lines = readCsv(file);
            lines.removeFirst();
            var summary = prefilleSummaries();



            var employeeData = new LinkedHashMap<String, List<String>>();
            var employeeHour = new LinkedHashMap<String, Double>();
            var employeeError = new LinkedHashMap<String, Integer>();
            for (String[] line : lines) {
                if (!line[6].equals("-")) {
                    var employeeName = line[2];
                    if (!employeeData.containsKey(employeeName)) {
                        employeeHour.put(employeeName, 0.0);
                        employeeError.put(employeeName, 0);
                        employeeData.put(employeeName, new ArrayList<>());
                        employeeData.get(employeeName).add(COMPANY + rink);
                        employeeData.get(employeeName).add("");
                        employeeData.get(employeeName).add(CHECK_IN_OUT);
                        employeeData.get(employeeName).add("");
                        employeeData.get(employeeName).add("TÊN NHÂN VIÊN: " + employeeName.toUpperCase()  + (managers.contains(employeeName) ? " (QUẢN LÝ) , " : ""));
                        employeeData.get(employeeName).add("");
                        employeeData.get(employeeName).add(header);
                    }

                    var date = line[0];
                    var checkin = line[6];
                    var checkout = line[7];
                    if (forgetToCheck(checkin) || forgetToCheck(checkout)) {
                        employeeData.get(employeeName).add(date + "," + checkin + "," + checkout + "," + 0 + "," + "2");
                        employeeError.put(employeeName, employeeError.get(employeeName) + 1);
                    } else {
                        var manager = managers.contains(employeeName);
                        var error = checkError(checkin, checkout, manager);
                        var roundedCheckin = fixCheckin(checkin);
                        var roundedCheckout = fixCheckout(checkout);
                        employeeError.put(employeeName, employeeError.get(employeeName) + ((isLate(checkin, manager) || isEarly(checkout)) ? 1 : 0));
                        var hours = getRoundedHourDifference(fixCheckin(checkin), fixCheckout(checkout));
                        var dateLine = date + "," + checkin + " -> (" + roundedCheckin + ")" + "," + checkout + " -> (" + roundedCheckout + ")" + "," + (manager ? 1 : hours) + "," + error;
                        employeeData.get(employeeName).add(dateLine);
                        employeeHour.put(employeeName, employeeHour.get(employeeName) + (manager ? 1 : hours));
                    }
                }
            }

            for (String employeeName : employeeData.keySet()) {
                employeeData.get(employeeName).add("");
                employeeData.get(employeeName).add(TOTAL_HOUR + employeeHour.get(employeeName));
                employeeData.get(employeeName).add(TOTAL_ERROR + employeeError.get(employeeName));
                employeeData.get(employeeName).add(ALLOWED_EDROR);
                employeeData.get(employeeName).add("");
                employeeData.get(employeeName).add(footer);
                String out = path + "/" + PREFIX + " " + employeeName + EXTENTION;
                writeListToCSV(employeeData.get(employeeName), out);
            }


            // SUMMARY
            for (String employeeName : employeeData.keySet()) {
                var data = employeeData.get(employeeName);
                summary.get(NAME).putIfAbsent(employeeName, employeeName + ",,,");
                summary.get(DATE).putIfAbsent(employeeName, "CHECK-IN,CHECK-OUT,CÔNG,LỖI");
                for (String date : summary.keySet()) {
                    if (isADate(date)) {
                        summary.get(date).putIfAbsent(employeeName, " - , - ,0,0");
                    }
                }
                for (String line: data) {
                    if (StringUtils.isNotEmpty(line)) {
                        var date = line.split(",")[0];
                        if (isADate(date)) {
                            summary.get(date).put(employeeName, line.substring(line.indexOf(",") + 1));
                        }
                    }
                }
                summary.get(HOUR_AND_ERROR).putIfAbsent(employeeName, ",," + employeeHour.get(employeeName) +"," +  employeeError.get(employeeName));
                summary.get(MANAGER_NOTE).putIfAbsent(employeeName, ",,,");
                summary.get(FINAL_HOURS_AND_ERROR).putIfAbsent(employeeName, ",,,");

            }



            List<String> summaryContent = new ArrayList<>();
            summaryContent.add(COMPANY + rink);
            summaryContent.add("");
            summaryContent.add(CHECK_IN_OUT);
            summaryContent.add("");
            for (String rowName : summary.keySet()) {
                StringBuilder line = new StringBuilder(rowName);
                for (String value : summary.get(rowName).values()){
                    line.append(",").append(value);
                }
                summaryContent.add(line.toString());
            }

            writeListToCSV(summaryContent, path + "/" + PREFIX + " TỔNG HỢP" + EXTENTION);

        }


    }

    public static LinkedHashMap<String, HashMap<String, String>> prefilleSummaries() {
        var summary = new LinkedHashMap<String, HashMap<String, String>>();
        var dates = generateMonthDates(year, month);
        summary.put(NAME, new HashMap<>());
        summary.put(DATE, new HashMap<>());
        for (String date : dates) {
            summary.putIfAbsent(date, new HashMap<>());
        }
        summary.put(HOUR_AND_ERROR, new HashMap<>());
        summary.put(MANAGER_NOTE, new HashMap<>());
        summary.put(FINAL_HOURS_AND_ERROR, new HashMap<>());
        System.out.println("");
        return summary;
    }

    public static List<String> generateMonthDates(String year, String month) {
        int fullYear = Integer.parseInt("20" + year);
        int monthNumber = Integer.parseInt(month.substring(1));

        LocalDate startDate = LocalDate.of(fullYear, monthNumber, 1);
        int daysInMonth = startDate.lengthOfMonth();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return IntStream.rangeClosed(1, daysInMonth)
                .mapToObj(day -> startDate.withDayOfMonth(day).format(formatter))
                .sorted()
                .collect(Collectors.toList());
    }

    public static boolean isADate(String dateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            LocalDate.parse(dateStr, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static void summarize(String filename, Map<String, List<String>> employeeData, Map<String, Double> employeeHour, Map<String, Integer> employeeError) {

        List<String> content = new ArrayList<>();
//        var names = "HỌ VÀ TÊN ,";
//        for (String employeeName : employeeHour.keySet()) {
//            names += (employeeName + (managers.contains(employeeName) ? " (QUẢN LÝ) , " : ","));
//        }
//        var hours = TOTAL_HOUR + " ,";
//        for (String employeeName : employeeHour.keySet()) {
//            hours += (employeeHour.get(employeeName) + " ,");
//        }
//        var error = TOTAL_ERROR + " ,";
//        for (String employeeName : employeeHour.keySet()) {
//            error += (employeeError.get(employeeName) + " ,");
//        }
//        content.add(names);
//        content.add(hours);
//        content.add(error);
        content.add(ALLOWED_EDROR);
        content.add(ADDED_HOURS);
//        content.add(FINAL_HOURS);
//        content.add(FINAL_ERROR);
        writeListToCSV(content, filename);
    }

    public static String checkError(String checkin, String checkout, boolean manager) {
        return (forgetToCheck(checkin) || forgetToCheck(checkout) || isLate(checkin, manager) || isEarly(checkout)) ? "1" : "0";
    }

    public static boolean forgetToCheck(String check) {
        return check.equals("...") || check.equals("-") || check.equals("HH:mm");
    }

    public static String adjustToAnchor(String timeStr, String anchorStr) {
        LocalTime time = LocalTime.parse(timeStr, DateTimeFormatter.ofPattern("HH:mm"));
        LocalTime anchor = LocalTime.parse(anchorStr, DateTimeFormatter.ofPattern("HH:mm"));

        long diff = ChronoUnit.MINUTES.between(anchor, time);
        if (Math.abs(diff) <= 15) {
            return anchorStr;
        }

        return timeStr;
    }

    public static String fixCheckin(String time) {
        var temp = time.startsWith("07") ? "08:00" : adjustToAnchor(time, "08:00");
        temp = adjustToAnchor(temp, "15:00"); // Use `temp` instead of `time`

        return roundToNearestQuarter(temp);
    }

    public static String fixCheckout(String time) {
        if (isMoreThanXMinutesLater(time, "15:00", 30)) {
            var temp = adjustToAnchor(time, "22:00");
            return roundToNearestQuarter(temp);
        } else {
            return "15:00";
        }

    }

    public static String roundToNearestQuarter(String timeStr) {
        LocalTime time = LocalTime.parse(timeStr, DateTimeFormatter.ofPattern("HH:mm"));
        int minutes = time.getMinute();
        int roundedMinutes = Math.round(minutes / 15.0f) * 15;

        if (roundedMinutes == 60) {
            time = time.plusHours(1).withMinute(0);
        } else {
            time = time.withMinute(roundedMinutes);
        }

        return time.format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    public static double getRoundedHourDifference(String time1, String time2) {
        LocalTime t1 = LocalTime.parse(time1);
        LocalTime t2 = LocalTime.parse(time2);

        long minutesDiff = ChronoUnit.MINUTES.between(t1, t2);
        double hourDiff = minutesDiff / 60.0; // Convert minutes to decimal hours

        return roundToNearestHalf(hourDiff);
    }

    private static double roundToNearestHalf(double value) {
        double floorHalf = Math.floor(value * 2) / 2.0;
        double ceilHalf = Math.ceil(value * 2) / 2.0;

        return (value >= floorHalf + 0.4) ? ceilHalf : floorHalf;
    }


    public static boolean isLate(String time, boolean manager) {
        var target =  manager ? "09:30" : "08:00";
        return isBeforeNoon(time) ? isMoreThan15MinutesLater(time, target) : isMoreThan15MinutesLater(time, "15:00");
    }

    public static boolean isEarly(String time) {
        return isBefore(time, "19:00") ? isMoreThan15MinutesEarlier(time, "15:00") : isMoreThan15MinutesEarlier(time, "22:00");
    }

    public static boolean isMoreThanXMinutesLater(String time1, String time2, long amount) {

        LocalTime t1 = LocalTime.parse(time1);
        LocalTime t2 = LocalTime.parse(time2);

        long minutesDiff = ChronoUnit.MINUTES.between(t2, t1);
        return minutesDiff > amount; // Checks if t1 is more than 15 minutes later than t2
    }
    public static boolean isMoreThan15MinutesLater(String time1, String time2) {

        return isMoreThanXMinutesLater(time1, time2, 15); // Checks if t1 is more than 15 minutes later than t2
    }

    public static boolean isMoreThan15MinutesEarlier(String time1, String time2) {
        LocalTime t1 = LocalTime.parse(time1);
        LocalTime t2 = LocalTime.parse(time2);

        long minutesDiff = ChronoUnit.MINUTES.between(t2, t1);
        return minutesDiff < -15; // Checks if t1 is more than 15 minutes earlier than t2
    }


    public static boolean isBeforeNoon(String time) {
        LocalTime givenTime = LocalTime.parse(time);
        LocalTime noon = LocalTime.NOON; // Represents 12:00 PM

        return givenTime.isBefore(noon);
    }


    public static boolean isBefore(String time1, String time2) {
        LocalTime givenTime = LocalTime.parse(time1);
        LocalTime targetTime = LocalTime.parse(time2);

        return givenTime.isBefore(targetTime);
    }


    public static void writeListToCSV(List<String> data, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            // Loop through each array in the list (each array is a row in the CSV)
            for (String row : data) {
                writer.append(row).append("\n");
            }
            System.out.println("CSV file was created successfully: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    static List<String[]> readCsv(String file) throws IOException {
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
