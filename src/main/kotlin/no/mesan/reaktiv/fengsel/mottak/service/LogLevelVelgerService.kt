package no.mesan.reaktiv.fengsel.mottak.service

import retrofit.RestAdapter

/**
 * Service for å velge loglevel.

 * @author Christian Ihle
 */
public class LogLevelVelgerService {

    private val debug: Debug

    init {
        debug = Debug()
    }

    public fun velgLogLevel(): RestAdapter.LogLevel {
        if (debug.erAktivert()) {
            return RestAdapter.LogLevel.FULL
        }

        return RestAdapter.LogLevel.BASIC
    }
}
