package kata.ex01.rules;

import kata.ex01.model.HighwayDrive;
import kata.ex01.model.RouteType;

import java.time.LocalDateTime;
import java.util.Optional;

public class MidnightDiscount implements DiscountRule {

    @Override
    public Optional<Long> apply(HighwayDrive drive) {
        LocalDateTime enteredHour = drive.getEnteredAt();
        LocalDateTime exitedHour = drive.getExitedAt();
        int count = drive.getDriver().getCountPerMonth();

        RouteType routeType = drive.getRouteType();
        if (
                enteredHour.isBefore(exitedHour.withHour(4)) && exitedHour.isAfter(enteredHour.withHour(0)) &&
                routeType == RouteType.RURAL &&
                count >= 5 && count < 10
        ) {
            return Optional.of(30L);
        } else {
            return Optional.empty();
        }
    }
}
