package net.androidweekly.data.models.issues

import pl.droidsonroids.jspoon.annotation.Selector

/**
 * Project: Android Weekly
 * Created: Nov 23, 2019
 *
 * @author Mohamed Hamdan
 */
data class AuthenticityTokens(

    @Selector(value = "#submit-link .form #new_link input[name=\"authenticity_token\"]", attr = "value")
    var linkToken: String? = null,

    @Selector(value = "#submit-conference .form #new_link input[name=\"authenticity_token\"]", attr = "value")
    var conferenceToken: String? = null
)
