package io.connorwyatt.airline.aircraft.service

import io.connorwyatt.airline.aircraft.messages.models.AircraftDefinition
import io.connorwyatt.airline.aircraft.messages.models.AircraftDefinitionRequest
import io.connorwyatt.airline.aircraft.messages.models.AircraftRegistration
import io.connorwyatt.airline.aircraft.messages.models.SeatingConfiguration
import io.connorwyatt.common.result.Result
import io.connorwyatt.common.validation.ValidationError
import io.connorwyatt.common.validation.ValidatorMapper

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
