package me.aslam.peaks_letsgo.domain.use_case

import me.aslam.peaks_letsgo.data.remote.repository.UserRepository
import javax.inject.Inject

/**
 * Created by aslam on 21,April,2022
 */
class UserNamesUseCase @Inject constructor(private val userRepository: UserRepository) {

    suspend operator fun invoke(elementId: String) = userRepository.getNames()
}

