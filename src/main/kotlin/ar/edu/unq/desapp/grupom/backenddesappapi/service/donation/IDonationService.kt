package ar.edu.unq.desapp.grupom.backenddesappapi.service.donation

import ar.edu.unq.desapp.grupom.backenddesappapi.model.Donation
import org.springframework.transaction.annotation.Transactional

// Collection of functions to use with the model class of Donation.
interface IDonationService {

    @Transactional
    fun addDonation(aDonation: Donation)

    @Transactional
    fun donationTopTen(): List<Donation>
}