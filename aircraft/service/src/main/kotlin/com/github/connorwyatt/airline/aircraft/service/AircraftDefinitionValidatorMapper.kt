package com.github.connorwyatt.airline.aircraft.service

import com.github.connorwyatt.airline.aircraft.messages.models.AircraftDefinition
import com.github.connorwyatt.airline.aircraft.messages.models.AircraftDefinitionRequest
import com.github.connorwyatt.airline.aircraft.messages.models.AircraftRegistration
import com.github.connorwyatt.airline.aircraft.messages.models.SeatingConfiguration
import com.github.connorwyatt.common.result.Result
import com.github.connorwyatt.common.validation.ValidationError
import com.github.connorwyatt.common.validation.ValidatorMapper

class AircraftDefinitionValidatorMapper :
    ValidatorMapper<AircraftDefinitionRequest, AircraftDefinition> {
    override fun validateAndMap(
        value: AircraftDefinitionRequest
    ): Result<AircraftDefinition, List<ValidationError>> {
        // TODO: Add validation.

        val seatingConfiguration = value.seatingConfiguration!!

        return Result.Success(
            AircraftDefinition(
                AircraftRegistration.parse(value.registration!!).getOrThrow(),
                value.manufacturer!!,
                value.model!!,
                SeatingConfiguration(
                    seatingConfiguration.standard!!,
                    seatingConfiguration.businessClass!!,
                    seatingConfiguration.firstClass!!
                ),
            )
        )
    }
}
