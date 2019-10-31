package kata.ex01.rules;

import kata.ex01.model.HighwayDrive;
import kata.ex01.model.RouteType;
import kata.ex01.util.HolidayUtils;

import java.time.LocalDateTime;
import java.util.Optional;

public class LoyalWeekDayMorningEveningDiscount implements DiscountRule {

    @Override
    public Optional<Long> apply(HighwayDrive drive) {
        LocalDateTime entered = drive.getEnteredAt();
        LocalDateTime exited = drive.getExitedAt();
        int count = drive.getDriver().getCountPerMonth();
        RouteType routeType = drive.getRouteType();

        if (
                isWeekDay(entered, exited) &&
                isTargetHour(entered, exited) &&
                routeType == RouteType.RURAL &&
                count >= 10
        ) {
            return Optional.of(50L);
        } else {
            return Optional.empty();
        }
    }

    private boolean isTargetHour(LocalDateTime entered, LocalDateTime exited) {

        return  (entered.isBefore(exited.withHour(20)) && exited.isAfter(entered.withHour(17))) ||
                (entered.isBefore(exited.withHour(9)) && exited.isAfter(entered.withHour(6)));
    }

    private boolean isWeekDay(LocalDateTime entered, LocalDateTime exited) {
        return !HolidayUtils.isHoliday(entered.toLocalDate()) && !HolidayUtils.isHoliday(exited.toLocalDate());
    }
}
