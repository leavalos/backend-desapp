package ar.edu.unq.desapp.grupom.backenddesappapi.model.donation

import ar.edu.unq.desapp.grupom.backenddesappapi.builders.DonationBuilder
import ar.edu.unq.desapp.grupom.backenddesappapi.model.Donation
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class TestDonation {

    private lateinit var myDonationBeforeCurrentMoth: Donation
    private lateinit var myDonation: Donation


    @Before
    fun setUp() {
        this.myDonation = DonationBuilder().build()
        val date = myDonation.date
        val aPreviousMonth = date.minusMonths((date.month.value - 1).toLong())
        this.myDonationBeforeCurrentMoth = DonationBuilder().withDate(aPreviousMonth).build()
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