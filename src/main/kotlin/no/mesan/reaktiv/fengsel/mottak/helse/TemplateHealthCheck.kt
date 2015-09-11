package no.mesan.reaktiv.fengsel.mottak.helse

import com.codahale.metrics.health.HealthCheck

public class TemplateHealthCheck(private val template: String?) : HealthCheck() {

    override fun check(): HealthCheck.Result {
        val saying = java.lang.String.format(template, "TEST")
        if (!saying.contains("TEST")) {
            return HealthCheck.Result.unhealthy("template doesn't include a name")
        }
        return HealthCheck.Result.healthy()
    }
}
