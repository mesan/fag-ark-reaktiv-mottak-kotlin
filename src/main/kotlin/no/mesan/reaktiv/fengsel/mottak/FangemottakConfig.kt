package no.mesan.reaktiv.fengsel.mottak

import io.dropwizard.Configuration

import org.hibernate.validator.constraints.NotEmpty

public class FangemottakConfig : Configuration() {

    @NotEmpty
    public var template: String? = null

    @NotEmpty
    public var defaultName: String = "Stranger"
}
