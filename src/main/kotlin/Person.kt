package org.example

class Person(weight: Double, height: Double) {

    companion object {
        const val AVERAGE_HEIGHT: Double = 1.75
        const val AVERAGE_WEIGHT: Double = 70.0
    }

    var weight = verifyWeight(weight)
        set(value) {
            field = verifyWeight(value)
        }

    var height: Double = verifyHeight(height)
        set(value) {
            field = verifyHeight(value)
        }

    var name: String? = null
        set(value) {
            field = verifyName(value)
        }

    val bmi: Double
        get() = calculateBMI(this.weight, this.height)


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


    /** Recibe el nombre y si recibe una cadena vacía devuelve "Sin nombre",
     * en caso de recibir una cadena con caracteres devuelve esta cadena.
     *
     * @param name El nombre de la persona
     *
     * @return El nombre (Nunca será una cadena vacía)
     */
    private fun verifyName(name: String?): String {
        return if (name!!.isBlank()) { //En el main me aseguro de que nunca pueda recibir algo que no sea String
            "Sin nombre"
        } else {
            name
        }
    }


    /** Calcula el Indice de Masa Corporal
     *
     * @param weight El peso de la persona
     * @param height La altura de la persona
     *
     * @return IMC
     */
    private fun calculateBMI(weight: Double, height: Double): Double {
        return weight / (height * height)
    }


    /** Devuelve un saludo con el nombre de la persona
     *
     * @return Un saludo
     */
    fun greet(): String {

        val greetName: String = when (name) {
            null -> "Sin Nombre"
            else -> name as String
        }

        return "Hola, soy $greetName"
    }


    /** Comprueba si la altura está por encima de la media
     *
     * @return devuelve si la altura de esta persona está por encima de la media
     */
    private fun isAboveAverageHeight(): Boolean {
        return height > AVERAGE_HEIGHT
    }


    /** Comprueba si el peso está por encima de la media
     *
     * @return devuelve si el peso de esta persona está por encima de la media
     */
    private fun isAboveAverageWeight(): Boolean {
        return weight > AVERAGE_WEIGHT
    }


    override fun toString(): String {
        return "Nombre: $name\nPeso: $weight\nAltura: $height\nIMC: $bmi\n"
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
        var result = weight.hashCode()
        result = 31 * result + height.hashCode()
        result = 31 * result + (name?.hashCode() ?: 0)
        return result
    }

}