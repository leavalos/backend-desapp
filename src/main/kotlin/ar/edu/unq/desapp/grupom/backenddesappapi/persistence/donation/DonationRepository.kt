package ar.edu.unq.desapp.grupom.backenddesappapi.persistence.donation

import ar.edu.unq.desapp.grupom.backenddesappapi.model.Donation
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DonationRepository: JpaRepository<Donation, Long>