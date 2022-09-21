import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ParkingBill {

    public static void main(String[] args) {
        System.out.println(parkingCostByTime("10:00", "13:21"));
    }

    private static Double stringTimeToIntMinutes(String time) {
        List<Double> doubleTime = stringTimeToListDouble(time);
        return doubleTime.get(0) * 60 + doubleTime.get(1);
    }

    private static String intMinutesToStringTime(int minute) {
        int HH = minute / 60;
        int MM = minute % 60;
        return String.format("%1$02d:%2$02d", HH, MM);
    }

    private static List<Double> stringTimeToListDouble(String time) {
        return Arrays.stream(time.split(":"))
                .map(Double::parseDouble)
                .collect(Collectors.toList());
    }

    private static double ruleOfThreeOutputsTimeToDouble(String time) {
        List<Double> doubleTime = stringTimeToListDouble(time);
        return doubleTime.get(0) + (doubleTime.get(1) / 60);
    }

    private static int parkingCostByTime(String beginTime, String endTime) {
        int entranceFee = 2;
        int firstFullOrPartialHourCosts = 3;
        int eachSuccessiveFullOrPartialHourAfterTheFirstCosts = 4;

        double parkingCost = 0;
        double intBeginTime = ruleOfThreeOutputsTimeToDouble(beginTime);
        double intEndTime = ruleOfThreeOutputsTimeToDouble(endTime);
        double parkingTime = intEndTime - intBeginTime;
        double ceilingParkingTime = Math.ceil(parkingTime);

        if (ceilingParkingTime == 1) {
            parkingCost = entranceFee + firstFullOrPartialHourCosts;
        }
        if (ceilingParkingTime > 1) {
            parkingCost = entranceFee + firstFullOrPartialHourCosts + ((ceilingParkingTime - 1) * eachSuccessiveFullOrPartialHourAfterTheFirstCosts);
        }
        return (int) parkingCost;
    }
}
