package kata.ex01;

import kata.ex01.model.HighwayDrive;
import kata.ex01.model.RouteType;
import kata.ex01.model.VehicleFamily;
import kata.ex01.util.HolidayUtils;

import java.time.LocalDateTime;

/**
 * @author kawasima
 */
public class DiscountServiceImpl implements DiscountService {
    @Override
    public long calc(HighwayDrive drive) {

        int enteredAt = drive.getEnteredAt().getHour();
        int exitedAt = drive.getExitedAt().getHour();
        int countPerMonth = drive.getDriver().getCountPerMonth();
        VehicleFamily vehicleFamily = drive.getVehicleFamily();
        RouteType routeType = drive.getRouteType();

        // 平日
        if (canAppliedWeekdaysDiscount(drive)) {
            if (countPerMonth >= 10) {
                return 50;
            } else if (countPerMonth >= 5) {
                return 30;
            }
        }

        // 深夜
        if (enteredAt <= 0 && exitedAt >= 4) {
            return 30;
        }

        // 休日
        if (vehicleFamily != VehicleFamily.OTHER && routeType == RouteType.RURAL) {
            return 30;
        }

        return 0;
    }

    private boolean canAppliedWeekdaysDiscount(HighwayDrive drive) {
        LocalDateTime enteredAt = drive.getEnteredAt();
        LocalDateTime exitedAt = drive.getExitedAt();

        if (drive.getRouteType() == RouteType.RURAL) {
            return false;
        }

        if ((HolidayUtils.isHoliday(enteredAt.toLocalDate()) && HolidayUtils.isHoliday(exitedAt.toLocalDate()))) {
            return false;
        }

        if ((enteredAt.getHour() <= 9 && exitedAt.getHour() >= 6)) {
            return true;
        }

        if ((enteredAt.getHour() <= 20 && exitedAt.getHour() >= 17)) {
            return true;
        }

        return false;
    }

}
