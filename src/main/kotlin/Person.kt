package org.example

import org.example.TypeOfBMI.Companion.obtainTypeOfBMI

class Person(weight: Double, height: Double) {
    companion object {
        const val AVERAGE_HEIGHT: Double = 1.75
        const val AVERAGE_WEIGHT: Double = 70.0
    }

    private var weight = verifyWeight(weight)
        set(value) {
            field = verifyWeight(value)
        }

    private var height: Double = verifyHeight(height)
        set(value) {
            field = verifyHeight(value)
        }

    private var name: String = "Sin Nombre"
        set(value) {
            field = verifyName(value)
        }

    private val bmi: Double
        get() = calculateBMI()


    constructor(name: String, weight: Double, height: Double): this(weight, height) {
        this.name = verifyName(name)
    }


    /** Recibe el peso y en caso de que sea menor o igual a 0 devuelve 1.0,
     *  en caso contrario devuelve el mismo peso
     *
     * @param weight El peso de la persona
     *
     * @return El peso (nunca será menor o igual a 0)
     */
    private fun verifyWeight(weight: Double): Double {
        return if (weight <= 0) {
            1.0
        } else {
            weight
        }
    }


    /** Recibe la altura y en caso de que sea menor o igual a 0 devuelve 1.0,
     *  en caso contrario devuelve la misma altura
     *
     * @param height La altura de la persona
     *
     * @return La altura (nunca será menor o igual a 0)
     */
    private fun verifyHeight(height: Double): Double {
        return if (height <= 0) {
            1.0
        } else {
            height
        }
    }


    /** Recibe el nombre y si recibe una cadena vacía o solo con espacios devuelve "Nombre Vacio",
     * en caso de recibir una cadena con caracteres devuelve esta cadena.
     *
     * @param name El nombre de la persona
     *
     * @return El nombre (Nunca será una cadena vacía)
     */
    private fun verifyName(name: String): String {

        return when (val newName = name.trim()) {
            "" -> "Nombre Vacio"
            else -> newName
        }
    }


    /** Calcula el Indice de Masa Corporal
     *
     * @return IMC
     */
    private fun calculateBMI(): Double {
        return this.weight / (this.height * this.height)
    }   //Esto podría ser una función lambda pero no quiero que se calcule cada instancia.


    /** Devuelve un saludo con el nombre de la persona
     *
     * @return Un saludo
     */
    private fun greet(): String {
        return "Hola, soy ${this.name}"
    }


    /** Muestra el saludo de la persona
     */
    fun showGreetings() {
        println(greet())
    }


    /** Comprueba si la altura está por encima de la media
     *
     * @return devuelve si la altura de esta persona está por encima de la media
     */
    private fun isAboveAverageHeight(): Boolean {
        return this.height > AVERAGE_HEIGHT
    } //Recordar pregunta sobre esta función


    /** Comprueba si el peso está por encima de la media
     *
     * @return devuelve si el peso de esta persona está por encima de la media
     */
    private fun isAboveAverageWeight(): Boolean {
        return this.weight > AVERAGE_WEIGHT
    } //Recordar pregunta sobre esta función


    /** Devuelve la descripción del estado de salud según el IMC
     *
     * @return Significado del IMC de esta persona
     */
    private fun obtainBMIDescription(): String {
        return obtainTypeOfBMI(this.bmi).description
    }


    /** Formatea el IMC para que solo tenga los 2 primeros números decimales
     *
     * @return IMC con solo 2 decimales
     */
    private fun formatBMI(): String {
        return "%.2f".format(this.bmi)
    } //Recordar pregunta sobre esta función


    /** Devuelve una descripción con nombre, altura, peso e IMC de la persona
     *
     * @return Descripción de esta persona
     */
    private fun obtainDescription(): String {
        val heightDescription = if (isAboveAverageHeight()) {
            "(Por encima de la media)"
        } else {
            "(Por debajo de la media)"
        }

        val weightDescription = if (isAboveAverageWeight()) {
            "(Por encima de la media)"
        } else {
            "(Por debajo de la media)"
        }

        return "${this.name} con una altura de ${this.height}m $heightDescription" +
                " y un peso ${this.weight}kg $weightDescription" +
                " tiene un IMC de ${formatBMI()} (${obtainBMIDescription()})"
    }


    /** Muestra la descripción de esta persona
     */
    fun showDescription() {
        println(obtainDescription())
    }


    override fun toString(): String {
        return "Nombre: ${this.name}\nPeso: ${this.weight}\nAltura: ${this.height}\nIMC: ${this.bmi}\n"
    }


    override fun equals(other: Any?): Boolean {
        var areEquals = true

        if (other is Person) {
            if (this.name != other.name) {areEquals = false}
            else if (this.weight != other.weight) {areEquals = false}
            else if (this.height != other.height) {areEquals = false}
        }

        return areEquals
    }


    // Modificado para no generar hashCode de la propiedad bmi,
    // no es necesario porque si weight y height son iguales generan siempre el mismo bmi
    override fun hashCode(): Int {
        var result = this.weight.hashCode()
        result = 31 * result + this.height.hashCode()
        result = 31 * result + this.name.hashCode()
        return result
    }

}