package ar.edu.unq.desapp.grupom.backenddesappapi.model.donation

import ar.edu.unq.desapp.grupom.backenddesappapi.builders.DonationBuilder
import ar.edu.unq.desapp.grupom.backenddesappapi.builders.LocationBuilder
import ar.edu.unq.desapp.grupom.backenddesappapi.builders.ProjectBuilder
import ar.edu.unq.desapp.grupom.backenddesappapi.model.Donation
import ar.edu.unq.desapp.grupom.backenddesappapi.model.Location
import ar.edu.unq.desapp.grupom.backenddesappapi.model.Project
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.time.LocalDateTime

class TestDonation {

    lateinit var myDonationBeforeCurrentMoth: Donation
    lateinit var myDonation: Donation


    @Before
    fun setUp() {
        var date =  LocalDateTime.now()
        this.myDonationBeforeCurrentMoth = DonationBuilder().withDate(date.minusMonths((date.month.value - 1).toLong())).build()
        this.myDonation = DonationBuilder().build()
    }

    @Test
    fun donationMadeBeforeTheCurrentMonth() {
        Assert.assertFalse(this.myDonationBeforeCurrentMoth.hasBeenMadeInTheCurrentMonth())
    }

    @Test
    fun donationMadeInTheCurrentMonth() {
        Assert.assertTrue(this.myDonation.hasBeenMadeInTheCurrentMonth())
    }
}