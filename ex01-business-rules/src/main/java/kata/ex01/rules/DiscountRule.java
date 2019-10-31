package kata.ex01.rules;

import kata.ex01.model.HighwayDrive;

import java.util.Optional;

public interface DiscountRule {

    public Optional<Long> apply(HighwayDrive drive);
}
