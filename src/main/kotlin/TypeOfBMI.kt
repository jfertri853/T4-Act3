package org.example

enum class TypeOfBMI(val min: Double, val description: String) {
    INSUFFICIENT(0.0, "peso insuficiente"),
    HEALTHY(18.5, "peso saludable"),
    OVERWEIGHT(25.0, "sobrepeso"),
    OBESITY(30.0, "obesidad");

    companion object {
        fun obtainTypeOfBMI(bmi: Double): TypeOfBMI {
            return when {
                bmi <= INSUFFICIENT.min -> throw IllegalArgumentException("Se ha ingresado un IMC negativo")
                bmi < HEALTHY.min -> INSUFFICIENT
                bmi < OVERWEIGHT.min -> HEALTHY
                bmi < OBESITY.min -> OVERWEIGHT
                else -> OBESITY
            }
        }
    }

}