package kata.ex01;

import kata.ex01.model.HighwayDrive;
import kata.ex01.rules.DiscountRule;
import kata.ex01.rules.DiscountRules;

import java.util.List;
import java.util.Optional;

/**
 * @author kawasima
 */
public class DiscountServiceImpl implements DiscountService {
    private List<DiscountRule> rules = new DiscountRules().getRules();

    @Override
    public long calc(HighwayDrive drive) {
        return rules.stream()
                .map(rule -> rule.apply(drive))
                .filter(Optional::isPresent)
                .mapToLong(Optional::get)
                .max().orElse(0);

    }
}
