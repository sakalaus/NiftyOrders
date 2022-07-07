package com.pa.niftyorders.domain.use_cases

import com.pa.niftyorders.domain.repository.Repository

class CreateDemoData(private val repository: Repository) {
    suspend operator fun invoke(){
        repository.createDemoData()
    }
}