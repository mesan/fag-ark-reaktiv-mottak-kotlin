package no.mesan.reaktiv.fengsel.mottak.actor

/**
 * Liste over actorer som kan slås opp, og adressen til de.

 * @author Christian Ihle
 */
public enum class Actorer(val navn: String, val adresse: String) {

    REGISTRERE_NAVN_OG_NR("RegistrerNavnOgNrActor", "/user/RegistrerNavnOgNrActor"),
    REGISTRERE_EIENDELER("RegistrerEiendelerActor", "/user/RegistrerEiendelerActor");
}
