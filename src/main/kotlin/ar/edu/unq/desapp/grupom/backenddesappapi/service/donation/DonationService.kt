package ar.edu.unq.desapp.grupom.backenddesappapi.service.donation

import ar.edu.unq.desapp.grupom.backenddesappapi.model.Donation
import ar.edu.unq.desapp.grupom.backenddesappapi.persistence.donation.DonationRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
// Implementation of a donation service.
class DonationService: IDonationService {

    @Autowired
    lateinit var donationRepository: DonationRepository

    @Transactional
    // Persist a donation in the database.
    override fun addDonation(aDonation: Donation) {
        donationRepository.save(aDonation)
    }

    @Transactional
    // Return from the database a top ten of the biggest donation.
    override fun donationTopTen(): List<Donation> {
        return donationRepository.donationTopTen()
    }
}