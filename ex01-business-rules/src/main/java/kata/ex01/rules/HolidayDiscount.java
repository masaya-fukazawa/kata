package kata.ex01.rules;

import kata.ex01.model.HighwayDrive;
import kata.ex01.model.RouteType;
import kata.ex01.model.VehicleFamily;
import kata.ex01.util.HolidayUtils;

import java.time.LocalDateTime;
import java.util.Optional;

public class HolidayDiscount implements DiscountRule {

    @Override
    public Optional<Long> apply(HighwayDrive drive) {
        VehicleFamily vehicleFamily = drive.getVehicleFamily();
        LocalDateTime entered = drive.getEnteredAt();
        LocalDateTime exitedDate = drive.getExitedAt();
        RouteType routeType = drive.getRouteType();

        if (
                isTargetVehicleFamily(vehicleFamily) &&
                isHoliday(entered, exitedDate) &&
                routeType == RouteType.RURAL
        ) {
            return Optional.of(30L);
        } else {
            return Optional.empty();
        }
    }

    private boolean isTargetVehicleFamily(VehicleFamily vehicleFamily) {
        return
            vehicleFamily == VehicleFamily.STANDARD ||
            vehicleFamily == VehicleFamily.MINI ||
            vehicleFamily == VehicleFamily.MOTORCYCLE;
    }

    private boolean isHoliday(LocalDateTime entered, LocalDateTime exited) {
        return HolidayUtils.isHoliday(entered.toLocalDate()) || HolidayUtils.isHoliday(exited.toLocalDate());
    }
}
