package kata.ex01.rules;

import java.util.Arrays;
import java.util.List;

public class DiscountRules {

    private List<DiscountRule> rules =
            Arrays.asList(
                    new HolidayDiscount(),
                    new LoyalWeekDayMorningEveningDiscount(),
                    new WeekDayMorningEveningDiscount(),
                    new MidnightDiscount());

    public List<DiscountRule> getRules() {
        return rules;
    }
}
