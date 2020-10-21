package ar.edu.unq.desapp.grupom.backenddesappapi.service.donation

import ar.edu.unq.desapp.grupom.backenddesappapi.model.Donation
import ar.edu.unq.desapp.grupom.backenddesappapi.persistence.donation.DonationRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DonationService: IDonationService {

    @Autowired
    lateinit var donationRepository: DonationRepository

    override fun addDonation(aDonation: Donation) {
        donationRepository.save(aDonation)
    }
}