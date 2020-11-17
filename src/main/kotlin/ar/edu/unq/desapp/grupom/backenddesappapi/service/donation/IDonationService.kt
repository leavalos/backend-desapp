package ar.edu.unq.desapp.grupom.backenddesappapi.service.donation

import ar.edu.unq.desapp.grupom.backenddesappapi.model.Donation
import org.springframework.transaction.annotation.Transactional

interface IDonationService {

    @Transactional
    fun addDonation(aDonation: Donation)

    fun donationTopTen(): List<Donation>
}